package com.blogspot.miguelinlas3.scala.intro.parsercombinators.instruction

import com.blogspot.miguelinlas3.scala.intro.parsercombinators.stack.ExecutionContext
import java.util.Scanner

/**
 * Created by IntelliJ IDEA.
 * User: migue
 * Date: 3/21/11
 * Time: 12:58 AM
 * To change this template use File | Settings | File Templates.
 */

class HeaderInstruction(n: String,private val numLocalVariables: Int, private val numInputParameters: Int) extends BytecodeInstruction(n) {

  def execute(context: ExecutionContext[Double]): Unit = {

  }

  def buildExecutionContext(args:Array[String]): ExecutionContext[Double] = {
    // user input should be correct
    assert(args.length == numInputParameters)

    val context = new ExecutionContext[Double](numLocalVariables)

    // store the input in the variables table
    var i:Int = 0
    for(userInput <- args){
      context.store(i,userInput.toDouble)
      i+=1
    }
    context

  }
}