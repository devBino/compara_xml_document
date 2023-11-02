/**
 * Projeto para comparação de Documentos
 * Xmls
 * 
 * Desenvolvido por: Fernando Bino Machado
 * Github: https://github.com/devBino
 * Projeto: https://github.com/devBino/compara_xml_document
 */
package br.com.fbm.xml.business;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import br.com.fbm.xml.business.bo.ComparBO;
import br.com.fbm.xml.processor.ComparDocumentProcessor;
import br.com.fbm.xml.repository.xml.XmlUtil;

/**
 * {@code ComparBusiness} tem responsabilidade de fazer o pré processamento dos
 * {@code Document}s e auxiliar durante processamento em 
 * {@link ComparDocumentProcessor#processarComparacao(br.com.fbm.xml.repository.docprocess.DocProcess)}
 * @author Fernando Bino Machado
 */
public class ComparBusiness {

	/**
	 * Define uma lista de {@link ComparBO}s a serem 
	 * processados e exibidos em {@link ComparDocumentProcessor#showResults}
	 */
	private List<ComparBO> listCompars;
	
	/**
	 * Define o primeiro {@code Document} envolvido no processamento
	 * da comparação entre dois {@code Document}s
	 */
	private Document document1;
	
	/**
	 * Define o primeiro {@code Document} envolvido no processamento
	 * da comparação entre dois {@code Document}s
	 */
	private Document document2;
	
	/**
	 * Define se durante a comparação, os {@code Node}s
	 * serão validaros por igualdade de conteúdo
	 */
	private boolean validarIgualdade;
	
	/**
	 * Cria instância de {@code ComparBusiness}
	 */
	public ComparBusiness() {
		listCompars = new ArrayList<>();
	}
	
	/**
	 * Define valor para o atributo {@link ComparBusiness#document1}
	 * @param pDocument
	 * @return
	 */
	public ComparBusiness setDocument1(final Document pDocument) {
		document1 = pDocument;
		return this;
	}
	
	/**
	 * Define valor para o atributo {@link ComparBusiness#document2}
	 * @param pDocument
	 * @return
	 */
	public ComparBusiness setDocument2(final Document pDocument) {
		document2 = pDocument;
		return this;
	}
	
	/**
	 * Define valor para o atributo {@link ComparBusiness#validarIgualdade}
	 * @param pValidar
	 * @return
	 */
	public ComparBusiness validarIgualdade(final boolean pValidar) {
		validarIgualdade = pValidar;
		return this;
	}
	
	/**
	 * Recupera a lista no atributo {@link ComparBusiness#listCompars}
	 * @return
	 */
	public List<ComparBO> getListCompars() {
		return listCompars;
	}
	
	/**
	 * Realiza a comparação entre dois {@code Document}s
	 * @return
	 */
	public ComparBusiness compararDocuments() {
		
		criarComparBOs(document1.getDocumentElement(),
				listCompars, document1);
		
		recuperarValoresDocCompare(listCompars, 
				document2);
		
		processNodeMatches();
		
		return this;
		
	}
	
	/**
	 * Cria uma lista de {@link ComparBO}s com todos os {@code Node}s
	 * do {@code Document} base para comparação
	 * @param pElem
	 * @param pListCompars
	 * @param pDocument
	 */
	private void criarComparBOs(final Element pElem, 
			final List<ComparBO> pListCompars, final Document pDocument) {
	
		int qtdeNdElem = XmlUtil.contarNodeElements( pElem.getChildNodes() );
		
		if( qtdeNdElem == 0 ) {
			return;
		}
		
		final NodeList childNodes = pElem.getChildNodes();
		
		for(int i=0; i<childNodes.getLength(); i++) {
			
			if( childNodes.item(i).getNodeType() != Node.ELEMENT_NODE ) {
				continue;
			}
			
			Element elFilho = (Element) childNodes.item(i);
			
			int qtdeNdsNodeFilho = XmlUtil.contarNodeElements( elFilho.getChildNodes() );
			
			ComparBO bo = new ComparBO();
			
			bo.setNodeName( elFilho.getNodeName() );
			bo.setXpath( XmlUtil.getXpathNode( childNodes.item(i) ) );
			bo.setContentNodeDocBase( XmlUtil.getNodeValueByXpath(
					bo.getXpath(), pDocument) );
			
			if( qtdeNdsNodeFilho > 0 ) {
				bo.setTagPai(true);
				bo.setContentNodeDocBase(null);
			}
			
			pListCompars.add(bo);
			
			criarComparBOs(elFilho, pListCompars, pDocument);
			
		}
		
	}
	
	/**
	 * Percorre a lista criada em {@link ComparBusiness#criarComparBOs(Element, List, Document)}
	 * adicionando os valores do segundo {@code Document} para comparação nos
	 * {@link ComparBO}s
	 * @param pListCompars
	 * @param pDocCompare
	 */
	private void recuperarValoresDocCompare(final List<ComparBO> pListCompars, 
			final Document pDocCompare) {
		
		for(final ComparBO bo : pListCompars) {
			
			if( bo.isTagPai() ) {
				bo.setContentNodeDocCompare(null);
				continue;
			}
			
			final String valorNodeDocCompare = XmlUtil
					.getNodeValueByXpath(bo.getXpath(), pDocCompare);
			
			bo.setContentNodeDocCompare( valorNodeDocCompare );
			
		}
		
	}
	
	/**
	 * Processa a lista de {@link ComparBO}s previamente criada em
	 * 
	 * {@link ComparBusiness#criarComparBOs(Element, List, Document)}
	 * 
	 * e
	 * 
	 * {@link ComparBusiness#recuperarValoresDocCompare(List, Document)}
	 * 
	 * definindo status para
	 * 
	 * {@link ComparBO#isMatch()}
	 * 
	 */
	private void processNodeMatches() {
		
		final Predicate<ComparBO> nodeMatches = bo -> {
			
			if( bo.getContentNodeDocBase() == null
					&& bo.getContentNodeDocCompare() == null ) {
				return true;
			}
			
			if( !bo.getContentNodeDocBase().isEmpty()
					&& !bo.getContentNodeDocCompare().isEmpty()
					&& !validarIgualdade) {
				return true;
			}
			
			if( !bo.getContentNodeDocBase().isEmpty()
					&& !bo.getContentNodeDocCompare().isEmpty()
					&& validarIgualdade
					&& bo.getContentNodeDocBase().equals( bo.getContentNodeDocCompare() ) ) {
				return true;
			}
			
			return false;
			
		};
		
		Iterator<ComparBO> itCompare = listCompars.iterator();
		
		while( itCompare.hasNext() ) {
			
			final ComparBO bo = itCompare.next();
			bo.setMatch( nodeMatches.test(bo) );
			
		}
		
	}
	
}
