package io.api.messenger.resources;

import java.util.Date;
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

import io.api.messenger.businessServices.ProfilesService;
import io.api.messenger.models.entities.Profile;

@Path("/profiles")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProfilesResource {
	private ProfilesService profileService = new ProfilesService();
	@GET
	public List<Profile> getProfiles() {
		return this.profileService.select();
	}
	@GET
	@Path("{profileName}")
	public Profile getOneProfile(@PathParam("profileName") String profileName) {
		return this.profileService.selectWhere(profileName);
	}
	@POST
	public Profile addProfile(Profile p) {
		p.setCreated(new Date());
		p.setModified(new Date());
		return this.profileService.insert(p);
	}
	@PUT
	@Path("{profileName}")
	public Profile updateProfile(@PathParam("profileName") String profileName, Profile p) {
		p.setProfileName(profileName);
		p.setModified(new Date());
		return this.profileService.update(p);
	}
	@DELETE
	@Path("{profileName}")
	public void deleteProfile(@PathParam("profileName") String profileName) {
		this.profileService.delete(profileName);
	}
}