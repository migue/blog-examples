package com.blogspot.miguelinlas3.scala.intro.parsercombinators.stack

import com.blogspot.miguelinlas3.scala.intro.parsercombinators.types.StackTypes


/**
 * This is a simple stack for our profiler
 *
 * Created by IntelliJ IDEA.
 * User: migue
 * Date: 3/17/11
 * Time: 12:34 AM
 * To change this template use File | Settings | File Templates.
 */

class ExecutionStack[T] {

  // Initially the stack is empty
  val stack = scala.collection.mutable.ListBuffer[T]()


  /**
   * Retrieves the current top of the stack. It currently does not remove the element
   * from the queue
   */
  def top(): T = {
    stack.head
  }

  /**
   * Retrieves the current top of the stack. It currently does remove the element
   * from the queue
   */
  def pop()(implicit num:Numeric[T]): T = {
    import num._
    stack.remove(0)
  }

  /**
   * Push a new value onto the top of the stack
   */
  def push(value: T)(implicit num:Numeric[T]) = {
    import num._
    value +=: stack
  }

  /**
   * Return the current size of the stack
   */
  def size(): Int = {
    stack.length
  }
}