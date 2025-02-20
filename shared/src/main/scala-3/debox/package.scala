import spire.syntax.cfor.{cfor => spirecfor}

package debox {
  type sp = scala.specialized

  def cfor[A](init: A)(test: A => Boolean, next: A => A)(body: A => Unit): Unit =
    spirecfor(init)(test, next)(body)
}
