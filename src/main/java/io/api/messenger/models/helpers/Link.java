package io.api.messenger.models.helpers;

public class Link {

	private String href; // url to the resource
	private String rel; // relation

	public Link() {
		super();
	}

	public Link(String href, String rel) {
		this.href = href;
		this.rel = rel;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getRel() {
		return rel;
	}

	public void setRel(String rel) {
		this.rel = rel;
	}

}
