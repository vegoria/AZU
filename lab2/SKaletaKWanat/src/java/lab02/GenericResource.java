/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab02;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

@Path("/my_app")
public class GenericResource {
    private List<User> userList;
    @Context
    private UriInfo context;

    public GenericResource() {
        userList = new ArrayList<>();
        userList.add(new User("anna", "anna"));
        userList.add(new User("ola", "ola"));
        userList.add(new User("maciek", "maciek"));
        userList.add(new User("michal", "michal"));
    }

    @GET
    @Path("/users")
    public String getUsersList() {
        StringBuilder sb = new StringBuilder();
        for(User usr: userList)
        {
            sb.append(usr.toString());
            sb.append("\n");
        }
        return sb.toString();
    }

    @GET
    @Path("/users/{login}")
    public String checkIfUserExist(@PathParam("login") String pLogin) {
        boolean userExist = false;
        for(User usr: userList)
        {
            if(usr.getLogin() == null ? pLogin == null : usr.getLogin().equals(pLogin))
            {
                userExist = true;
                break;
            }
        }
        if(userExist)
        {
            return "uzytkownik istnieje";
        }
        return "brak uzytkownika";
    }
    
    @POST
    @Path("/users")
    public void createUser(String req) {
        String[] parts = req.split("\n");
        userList.add(new User(parts[0], parts[1]));
    }
    
    @PUT
    @Path("/users/{login}")
    public String setNewPasswdForUser(@PathParam("login") String pLogin) {
        for(User usr: userList)
        {
            if(usr.getLogin() == null ? pLogin == null : usr.getLogin().equals(pLogin))
            {
                usr.setPassword("12345");
                break;
            }
        }
        return "Nowe haslo: 1234";
        
    }
    
    @DELETE
    @Path("/users/{login}")
    public void deleteUser(@PathParam("login") String pLogin) {
        for(User usr: userList)
        {
            if(usr.getLogin() == null ? pLogin == null : usr.getLogin().equals(pLogin))
            {
                userList.remove(usr);
                break;
            }
        }
    }
}
