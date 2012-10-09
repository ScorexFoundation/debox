package debox.set

import debox._

import scala.annotation.{switch, tailrec}
import scala.reflect.ClassTag
import scala.{specialized => spec}

class InvalidSizes(k: Int, v: Int) extends Exception("%s, %s" format (k, v))
class SetOverflow(n: Int) extends Exception("size %s exceeds max" format n)

object Set {
  def empty[
    @spec(Int, Long, Double, AnyRef) A:ClassTag
  ] = new Set(new Array[A](8), new Array[Byte](8), 0, 0)

  def ofDim[
    @spec(Int, Long, Double, AnyRef) A:ClassTag
  ](n:Int) = {
    val sz = Util.nextPowerOfTwo(n)
    if (sz < 1) throw new SetOverflow(n)
    new Set(new Array[A](sz), new Array[Byte](sz), 0, 0)
  }

  def apply[
    @spec(Int, Long, Double, AnyRef) A:ClassTag
  ]():Set[A] = empty[A]

  def apply[
    @spec(Int, Long, Double, AnyRef) A:ClassTag
  ](as:Array[A]) = {
    val set = ofDim[A](as.length)
    val limit = as.length - 1
    @inline @tailrec
    def loop(i:Int) {
      set.add(as(i))
      if (i < limit) loop(i + 1)
    }
    loop(0)
    set
  }
}

final class Set[
  @spec(Int, Long, Double, AnyRef) A:ClassTag
] protected[debox] (
  as:Array[A], bs:Array[Byte], n:Int, u:Int
) extends Function1[A, Boolean] {

  var items:Array[A] = as
  var buckets:Array[Byte] = bs
  var len:Int = n
  var used:Int = u

  def getBuckets: Array[Byte] = buckets

  // hashing internals
  var mask = items.length - 1 // size-1, used for hashing
  var limit = (items.length * 0.65).toInt // point at which we should resize

  final def length:Int = len

  final def update(item:A, b:Boolean) = if (b) add(item) else remove(item)

  final def add(item:A):Boolean = {
    @inline @tailrec
    def loop(i:Int, perturbation:Int): Boolean = {
      val j = i & mask
      val status = buckets(j)
      if (status == 0) {
        items(j) = item
        buckets(j) = 3
        len += 1
        used += 1
        if (used > limit) resize()
        true
      } else if (status == 2) {
        items(j) = item
        buckets(j) = 3
        len += 1
        true
      } else if (items(j) == item) {
        false
      } else {
        loop((i << 2) + i + perturbation + 1, perturbation >> 5)
      }
    }
    val i = item.## & 0x7fffffff
    loop(i, i)
  }

  final def remove(item:A):Boolean = {
    @inline @tailrec
    def loop(i:Int, perturbation:Int): Boolean = {
      val j = i & mask
      val status = buckets(j)
      if (status == 3 && items(j) == item) {
        buckets(j) = 2
        len -= 1
        true
      } else if (status == 0) {
        false
      } else {
        loop((i << 2) + i + perturbation + 1, perturbation >> 5)
      }
    }
    val i = item.## & 0x7fffffff
    loop(i, i)
  }

  final def copy:Set[A] = new Set(items.clone, buckets.clone, len, used)

  final def apply(item:A):Boolean = {
    @inline @tailrec
    def loop(i:Int, perturbation:Int): Boolean = {
      val j = i & mask
      val status = buckets(j)
      if (status == 0) {
        false
      } else if (status == 3 && items(j) == item) {
        true
      } else {
        loop((i << 2) + i + perturbation + 1, perturbation >> 5)
      }
    }
    val i = item.## & 0x7fffffff
    loop(i, i)
  }

  def map[@spec(Int, Long, Double, AnyRef) B:ClassTag](f: A => B): Set[B] = {
    val out = Set.empty[B]
    foreach(a => out.add(f(a)))
    out
  }

  final def foreach(f: A => Unit) {
    @inline @tailrec
    def loop(i: Int, count: Int, len: Int) {
      if (count < len) {
        if (buckets(i) == 3) {
          f(items(i))
          loop(i + 1, count + 1, len)
        } else {
          loop(i + 1, count, len)
        }
      }
    }
    loop(0, 0, length)
  }

  final def hash(item:A, _mask:Int, _items:Array[A], _buckets:Array[Byte]):Int = {
    @inline @tailrec
    def loop(i:Int, perturbation:Int): Int = {
      val j = i & _mask
      if (_buckets(j) == 3 && _items(j) != item)
        loop((i << 2) + i + perturbation + 1, perturbation >> 5)
      else
        j
    }
    val i = item.## & 0x7fffffff
    loop(i, i)
  }

  final def resize(): Unit1[A] = {
    val size = items.length
    val factor = if (size < 10000) 4 else 2
    
    val nextsize = size * factor
    val nextmask = nextsize - 1
    val nextitems = new Array[A](nextsize)
    val nextbs = new Array[Byte](nextsize)

    @inline @tailrec
    def loop(i: Int, count: Int, len: Int) {
      if (count < len) {
        if (buckets(i) == 3) {
          val item = items(i)
          val j = hash(item, nextmask, nextitems, nextbs)
          nextitems(j) = item
          nextbs(j) = 3
          loop(i + 1, count + 1, len)
        } else {
          loop(i + 1, count, len)
        }
      }
    }
    loop(0, 0, length)

    items = nextitems
    buckets = nextbs
    mask = nextmask
    limit *= factor

    new Unit1[A]
  }

  //def union(that: Set[A]): Set[A] = {
  //  if (length > that.length) return that.union(this)
  //  val out = that.copy
  //  foreach(out.add)
  //  out
  //}
  //
  //def intersection(that: Set[A]): Set[A] = {
  //  if (length < that.length) return that.intersection(this)
  //  val out = Set.empty[A]
  //  foreach(a => if (that(a)) out.add(a))
  //  out
  //}
  //
  //def difference(that: Set[A]): Set[A] = {
  //  val out = Set.empty[A]
  //  foreach(a => if (!that(a)) out.add(a))
  //  out
  //}
  //
  //def extend(that: Set[A]): Unit = foreach(add)
}
