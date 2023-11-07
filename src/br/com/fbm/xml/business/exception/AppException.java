/**
 * Projeto para comparação de Documentos
 * Xmls
 * 
 * Desenvolvido por: Fernando Bino Machado
 * Github: https://github.com/devBino
 * Projeto: https://github.com/devBino/compara_xml_document
 */
package br.com.fbm.xml.business.exception;

import br.com.fbm.xml.repository.type.Erro;

/**
 * {@code AppException} trata Exceptions personalizadas
 * durante execução da aplicação
 * @author Fernando Bino Machado
 */
public class AppException extends Exception {

	private static final long serialVersionUID = -2868155810398692988L;

	/**
	 * Define um tipo de {@code Erro}
	 */
	Erro erro;
	
	/**
	 * Define uma causa para essa {@code AppException}
	 */
	Throwable throwable;
	
	/**
	 * Recebe um {@code Erro} e cria uma nova {@code AppException}
	 * @param pErro
	 */
	public AppException(final Erro pErro) {
		erro = pErro;
		throwable = new Throwable(Erro.ERRO_DESCONHECIDO.getMensagem());
		super.initCause(throwable);
	}
	
	/**
	 * Recebe um {@code Erro}, um {@code Throwable}
	 * e cria uma nova {@code AppException}
	 * @param pErro
	 * @param pThrowable
	 */
	public AppException(final Erro pErro, final Throwable pThrowable) {
		erro = pErro;
		throwable = pThrowable;
		super.initCause(throwable);
	}
	
	@Override
	public String getMessage() {
		return erro.compilarMensagemException(throwable);
	}
	
}
