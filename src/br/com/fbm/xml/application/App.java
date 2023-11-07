/**
 * Projeto para comparação de Documentos
 * Xmls
 * 
 * Desenvolvido por: Fernando Bino Machado
 * Github: https://github.com/devBino
 * Projeto: https://github.com/devBino/compara_xml_document
 */
package br.com.fbm.xml.application;

import br.com.fbm.xml.business.exception.AppException;
import br.com.fbm.xml.processor.ComparDocumentProcessor;
import br.com.fbm.xml.repository.docprocess.DocProcess;
import br.com.fbm.xml.repository.docprocess.DocProcessBuilder;
import br.com.fbm.xml.repository.type.Erro;

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
	public static void main(String[] args) throws AppException {
		
		try {
		
			final DocProcessBuilder docProcessBuilder = new DocProcessBuilder();
			
			docProcessBuilder.setXmlBase("documents/base.xml");
			docProcessBuilder.setXmlCompare("documents/compare.xml");
			
			docProcessBuilder.addPrefixoNamespaceRemover("nfse","nfse1","nfse2");
			
			final DocProcess docProcess = docProcessBuilder.build();
	
			ComparDocumentProcessor.processarComparacao(docProcess);
			
		}catch(final AppException exception) {
			
			throw exception;
			
		}catch(final Exception exception) {
			
			throw new AppException(Erro.ERRO_DESCONHECIDO);
			
		}
		
				
	}
	
	
}
