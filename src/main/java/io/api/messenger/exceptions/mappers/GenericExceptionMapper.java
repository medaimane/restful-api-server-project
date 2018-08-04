package io.api.messenger.exceptions.mappers;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;

import io.api.messenger.models.errors.MessagesErrors;

// @Provider This generic class is not appropriated because it cache to all exception, 
// we are using Jersy web application exception.
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {
	
	@Override
	@Produces(MediaType.APPLICATION_JSON)
	public Response toResponse(Throwable exception) {
		MessagesErrors messageError = new MessagesErrors(exception.getMessage(), 500, "link in production!");
		return Response.status(Status.INTERNAL_SERVER_ERROR)
					   .entity(messageError)
					   .type(MediaType.APPLICATION_JSON)
				       .build();
	}
}