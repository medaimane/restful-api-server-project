package io.api.messenger.models.errors;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MessagesErrors {

	private String errorMessage;
	private Integer codeMessage;
	private String docsLink;

	public MessagesErrors() {
		super();
	}

	public MessagesErrors(String errorMessage, Integer codeMessage, String docsLink) {
		super();
		this.errorMessage = errorMessage;
		this.codeMessage = codeMessage;
		this.docsLink = docsLink;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Integer getCodeMessage() {
		return codeMessage;
	}

	public void setCodeMessage(Integer codeMessage) {
		this.codeMessage = codeMessage;
	}

	public String getDocsLink() {
		return docsLink;
	}

	public void setDocsLink(String docsLink) {
		this.docsLink = docsLink;
	}

}