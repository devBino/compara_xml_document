/**
 * Projeto para comparação de Documentos
 * Xmls
 * 
 * Desenvolvido por: Fernando Bino Machado
 * Github: https://github.com/devBino
 * Projeto: https://github.com/devBino/compara_xml_document
 */
package br.com.fbm.xml.repository.type;

/**
 * {@code Erro} define os erros que podem ocorrer
 * durante execução da aplicação
 * @author Fernando Bino Machado
 */
public enum Erro {

	/**
	 * Itens que definem os tipos de erro em {@code Erro}
	 */
	ERRO_DESCONHECIDO( (byte)1, "Erro desconhecido. " ),
	ERRO_FILE_XML_NOT_FOUND( (byte)2, "Arquivo xml para comparação não encontrado. " );
	
	/**
	 * Define o código do erro
	 */
	byte codigo;
	
	/**
	 * Define a mensagem personalizada do erro
	 */
	String mensagem;
	
	/**
	 * Recebe parâmetros e cria instância para o tipo
	 * de {@code Erro} 
	 * @param pCodigo
	 * @param pMensagem
	 */
	Erro(final byte pCodigo, final String pMensagem) {
		codigo = pCodigo;
		mensagem = pMensagem;
	}
	
	/**
	 * Retorna o valor do atributo {@link Erro#codigo}
	 * @return
	 */
	public byte getCodigo() {
		return codigo;
	}
	
	/**
	 * Retorna o valor do atributo {@link Erro#mensagem}
	 * @return
	 */
	public String getMensagem() {
		return mensagem;
	}
	
	/**
	 * Recebe {@code Throwable} e monta uma mensagem
	 * personalizada com {@link Erro@#codigo} e {@link Erro#mensagem}
	 * @param pThrowable
	 * @return
	 */
	public String compilarMensagemException(final Throwable pThrowable) {
		
		return new StringBuilder()
				.append("[")
				.append(codigo)
				.append("] - ")
				.append(mensagem)
				.append("\n")
				.append(pThrowable.getMessage())
				.toString();
		
	}
	
	
}
