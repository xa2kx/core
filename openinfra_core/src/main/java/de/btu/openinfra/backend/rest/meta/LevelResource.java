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

import de.btu.openinfra.backend.db.pojos.meta.LevelPojo;
import de.btu.openinfra.backend.db.rbac.OpenInfraHttpMethod;
import de.btu.openinfra.backend.db.rbac.meta.LevelRbac;
import de.btu.openinfra.backend.rest.OpenInfraResponseBuilder;

@Path(OpenInfraResponseBuilder.REST_URI_METADATA + "/level")
@Produces({MediaType.APPLICATION_JSON + OpenInfraResponseBuilder.JSON_PRIORITY
    + OpenInfraResponseBuilder.UTF8_CHARSET,
    MediaType.APPLICATION_XML + OpenInfraResponseBuilder.XML_PRIORITY
    + OpenInfraResponseBuilder.UTF8_CHARSET})
public class LevelResource {

    @GET
    public List<LevelPojo> get(
            @Context UriInfo uriInfo,
            @Context HttpServletRequest request,
            @QueryParam("offset") int offset,
            @QueryParam("size") int size) {
        return new LevelRbac().read(
                OpenInfraHttpMethod.valueOf(request.getMethod()),
                uriInfo,
                null,
                offset, size);
    }

    @GET
    @Path("{levelId}")
    public LevelPojo get(
            @Context UriInfo uriInfo,
            @Context HttpServletRequest request,
            @PathParam("levelId") UUID levelId) {
        return new LevelRbac().read(
                OpenInfraHttpMethod.valueOf(request.getMethod()),
                uriInfo,
                null,
                levelId);
    }

    @GET
    @Path("count")
    @Produces({MediaType.TEXT_PLAIN})
    public long getCount(
            @Context UriInfo uriInfo,
            @Context HttpServletRequest request) {
        return new LevelRbac().getCount(
                OpenInfraHttpMethod.valueOf(request.getMethod()),
                uriInfo);
    }

    @POST
    public Response create(
            @Context UriInfo uriInfo,
            @Context HttpServletRequest request,
            LevelPojo pojo) {
        UUID id = new LevelRbac().createOrUpdate(
                OpenInfraHttpMethod.valueOf(request.getMethod()),
                uriInfo,
                null,
                pojo);
        return OpenInfraResponseBuilder.postResponse(id);
    }

    @PUT
    @Path("{levelId}")
    public Response update(
            @Context UriInfo uriInfo,
            @Context HttpServletRequest request,
            @PathParam("levelId") UUID levelId,
            LevelPojo pojo) {
        UUID id = new LevelRbac().createOrUpdate(
                OpenInfraHttpMethod.valueOf(request.getMethod()),
                uriInfo,
                levelId,
                pojo);
        return OpenInfraResponseBuilder.putResponse(id);
    }

    @DELETE
    @Path("{levelId}")
    public Response delete(
            @Context UriInfo uriInfo,
            @Context HttpServletRequest request,
            @PathParam("levelId") UUID levelId) {
        boolean deleteResult = new LevelRbac().delete(
                OpenInfraHttpMethod.valueOf(request.getMethod()),
                uriInfo,
                levelId);
        return OpenInfraResponseBuilder.deleteResponse(deleteResult, levelId);
    }

}
