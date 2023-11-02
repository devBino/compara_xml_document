package br.com.fbm.xml.repository.docprocess;

import org.w3c.dom.Document;

/**
 * {@code DocProcess} contém dois atributos do tipo
 * {@link Document}, um sendo o documento base, e o outro
 * o documento destino de comparação, ambos representando diferentes
 * xmls.
 * Além disso contém outros atributos para preparar a
 * comparação dos documentos xml
 * @author Fernando Bino Machado
 */
public class DocProcess {

	/**
	 * Atributo do tipo {@link Document} que representa 
	 * o xml base de comparação 
	 */
	private Document docBase;
	
	/**
	 * Atributo do tipo {@link Document} que representa 
	 * o xml destino de comparação 
	 */
	private Document docCompare;
	
	/**
	 * Atributo do tipo boolean que define se 
	 * deve comparar igualdade dos conteúdos nas tags
	 */
	private boolean validarIgualdade;
	
	/**
	 * Define valor para o atributo {@link DocProcess#docBase}
	 * @param docBase
	 */
	public void setDocBase(Document docBase) {
		this.docBase = docBase;
	}
	
	/**
	 * Retorna o valor do atributo {@link DocProcess#docBase}
	 * @return
	 */
	public Document getDocBase() {
		return docBase;
	}
	
	/**
	 * Define valor para o atributo {@link DocProcess#docCompare}
	 * @param docCompare
	 */
	public void setDocCompare(Document docCompare) {
		this.docCompare = docCompare;
	}
	
	/**
	 * Retorna o valor do atributo {@link DocProcess#docCompare}
	 * @return
	 */
	public Document getDocCompare() {
		return docCompare;
	}

	/**
	 * Define valor para o atributo {@link DocProcess#validarIgualdade}
	 * @param validarIgualdade
	 */
	public void setValidarIgualdade(boolean validarIgualdade) {
		this.validarIgualdade = validarIgualdade;
	}
	
	/**
	 * Retorna valor do atributo {@link DocProcess#validarIgualdade}
	 * @return
	 */
	public boolean isValidarIgualdade() {
		return validarIgualdade;
	}
		
	
}
