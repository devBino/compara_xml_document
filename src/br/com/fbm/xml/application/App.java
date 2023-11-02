/**
 * Projeto para comparação de Documentos
 * Xmls
 * 
 * Desenvolvido por: Fernando Bino Machado
 * Github: https://github.com/devBino
 * Projeto: https://github.com/devBino/compara_xml_document
 */
package br.com.fbm.xml.application;

import br.com.fbm.xml.processor.ComparDocumentProcessor;
import br.com.fbm.xml.repository.docprocess.DocProcess;
import br.com.fbm.xml.repository.docprocess.DocProcessBuilder;

/**
 * {@code App} tem responsabilidade de iniciar a
 * aplicação e rodar alguns métodos de exemplo 
 * de conversão de {@code org.w3c.dom.Document}
 * 
 * @author Fernando Bino Machado
 */
public class App {

	/**
	 * Exemplo de uso
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		
		final DocProcessBuilder docProcessBuilder = new DocProcessBuilder();
		
		//define o xml base para comparação
		docProcessBuilder.setXmlBase("documents/base.xml");
		
		//define o xml que será comparado com xml base
		docProcessBuilder.setXmlCompare("documents/compare.xml");
		
		//define se as tags serão comparadas por igualdade de conteúdo
		//docProcessBuilder.validarIgualdade();
				
		//caso existam, define os prefixos namespaces a serem removidos das tags
		//docProcessBuilder.addPrefixoNamespaceRemover("nfse","nfse1","nfse2");
		
		final DocProcess docProcess = docProcessBuilder.build();
		
		ComparDocumentProcessor.processarComparacao(docProcess);
		
	}
	
	
}
