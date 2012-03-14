package com.blogspot.miguelinlas3.scala.intro.parsercombinators.instruction

import com.blogspot.miguelinlas3.scala.intro.parsercombinators.stack.ExecutionContext

/**
 * Created by IntelliJ IDEA.
 * User: migue
 * Date: 3/20/11
 * Time: 7:22 PM
 * To change this template use File | Settings | File Templates.
 */

class IfNeInstruction(n: String) extends BytecodeInstruction(n) {
  def execute(context: ExecutionContext[Double]): Unit = {
    val a = context.pop
    if (a == 0) {
      context.gotoInstruction(context.retrieveInstruction(2).asInstanceOf[ArgumentInstruction].v)
      return
    }

    context.gotoInstruction(context.retrieveInstruction(1).asInstanceOf[ArgumentInstruction].v)
  }
}