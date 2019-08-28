/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isib.apiRest.rest;

import isib.apiRest.dao.IDAO;
import isib.apiRest.dao.UserDAO;
import isib.apiRest.entity.User;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MultivaluedMap;

/**
 *
 * @author LOIC
 */
@Path("/users")
public class UserRest implements IRest<User> {

    @Override
    public IDAO<User> dao() {
        return new UserDAO();
    }
    
    @POST
    @Override
    @Consumes({"application/xml", "application/json", "application/x-www-form-urlencoded;charset=UTF-8"})
    public User create(MultivaluedMap<String, String> params) {
        return
            dao().create(
                new User(
                    params.getFirst("login"), 
                    params.getFirst("password"),
                    Boolean.parseBoolean(params.getFirst("enabled"))
                )
            );
    }

    @PUT
    @Override
    @Consumes({"application/xml", "application/json", "application/x-www-form-urlencoded;charset=UTF-8"})
    public User update(MultivaluedMap<String, String> params) {
        return dao().update(
                new User(
                        Integer.parseInt(params.getFirst("id")),
                        params.getFirst("login"),
                        params.getFirst("password"),
                        Boolean.parseBoolean(params.getFirst("enabled"))
                )
        );
    }

    @DELETE
    @Override
    @Path("{id}")
    public boolean delete(@PathParam("id") int id) {
        return dao().delete(id);
    }

    @GET
    @Override
    @Path("{id}")
    public User read(@PathParam("id") int id) {
        return dao().get(id);
    }

    @GET
    @Path("{login}/{password}")
    public User read(@PathParam("login") String login, @PathParam("password") String password) {
        return ((UserDAO)dao()).get(login, password);
    }

    @GET
    @Override
    public List<User> readAll() {
        return dao().getAll();
    }
}
