package br.com.fbm.xml.business;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.ArrayList;
import java.util.Iterator;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import br.com.fbm.xml.business.bo.ComparBO;
import br.com.fbm.xml.repository.docprocess.DocProcess;
import br.com.fbm.xml.repository.xml.XmlUtil;

public class ComparBusiness {

	private List<ComparBO> listCompars;
	private Document document1;
	private Document document2;
	private boolean validarIgualdade;
	
	public ComparBusiness() {
		listCompars = new ArrayList<>();
	}
	
	public ComparBusiness setDocument1(final Document pDocument) {
		document1 = pDocument;
		return this;
	}
	
	public ComparBusiness setDocument2(final Document pDocument) {
		document2 = pDocument;
		return this;
	}
	
	public ComparBusiness validarIgualdade(final boolean pValidar) {
		validarIgualdade = pValidar;
		return this;
	}
	
	public List<ComparBO> getListCompars() {
		return listCompars;
	}
	
	public ComparBusiness buildCompareBusiness() {
		
		criarComparBOs(document1.getDocumentElement(),
				listCompars, document1);
		
		recuperarValoresDocCompare(listCompars, 
				document2);
		
		processNodeMatches();
		
		return this;
		
	}
	
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
