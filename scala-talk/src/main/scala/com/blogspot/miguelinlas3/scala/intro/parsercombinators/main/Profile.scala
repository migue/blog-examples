package com.blogspot.miguelinlas3.scala.intro.parsercombinators.main

import com.blogspot.miguelinlas3.scala.intro.parsercombinators.profiler.visitor.ExecutionVisitor

/**
 * 
 */

object Profile{
  def main(args:Array[String]):Unit = {

      val result = new ExecutionVisitor().execute(args.head,args.tail)

      println("Result: " + result.result)
      println("Count: " + result.numOfExectutions)


  }
}