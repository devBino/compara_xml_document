package br.com.fbm.xml.cache;

import java.util.List;

import org.w3c.dom.Node;

import br.com.fbm.xml.business.bo.ComparBO;

/**
 * {@code CacheManager} gerencia dados
 * armazenados em {@link DocCache}
 * @author Fernando Bino Machado
 */
public class CacheManager {

	/**
	 * Refere-se a instância única de {@link DocCache}
	 */
	DocCache cache;
	
	/**
	 * Cria instância de {@code CacheManager}
	 */
	public CacheManager() {
		cache = DocCache.getCache();
	}
	
	/**
	 * Utiliza o método {@link DocCache#getMapComparacoes()}
	 * para acessar o map de resultados de comparações 
	 * na instância única de {@link DocCache}
	 * e adiciona uma lista de comparações entre os
	 * {@code Node} nodes dos dois documentos.
	 * @param pName
	 * @param pListComparResult
	 */
	public void addComparResult(final String pName, 
			final List<ComparBO> pListComparResult ) {
		cache.getMapComparacoes().put(pName, pListComparResult);
	}
	
	/**
	 * Utiliza o método {@link DocCache#getMapComparacoes()}
	 * para acessar o map de resultados de comparações 
	 * na instância única de {@link DocCache} e 
	 * remover uma lista de comparações.
	 * @param pName
	 */
	public void removeComparResult(final String pName) {
		cache.getMapComparacoes().remove(pName);
	}
	
	
}
