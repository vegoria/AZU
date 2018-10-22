/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab02;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

@Path("generic")
public class GenericResource {

    @Context
    private UriInfo context;

    public GenericResource() {
    }

    @GET
    @Path("/users")
    public String getUsersList() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    @GET
    @Path("/users/{login}")
    public String checkIfUserExist() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }
    
    @POST
    @Path("/users")
    public String createUser() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }
    
    @PUT
    @Path("/users/{login}")
    public void setNewPasswdForUser(String content) {
    }
    
    @DELETE
    @Path("/users/{login}")
    public void deleteUser(String content) {
    }
}
