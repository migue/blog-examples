package com.blogspot.miguelinlas3.scala.intro.parsercombinators.instruction

import com.blogspot.miguelinlas3.scala.intro.parsercombinators.stack.{ExecutionContext, VariablesTable, ExecutionStack}

/**
 * This trait aims to model the different instructions used to modeling the different bytecode instructions of our simple profiler
 *
 * Created by IntelliJ IDEA.
 * User: migue
 * Date: 3/11/11
 * Time: 5:14 PM
 * To change this template use File | Settings | File Templates.
 */

abstract class BytecodeInstruction(val name: String) {
  def execute(context: ExecutionContext[Double]): Unit
}