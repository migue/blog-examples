package com.blogspot.miguelinlas3.scala.intro.parsercombinators.stack

import com.blogspot.miguelinlas3.scala.intro.parsercombinators.instruction.BytecodeInstruction

/**
 * This abstraction contains the execution context of our profile
 * Created by IntelliJ IDEA.
 * User: migue
 * Date: 3/20/11
 * Time: 7:10 PM
 * To change this template use File | Settings | File Templates.
 */


class ExecutionContext[T: Manifest](numVariables: Int,private val numOfArguments:Int = 0) {

  private val stack = new ExecutionStack[T]()

  private val variablesTable = new VariablesTable[T](numVariables)

  var instructions = List[BytecodeInstruction]()

  var programCounter: Int = 0

  var result:Double = 0

  private var numOfExecutedBytecodes:Long = 0

  def countNewInstruction(count:Int = 1)={
      numOfExecutedBytecodes += count
  }

  def numOfExectutions : Long = {
      numOfExecutedBytecodes
  }

  def retrieveInstruction(shift:Int):BytecodeInstruction = {
    instructions(programCounter + shift)
  }

  def gotoInstruction(position: Int) = {
    programCounter = position
  }

  def nextInstruction = {
    programCounter += 1
  }

  def nextInstruction(shift:Int) = {
    programCounter += shift
  }

  def push(value: T)(implicit n:Numeric[T]) = {
    stack.push(value)
  }

  def pop(implicit n:Numeric[T]): T = {
    stack.pop
  }

  def top:T = {
    stack.top
  }

  def store(position: Int, value: T) = {
    variablesTable.store(position, value)
  }

  def retrieve(position: Int): T = {
    variablesTable.retrieve(position)
  }

  def elementsInStack: Int = {
    stack.size
  }
}