package io.api.messenger.models.entities;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Comment {

	private Long id;
	private String body;
	private String author;
	private Date created;
	private Date updated;

	public Comment() {
		super();
	}

	public Comment(Long id, String body, String author) {
		this.id = id;
		this.body = body;
		this.author = author;
		this.created = new Date();
		this.updated = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", body=" + body + ", author=" + author + ", created=" + created + ", updated="
				+ updated + "]";
	}

}
