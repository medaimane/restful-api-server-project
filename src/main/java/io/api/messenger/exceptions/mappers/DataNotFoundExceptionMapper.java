package io.api.messenger.exceptions.mappers;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import io.api.messenger.exceptions.DataNotFoundException;
import io.api.messenger.models.errors.MessagesErrors;

@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException> {

	@Override
	public Response toResponse(DataNotFoundException exception) {
		MessagesErrors messageError = new MessagesErrors(exception.getMessage(), 404, "link in production!");
		return Response.status(Status.NOT_FOUND)
					   .entity(messageError)
				       .build();
	}

}
