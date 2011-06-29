package com.blogspot.miguelinlas3.aspectj;

public aspect ScalaAspectAdvicing {

	public pointcut scalaMethodCall() : execution(* com.blogspot.miguelinlas3.scala.SimpleClass.exec*(..));
	
	String around(): scalaMethodCall() {
		System.out.println("Before scala method");
		String result =  proceed();
		System.out.println("After scala method");
		return result;
	}
	
}
