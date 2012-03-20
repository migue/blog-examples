package com.blogspot.miguelinlas3.scala.intro.highorderunction.search

object Search {
	
	private def filesInCurrentFolder = (new java.io.File(".")).listFiles
	
	private def filesMatching(matcher: String => Boolean) =
		for (file <- filesInCurrentFolder; if matcher(file.getName)) yield file

	def filesEndingWith(query: String) = filesMatching(_.endsWith(query))
	
	def filesContaining(query: String) = filesMatching(_.contains(query))
	
	def filesMatchRegex(query: String) = filesMatching(_.matches(query))


	def main (args : Array[String]) : Unit = {
		
		val foundFiles = filesEndingWith("")

		for (file <- foundFiles) {
			println (file)
		}
	}
}