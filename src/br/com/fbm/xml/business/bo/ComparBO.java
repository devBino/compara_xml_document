package br.com.fbm.xml.business.bo;

public class ComparBO {

	private String xpath;
	private String nodeName;
	private String content;
	private String nameDocBase;
	private String nameDocCompare;
	private boolean match;
	
	public ComparBO() {}
	
	public String getXpath() {
		return xpath;
	}
	
	public void setXpath(String xpath) {
		this.xpath = xpath;
	}
	
	public String getNodeName() {
		return nodeName;
	}
	
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	public String getNameDocBase() {
		return nameDocBase;
	}
	
	public void setNameDocBase(String nameDocBase) {
		this.nameDocBase = nameDocBase;
	}
	public String getNameDocCompare() {
		return nameDocCompare;
	}
	
	public void setNameDocCompare(String nameDocCompare) {
		this.nameDocCompare = nameDocCompare;
	}
	
	public void setMatch(boolean match) {
		this.match = match;
	}
	
	public boolean isMatch() {
		return match;
	}
	
}
