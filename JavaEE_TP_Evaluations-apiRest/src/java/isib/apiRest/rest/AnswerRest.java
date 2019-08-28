/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isib.apiRest.rest;

import isib.apiRest.dao.IDAO;
import isib.apiRest.dao.AnswerDAO;
import isib.apiRest.entity.Answer;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MultivaluedMap;

/**
 *
 * @author LOIC
 */
@Path("/answers")
public class AnswerRest implements IRest<Answer> {

    @Override
    public IDAO<Answer> dao() {
        return new AnswerDAO();
    }
    
    @POST
    @Override
    @Consumes({"application/xml", "application/json", "application/x-www-form-urlencoded;charset=UTF-8"})
    public Answer create(MultivaluedMap<String, String> params) {
        return
            dao().create(
                new Answer(
                    Integer.parseInt(params.getFirst("id_question")), 
                    params.getFirst("code"), 
                    params.getFirst("title"),
                    Boolean.parseBoolean(params.getFirst("truth"))
                )
            );
    }

    @PUT
    @Override
    @Consumes({"application/xml", "application/json", "application/x-www-form-urlencoded;charset=UTF-8"})
    public Answer update(MultivaluedMap<String, String> params) {
        return dao().update(
                new Answer(
                        Integer.parseInt(params.getFirst("id")),
                        Integer.parseInt(params.getFirst("id_question")),
                        params.getFirst("code"),
                        params.getFirst("title"),
                        Boolean.parseBoolean(params.getFirst("truth"))
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
    public Answer read(@PathParam("id") int id) {
        return dao().get(id);
    }

    @GET
    @Override
    public List<Answer> readAll() {
        return dao().getAll();
    }
}
