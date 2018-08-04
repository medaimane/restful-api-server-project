package io.api.messenger.models.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import io.api.messenger.models.helpers.Link;

@XmlRootElement
public class Message {

	private Long id;
	private String content;
	private Date created;
	private String author;

	private Map<Long, Comment> comments = new HashMap<>();
	private List<Link> links = new ArrayList<>();

	public Message() {
		super();
	}

	public Message(Message m) {
		super();
		this.id = m.getId();
		this.content = m.getContent();
		this.created = m.getCreated();
		this.author = m.getAuthor();
	}

	public Message(Long id, String content, String author) {
		super();
		this.id = id;
		this.content = content;
		this.created = new Date();
		this.author = author;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@XmlTransient
	public Map<Long, Comment> getComments() {
		return comments;
	}

	public void setComments(Map<Long, Comment> comments) {
		this.comments.putAll(comments);
	}

	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}
	
	public void addLink(String href, String rel) {
		this.links.add(new Link(href, rel));
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", content=" + content + ", created=" + created + ", author=" + author + "]";
	}
}