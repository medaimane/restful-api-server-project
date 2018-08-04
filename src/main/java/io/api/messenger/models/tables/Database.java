package io.api.messenger.models.tables;

import java.util.HashMap;
import java.util.Map;

import io.api.messenger.models.entities.Comment;
import io.api.messenger.models.entities.Message;
import io.api.messenger.models.entities.Profile;
import io.api.messenger.models.entities.User;

public class Database {

	private static Map<Long, User> usersTable = new HashMap<>();
	private static Map<String, Profile> profilesTable = new HashMap<>();
	private static Map<Long, Message> messagesTable = new HashMap<>();
	private static Map<Long, Comment> commentsTable = new HashMap<>();

	public static Map<Long, User> getUsersTable() {
		return usersTable;
	}

	public static Map<String, Profile> getProfilesTable() {
		return profilesTable;
	}

	public static Map<Long, Message> getMessagesTable() {
		return messagesTable;
	}

	public static Map<Long, Comment> getCommentsTable() {
		return commentsTable;
	}

}