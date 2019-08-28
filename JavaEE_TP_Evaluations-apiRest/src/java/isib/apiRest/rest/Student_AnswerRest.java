/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isib.apiRest.rest;

import isib.apiRest.dao.IDAO;
import isib.apiRest.dao.Student_AnswerDAO;
import isib.apiRest.entity.Student_Answer;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.*;
import javax.ws.rs.core.MultivaluedMap;
import isib.apiRest.tools.Tools;

/**
 *
 * @author LOIC
 */
@Path("/students_answers")
public class Student_AnswerRest implements IRest<Student_Answer> {

    @Override
    public IDAO<Student_Answer> dao() {
        return new Student_AnswerDAO();
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json", "application/x-www-form-urlencoded;charset=UTF-8"})
    public Student_Answer create(MultivaluedMap<String, String> params) {
        try {
            return dao().create(
                    new Student_Answer(
                            Tools.convertStringToDate(
                                    params.getFirst("date")
                            ),
                            Integer.parseInt(params.getFirst("id_student")),
                            Integer.parseInt(params.getFirst("id_answer"))
                    )
            );
        } catch (ParseException ex) {
            Logger.getLogger(Student_AnswerRest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }

    @PUT
    @Override
    @Consumes({"application/xml", "application/json", "application/x-www-form-urlencoded;charset=UTF-8"})
    public Student_Answer update(MultivaluedMap<String, String> params) {
        try {
            return dao().update(
                    new Student_Answer(
                            Integer.parseInt(params.getFirst("id")),
                            Tools.convertStringToDate(
                                    params.getFirst("date")
                            ),
                            Integer.parseInt(params.getFirst("id_student")),
                            Integer.parseInt(params.getFirst("id_answer"))
                    )
            );
        } catch (ParseException ex) {
            Logger.getLogger(Student_AnswerRest.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
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
    public Student_Answer read(@PathParam("id") int id) {
        return dao().get(id);
    }

    @GET
    @Override
    public List<Student_Answer> readAll() {
        return dao().getAll();
    }
}
