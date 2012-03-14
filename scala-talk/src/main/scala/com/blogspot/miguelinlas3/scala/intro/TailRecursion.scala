package com.blogspot.miguelinlas3.scala.intro

object TailRecursion {

  // no es tail recursive
  def factorial(num: Int): Int = {
    if (num == 0) {
      1
    } else {
      factorial(num - 1) * num
    }
  }

  def isGoodEnough(num: Double): Boolean = {
    // template
    true
  }

  def improve(num: Double): Double = {
    // template 
    1.1
  }

  // Si es tail recursive (patrón utilizado en búsquedas)
  def approximate(guess: Double): Double =
    if (isGoodEnough(guess)) guess
    else approximate(improve(guess))

  def nonTailRecursive(x: Int): Int =
    if (x == 0) throw new Exception("Stop!")
    else nonTailRecursive(x - 1) + 1

  def tailRecursive(x: Int): Int =
    if (x == 0) throw new Exception("Stop!")
    else tailRecursive(x - 1)

  def main(args: Array[String]): Unit = {
    // println("Factorial de 1: " + TailRecursion.factorial(3))
    //nonTailRecursive(1000)
    
    tailRecursive(10000)
  }
}
