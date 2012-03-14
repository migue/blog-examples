package com.blogspot.miguelinlas3.scala.intro.parsercombinators.instruction

import com.blogspot.miguelinlas3.scala.intro.parsercombinators.stack.ExecutionContext

/**
 * Created by IntelliJ IDEA.
 * User: migue
 * Date: 3/20/11
 * Time: 7:21 PM
 * To change this template use File | Settings | File Templates.
 */

class ILoadInstruction(n: String, val position: Int) extends BytecodeInstruction(n) {

  def execute(context: ExecutionContext[Double]): Unit = {
    val value = context.retrieve(position)
    context.push(value)
    context.nextInstruction
    context.countNewInstruction()
  }
}