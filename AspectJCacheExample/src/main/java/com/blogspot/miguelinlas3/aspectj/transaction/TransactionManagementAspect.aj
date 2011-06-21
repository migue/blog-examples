package com.blogspot.miguelinlas3.aspectj.transaction;

import com.blogspot.miguelinlas3.aspectj.transaction.manager.MockTransactionManager;
import com.blogspot.miguelinlas3.aspectj.transaction.marker.Transactional;

/**
 * This aspect is responsible for transaction management
 * 
 * @author migue
 * 
 */
public aspect TransactionManagementAspect {

	pointcut transactionalOperation() : execution(@Transactional * * (..) );

	Object around() throws Throwable : transactionalOperation() {
		mockTransactionManager.openTransaction();
		try {
			Object result = proceed();
			mockTransactionManager.commit();
			return result;
		} catch (Throwable t) {
			mockTransactionManager.rollback();
			throw t;
		} finally {
			mockTransactionManager.closeTransaction();
		}

	}

	private MockTransactionManager mockTransactionManager = new MockTransactionManager();
}
