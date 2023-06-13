/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cst8218.pate0968.heartbeat.resources;


import cst8218.pate0968.heartbeat.entity.Heart;
import java.util.List;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Owner
 */
@Stateless
@DeclareRoles({"Admin","ApiGroup"})
@RolesAllowed({"Admin","ApiGroup"})
@Path("cst8218.pate0968.heartbeat.entity.heart")
public class HeartFacadeREST extends AbstractFacade<Heart> {

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    public HeartFacadeREST() {
        super(Heart.class);
    }
    
    // POST method when id is provide in entity body
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response postRoot(Heart entity) {
        if (entity.getId() == null){            // if the no ID is provided in the entity body then create heart with new id
            //creating a new heart
            entity.init();
            super.create(entity);
            //return Response
            return Response.status(Response.Status.CREATED).entity("New ID is created").build();
        }else{
            //id in body is not null
            Heart heart = super.find(entity.getId());
            if (heart != null){                 // if the ID is found inside then database then make changes and return OK response
                // heart with the id does exist
                entity.updates(heart);
                // return response
                return Response.status(Response.Status.OK).entity("ID "+entity.getId()+" is updated").build();
            } else {                        // otherwise return NOT_FOUND response
                // heart with that id does not exist
                // return BAD_REQUEST or NOT_FOUND
                return Response.status(Response.Status.NOT_FOUND).entity("ID "+ entity.getId() +" does not exist").build();
            }   
        }
    }
    
    // POST method when ID is provided in URL
    @POST
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response postSpecificId(@PathParam("id") Long id, Heart entity) {
        if (entity.getId() == null){            // if the no ID is provided in the entity body then set the ID from URL
            entity.setId(id);          
        }
        // entity has non-null id
        if (entity.getId().equals(id)){         // if the URL if and entity body ID matches then find the heart and update the changes, at last return OK response
            Heart heart = super.find(id);
            entity.updates(heart);
        }else{                          // otherwise return BAD REQUEST response
            // entity id does not match URL id
            return Response.status(Response.Status.BAD_REQUEST).entity("ID provided in URL does not match with enity ID").build();
        }
        
        super.edit(entity);
        return Response.status(Response.Status.OK).entity("ID "+entity.getId()+" is updated").build();
    }
    
    // PUT method when ID is provided in URL
    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response edit(@PathParam("id") Long id, Heart entity) {
        // ensure the request is valid
        if (entity.getId() == null){            // if the no ID is provided in the entity body then set the ID from URL
            entity.setId(id);
        } 
        if (!entity.getId().equals(id)){
            //return bad request
            return Response.status(Response.Status.BAD_REQUEST).entity("ID provided in URL does not match with enity ID").build();
        }
        Heart heart = super.find(id);
        if (heart != null){                 // if the provide ID in URL is found then make changer and return OK response
            entity.init();
            super.edit(entity);   
        }else {                             // otherwise give a not found response
            return Response.status(Response.Status.NOT_FOUND).entity("ID "+ entity.getId() +" does not found").build();
        }
        return Response.status(Response.Status.OK).entity("ID "+entity.getId()+" is edited").build();
    }
    
    // PUT method when id is provide in entity body
    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response postEdit(Heart entity) {
        if (entity.getId() == null){                // if the ID is not provide in the body then return the BAD_REQUEST response
            //creating a new heart
            //super.create(entity);
            //return Response
            return Response.status(Response.Status.BAD_REQUEST).entity("ID is not specified in body").build();
        }else {
            //id in body is not null
            Heart heart = super.find(entity.getId());
            if (heart != null){                 // if the heart is found then edits the heart values and return OK response
                // heart with the id does exist
                entity.init();
                super.edit(entity);
                // return response
                return Response.status(Response.Status.OK).entity("ID "+entity.getId()+" is edited").build();
            } else {                            // other return NOT_FOUND response
                // heart with that id does not exist
                // return BAD_REQUEST or NOT_FOUND
                return Response.status(Response.Status.NOT_FOUND).entity("ID "+ entity.getId() +" does not found").build();
            }   
        }
    }
    
    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        super.remove(super.find(id));
    }

    // GET method ID is provide in URL and return not found response if id is not exist or return OK reponse with all the values
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response find(@PathParam("id") Long id) {
        if (super.find(id) == null)         // if the ID provide in URL not found, then return NOT_FOUND response 
            return Response.status(Response.Status.NOT_FOUND).entity("ID "+ id +" does not found").build();
        // otherwise return OK response
        return Response.status(Response.Status.OK).entity(super.find(id)).build();
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Heart> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Heart> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
