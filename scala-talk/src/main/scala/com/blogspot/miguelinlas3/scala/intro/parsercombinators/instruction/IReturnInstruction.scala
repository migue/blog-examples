package com.blogspot.miguelinlas3.scala.intro.parsercombinators.instruction

import com.blogspot.miguelinlas3.scala.intro.parsercombinators.stack.ExecutionContext

/**
 * Created by IntelliJ IDEA.
 * User: migue
 * Date: 3/20/11
 * Time: 10:59 PM
 * To change this template use File | Settings | File Templates.
 */

class IReturnInstruction(n: String) extends BytecodeInstruction(n) {
  def execute(context: ExecutionContext[Double]): Unit = {
    // TODO  Include call to other functions. By now it only pops de top of the stack
    context.result = context.pop

    // no more instructions should be executed
    context.programCounter = -1
    context.countNewInstruction()
  }
}