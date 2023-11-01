package br.com.fbm.xml.processor;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import br.com.fbm.xml.business.bo.ComparBO;
import br.com.fbm.xml.repository.docprocess.DocProcess;
import br.com.fbm.xml.repository.xml.XmlUtil;

/**
 * TODO BINO Documentar Classe
 */
public class ComparDocumentProcessor {

	public static void processarComparacao(final DocProcess pDocProcess) {
		
		final List<ComparBO> listCompars = new ArrayList<>();
		
		criarComparBOs(pDocProcess.getDocBase().getDocumentElement(), 
				listCompars, pDocProcess.getDocBase());
		
		System.out.println();
		
	}
	
	private static void criarComparBOs(final Element pElem, 
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
	
}
