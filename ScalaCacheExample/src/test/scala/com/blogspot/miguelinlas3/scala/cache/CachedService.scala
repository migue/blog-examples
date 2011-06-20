package com.blogspot.miguelinlas3.scala.cache

case class SimpleObject(val x: Int , val y: Int)

class CachedService extends Caching {
  def buildSimpleObject (x: Int, y: Int): SimpleObject = {
    cache[SimpleObject](x + ":" + y) {
    	SimpleObject(x, y)
    }
  }
}

object CachedService {

  def main(args: Array[String]): Unit = {

    val cachedService = new CachedService()
    
    for (index <- 1 to 10){
      val obj = cachedService.buildSimpleObject(index, index)
      println("Result:" + obj.x + "--" + obj.y)
    }
    
    // all this elements should be cache hits
    for (index <- 1 to 10){
      val obj = cachedService.buildSimpleObject(index, index)
      println("Result:" + obj.x + "--" + obj.y)
    }
  }

}