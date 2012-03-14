package com.blogspot.miguelinlas3.scala.intro.parsercombinators.stack

/**
 * This abstraction is used to store the values of the variables
 *
 * Created by IntelliJ IDEA.
 * User: migue
 * Date: 3/20/11
 * Time: 5:11 PM
 * To change this template use File | Settings | File Templates.
 */
class VariablesTable[T: Manifest](initialSize: Int) {

  require(initialSize >= 0)

  private val table: Array[T] = new Array[T](initialSize)

  /**
   * Stores the value in the position indicated in the first argument
   */
  def store(position: Int, value: T): Unit = {
    this.table(position) = value
  }

  /**
   * Retrieves the value of the variable stored at the indicated position
   */
  def retrieve(position: Int): T = {
    this.table(position)
  }

  /**
   * Retrieves the size of the variables table
   */
  def size: Int = {
    this.table.length
  }
}