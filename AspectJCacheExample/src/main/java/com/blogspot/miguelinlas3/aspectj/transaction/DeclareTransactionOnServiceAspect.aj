package com.blogspot.miguelinlas3.aspectj.transaction;

import com.blogspot.miguelinlas3.aspectj.transaction.marker.Transactional;

/**
 * This aspect includes de {@link Transactional} annotation on all the classes
 * whose name is terminated on Service
 * 
 * @author migue
 * 
 */
public aspect DeclareTransactionOnServiceAspect {

	declare @type : *Service : @Transactional ;
}
