package com.blogspot.miguelinlas3.scala.intro.parsercombinators.instruction

import com.blogspot.miguelinlas3.scala.intro.parsercombinators.stack.ExecutionContext

/**
 * Created by IntelliJ IDEA.
 * User: migue
 * Date: 3/20/11
 * Time: 11:00 PM
 * To change this template use File | Settings | File Templates.
 */

class GotoInstruction(n: String) extends BytecodeInstruction(n) {
  override def execute(context: ExecutionContext[Double]): Unit = {
      context.gotoInstruction(context.retrieveInstruction(1).asInstanceOf[ArgumentInstruction].v)
  }
}