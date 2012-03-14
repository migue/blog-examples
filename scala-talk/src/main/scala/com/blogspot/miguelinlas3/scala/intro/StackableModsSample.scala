package com.blogspot.miguelinlas3.scala.intro

import scala.collection.mutable.ArrayBuffer

abstract class IntQueue {
  def get(): Int
  def put(x: Int)
}

// implementación sencilla de una cola de enteros
class BasicIntQueue extends IntQueue {

  private val buf = new ArrayBuffer[Int]

  def get() = buf.remove(0)
  def put(x: Int) { buf += x }
}

trait Duplication extends IntQueue {
  // este trait mixin con una clase que 
  //tenga implementación concreta de put
  abstract override def put(x: Int) { super.put(2 * x) }
}

trait Increment extends IntQueue {
  abstract override def put(x: Int) { super.put(x + 1) }
}

trait Filter extends IntQueue {
  abstract override def put(x: Int) { if (x >= 0) super.put(x) }
}

// Ejemplo 1: mixin 1
class MyQueue extends BasicIntQueue with Duplication

// Ejemplo 2: stacking modifications
class MyQueueMultipleMods extends BasicIntQueue with Duplication with Increment

object stackableMain {
  def main(args: Array[String]): Unit = {
    var q = new MyQueue
    q.put(10)
    // debería retornar 20
    println(q.get())
    var q2 = new MyQueueMultipleMods
    q2.put(10)
    // debería retornar 22
    println(q2.get())
  }
}