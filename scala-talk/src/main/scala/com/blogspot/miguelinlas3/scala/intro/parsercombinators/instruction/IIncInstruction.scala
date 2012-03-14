package com.blogspot.miguelinlas3.scala.intro.parsercombinators.instruction

import com.blogspot.miguelinlas3.scala.intro.parsercombinators.stack.ExecutionContext

/**
 * Created by IntelliJ IDEA.
 * User: migue
 * Date: 3/20/11
 * Time: 11:01 PM
 * To change this template use File | Settings | File Templates.
 */

class IIncInstruction(n: String) extends BytecodeInstruction(n) {
  def execute(context: ExecutionContext[Double]): Unit = {
    val variable =  context.retrieveInstruction(1).asInstanceOf[ArgumentInstruction].v
    val increment = context.retrieveInstruction(2).asInstanceOf[ArgumentInstruction].v
    context.store(variable, context.retrieve(variable) + increment)
    context.nextInstruction(3)
    context.countNewInstruction()
  }
}