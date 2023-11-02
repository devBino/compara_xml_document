package br.com.fbm.xml.processor;

import java.util.function.Consumer;

import br.com.fbm.xml.business.ComparBusiness;
import br.com.fbm.xml.business.bo.ComparBO;
import br.com.fbm.xml.repository.docprocess.DocProcess;

/**
 * TODO BINO Documentar Classe
 */
public class ComparDocumentProcessor {

	public static void processarComparacao(final DocProcess pDocProcess) {
		
		ComparBusiness compar1 = new ComparBusiness()
				.setDocument1( pDocProcess.getDocBase() )
				.setDocument2( pDocProcess.getDocCompare() )
				.validarIgualdade(pDocProcess.isValidarIgualdade())
				.buildCompareBusiness();
		
		ComparBusiness compar2 = new ComparBusiness()
				.setDocument1( pDocProcess.getDocCompare() )
				.setDocument2( pDocProcess.getDocBase() )
				.validarIgualdade(pDocProcess.isValidarIgualdade())
				.buildCompareBusiness();
		
		System.out.println( "\nResultados de Documento Base X Documento Compare\n" );
		showResults(compar1);
		
		System.out.println( "\nResultados de Documento Compare X Documento Base" );
		showResults(compar2);
		
	}
	
	private static void showResults(final ComparBusiness pCompar) {

		Consumer<ComparBO> cs = bo -> {
			
			final StringBuilder linha = new StringBuilder();
			
			linha
				.append( bo.getNodeName() );
			
			if( bo.isTagPai() ) {
				linha.append(" => tag Pai");
			}
			
			linha
				.append("\n")
				.append( bo.getXpath() )
				.append("\nValor DocBase = ")
				.append( bo.getContentNodeDocBase() )
				.append("\nValor DocCompare = ")
				.append( bo.getContentNodeDocCompare() );
			
			if(!bo.isMatch()) {
				linha.append("\n---------------------------------------------------------\n");
				System.err.println( linha.toString() );
				return;
			}
			
			linha.append("\n---------------------------------------------------------\n");
			System.out.println( linha.toString() );
			
		};
		
		for(final ComparBO bo : pCompar.getListCompars()) {
			cs.accept(bo);
		}

	}
	
}
