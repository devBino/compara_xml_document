package br.com.fbm.xml.repository.docprocess;

import org.w3c.dom.Document;

import br.com.fbm.xml.repository.xml.XmlUtil;

/**
 * {@code DocProcessBuilder} tem responsabilidade de receber duas
 * Strings de xmls e faz o build do objeto {@link DocProcess}
 * definindo nele os {@link Document}s com base
 * nos xmls recebidos.  
 * @author Fernando Bino Machado
 */
public class DocProcessBuilder {

	/**
	 * Atributo define a instância que será construída nesse
	 * Buider
	 */
	private DocProcess docProcess;
	
	/**
	 * Representa o xml de base para comparação
	 */
	private String xmlBase;
	
	/**
	 * Representa o xml de destino para comparação
	 */
	private String xmlCompare;
	
	/**
	 * Cria nova instância de {@code DocProcessBuilder}
	 */
	public DocProcessBuilder() {
		docProcess = new DocProcess();
	}
	
	/**
	 * Define valor para {@link DocProcessBuilder#xmlBase}
	 * @param pXml
	 * @return
	 */
	public DocProcessBuilder setXmlBase(final String pXml) {
		xmlBase = pXml;
		return this;
	}
	
	/**
	 * Define valor para {@link DocProcessBuilder#xmlCompare}
	 * @param pXml
	 * @return
	 */
	public DocProcessBuilder setXmlCompare(final String pXml) {
		xmlCompare = pXml;
		return this;
	}
	
	/**
	 * Finaliza a criação do objeto {@link DocProcess}
	 * definindo os seus {@link Document}s
	 * @return
	 */
	public DocProcess build() {
		
		docProcess.setDocBase( XmlUtil.getDocumentFromStringXml(xmlBase) );
		docProcess.setDocCompare( XmlUtil.getDocumentFromStringXml(xmlCompare) );
		
		return docProcess;
		
	}
	
	
}
