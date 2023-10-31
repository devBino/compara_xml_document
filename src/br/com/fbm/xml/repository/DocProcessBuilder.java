package br.com.fbm.xml.repository;

import org.w3c.dom.Document;

public class DocProcessBuilder {

	private String xmlBase, xmlCompar;
	
	private Document docBase, docCompar;
	
		
	public DocProcessBuilder setXmlBase(String xmlBase) {
		this.xmlBase = xmlBase;
		return this;
	}
	
	public DocProcessBuilder setXmlCompar(String xmlCompar) {
		this.xmlCompar = xmlCompar;
		return this;
	}
	
	public DocProcessBuilder build() {
		return this;
	}
	
	public Document getDocBase() {
		return docBase;
	}
	
	public Document getDocCompar() {
		return docCompar;
	}
	
	
}
