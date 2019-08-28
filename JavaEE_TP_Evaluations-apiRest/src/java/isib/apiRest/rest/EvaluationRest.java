/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isib.apiRest.rest;

import isib.apiRest.dao.IDAO;
import isib.apiRest.dao.EvaluationDAO;
import isib.apiRest.entity.Evaluation;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MultivaluedMap;

/**
 *
 * @author POKA
 */
@Path("/evaluations")
public class EvaluationRest implements IRest<Evaluation> {

    @Override
    public IDAO<Evaluation> dao() {
        return new EvaluationDAO();
    }
    
    @POST
    @Override
    @Consumes({"application/xml", "application/json", "application/x-www-form-urlencoded;charset=UTF-8"})
    public Evaluation create(MultivaluedMap<String, String> params) {
        return
            dao().create(
                new Evaluation(
                    params.getFirst("code"), 
                    params.getFirst("title"),
                    Integer.parseInt(params.getFirst("duration"))
                )
            );
    }

    @PUT
    @Override
    @Consumes({"application/xml", "application/json", "application/x-www-form-urlencoded;charset=UTF-8"})
    public Evaluation update(MultivaluedMap<String, String> params) {
        return dao().update(
                new Evaluation(
                        Integer.parseInt(params.getFirst("id")),
                        params.getFirst("code"),
                        params.getFirst("title"),
                        Integer.parseInt(params.getFirst("duration"))
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
    public Evaluation read(@PathParam("id") int id) {
        return dao().get(id);
    }

    @GET
    @Override
    public List<Evaluation> readAll() {
        return dao().getAll();
    }
}
