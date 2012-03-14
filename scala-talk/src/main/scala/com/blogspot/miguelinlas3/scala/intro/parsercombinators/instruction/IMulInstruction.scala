package com.blogspot.miguelinlas3.scala.intro.parsercombinators.instruction

import com.blogspot.miguelinlas3.scala.intro.parsercombinators.stack.ExecutionContext

/**
 * Created by IntelliJ IDEA.
 * User: migue
 * Date: 3/20/11
 * Time: 10:58 PM
 * To change this template use File | Settings | File Templates.
 */

class IMulInstruction(n: String) extends BytecodeInstruction(n) {
  def execute(context: ExecutionContext[Double]): Unit = {
    val op1 = context.pop
    val op2 = context.pop
    context.push(op1 * op2)
    context.nextInstruction
    context.countNewInstruction()
  }
}