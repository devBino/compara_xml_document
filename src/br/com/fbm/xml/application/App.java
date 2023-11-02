package br.com.fbm.xml.application;

import org.w3c.dom.Document;

import br.com.fbm.xml.processor.ComparDocumentProcessor;
import br.com.fbm.xml.repository.docprocess.DocProcess;
import br.com.fbm.xml.repository.docprocess.DocProcessBuilder;

/**
 * {@code App} tem responsabilidade de iniciar a
 * aplicação e rodar alguns métodos de exemplo 
 * de conversão de {@link Document}
 * 
 * @author Fernando Bino Machado
 */
public class App {

	public static void main(String[] args) throws Exception {
		
		final DocProcess docProcess = new DocProcessBuilder()
				.setXmlBase("documents/base.xml")
				.setXmlCompare("documents/compare.xml")
				.addPrefixoNamespaceRemover("nfse","nfse1","nfse2")
				.build();
		
		ComparDocumentProcessor.processarComparacao(docProcess);
		
	}

}
