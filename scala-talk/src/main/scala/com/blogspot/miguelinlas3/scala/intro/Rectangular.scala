package com.blogspot.miguelinlas3.scala.intro

class Point(val x: Int, val y: Int)

trait Rectangular {
  def topLeft: Point
  def bottomRight: Point
  def left = topLeft.x
  def right = bottomRight.x
  def width = right - left

  // más métodos
}

// mixin de la clase Component con el trait Rectangular

abstract class Component extends Rectangular {
  // nuevos métodos para este tipo de widgets
}