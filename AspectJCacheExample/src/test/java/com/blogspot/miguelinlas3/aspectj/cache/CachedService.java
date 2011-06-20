package com.blogspot.miguelinlas3.aspectj.cache;

import com.blogspot.miguelinlas3.aspectj.cache.marker.Cachable;

public class CachedService {

	@Cachable(scriptKey="$x + ':' + $y")
	public Integer sum(Integer x, Integer y) {
		return x + y;
	}
	
	public static void main(String[] args) {
		CachedService cachedService = new CachedService();
		
		for(int i = 0 ; i <= 10 ; ++i){
			cachedService.sum(i, i);
		}
	}
}
