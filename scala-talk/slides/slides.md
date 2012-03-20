#

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

Closures and functions
====================================

- Literal and value functions
- Closures: free variables
- Changes visibility
- High order functions

---

Tail recursion
====================================

- Not all the recursive functions are tail recursive
- __JVM bytecode__ restrictions/limitations
- Indirect recursions does not work

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

Lists and collections
===========================
- XXX Complete this section!

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

---

Pattern matching
==============================

Kinds of patterns
-----------------

- Wildcard
- Constant
- Variables
- Constructor
- Sequence
- Type

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
	- `Scala IDE`
	- `IntelliJ`
	- `Sublime Text` (I really like it!)
	- . . . 
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
- []()

---

__Q & A__ (hopefully)
=====================