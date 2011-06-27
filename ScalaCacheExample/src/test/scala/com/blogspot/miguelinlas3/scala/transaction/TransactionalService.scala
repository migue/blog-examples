package com.blogspot.miguelinlas3.scala.transaction

import com.blogspot.miguelinlas3.scala.transaction.manager.{ MockTransactionManager, TransactionManagement }

class TransactionalService(txManager: MockTransactionManager) extends TransactionManagement(txManager) {

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

object TransactionalService {

  def main(args: Array[String]): Unit = {
    def transactionalService = new TransactionalService(new MockTransactionManager())

    for (index <- 1 to 10) {
      val result = transactionalService.sum(index, index)
      println("Result:" + result)
    }

    for (index <- 1 to 10) {
      try {
        val result = transactionalService.buggy(index, index)
        println("Result:" + result)
      } catch {
        case e => println("An error has ocurred and the transaction has been rolled back")
      }
    }

  }
}