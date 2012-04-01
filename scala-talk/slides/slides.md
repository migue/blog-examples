#

---

Twitter & friends
==================================
- `@MadridJUG`
- `#scalafans`
- `#codemotion` `#es`

---


About me
==================================
- Miguel Pastor
- Interested in distributed systems, scalability and PaaS.
- AspectJ and Scala enthusiast. Erlang aficionado.
- Phd student??
- Twitter: @miguelinlas3

---

Agenda
======

- Global overview of the language
- Language fundamentals
- A different approach to concurrency
- Where to go next?
- Q & A

---

What is Scala?
========================

- Statically typed language 
- Run on top of the __JVM__ ( and __.NET__ framework)
- Mixin of functional and object oriented paradigms
- Extensible language
- Crisis
    - Moore's law
    - Multicore trends

---

Classes and objects
=================================
- Class definition
- __vals__ and __vars__
- Inmutable arguments
- Semicolon inference

---

Code example
=============

    !scala
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

---

Singleton objects
=================================

- No __static__ methods
- __Companion__ class and __companion__ objects
- We can not instantiate them
- Implemented using __synthetic classes__
- __Static__ semantic

---

Code example
============
    !scala
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

---

Functional objects
================================

- Class parameters
- Overwriting
- Preconditions
- Methods and fields
- `private` methods
- Operators

---

Code example
============
    !scala
	class Rational(n: Int, d: Int) {
	  require(d != 0, "Denominador no puede ser cero")

	  val numer: Int = n
	  val denom: Int = d

	  override def toString = n + "/" + d

	  def add(that: Rational): Rational =
	    new Rational(numer * that.denom + that.numer * denom, denom * that.denom)
	  private def gcd(a: Int, b: Int): Int =
	    if (b == 0)
	      a
	    else gcd(b, a % b)

	  def +(that: Rational): Rational =
	    new Rational(numer * that.denom + that.numer * denom, denom * that.denom)
	  def *(that: Rational): Rational =
	    new Rational(numer * that.numer, denom * that.denom)
	}

 
---

Functions and closures
======================
- Methods
- Literal and value functions
- Placeholder and partial functions
- Closures

---

Methods
=======

	!scala
	object MethodsSample {
		def processFile(filename: String, width: Int) {
		
			val source = Source.fromFile(filename)
		
			for (line <- source.getLines())

			processLine(filename, width, line)
		}

		private def processLine(filename: String,width: Int, line: String) {
			if (line.length > width)
				println(filename +": "+ line.trim)
		}
	}


---

Literal functions
======================
	!scala
	object FirstClassFunctions {
		val concat = (s:String ) => s + ".scala"

		val concat2 = (s1:String, s2:String) => {
			println ("More examples con literal functions")
			s1 + ".scala" + "---" + s2 + ".scala"
		}

		def main (args : Array[String]) : Unit = {
			println(concat("file"))
			println(concat2("file1", "file2"))

			// use them in Scala library
			val nums = List(1, 2, 3, 4, 5, 6)

			nums.foreach((x:Int) => {val t = x + 1; println(t);})

		}
	}	

---

Placeholder syntax
===================

	!scala
	object Search {	
		private def filesInCurrentFolder = (new java.io.File(".")).listFiles
		
		private def filesMatching(matcher: String => Boolean) =
			for (file <- filesInCurrentFolder; if matcher(file.getName)) yield file

		def filesEndingWith(query: String) = filesMatching(_.endsWith(query))
		
		def filesContaining(query: String) = filesMatching(_.contains(query))
		
		def filesMatchRegex(query: String) = filesMatching(_.matches(query))


		def main (args : Array[String]) : Unit = {		
			val foundFiles = filesEndingWith("sbt")

			for (file <- foundFiles) {
				println (file)
			}
		}
	}

---

Partial functions
==================

	!scala
	object PartialFunction {
	
		def concat(s1:String, s2:String, s3:String) = s1 + s2 + s3

		def concat2 = concat("Prefix", _:String, "Suffix")
	}

---


Closures
========

	!scala
	var sum = 0

	val nums = List(1,2,3,4,5,6,8,9,10,11)

	nums.foreach(sum += _)

	println(sum)
	

---


Tail recursion
====================================

- Not all the recursive functions are tail recursive
- __JVM bytecode__ restrictions/limitations
- Indirect recursions does not work

---

Tail recursion
====================================
	
