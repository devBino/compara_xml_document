package br.com.fbm.xml.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Document;

import br.com.fbm.xml.business.bo.ComparBO;

/**
 * {@code DocCache} armazena em memória, 
 * em tempo de execução resultados de comparações
 * de documentos representados por
 * objetos do tipo {@link Document}
 * 
 * @author Fernando Bino Machado
 */
public class DocCache {

	/**
	 * Instância única de {@code DocCache}
	 */
	private static DocCache cache;
	
	/**
	 * Armazena por chaves String, listas de {@link ComparBO},
	 * representando resultados de comparações entre 
	 * dois {@link Document}
	 */
	private Map<String, List<ComparBO>> mapComparacoes;
	
	/**
	 * Cria instância de {@code DocCache}
	 */
	private DocCache() {
		mapComparacoes = new HashMap<>();
	}
	
	/**
	 * Retorna instância única de {@code DocCache}
	 * @return
	 */
	public static DocCache getCache() {
		if(cache == null) {
			cache = new DocCache();
		}
		return cache;
	}
	
	/**
	 * Retorna listas mapeadas por Strings 
	 * no atributo {@link DocCache#mapComparacoes}
	 * @return
	 */
	public Map<String, List<ComparBO>> getMapComparacoes() {
		return mapComparacoes;
	}
	
	
}
