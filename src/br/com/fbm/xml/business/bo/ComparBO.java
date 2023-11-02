/**
 * Projeto para comparação de Documentos
 * Xmls
 * 
 * Desenvolvido por: Fernando Bino Machado
 * Github: https://github.com/devBino
 * Projeto: https://github.com/devBino/compara_xml_document
 */
package br.com.fbm.xml.business.bo;

/**
 * {@code ComparBO} agrupa atributos para realização
 * de comparação de objetos do tipo {@code org.w3c.dom.Document} 
 * que representam os documentos de xml, um sendo 
 * documento base e outro destino de comparação.
 * @author Fernando Bino Machado
 */
public class ComparBO {

	/**
	 * Define o xpath da tag representada pelo 
	 * {@code org.w3c.dom.Node} dentro dos documentos de comparação
	 */
	private String xpath;
	
	/**
	 * Nome do {@code org.w3c.dom.Node} de comparação
	 */
	private String nodeName;
	
	/**
	 * Conteúdo do Node no {@code org.w3c.dom.Document} base
	 * para comparação
	 */
	private String contentNodeDocBase;
	
	/**
	 * Conteúdo do Node no {@code org.w3c.dom.Document} destino
	 * de comparação
	 */
	private String contentNodeDocCompare;
	
	/**
	 * Define se o node no {@code org.w3c.dom.Document} 
	 * é um node que possui elementos filhos
	 */
	private boolean tagPai;
	
	/**
	 * Atributo determina se houve match entre os dois elementos
	 */
	private boolean match;
	
	/**
	 * Cria nova instância de {@code ComparBO}
	 */
	public ComparBO() {}
	
	/**
	 * Recupera valor do atributo {@link ComparBO#xpath}
	 * @return
	 */
	public String getXpath() {
		return xpath;
	}
	
	/**
	 * Define valor para o atributo {@link ComparBO#xpath}
	 * @param xpath
	 */
	public void setXpath(String xpath) {
		this.xpath = xpath;
	}
	
	/**
	 * Recupera valor do atributo {@link ComparBO#nodeName}
	 * @return
	 */
	public String getNodeName() {
		return nodeName;
	}
	
	/**
	 * Define valor para o atributo {@link ComparBO#nodeName}
	 * @param nodeName
	 */
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	
	/**
	 * Recupera valor do atributo {@link ComparBO#contentNodeDocBase}
	 * @return
	 */
	public String getContentNodeDocBase() {
		return contentNodeDocBase;
	}
	
	/**
	 * Define valor para o atributo {@link ComparBO#contentNodeDocBase}
	 * @param contentNodeDocBase
	 */
	public void setContentNodeDocBase(String contentNodeDocBase) {
		this.contentNodeDocBase = contentNodeDocBase;
	}
	
	/**
	 * Recupera valor do atributo {@link ComparBO#contentNodeDocCompare}
	 * @return
	 */
	public String getContentNodeDocCompare() {
		return contentNodeDocCompare;
	}
	
	/**
	 * Define valor para o atributo {@link ComparBO#contentNodeDocCompare}
	 * @param contentNodeDocCompare
	 */
	public void setContentNodeDocCompare(String contentNodeDocCompare) {
		this.contentNodeDocCompare = contentNodeDocCompare;
	}
	
	/**
	 * Recupera valor do atributo {@link ComparBO#tagPai}
	 * @return
	 */
	public void setTagPai(boolean tagPai) {
		this.tagPai = tagPai;
	}
	
	/**
	 * Recupera valor do atributo {@link ComparBO#tagPai}
	 * @return
	 */
	public boolean isTagPai() {
		return tagPai;
	}
	
	/**
	 * Define valor para o atributo {@link ComparBO#match}
	 * @param match
	 */
	public void setMatch(boolean match) {
		this.match = match;
	}
	
	/**
	 * Recupera valor do atributo {@link ComparBO#match}
	 * @return
	 */
	public boolean isMatch() {
		return match;
	}
	
	@Override
	public String toString() {
		
		return new StringBuilder()
				.append("ComparBO[")
				.append("\n\tnodeName=")
				.append(nodeName)
				.append(",\n\txpath=")
				.append(xpath)
				.append(",\n\tcontentNodeDocBase=")
				.append(contentNodeDocBase)
				.append(",\n\tcontentNodeDocCompare=")
				.append(contentNodeDocCompare)
				.append(",\n\ttagPai=")
				.append(tagPai)
				.append(",\n\tmatch=")
				.append(match)
				.append("\n]")
				.toString();
		
	}
	
}
