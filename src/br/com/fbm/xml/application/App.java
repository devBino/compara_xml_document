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

	public static void main(String[] args) {
		
		final DocProcess docProcess = new DocProcessBuilder()
				.setXmlBase( getXmlBase() )
				.setXmlCompare( getXmlCompare() )
				.build();
		
		ComparDocumentProcessor.processarComparacao(docProcess);
		
	}
	
	private static String getXmlBase() {
		return "<teste><um><n1>val1</n1></um><dois><n2>val2</n2></dois><tres><n3>val3</n3></tres></teste>";
	}
	
	private static String getXmlCompare() {
		return "<teste><um><n1>val1</n1></um><dois></dois><tres><n3></n3></tres><quatro></quatro></teste>";
	}

}
