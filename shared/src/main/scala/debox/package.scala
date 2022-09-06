import spire.macros.Syntax

package object debox {
  type sp          = scala.specialized

  def cfor[A](init: A)(test: A => Boolean, next: A => A)(body: A => Unit): Unit =
    macro Syntax.cforMacro[A]
}
