package br.com.fbm.xml.repository.xml;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 * {@code XmlUtil} fornece métodos úteis
 * para manipulação de {@link Document}s representando
 * Xmls
 * @author Fernando Bino Machado
 */
public class XmlUtil {

	public static String getXpathNode(final Node pNode) {
		
		if( pNode == null ) {
			return "";
		}
		
		final Node parent = pNode.getParentNode();
		
		if(parent == null) {
			return "/";
		}
		
		final NodeList childNodesParent = parent.getChildNodes();
		
		int index = 1;
		
		for(int i=0; i<childNodesParent.getLength(); i++) {
			
			final Node sibling = childNodesParent.item(i);
			
			if( sibling == pNode ) {
				break;
			}
			
			if( sibling.getNodeType() == Node.ELEMENT_NODE
					&& sibling.getNodeName().equals(pNode.getNodeName())) {
				index++;
			}
			
		}
		
		return getXpathNode(parent) + "/" + pNode.getNodeName() + "[" + index + "]";
		
	}
	
	public static String getNodeValueByXpath(final String pXpath, 
			final Document pDocument) {

		try {
		
			XPathFactory xPathFactory = XPathFactory.newDefaultInstance();
			XPath xPath = xPathFactory.newXPath();
			XPathExpression exp = xPath.compile(pXpath);
			
			return (String) exp.evaluate(pDocument, 
					XPathConstants.STRING);
			
		}catch(final Exception exception) {
			//TODO BINO Definir tratativas para exception
			exception.printStackTrace();
		}
		
		return "";
		
	}
	
	/**
	 * Recebe um {@link NodeList} e faz uma contagem
	 * para saber quantos nodes são do tipo {@link Node#ELEMENT_NODE}
	 * @param pNodeList
	 * @return
	 */
	public static int contarNodeElements(final NodeList pNodeList) {
		
		int qtdeNdElem = 0;
		
		for(int i=0; i<pNodeList.getLength(); i++) {
			if(pNodeList.item(i).getNodeType() != Node.ELEMENT_NODE) {
				continue;
			}
			qtdeNdElem++;
		}
		
		return qtdeNdElem;
		
	}
	
	/**
	 * Recebe uma string representando um xml e 
	 * retorna um {@link Document}
	 * @param pXml
	 * @return
	 */
	public static Document getDocumentFromStringXml(final String pXml) {
		
		Document document = null;
		
		try {
			
			StringReader reader = new StringReader(pXml.toString());
			
			DocumentBuilderFactory factory = criarDocumentBuilderFactory();
			document = factory.newDocumentBuilder().parse(new InputSource(reader));
			
		}catch(final Exception exception) {
			//TODO BINO Definir tratativas para exception
			exception.printStackTrace();
		}
		
		return document;
		
	}
	
	/**
	 * Cria instância segura de {@link DocumentBuilderFactory}
	 * @return
	 */
	public static DocumentBuilderFactory criarDocumentBuilderFactory() {

	    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance(); 
	       
	    dbf.setAttribute("http://javax.xml.XMLConstants/property/accessExternalDTD", "");
	    dbf.setAttribute("http://javax.xml.XMLConstants/property/accessExternalSchema", "");

	    habilitaXmlFeature(dbf, "http://apache.org/xml/features/disallow-doctype-decl", true);
	    habilitaXmlFeature(dbf, "http://javax.xml.XMLConstants/feature/secure-processing", true);
	    habilitaXmlFeature(dbf, "http://xml.org/sax/features/external-general-entities", false);
	    habilitaXmlFeature(dbf, "http://xml.org/sax/features/external-parameter-entities", false); 
	    habilitaXmlFeature(dbf, "http://apache.org/xml/features/nonvalidating/load-external-dtd", false);

	    dbf.setXIncludeAware(false);
	    dbf.setExpandEntityReferences(false);
	 
	    return dbf;

	}
	
	/**
	 * Habilita ou desabilita features de uma instância de {@link DocumentBuilderFactory}
	 * de acordo com parâmetros recebidos
	 * @param pDbf
	 * @param pFeature
	 * @param pEnable
	 */
	private static void habilitaXmlFeature(DocumentBuilderFactory pDbf, 
			String pFeature, boolean pEnable) {
		try {
			pDbf.setFeature(pFeature, pEnable);
		} catch (final Exception exception) {
			//TODO BINO Definir tratativas para exception
			exception.printStackTrace();
		}
	}
	
}
