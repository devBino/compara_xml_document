/**
 * Projeto para comparação de Documentos
 * Xmls
 * 
 * Desenvolvido por: Fernando Bino Machado
 * Github: https://github.com/devBino
 * Projeto: https://github.com/devBino/compara_xml_document
 */
package br.com.fbm.xml.repository.xml;

import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

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
 * Xmls, além de {@code String}s que também representão xmls
 * @author Fernando Bino Machado
 */
public class XmlUtil {

	/**
	 * Recupera o conteúdo xml de um arquivo
	 * @param pPath
	 * @return
	 * @throws Exception
	 */
	public static String getXmlArquivo(final String pPath) throws Exception {
		
		final Path path = Paths.get(pPath);
		
		String xml = "";
		
		try {
		
			xml = Files
					.readAllLines(path)
					.stream()
					.collect(Collectors.joining("\n"));
			
		}catch(final Exception exception) {
			throw new Exception("O arquivo base deve estar dentro do folder " + pPath);
		}
		
		return xml;
		
	}

	/**
	 * Recupera o xPath de um {@code Node}
	 * @param pNode
	 * @return
	 */
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
	
	/**
	 * Recupera o valor de um {@code Node} através do seu 
	 * {@code String} xPath dentro de um {@code Document}
	 * @param pXpath
	 * @param pDocument
	 * @return
	 */
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
	 * @param pListaPrefixos
	 * @return
	 */
	public static Document getDocumentFromStringXml(final String pXml,
			final Set<String> pListaPrefixos) {
		
		Document document = null;
		
		try {
			
			final String xmlTratado = removerPrefixNamespacesXml(pXml, 
					pListaPrefixos);
			
			final StringReader reader = new StringReader(xmlTratado);
			
			DocumentBuilderFactory factory = criarDocumentBuilderFactory();
			document = factory.newDocumentBuilder().parse(new InputSource(reader));
			
		}catch(final Exception exception) {
			//TODO BINO Definir tratativas para exception
			exception.printStackTrace();
		}
		
		return document;
		
	}
	
	/**
	 * Remove os prefixos das tags de um {@code String} xml
	 * @param pXmlOriginal
	 * @param pPrefix
	 * @return
	 */
	public static String removerPrefixNamespacesXml(final String pXmlOriginal, final Set<String> pPrefix) {
		
		final BiFunction<String, String, String> fnRemovePrefix = (pfx, xml) -> {
			return xml.replace(pfx.concat(":"), "");
		};
		
		StringBuilder bdXml = new StringBuilder( pXmlOriginal );
		
		Iterator<String> itPrefix = pPrefix.iterator();
		
		while( itPrefix.hasNext() ) {
			
			String xmlTratado = fnRemovePrefix.apply(itPrefix.next(), 
					bdXml.toString());
			
			bdXml.delete(0, bdXml.length());
			bdXml.append(xmlTratado);
			
		}
		
		return bdXml.toString();
		
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
