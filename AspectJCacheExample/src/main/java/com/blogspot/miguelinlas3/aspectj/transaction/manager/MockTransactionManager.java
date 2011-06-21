package com.blogspot.miguelinlas3.aspectj.transaction.manager;

/**
 * Stupid class to emulate the basic operations of a transaction manager
 * 
 * @author migue
 * 
 */
public class MockTransactionManager {

	public void openTransaction(){
		System.out.println("Opening transaction");
	}
	
	public void closeTransaction(){
		System.out.println("Closing transaction");
	}
	
	public void rollback(){
		System.out.println("Roll back previous work");
	}
	
	public void commit(){
		System.out.println("Commiting the work");
	}
}
