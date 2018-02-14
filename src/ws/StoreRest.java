package ws;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import service.PastaService;
import service.SauceService;
import service.StoreService;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import entity.OrderEntity;
import entity.PastaEntity;

@Path("store")
public class StoreRest {
	
	@GET
	@Path("hello")
	@Produces(MediaType.TEXT_PLAIN)
	public String hello() {
		return "Hello World";
	}
	
	@GET
	@Path("pastaTypes")
	@Produces(MediaType.TEXT_PLAIN)
	public Response getAllPastaTypes() {
		String json = toJson(PastaService.getInstance().getAllPastaTypes());
		ResponseBuilder responseBuilder = Response.ok(json);
		return responseBuilder.build();
	}
	
	@GET
	@Path("sauceTypes")
	@Produces(MediaType.TEXT_PLAIN)
	public Response getAllSauceTypes() {
		String json = toJson(SauceService.getInstance().getAllSauceTypes());
		ResponseBuilder responseBuilder = Response.ok(json);
		return responseBuilder.build();
	}

	@GET
	@Path("pasta/{pasta}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response getPasta(@PathParam("pasta") String name) {
		if (name == null) {
			return Response.status(Response.Status.NOT_FOUND).entity("pasta attribute not found").build();
		}

		PastaEntity pastaEntity = PastaService.getInstance().get(name);
		if (pastaEntity == null) {
			return Response.status(Response.Status.NOT_FOUND).entity("pasta was not found").build();
		}
		
		String json = toJson(pastaEntity);
		
		ResponseBuilder responseBuilder = Response.ok(json);
		return responseBuilder.build();
	}
	
	private String toJson(Object entity) {
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		String json = gson.toJson(entity);
		return json;
	}

	@GET
	@Path("order/pasta/{pasta}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response getOrderForPasta(@PathParam("pasta") String name) {
		PastaEntity pastaEntity = PastaService.getInstance().get(name);
		
		try {
			OrderEntity doOrder = StoreService.getInstance().doOrder(pastaEntity);
			String json = toJson(doOrder);
			ResponseBuilder responseBuilder = Response.ok(json);
			return responseBuilder.build();
		} catch (Exception e) {
			return Response.status(Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
		}
	}
	
}
