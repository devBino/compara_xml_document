# Comparador de Xml Documents &#129299;<br>

<p align="justify">Autor: Fernando Bino Machado <br><br>O presente projeto em um comparador de xmls.</p>
<br>
<p align="justify">A utilização é bem simples, basta seguir a sequência propósta abaixo:</p>
<br>

<h2>1 - Criar o trecho de código abaixo no método main da classe App, package application</h2>
<br>

```java
package br.com.fbm.xml.application;

import br.com.fbm.xml.processor.ComparDocumentProcessor;
import br.com.fbm.xml.repository.docprocess.DocProcess;
import br.com.fbm.xml.repository.docprocess.DocProcessBuilder;

public class App {

	public static void main(String[] args) throws Exception {

    //inicia builder padrão para construir um DocProcess
		final DocProcessBuilder docProcessBuilder = new DocProcessBuilder();
		
		//define o xml base para comparação
		docProcessBuilder.setXmlBase("documents/base.xml");
		
		//define o xml que será comparado com xml base
		docProcessBuilder.setXmlCompare("documents/compare.xml");
		
		//define se as tags serão comparadas por igualdade de conteúdo
		//caso omitido, será validada apenas a existência das tags nos dois xmls
		//docProcessBuilder.validarIgualdade();
		
		//define se deverão ser exibidos os resultados de todas as comparações
		//caso omitido, serão impressos apenas as diferenças
		//docProcessBuilder.imprimirTodosResultados();
				
		//caso existam, define os prefixos namespaces a serem removidos das tags
		docProcessBuilder.addPrefixoNamespaceRemover("nfse","nfse1","nfse2");
		
		final DocProcess docProcess = docProcessBuilder.build();

    //processa o objeto DocProcess, realizando as comparações
		ComparDocumentProcessor.processarComparacao(docProcess);
		
	}
	
	
}

```

<br>
<h2>2 - Criar os arquivos xmls</h2>
<br>

<p align="justify">Dentro da pasta documents, nesse projeto, criar os dois arquivos com extensão .xml, com os mesmos nomes definidos no trecho abaixo, dentro do método main:</p>

<br>

```java

    //define o xml base para comparação
		docProcessBuilder.setXmlBase("documents/base.xml");
		
		//define o xml que será comparado com xml base
		docProcessBuilder.setXmlCompare("documents/compare.xml");

```

<br>
<h2>3 - Processando os Xmls</h2>
<br>
<p align="justify">Finalmente, basta executar a aplicação selecionando essa classe App com seu método main.</p>


