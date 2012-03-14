package com.blogspot.miguelinlas3.scala.intro.parsercombinators.instruction

import com.blogspot.miguelinlas3.scala.intro.parsercombinators.stack.ExecutionContext

/**
 * Created by IntelliJ IDEA.
 * User: migue
 * Date: 3/20/11
 * Time: 11:07 PM
 * To change this template use File | Settings | File Templates.
 */

class IPushInstruction(n: String,val value: Int) extends BytecodeInstruction(n) {
  def execute(context: ExecutionContext[Double]): Unit = {
    context.push(value)
    context.nextInstruction
    context.countNewInstruction()
  }
}