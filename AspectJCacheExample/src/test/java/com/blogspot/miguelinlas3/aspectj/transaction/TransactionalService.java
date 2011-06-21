package com.blogspot.miguelinlas3.aspectj.transaction;

import com.blogspot.miguelinlas3.aspectj.transaction.marker.Transactional;

public class TransactionalService {

	@Transactional
	public Integer sum(Integer x, Integer y) throws Throwable {
		return x + y;
	}

	public static void main(String[] args) {
		TransactionalService transactionalService = new TransactionalService();

		for (int i = 0; i <= 1; ++i) {
			try {
				transactionalService.sum(i, i);
			} catch (Throwable t ) {
				System.out.println("Exception ocurred on transactional operation." + t.getLocalizedMessage());
			}
		}
	}
}