Non tail recursive

	!scala
	def nonTailRecursive(x: Int): Int =
	    if (x == 0) throw new Exception("Stop!")
    	else nonTailRecursive(x - 1) + 1

Tail recursive

	!scala
	def tailRecursive(x: Int): Int =
    	if (x == 0) throw new Exception("Stop!")
    	else tailRecursive(x - 1)


---

Currying
====================================

- Functional paradigm technique
- Multiple argument lists
- Subsequent invocation of functions

---

Code example (I)
=================

    !scala
	abstract class TransactionManagement
		(val transactionManager: MockTransactionManager) {

	  def transactional[T]()(fun: => T): T = {
	    transactionManager.openTransaction
	    try {
	      fun
	    } catch {
	      case e => {
	        transactionManager.rollback
	        throw e
	      }
	    } finally {
	      transactionManager.closeTransaction
	    }

	  }
	}

---

Code example (II)
=================

    !scala
    class TransactionalService (txManager: MockTransactionManager) 
    	extends TransactionManagement(txManager) {

	  def sum(x: Int, y: Int): Int = {
	    transactional[Int]() {
	      x + y
	    }
	  }

	  def buggy(x: Int, y: Int): Int = {
	    transactional[Int]() {
	      val result = x + y
	      if (result == 2)
	        throw new Exception()
	      result
	    }
	  }
	}

---

Traits (I)
========================

- Attributes and methods
- __Mixin__ within multiple classes
- Using __extend__ or __with__
- Define types by themselves
- More advanced than __interfaces__
    - Class parameters are not allowed
    - __super__ calls are linked dynamically

---

Traits (II)
=========================

- Enrich existing interfaces
- __Stackable__ modifications
    - Modify the method of a class
    - Stackable modifications
- __Abstract override__

---

Traits (III)
===========================

Should I use __traits__?
-------------------------

- No reuse --> __class__
- Reuse on multiple unrelated classes --> __trait__
- Do I need to extend from a __Java__ class? --> __class__
- Efficiency --> __class__
- I am not sure . . . --> start with __traits__

---

Code example
=============

    !scala
	abstract class IntQueue {
	  def get(): Int
	  def put(x: Int)
	}

	class BasicIntQueue extends IntQueue {

	  private val buf = new ArrayBuffer[Int]

	  def get() = buf.remove(0)
	  def put(x: Int) { buf += x }
	}

	trait Duplication extends IntQueue {
	  abstract override def put(x: Int) { super.put(2 * x) }
	}

	trait Increment extends IntQueue {
	  abstract override def put(x: Int) { super.put(x + 1) }
	}

	trait Filter extends IntQueue {
	  abstract override def put(x: Int) { if (x >= 0) super.put(x) }
	}

---

