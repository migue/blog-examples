package com.blogspot.miguelinlas3.scala.intro.highorderunction.cache

import scala.collection.mutable.Map

abstract class Caching {

  def cache[T](key: String)(value: => T): T = {

    if (cachedValues.contains(key)) {
      println("Cache hit for " + key + " with value " + cachedValues.get(key))
      cachedValues.get(key).get.asInstanceOf[T]
    } else {
      val tVal: T = value
      cachedValues.put(key, tVal)
      tVal
    }
  }

  val cachedValues = Map.empty[String, Any]
}