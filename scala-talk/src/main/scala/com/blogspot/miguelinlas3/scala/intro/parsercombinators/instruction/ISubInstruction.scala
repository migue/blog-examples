package com.blogspot.miguelinlas3.scala.intro.parsercombinators.instruction

import com.blogspot.miguelinlas3.scala.intro.parsercombinators.stack.ExecutionContext

/**
 * Created by IntelliJ IDEA.
 * User: migue
 * Date: 3/20/11
 * Time: 10:58 PM
 * To change this template use File | Settings | File Templates.
 */

class ISubInstruction(n: String) extends BytecodeInstruction(n) {
  def execute(context: ExecutionContext[Double]): Unit = {
    // pop the two operands of the stack
    val a = context.pop
    val b = context.pop
    // store the result in the top of the stack
    context.push(a - b)
    context.nextInstruction
    context.countNewInstruction()
  }
}