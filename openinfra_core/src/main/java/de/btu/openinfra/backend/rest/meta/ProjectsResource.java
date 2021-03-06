package de.btu.openinfra.backend.rest.meta;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import de.btu.openinfra.backend.db.pojos.meta.ProjectsPojo;
import de.btu.openinfra.backend.db.rbac.OpenInfraHttpMethod;
import de.btu.openinfra.backend.db.rbac.meta.ProjectsRbac;
import de.btu.openinfra.backend.rest.OpenInfraResponseBuilder;

@Path(OpenInfraResponseBuilder.REST_URI_METADATA + "/projects")
@Produces({MediaType.APPLICATION_JSON + OpenInfraResponseBuilder.JSON_PRIORITY
    + OpenInfraResponseBuilder.UTF8_CHARSET,
    MediaType.APPLICATION_XML + OpenInfraResponseBuilder.XML_PRIORITY
    + OpenInfraResponseBuilder.UTF8_CHARSET})
public class ProjectsResource {

    @GET
    public List<ProjectsPojo> get(
            @Context UriInfo uriInfo,
            @Context HttpServletRequest request,
            @QueryParam("offset") int offset,
            @QueryParam("size") int size) {
        return new ProjectsRbac().read(
                OpenInfraHttpMethod.valueOf(request.getMethod()),
                uriInfo,
                null,
                offset, size);
    }

    @GET
    @Path("{projectsId}")
    public ProjectsPojo get(
            @Context UriInfo uriInfo,
            @Context HttpServletRequest request,
            @PathParam("projectsId") UUID projectsId) {
        return new ProjectsRbac().read(
                OpenInfraHttpMethod.valueOf(request.getMethod()),
                uriInfo,
                null,
                projectsId);
    }

    @GET
	@Path("count")
	@Produces({MediaType.TEXT_PLAIN})
	public long getCount(
	        @Context UriInfo uriInfo,
            @Context HttpServletRequest request) {
        return new ProjectsRbac().getCount(
                OpenInfraHttpMethod.valueOf(request.getMethod()),
                uriInfo);
	}

    @POST
    public Response create(
            @Context UriInfo uriInfo,
            @Context HttpServletRequest request,
            ProjectsPojo pojo) {
        UUID id = new ProjectsRbac().createOrUpdate(
                OpenInfraHttpMethod.valueOf(request.getMethod()),
                uriInfo,
                null,
                pojo);
        return OpenInfraResponseBuilder.postResponse(id);
    }

    @PUT
    @Path("{projectsId}")
    public Response update(
            @Context UriInfo uriInfo,
            @Context HttpServletRequest request,
            @PathParam("projectsId") UUID projectsId,
            ProjectsPojo pojo) {
        UUID id = new ProjectsRbac().createOrUpdate(
                OpenInfraHttpMethod.valueOf(request.getMethod()),
                uriInfo,
                projectsId,
                pojo);
        return OpenInfraResponseBuilder.putResponse(id);
    }

    @DELETE
    @Path("{projectsId}")
    public Response delete(
            @Context UriInfo uriInfo,
            @Context HttpServletRequest request,
            @PathParam("projectsId") UUID projectsId) {
        boolean deleteResult = new ProjectsRbac().delete(
                OpenInfraHttpMethod.valueOf(request.getMethod()),
                uriInfo,
                projectsId);
        return OpenInfraResponseBuilder.deleteResponse(
                deleteResult,
                projectsId);
    }

}
