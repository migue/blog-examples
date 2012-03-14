package com.blogspot.miguelinlas3.scala.intro.parsercombinators.instruction

import com.blogspot.miguelinlas3.scala.intro.parsercombinators.stack.ExecutionContext

/**
 * This instruction ends a method call whose return type is void
 *
 * Created by IntelliJ IDEA.
 * User: migue
 * Date: 3/20/11
 * Time: 10:59 PM
 * To change this template use File | Settings | File Templates.
 */

class ReturnInstruction(n: String) extends BytecodeInstruction(n) {
  def execute(context: ExecutionContext[Double]): Unit = {
    // no more instructions should be executed
    context.programCounter = -1
    context.countNewInstruction()
  }
}