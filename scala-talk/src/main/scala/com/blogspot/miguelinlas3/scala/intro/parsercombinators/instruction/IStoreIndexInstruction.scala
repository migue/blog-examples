package com.blogspot.miguelinlas3.scala.intro.parsercombinators.instruction

import com.blogspot.miguelinlas3.scala.intro.parsercombinators.stack.ExecutionContext

/**
 * Created by IntelliJ IDEA.
 * User: migue
 * Date: 3/16/11
 * Time: 7:48 PM
 * To change this template use File | Settings | File Templates.
 */

class IStoreIndexInstruction(n: String) extends BytecodeInstruction(n) {

  def execute(context: ExecutionContext[Double]): Unit = {
    val top = context.pop
    context.store(context.retrieveInstruction(1).asInstanceOf[ArgumentInstruction].v, top)
    context.nextInstruction(2)
    context.countNewInstruction()
  }
}