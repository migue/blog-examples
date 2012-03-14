package com.blogspot.miguelinlas3.scala.intro.parsercombinators.profiler.visitor

import com.blogspot.miguelinlas3.scala.intro.parsercombinators.instruction.HeaderInstruction
import com.blogspot.miguelinlas3.scala.intro.parsercombinators.profiler.parser.EfficientByteCodeParser
import com.blogspot.miguelinlas3.scala.intro.parsercombinators.stack.ExecutionContext

/**
 * This visitor is used to traverse de AST returned by the parser (a simple List with bytecode isntructions)
 * an execute them
 *
 * Created by IntelliJ IDEA.
 * User: migue
 * Date: 3/20/11
 * Time: 5:05 PM
 * To change this template use File | Settings | File Templates.
 */

class ExecutionVisitor {

  val parser = new EfficientByteCodeParser

  /**
   * Executes all the bytecode instructions of the current AST traversed
   */
  def execute(s: String,args:Array[String]): ExecutionContext[Double] = {

    // 1. parse the source code (builds the AST)
    val instructions = parser.parse(s)

    // 2 set up the execution context.
    val executionContext = instructions.head.asInstanceOf[HeaderInstruction].buildExecutionContext(args)
    val executableInstructions = instructions.tail
    executionContext.instructions = executableInstructions

    // 3. traverse our AST
    var index = 0
    while(index >= 0 && index < executableInstructions.length){
        executableInstructions(index).execute(executionContext)
        index = executionContext.programCounter
    }

    println("Number of executed bytecodes: " + executionContext.numOfExectutions)
    executionContext
  }
}