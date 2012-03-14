package com.blogspot.miguelinlas3.scala.intro

class Rational(n: Int, d: Int) {
  // Precondiciones: irán al constructor primario
  require(d != 0, "Denominador no puede ser cero")

  // damos acceso a fuera del ámtio de la clase
  // OJO a val: mantenemos inmutabilidad
  val numer: Int = n
  val denom: Int = d

  // sobreescritura del método to String
  override def toString = n + "/" + d

  // método público
  def add(that: Rational): Rational =
    new Rational(numer * that.denom +
      that.numer * denom,
      denom * that.denom)

  // método privado
  private def gcd(a: Int, b: Int): Int =
    if (b == 0)
      a
    else gcd(b, a % b)

  // operadores
  def +(that: Rational): Rational =
    new Rational(
      numer * that.denom + that.numer * denom,
      denom * that.denom)
  def *(that: Rational): Rational =
    new Rational(numer * that.numer,
      denom * that.denom)
}

object mainRational {
  def main(args: Array[String]): Unit = {

    var r = new Rational(2, 1)
    println(r)

    var bad = new Rational(2, 0)
  }
}
