package br.com.fbm.xml.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.fbm.xml.business.bo.ComparBO;

public class DocCache {

	private static DocCache cache;
	private Map<String, List<ComparBO>> mapComparacoes;
	
	private DocCache() {
		mapComparacoes = new HashMap<>();
	}
	
	public static DocCache getCache() {
		if(cache == null) {
			cache = new DocCache();
		}
		return cache;
	}
	
	public Map<String, List<ComparBO>> getMapComparacoes() {
		return mapComparacoes;
	}
	
	
}
