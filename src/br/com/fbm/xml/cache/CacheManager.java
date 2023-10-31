package br.com.fbm.xml.cache;

import java.util.List;

import br.com.fbm.xml.business.bo.ComparBO;

public class CacheManager {

	DocCache cache;
	
	public CacheManager() {
		cache = DocCache.getCache();
	}
	
	public void addComparResult(final String pName, 
			final List<ComparBO> pListComparResult ) {
		cache.getMapComparacoes().put(pName, pListComparResult);
	}
	
	public void removeComparResult(final String pName) {
		cache.getMapComparacoes().remove(pName);
	}
	
	
}
