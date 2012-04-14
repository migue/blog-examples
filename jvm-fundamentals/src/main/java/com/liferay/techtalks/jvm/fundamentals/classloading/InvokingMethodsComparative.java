package com.liferay.techtalks.jvm.fundamentals.classloading;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class SimpleService {
	private String methodA() {
		return "Executing method " + "methodA() of " + this.getClass();
	}
}

public class InvokingMethodsComparative {

	public String invokeUsingMethodHandles(SimpleService simpleService)
			throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {

		Method m = SimpleService.class.getMethod("methodA");
		m.setAccessible(true);

		return (String) m.invoke(simpleService);
	}

	public String invokeUsingHandles(SimpleService simpleService)
			throws Throwable {
		MethodHandle methodHandle;
		MethodType methodType = MethodType.methodType(String.class);

		methodHandle = MethodHandles.lookup().findVirtual(SimpleService.class,
				"methodA", methodType);

		return (String) methodHandle.invoke();
	}
}