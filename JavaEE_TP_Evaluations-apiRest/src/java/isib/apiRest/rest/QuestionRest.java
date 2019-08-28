/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isib.apiRest.rest;

import isib.apiRest.dao.IDAO;
import isib.apiRest.dao.QuestionDAO;
import isib.apiRest.entity.Question;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MultivaluedMap;

/**
 *
 * @author LOIC
 */
@Path("/questions")
public class QuestionRest implements IRest<Question> {

    @Override
    public IDAO<Question> dao() {
        return new QuestionDAO();
    }
    
    @POST
    @Override
    @Consumes({"application/xml", "application/json", "application/x-www-form-urlencoded;charset=UTF-8"})
    public Question create(MultivaluedMap<String, String> params) {
        return
            dao().create(
                new Question(
                    Integer.parseInt(params.getFirst("id_evaluation")), 
                    params.getFirst("code"), 
                    params.getFirst("title"),
                    Integer.parseInt(params.getFirst("marks"))
                )
            );
    }

    @PUT
    @Override
    @Consumes({"application/xml", "application/json", "application/x-www-form-urlencoded;charset=UTF-8"})
    public Question update(MultivaluedMap<String, String> params) {
        return dao().update(
                new Question(
                        Integer.parseInt(params.getFirst("id")),
                        Integer.parseInt(params.getFirst("id_evaluation")),
                        params.getFirst("code"),
                        params.getFirst("title"),
                        Integer.parseInt(params.getFirst("marks"))
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
    public Question read(@PathParam("id") int id) {
        return dao().get(id);
    }

    @GET
    @Override
    public List<Question> readAll() {
        return dao().getAll();
    }
}
