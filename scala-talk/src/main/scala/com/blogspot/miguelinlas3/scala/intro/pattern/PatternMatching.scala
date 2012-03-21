package com.blogspot.miguelinlas3.scala.intro.pattern

object PatternMatchingExamples {
  
  	def main(args : Array[String]) : Unit = {
	
		case class Simple (private val x:Int, private val y:Int, private val operator:String)
	
		// 1 Using wildcards

		Simple(1, 2, "+") match {			
			case s@Simple(_,_,_) => println(s + " is a Simple object")			
			case _ => println("Matching all the rest!!")
		}

		//  2 Using constants
		
		def what(x: Any) = x match {
			case 11 => "eleven"
			case Nil => "Empty"
			case true => "True"
			case _ => "Unhandled!"
		}		
		
		println(what(12))

 		// 3 Using variables

 		val x = 1;

 		x match {
 			case 0 => println("0 value")
 			case variable => println("The variable " + variable + " is bounded so we can use it!")
 		}
 
 		// 4 Using constructors (very powerful, see 1)	

		// 5 Sequence patterns

		def firstElem[A](x:List[A]) = x match {			
			case List(first@_, _*) => println("This is the first eleme of the list is " + first)
			case _ 	=>
		}

		firstElem[Int](List(3,4,1))

		// 6 Typed patterns

		def determineType(x:Any) = x match {
			case s: String => println ("String")
			case l: List[_] => println ("List")
			case l: Map[_,_] => println ("Map")
			case _ => println ("Unhandled type!")
		}
		
		determineType(List(1,2,3))

		determineType("foo")
	}

}