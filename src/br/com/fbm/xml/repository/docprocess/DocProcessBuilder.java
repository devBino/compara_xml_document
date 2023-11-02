package br.com.fbm.xml.repository.docprocess;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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
	 * Define um Set<String> contendo os prefixos de namespaces
	 * a serem removidos durante o build dos {@code Document}s
	 */
	private Set<String> prefixRemover;
	
	/**
	 * Cria nova instância de {@code DocProcessBuilder}
	 */
	public DocProcessBuilder() {
		docProcess = new DocProcess();
		prefixRemover = new HashSet<>();
	}
	
	/**
	 * Define valor para {@link DocProcessBuilder#xmlBase}
	 * @param pPathArquivo
	 * @return
	 * @throws Exception
	 */
	public DocProcessBuilder setXmlBase(final String pPathArquivo)
		throws Exception {
		xmlBase = XmlUtil.getXmlArquivo(pPathArquivo);
		return this;
	}
	
	/**
	 * Define valor para {@link DocProcessBuilder#xmlCompare}
	 * @param pPathArquivo
	 * @return
	 * @throws Exception
	 */
	public DocProcessBuilder setXmlCompare(final String pPathArquivo)
		throws Exception {
		xmlCompare = XmlUtil.getXmlArquivo(pPathArquivo);
		return this;
	}
	
	/**
	 * Define se {@link DocProcess#isValidarIgualdade()}
	 * sera igual a true
	 * @return
	 */
	public DocProcessBuilder validarIgualdade() {
		docProcess.setValidarIgualdade(true);
		return this;
	}
	
	/**
	 * Adiciona uma String representando um prefixo de namespace
	 * em {@link DocProcessBuilder#prefixRemover}
	 * @param pNamespace
	 * @return
	 */
	public DocProcessBuilder addPrefixoNamespaceRemover(final String... pPrefix) {
		prefixRemover.addAll( Arrays.asList(pPrefix) );
		return this;
	}
	
	/**
	 * Finaliza a criação do objeto {@link DocProcess}
	 * definindo os seus {@link Document}s
	 * @return
	 */
	public DocProcess build() {
		
		docProcess.setDocBase( XmlUtil.getDocumentFromStringXml(xmlBase, prefixRemover) );
		docProcess.setDocCompare( XmlUtil.getDocumentFromStringXml(xmlCompare, prefixRemover) );
		
		return docProcess;
		
	}
	
	
}
