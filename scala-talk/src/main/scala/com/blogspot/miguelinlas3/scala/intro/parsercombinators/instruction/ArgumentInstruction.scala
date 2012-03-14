package com.blogspot.miguelinlas3.scala.intro.parsercombinators.instruction

import com.blogspot.miguelinlas3.scala.intro.parsercombinators.stack.ExecutionContext

/**
 * Created by IntelliJ IDEA.
 * User: migue
 * Date: 3/16/11
 * Time: 7:48 PM
 * To change this template use File | Settings | File Templates.
 */

class ArgumentInstruction(val v: Int) extends BytecodeInstruction("argument") {

  def execute(context: ExecutionContext[Double]): Unit = {
    // Do nothing
  }
}