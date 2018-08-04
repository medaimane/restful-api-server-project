package io.api.messenger.resources;

import java.net.URI;
import java.util.Date;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import io.api.messenger.businessServices.MessagesService;
import io.api.messenger.models.entities.Message;
import io.api.messenger.resources.beans.MessageFilterBean;

@Path("/messages")
//Content Negotiation Concept : to accept the "Content-Type Header" parameter of a request object,
// In this case a client tell server with any format he sending the content in a request object. view in @Consumes
//@Consumes(value = {MediaType.APPLICATION_JSON, MediaType.TEXT_XML, MediaType.APPLICATION_XML} )
@Consumes(MediaType.APPLICATION_JSON)
//Content Negotiation Concept : to accept the "Accept Header" parameter of a request object,
//this mean we accept lot of media type of data wish client asking for it's. view in @Produces
//@Produces(value = {MediaType.APPLICATION_JSON, MediaType.TEXT_XML, MediaType.APPLICATION_XML} )
@Produces(MediaType.APPLICATION_JSON)
public class MessagesResource { // this is a root resource .
	private MessagesService messagesService = new MessagesService();

	@GET
	@Produces(MediaType.APPLICATION_JSON) // Accept -> application/json
	// public List<Message> getAllMessages(@QueryParam("year") int year,
	// @QueryParam("start") int start, @QueryParam("size") int size) {
	// with bean filter annotation :
	public List<Message> getAllMessagesInJson(@BeanParam MessageFilterBean mBean) {
		if (mBean.getYear() > 0 && mBean.getStart() == 0 && mBean.getSize() == 0) {
			return this.messagesService.getAllByYear(mBean.getYear());
		}
		if (mBean.getYear() > 0 && mBean.getStart() >= 0 && mBean.getSize() > 0) {
			return this.messagesService.getAllByYearPaginated(mBean.getYear(), mBean.getStart(), mBean.getSize());
		}
		if (mBean.getStart() >= 0 && mBean.getSize() > 0) {
			return this.messagesService.getAllByPaginated(mBean.getStart(), mBean.getSize());
		}
		return this.messagesService.getAll();
	}
	
	@GET
	@Produces(MediaType.TEXT_XML) // Accept -> text/xml
	// public List<Message> getAllMessages(@QueryParam("year") int year,
	// @QueryParam("start") int start, @QueryParam("size") int size) {
	// with bean filter annotation :
	public List<Message> getAllMessagesInXml(@BeanParam MessageFilterBean mBean) {
		if (mBean.getYear() > 0 && mBean.getStart() == 0 && mBean.getSize() == 0) {
			return this.messagesService.getAllByYear(mBean.getYear());
		}
		if (mBean.getYear() > 0 && mBean.getStart() >= 0 && mBean.getSize() > 0) {
			return this.messagesService.getAllByYearPaginated(mBean.getYear(), mBean.getStart(), mBean.getSize());
		}
		if (mBean.getStart() >= 0 && mBean.getSize() > 0) {
			return this.messagesService.getAllByPaginated(mBean.getStart(), mBean.getSize());
		}
		return this.messagesService.getAll();
	}

	@GET
	@Path("/{messageId}")
	// Implementing HATEOAS Concept for this method:
	public Message getOneMessage(@BeanParam MessageFilterBean mBean) { // with @PathParam("id") Long id !
		Message m = this.messagesService.getOne(mBean.getId());
		m.addLink(getUriForSelf(mBean, m), "self");
		m.addLink(getUriForHome(mBean, m), "home");
		m.addLink(getUriForProfiles(mBean, m), "profile");
		m.addLink(getUriForComments(mBean, m), "comments");
		return m;
	}
	
	private String getUriForHome(MessageFilterBean mBean, Message m) {
		return mBean.getUriInfo()
				.getAbsolutePathBuilder()
				.build()
				.toString();
	}

	// build URI for a link to messages/{messageId}/comments :
	private String getUriForComments(MessageFilterBean mBean, Message m) {
		return mBean.getUriInfo()
				.getAbsolutePathBuilder()
				.path(MessagesResource.class)
				.path(MessagesResource.class, "goToCommentsResource")
				.path(CommentsResource.class)
				.resolveTemplate("messageId", m.getId())
				.build()
				.toString();
	}

	// build URI for a link to messages/{messageId} :
	private String getUriForSelf(MessageFilterBean mBean, Message m) {
		return mBean.getUriInfo()
				.getAbsolutePathBuilder()
				.path(MessagesResource.class)
				.path(Long.toString(m.getId()))
				.build()
				.toString();
	}

	// build URI for a link to profiles/{profileName} :
	private String getUriForProfiles(MessageFilterBean mBean, Message m) {
		return mBean.getUriInfo()
				.getAbsolutePathBuilder()
				.path(ProfilesResource.class)
				.path(m.getAuthor())
				.build()
				.toString();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	// Creating and returning
	// a new instance of
	// Response object with
	// all data.
	public Response addJsonMessage(Message m, @BeanParam MessageFilterBean mBean) {
		m.setCreated(new Date());
		m = this.messagesService.addMessage(m);
		String newId = String.valueOf(m.getId());
		URI uri = mBean.getUriInfo().getAbsolutePathBuilder().path(newId).build();
		return Response.created(uri).entity(m).build();
		/*
		 * return Response.status(Status.CREATED) .entity(newMessage) .build(); //
		 * return newMessage;
		 */
	}
	
	@POST
	@Consumes(MediaType.TEXT_XML)
	// Creating and returning
	// a new instance of
	// Response object with
	// all data.
	public Response addXmlMessage(Message m, @BeanParam MessageFilterBean mBean) {
		m.setCreated(new Date());
		m = this.messagesService.addMessage(m);
		String newId = String.valueOf(m.getId());
		URI uri = mBean.getUriInfo().getAbsolutePathBuilder().path(newId).build();
		return Response.created(uri).entity(m).build();
		/*
		 * return Response.status(Status.CREATED) .entity(newMessage) .build(); //
		 * return newMessage;
		 */
	}

	@PUT
	@Path("{messageId}")
	public Response updateMessage(Message m, @BeanParam MessageFilterBean mBean) {
		m.setId(mBean.getId());
		m = this.messagesService.updateMessage(m);
		return Response.status(Status.OK).entity(m).build();
		// return m;
	}

	@DELETE
	@Path("/{messageId}")
	public void deleteMessage(@BeanParam MessageFilterBean mBean) {
		this.messagesService.removeMessage(mBean.getId());
	}

	@Path("/{messageId}/comments")
	// Change the path and all Operations to the comments resource
	// class
	public CommentsResource goToCommentsResource() {
		return new CommentsResource();
	}

}