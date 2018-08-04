package io.api.messenger.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.api.messenger.businessServices.CommentsService;
import io.api.messenger.models.entities.Comment;

@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CommentsResource { // this is a sub resource of a Messages root resources
	
	private CommentsService commentsService = new CommentsService();
	
	@GET
	public List<Comment> getAllComments(@PathParam("messageId") Long messageId) { 
		return this.commentsService.getAllComments(messageId);
	}
	
	@GET
	@Path("{commentId}")
	public Comment getOneComment(@PathParam("messageId") Long messageId, @PathParam("commentId") Long commentId) {
		return this.commentsService.getOneComment(messageId, commentId);
	}
	
	@POST
	public Comment addComment(@PathParam("messageId") Long messageId, Comment comment) {
		return this.commentsService.addComment(messageId, comment);
	}
	
	@PUT
	@Path("{commentId}")
	public Comment updateComment(@PathParam("messageId") Long messageId, @PathParam("commentId") Long commentId, Comment comment) {
		comment.setId(commentId);
		return this.commentsService.updateComment(messageId, comment);
	}
	
	@DELETE
	@Path("{commentId}")
	public void deleteComment(@PathParam("messageId") Long messageId, @PathParam("commentId") Long commentId) {
		this.commentsService.deleteComment(messageId, commentId);
	}
}
