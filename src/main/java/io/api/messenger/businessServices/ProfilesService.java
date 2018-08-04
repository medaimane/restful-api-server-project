package io.api.messenger.businessServices;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.api.messenger.models.entities.Profile;
import io.api.messenger.models.tables.Database;

public class ProfilesService {

	private Map<String, Profile> profiles = Database.getProfilesTable();

	public ProfilesService() {
		super();
		this.profiles.put("author4", new Profile(1L, "Author4", "author4@gmail.com","secret", true));
		this.profiles.put("author2", new Profile(3L, "Author2", "author2@gmail.com","secret", true));
		this.profiles.put("author5", new Profile(4L, "Author5", "author5@gmail.com","secret", true));
		this.profiles.put("author3", new Profile(5L, "Author3", "author3@gmail.com","secret", true));
		this.profiles.put("author1", new Profile(2L, "Author1", "author1@gmail.com","secret", true));
	}

	public List<Profile> select() {
		return new ArrayList<Profile>(this.profiles.values());
	}

	public Profile selectWhere(String profileName) {
		return this.profiles.get(profileName);
	}

	public Profile insert(Profile p) {
		p.setId(this.profiles.size() + 1L);
		this.profiles.put(p.getProfileName(), p);
		return p;
	}

	public Profile update(Profile p) {
		if (p.getProfileName().isEmpty()) { return null; }
		this.profiles.put(p.getProfileName(), p);
		return p;
	}

	public Profile delete(String profileName) {
		return this.profiles.remove(profileName);
	}
}
