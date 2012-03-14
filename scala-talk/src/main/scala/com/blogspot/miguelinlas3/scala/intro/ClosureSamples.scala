package com.blogspot.miguelinlas3.scala.intro

class ClosureSamples {

}

object mainClosures {
  def main(args: Array[String]): Unit = {

    var other = 1

    def closure1 = (x: Int) => { x * other }

    def closure2(x: Int) = { x * other }

    println("Invocacion de la closure1: " + closure1(11))

    println("Invocacion de la closure2: " + closure2(11))

    other = 10

    println("Invocacion de la closure1: " + closure1(11))

    println("Invocacion de la closure2: " + closure2(11))
  }
}