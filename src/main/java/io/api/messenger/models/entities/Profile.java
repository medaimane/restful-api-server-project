package io.api.messenger.models.entities;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Profile {

	private Long id;
	private String email;
	private String profileName;
	private String password;
	private Date created;
	private Date modified;
	private Boolean status;

	public Profile() {
		super();
	}

	public Profile(Long id, String profileName, String email, String password, Boolean status) {
		this.id = id;
		this.email = email;
		this.profileName = profileName;
		this.password = password;
		this.created = new Date();
		this.modified = new Date();
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getProfileName() {
		return profileName;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Profile [id=" + id + ", email=" + email + ", profileName=" + profileName + ", password="
				+ password + ", created=" + created + ", modified=" + modified + ", status=" + status + "]";
	}

}