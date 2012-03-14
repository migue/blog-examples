package com.blogspot.miguelinlas3.scala.intro

object SingletonObject {
  def methodOnObject() {
    println("Method on singleton object")
  }
}

class SingletonObject {
  def methodOnClass() {
    println("Method on class")
  }
}

object main {
	def main (args : Array[String]) : Unit = {
		def foo = new SingletonObject()
		foo.methodOnClass()
		SingletonObject.methodOnObject()
	}
}