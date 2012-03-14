package com.blogspot.miguelinlas3.scala.intro

object CurriedSamples {

  // suma tradicional
  def plainSum(x: Int, y: Int) = x + y

  // versión curried
  def curriedSum(x: Int)(y: Int) = x + y

  def main(args: Array[String]) = {

    println("Suma tradicional: " + plainSum(1, 10))
    println("Suma curried: " + curriedSum(1)(10))

    // otras posibilidades
    var partialSum = curriedSum(1)_
    println("En dos fases: " + partialSum(10))
  }
}