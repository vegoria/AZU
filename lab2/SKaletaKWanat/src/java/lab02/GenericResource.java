/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab02;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

@Path("/my_app")
public class GenericResource {
    @EJB
    private IUserList userListContainer;
    @Context
    private UriInfo context;

    public GenericResource()
    {
        
    }
    
        private void sortUsers(List<User> userList, boolean descOrder)
    {
        Collections.sort(userList, new Comparator<User>() {
                @Override
                public int compare(User lhs, User rhs) {
                return lhs.getLogin().compareTo(rhs.getLogin());
                }
                });
        if(descOrder)
        {
            Collections.reverse(userList);
        }
    }
    
    private void getUserToDisplay(int page, int count, List<User> userList, List<User> listToDisplay)
    {
        int currPage = 1;
        int currCount = 0;
        for(User usr: userList)
        {
            if(currPage==page)
            {
                listToDisplay.add(usr);
            }
            currCount++;
            if(currCount == count)
            {
                currPage++;
                currCount=0;
            }
            if(currPage>page) break;
        }
    }
    
    private StringBuilder getStringBuilder(List<User> userList, int type)
    {
        StringBuilder sb = new StringBuilder();
        if(type==1)
        {
            sb.append("<table>\n");
            for(User usr: userList)
            {
                sb.append("<tr><td>");
                sb.append(usr.toString());
                sb.append("</td></tr>\n");
            }
            sb.append("</table>");
        }
        else if(type==2)
        {
            Integer counter = 0;
            for(User usr: userList)
            {
                counter++;
                sb.append("<user ID="+counter.toString()+">\n");
                sb.append("\t<login>\n");
                sb.append("\t\t");
                sb.append(usr.toString());
                sb.append("\n\t</login>\n");
                sb.append("</user>\n");
            }
        }
        else if(type==3)
        {
            Integer counter = 0;
            for(User usr: userList)
            {
                counter++;
                sb.append("{\n");
                sb.append("\t\"user"+counter.toString()+"\": {\n");
                sb.append("\t\t\"login\": \"");
                sb.append(usr.toString());
                sb.append("\"\n");
                sb.append("\t}\n");
            }
        }
        else{
            for(User usr: userList)
            {
                sb.append(usr.toString());
                sb.append("\n");
            }
        }
        return sb;
    }
    
    public Response performGetReq(String sortBy,
                                  String sortDir,
                                  String page,
                                  String count,
                                  int type)
    {
        List<User> userList = userListContainer.getUsers();
        List<User> listToDisplay = new ArrayList<>();
        int pageNumber = 0;
        boolean descending = false;
        boolean limitedView = false;
        if(sortDir != null)
        {
            if(sortDir.equals("desc")) descending=true;
        }
        
        if(sortBy != null)
        {
            if(sortBy.equals("login"))
            {
                sortUsers(userList, descending);
            }
        }
        
        int status = 200;
        StringBuilder sb;
        if(page != null? count != null: false)
        {
            limitedView = true;
            getUserToDisplay(Integer.parseInt(page), Integer.parseInt(count), userList, listToDisplay);
        }
        if(limitedView)
        {
            sb = getStringBuilder(listToDisplay, type);
        }
        else
        {
            sb = getStringBuilder(userList, type);
        }
        
        return Response.status(status).entity(sb.toString()).build();
    }
    
    
    @GET
    @Consumes({"text/plain"})
    @Path("/users")
    public Response getUsersList(
            @QueryParam("sortBy") String sortBy,
            @QueryParam("sortDir") String sortDir,
            @QueryParam("page") String page,
            @QueryParam("count") String count)
    {
        return performGetReq(sortBy, sortDir, page, count, 0);
    }
    
    @GET
    @Consumes({"text/html"})
    @Path("/users")
    public Response getUsersListHtml(
            @QueryParam("sortBy") String sortBy,
            @QueryParam("sortDir") String sortDir,
            @QueryParam("page") String page,
            @QueryParam("count") String count)
    {
        return performGetReq(sortBy, sortDir, page, count, 1);
    }
    
    @GET
    @Consumes({"text/xml"})
    @Path("/users")
    public Response getUsersListXml(
            @QueryParam("sortBy") String sortBy,
            @QueryParam("sortDir") String sortDir,
            @QueryParam("page") String page,
            @QueryParam("count") String count)
    {
        return performGetReq(sortBy, sortDir, page, count, 2);
    }
    
    @GET
    @Consumes({"application/json"})
    @Path("/users")
    public Response getUsersListJson(
            @QueryParam("sortBy") String sortBy,
            @QueryParam("sortDir") String sortDir,
            @QueryParam("page") String page,
            @QueryParam("count") String count)
    {
        return performGetReq(sortBy, sortDir, page, count, 3);
    }

