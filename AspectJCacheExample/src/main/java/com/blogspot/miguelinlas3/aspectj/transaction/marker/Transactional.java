package com.blogspot.miguelinlas3.aspectj.transaction.marker;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This interface is used to determine those classes or methods who need to
 * execute in a transactional context
 * 
 * @author migue
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = { ElementType.TYPE, ElementType.METHOD })
public @interface Transactional {
	boolean readOnly() default true;
}
