package com.blogspot.miguelinlas3.scala.intro

class MyFirstClass {
	
	var a = 1
	
	def add(b:Byte):Unit={	
			a += b		
			println("Current value " + a)
	}
	
	def showCurrentValue() = {
		println("Current value " + a)
	}
	
}


object sample {
  def main(args : Array[String]) : Unit = {
	  
	  var a = new MyFirstClass()	   
	  a.add(1) 
	  
	  a.showCurrentValue()
  }
}