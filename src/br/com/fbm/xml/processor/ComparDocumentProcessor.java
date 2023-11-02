/**
 * Projeto para comparação de Documentos
 * Xmls
 * 
 * Desenvolvido por: Fernando Bino Machado
 * Github: https://github.com/devBino
 * Projeto: https://github.com/devBino/compara_xml_document
 */
package br.com.fbm.xml.processor;

import java.util.function.Consumer;

import br.com.fbm.xml.business.ComparBusiness;
import br.com.fbm.xml.business.bo.ComparBO;
import br.com.fbm.xml.repository.docprocess.DocProcess;

/**
 * Aplica o processamento para comparar dois {@code org.w3c.dom.Document}s
 * @author Fernando Bino Machado
 */
public class ComparDocumentProcessor {

	/**
	 * Se utiliza de {@link ComparBusiness#compararDocuments()}
	 * para aplicar o processamento e comparar os dois {@code org.w3c.dom.Document}
	 * definidos 
	 * @param pDocProcess
	 */
	public static void processarComparacao(final DocProcess pDocProcess) {
		
		final ComparBusiness compar1 = new ComparBusiness()
				.setDocument1( pDocProcess.getDocBase() )
				.setDocument2( pDocProcess.getDocCompare() )
				.validarIgualdade(pDocProcess.isValidarIgualdade())
				.compararDocuments();
		
		final ComparBusiness compar2 = new ComparBusiness()
				.setDocument1( pDocProcess.getDocCompare() )
				.setDocument2( pDocProcess.getDocBase() )
				.validarIgualdade(pDocProcess.isValidarIgualdade())
				.compararDocuments();
		
		System.out.println( "\nResultados de Documento Base X Documento Compare\n" );
		showResults(compar1, 
				pDocProcess.isImprimirTodosResultados());
		
		System.out.println( "\nResultados de Documento Compare X Documento Base\n" );
		showResults(compar2, 
				pDocProcess.isImprimirTodosResultados());
		
	}
	
	/**
	 * Exibe os resultados do processamento
	 * @param pCompar
	 */
	private static void showResults(final ComparBusiness pCompar, 
			final boolean pImprimirTodasComparacoes) {

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
			
			//caso não deva imprimir todas as comparações
			//serão puladas as que deram sucesso
			if( !pImprimirTodasComparacoes && bo.isMatch() ) {
				continue;
			}
			
			//todas as que não deram match
			if( !pImprimirTodasComparacoes && !bo.isMatch() ) {
				cs.accept(bo);
				continue;
			}
			
			//exibe todas as comparações
			cs.accept(bo);
			
		}

	}
	
}
