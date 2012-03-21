package com.blogspot.miguelinlas3.scala.intro.highorderunction.sample

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

object MethodsSample {
	def processFile(filename: String, width: Int) {
	
		import scala.io.Source

		val source = Source.fromFile(filename)
	
		for (line <- source.getLines())

		processLine(filename, width, line)
	}

	private def processLine(filename: String,width: Int, line: String) {
		if (line.length > width)
			println(filename +": "+ line.trim)
	}

	def main (args : Array[String]) : Unit = {
		processFile("/tmp/foo", 50)
	}
}

object PartialFunction {
	
	def concat(s1:String, s2:String, s3:String) = s1 + s2 + s3

	def concat2 = concat("Prefix", _:String, "Suffix")
}

object ClosuresExamples {
	
	def main (args : Array[String]) : Unit = {
		var sum = 0

		val nums = List(1,2,3,4,5,6,8,9,10,11)

		nums.foreach(sum += _)

		println(sum)
	}	
	
}

