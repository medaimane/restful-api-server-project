package io.api.messenger.businessServices;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import io.api.messenger.models.entities.Comment;
import io.api.messenger.models.entities.Message;
import io.api.messenger.models.errors.MessagesErrors;
import io.api.messenger.models.tables.Database;

public class CommentsService { // B.Service
	private Map<Long, Message> messages = Database.getMessagesTable();

	public List<Comment> getAllComments(Long messageId) {
		Map<Long, Comment> comments = this.messages.get(messageId).getComments();
		return new ArrayList<Comment>(comments.values());
	}
	
	// Using the Web Application Exception to cache exceptions in this method 
	public Comment getOneComment(Long messageId, Long commentId) {
		Message message = this.messages.get(messageId);
		// Create error instance :
		MessagesErrors messageError = new MessagesErrors("Not found id", 404, "link in production!");
		// Define a response instance :
		Response response = Response.status(Status.NOT_FOUND)
									.entity(messageError)
									.type(MediaType.APPLICATION_JSON)
									.build();
		if (message == null) {
			throw new WebApplicationException(response);
		}
		Map<Long, Comment> comments = message.getComments();
		Comment comment = comments.get(commentId);
		if (comment == null) {
			throw new WebApplicationException(response);
		}
		return comment;
	}

	public Comment addComment(Long messageId, Comment comment) {
		Map<Long, Comment> comments = this.messages.get(messageId).getComments();
		comment.setId(comments.size() + 1L);
		comment.setCreated(new Date());
		comment.setUpdated(new Date());
		comments.put(comment.getId(), comment);
		this.messages.get(messageId).setComments(comments);
		return comment;
	}

	public Comment updateComment(Long messageId, Comment comment) {
		Map<Long, Comment> comments = this.messages.get(messageId).getComments();
		if (comment.getId() <= 0) {
			return null;
		}
		comment.setUpdated(new Date());
		comments.put(comment.getId(), comment);
		this.messages.get(messageId).setComments(comments);
		return comment;
	}

	public Comment deleteComment(Long messageId, Long commentId) {
		Map<Long, Comment> comments = this.messages.get(messageId).getComments();
		return comments.remove(commentId);
	}
}