    @GET
    @Path("/users/{login}")
    public Response checkIfUserExist(@PathParam("login") String pLogin) {
        boolean userExist = false;
        int status = 200;
        List<User> userList = userListContainer.getUsers();
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
            return Response.status(status).entity("Uzytkownik istnieje").build();
        }
        return Response.status(status).entity("Brak uzytkownika").build();
    }
     @GET
    @Path("/login")
    public Response checkIfUserLogged(@CookieParam("name") Cookie cookie) {
         int status = 200;
        if (cookie == null) {
        return Response.status(status).entity("Brak cookie").build();
    } else {
        List<User> userList = userListContainer.getUsers();
        boolean userLogged=false;
        for(User usr: userList)
        {
            if(usr.getSession() == null ? cookie.getValue() == null : usr.getSession().equals(cookie.getValue()))
            {
                userLogged = true;
                return Response.status(status).entity(usr.getLogin()+" "+cookie.getValue()).build();
            
            }
        }
        return Response.status(status).entity("Nikt nie jest zalogowany"+cookie.getValue()).build();
    }
    }
    
  @GET
    @Path("/logout")
    public Response logoutUser(@CookieParam("name") Cookie cookie) {
         int status = 200;
        if (cookie == null) {
        return Response.status(status).entity("Brak cookie").build();
    } else {
        List<User> userList = userListContainer.getUsers();
        boolean userLogged=false;
        for(User usr: userList)
        {
            if(usr.getSession() == null ? cookie.getValue() == null : usr.getSession().equals(cookie.getValue()))
            {
                userListContainer.setSession(usr.getLogin(), "0");
                NewCookie newCookie = new NewCookie(cookie, null, 0, false);
                return Response.ok("OK").cookie(newCookie).build();
                
            }
        }
        return Response.status(status).entity("Nikt nie jest zalogowany").build();
    }
    }
    
    
    @POST
    @Path("/login")
    public Response loginUser(String req) {
        String[] parts = req.split("\n");
         boolean userLogged = false;
         NewCookie cookie=null;
         Timestamp time= new Timestamp(System.currentTimeMillis());
         long milis= time.getTime();
        List<User> userList = userListContainer.getUsers();
        for(User usr: userList)
        {
            if(usr.getLogin() == null ? parts[0].trim() == null : usr.getLogin().equals(parts[0].trim()))
            {
                if(usr.getPassword() == null ? parts[1].trim() == null : usr.getPassword().equals(parts[1].trim())){
                userLogged = true;
                userListContainer.setSession(parts[0].trim(), Long.toString(milis));
                break;
                }
            }
        }
        if(userLogged)
        {
            //dodac cookie
            cookie = new NewCookie("name", Long.toString(milis));
            
        }
        
    return Response.ok("OK").cookie(cookie).build();
        
    }
    
    @POST
    @Path("/users")
    public Response createUser(String req) {
        int status = 200;
        String msg = "Dodano uzytkownika";
        boolean dodano = false;
        String[] parts = req.split("\n");
        if(parts.length != 2)
        {
            status = 411;
            msg = "Brak odpowiednich danych";
        }
        parts[1] = parts[1].trim();
        if(parts[1].equals(""))
        {
            status = 400;
            msg = "Puste haslo";
        }
        if(status == 200)
        {
            dodano = userListContainer.addUser(new User(parts[0].trim(), parts[1].trim()));
        }
        if(!dodano)
        {
            status = 409;
            msg = "Uzytkownik o takim loginie istnieje";
        }
        return Response.status(status).entity(msg).build();
    }
    
    @PUT
    @Path("/users/{login}")
    public Response setNewPasswdForUser(@PathParam("login") String pLogin) {
        int status = 404;
        String msg = "Nie znaleziono uzytkownika"; 
        List<User> userList = userListContainer.getUsers();
        for(User usr: userList)
        {
            if(usr.getLogin() == null ? pLogin == null : usr.getLogin().equals(pLogin))
            {
                usr.setPassword("12345");
                status = 200;
                msg = "zmieniono haslo na 12345";
                break;
            }
        }
        return Response.status(status).entity(msg).build();
        
    }
    
    @DELETE
    @Path("/users/{login}")
    public Response deleteUser(@PathParam("login") String pLogin) {
       boolean removed = userListContainer.removeUser(pLogin);
       if(removed)
       {
           return Response.status(200).entity("Usunieto").build();
       }
       else
       {
            return Response.status(404).entity("Nie znaleziono uzytkownika").build();
       }
    }
}
