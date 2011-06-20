package com.blogspot.miguelinlas3.aspectj.cache.marker;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Marker annotation
 * 
 * @author migue
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Cachable {
	String scriptKey () default "";
}
