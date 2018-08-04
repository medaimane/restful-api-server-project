package io.api.messenger.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("/demo")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class InjectDemoResources {
	
	// Part 1 : Parameters Annotations. 
	@GET
	@Path("matrixParams") // using the ; in place of ?.
	public String test1ParamsAnnotations(@MatrixParam("matrix") String matrix) {
		return "Matrix Param tested with : " + matrix;
	}
	@GET
	@Path("headerParams") // using customs header parameters to send meta-data with request 
						  // like authentications, Sessions with his tokens values.
	public String test2ParamsAnnotations(@HeaderParam("headerAuth") String headerAuth) {
		return "Header Param tested with : " + headerAuth;
	}
	@GET
	@Path("cookieParams") // using cookie parameters
	public String test3ParamsAnnotations(@CookieParam("cookieName") String cookieName) {
		return "Cookie Param tested with : " + cookieName;
	}
	// @FormParam : using this when we sending request to the API from HTML form.
	// @PathParam
	// @QueryParam
	
	// Part 2 : Context Annotation.
	@GET
	@Path("context")
	public String testParamsContext(@Context UriInfo uriInfo, @Context HttpHeaders httpHeaders) {
		String path = uriInfo.getAbsolutePath().toString(); // get URI informations -> See more methods with eclipse help : uriInfo.____
	    String cookies = httpHeaders.getCookies().toString(); // get Header informations -> See more methods with eclipse help : httpHeaders.___
		return "Path : " + path + " Cookies : " + cookies; 
	}
	
	// Part 3 : Bean Parameter Annotation. "@BeanParam"
	// Go to Beans Package to See this concept & his application. 
	// The concept is to group all annotations in one object wish is a Bean filter and access them with an instance of this bean. 
}
