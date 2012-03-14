package com.blogspot.miguelinlas3.scala.intro.parsercombinators.types

/**
 *  Integer type for our execution stack
 *
 * Created by IntelliJ IDEA.
 * User: migue
 * Date: 3/19/11
 * Time: 12:42 PM
 * To change this template use File | Settings | File Templates.
 */

class IntType(v: Int) extends StackTypes {
  override val value = v
}