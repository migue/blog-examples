package com.blogspot.miguelinlas3.scala.intro.parsercombinators.instruction

import com.blogspot.miguelinlas3.scala.intro.parsercombinators.stack.{ExecutionContext, ExecutionStack}

/**
 * Created by IntelliJ IDEA.
 * User: migue
 * Date: 3/16/11
 * Time: 7:48 PM
 * To change this template use File | Settings | File Templates.
 */

class IConstInstruction(n: String, val value: Int) extends BytecodeInstruction(n) {

  def execute(context: ExecutionContext[Double]): Unit = {
    context.push(value)
    context.nextInstruction
    context.countNewInstruction()
  }
}