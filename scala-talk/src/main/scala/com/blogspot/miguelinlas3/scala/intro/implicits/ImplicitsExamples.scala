package com.blogspot.miguelinlas3.scala.implicits

/**
* This is an external class with two stupid methods f1() and f2().
* It could be located on a third-party jar file but for our examples 
* we are going to use the class on this way
*/

class ExternalClass {
	def f1() = {
		"f1"
	}

	def f2() = {
		"f2"
	}
}

trait Encoder {
		def encode(password:String): String
} 

class User (private val username:String) {
	
	def encodePassword(password:String)(implicit encoder: Encoder) : String = {
		encoder.encode(password)	
	} 
}

object UserImplictis {
	
	/**
	* The compiler will include this implicit in order to convert
	* a string to an User class instance so we can write code like
	* this:
	*
	* 	val user: User = "migue"
	*
	*/
	
	implicit def stringToUsername(user: String) = new User(user)

	/**
	* Using this implicit we are converting an method call object
	* receiver. This approach will allow us to include new methods
	* on an existing class.
	* The class ExternalClass has two methods f1() and f2(), and we
	* want to add a new method f3() to this class in order to do things
	* like
	* 	val externalClass:ExternalClass = new ExternalClass()
	* 	externalClass.f3
	*
	*/
	class MethodHelper {
		def f3(): String = {
			"f3"
		}
	}

	implicit def newMethodsToExternalClass(externalClass:ExternalClass) : MethodHelper = {
		new MethodHelper
	}

	/**
	 * This two objects could be used as implicits parameters on the method calls to the
	 * encodePassword method on the User class
	 */
	implicit object IdentityEncoder extends Encoder {
		override def encode(password:String): String = password
	}

	implicit object ReverseEncoder extends Encoder {
		override def encode(password:String): String = password.reverse	
	}
}


object ImplicitsExamples {

    // main method to run the implicits examples

    def main(args: Array[String]) {
      
	      // Example A: Using implicits to convert to an expected type

	      // A-1: make the implicit definition available in this scope
	      import UserImplictis.stringToUsername
	      	
	      // A-2: the compiler will insert here the conversion from String 
	      // to an User class instance
	      val user: User = "migue"

	      println("The user: " + user)

	      // Example B: Using implicits to include new methods to existing classes

	      // B-1: Create a new instance of the ExternalClass
	      val externalClass:ExternalClass = new ExternalClass

	      // B-2: The method f1() and f2 are available at the class definitions
	      println("Method f1() defined in ExternalClass: " + externalClass.f1())

	      // B-3: The method f3() is included via an implicit conversion on the
	      // receiver object
	      // make the implicit conversion available on this scope
	      import UserImplictis.newMethodsToExternalClass
	           
	      println("Method f3() is added via an implitict conversion: " + externalClass.f3())

	      // Example C: using implicits parameters
	      // C-1: build a new user (note the implicit conversion it is being used again)
	      val user2:User = "migue"

	      // C-2: make available de implicit encoders parameters
	      // import UserImplictis.IdentityEncoder
	      import UserImplictis.ReverseEncoder

	      // The encoder is included by the compiler using the implicits definitions
	      println("The encoded password is: " + user2.encodePassword("foo"))
    }
}