Code example (II)
=================

    !scala
    // Ejemplo 1: mixin 1
	class MyQueue extends BasicIntQueue with Duplication

	// Ejemplo 2: stacking modifications
	class MyQueueMultipleMods extends BasicIntQueue with Duplication 
													with Increment

	object stackableMain {
	  def main(args: Array[String]): Unit = {
	    var q = new MyQueue
	    q.put(10)
	    // print 20
	    println(q.get())
	    var q2 = new MyQueueMultipleMods
	    q2.put(10)
	    // print 22
	    println(q2.get())
	}

---


Pattern matching
==============================

Alternative selector
--------------------
- __selector match { alternatives }__

Case classes
------------

- __factory-method__ is included
- Parameter list: implicit __val__
- Instintive implementation __ToString__, __equals__ y __hashCode__
- We can use them in pattern matching
- __sealed__ classes

---

Pattern matching: Wildcard
==============================
    
    !scala
    case class Simple 
    	(private val x:Int, private val y:Int, private val operator:String) {}
	
	Simple(1, 2, "+") match {			
		case s@Simple(_,_,_) => println(s + " is a Simple object")			
		case _ => println("Matching all the rest!!")
	}

--- 

Pattern matching: Constant
==============================

    !scala
    def what(x: Any) = x match {
		case 11 => "eleven"
		case Nil => "Empty"
		case true => "True"
		case _ => "Unhandled!"
	}

--- 

Pattern matching: Variables
==============================
    
    !scala
    val x = 1;

	x match {
		case 0 => println("0 value")
		case variable => 
			println("The variable " + variable + " is bounded so we can use it!")
	}

--- 

Pattern matching: Constructor
==============================

	!scala
    case class Simple 
    	(private val x:Int, private val y:Int, private val operator:String)
	
	Simple(1, 2, "+") match {			
		case s@Simple(_,_,_) => println(s + " is a Simple object")			
		case _ => println("Matching all the rest!!")
	}

--- 

Pattern matching: Sequence
==========================

	!scala
	def firstElem[A](x:List[A]) = x match {			
		case List(first@_, _*) => 
			println("This is the first eleme of the list is " + first)
		case _ 	=>
	}

	firstElem[Int](List(3,4,1))

--- 

Pattern matching: Type
==============================
	
	!scala
	def determineType(x:Any) = x match {
		case s: String => println ("String")
		case l: List[_] => println ("List")
		case l: Map[_,_] => println ("Map")
		case _ => println ("Unhandled type!")
	}

--- 


Implicits (I)
==============================

- __Similar__ to open classes or metaclasses
- Try to solve __type problems__
- The compiler will try to include them (should be in the scope)
- One __implicit__ at a time

---

Implicits (II)
==============================
Where __implicits__ can be used?

- Convert to a custom type
- Convert the receiving object of a method call
- Function parameters

---

Code example (I)
=================
    !scala
    class User (private val username:String) {	
		def encodePassword(password:String)(implicit encoder: Encoder) : String = {
			encoder.encode(password)	
		}
		object UserImplictis {	
			implicit def stringToUsername(user: String) = new User(user)
		}
	}

Using the previous code
    
    !scala
    object ImplicitsExamples {

	    def main(args: Array[String]) {      	  
	      	  import UserImplictis.stringToUsername	      	
		      val user: User = "migue"
		      println("The user: " + user)
		} 
	}

---

Code example (II)
==================

    !scala
    class ExternalClass {
		def f1() = {
			"f1"
		}
	}

	object UserImplictis {	
		class MethodHelper {
			def f3(): String = {
				"f3"
			}
		}
		implicit def newMethodsToExternalClass(
				externalClass:ExternalClass) : MethodHelper = {
			
			new MethodHelper
		}
	}
    
    import UserImplictis.newMethodsToExternalClass
	           
	println("f3() added via an implitict conversion: " + externalClass.f3())

---

Code example (III)
==================

    !scala
    trait Encoder {
		def encode(password:String): String
	}

    implicit object IdentityEncoder extends Encoder {
		override def encode(password:String): String = password
	}

	implicit object ReverseEncoder extends Encoder {
		override def encode(password:String): String = password.reverse	
	}

	import UserImplictis.ReverseEncoder

	println("The encoded password is: " + user2.encodePassword("foo"))

---

What else?
==========

- Scala collections
- Extractors
- Generics
- Variance annotations
- Lower and upper bounds
- Typeclasses
- Parser combinators

---

Concurrency
============

- Developing multithreaded programs is complex
- No determinism
- Hard to test and debug
- Shared memory
- Deadlocks, race conditions, . . .

---

Actor model
===========
- Shared nothing
- Mailbox
- Message passing
- Borrowed from __Erlang__
- __Akka__ will replace the current implementation

---

Some thoughts
==============

---

Benefits
========

- General purpose
	- Productive
	- Powerful
- Server side
- Web development
- Scalability and concurrency
- Writing DSLs

---

Drawbacks
=========
- Compiler performance
	- Much slower than `javac`
- IDE support
	- `Scala IDE`, `IntelliJ`, `Sublime Text` (I really like it!)
- Binary compatibility
- Learning curve
- Community

---

Where did I use?
================

- Building some modules of an IDE (Eclipse based)
- Writing some middleware (cloud related)
- Writing some DSL

---

OSS using __Scala__
================================
- [Akka](http://akka.io/)
- [Apache Kafka](http://incubator.apache.org/kafka/)
- [Lift](http://liftweb.net/)
- [Play2](http://www.playframework.org/)
- [SBT](http://github.com/harrah/sbt)
- ...

---

Some references
===============
- [Programming in Scala 2nd Edition](http://www.artima.com/shop/programming_in_scala)
- [Scala in depth](http://manning.com/suereth/)
- [Programming Scala](http://ofps.oreilly.com/titles/9780596155957/)
- [Programming Scala: Tackle Multi-Core Complexity on the Java Virtual Machine](http://pragprog.com/book/vsscala/programming-scala) 
- [Functional programming in Scala](http://manning.com/bjarnason/)
- [Learn you Haskell for a Great Good](http://learnyouahaskell.com/)

---

__Q & A__ (hopefully)
=====================