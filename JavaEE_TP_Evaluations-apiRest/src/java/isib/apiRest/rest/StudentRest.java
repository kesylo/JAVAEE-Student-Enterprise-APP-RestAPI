/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isib.apiRest.rest;

import isib.apiRest.dao.IDAO;
import isib.apiRest.dao.StudentDAO;
import isib.apiRest.entity.Student;
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
@Path("/students")
public class StudentRest implements IRest<Student> {

    @Override
    public IDAO<Student> dao() {
        return new StudentDAO();
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json", "application/x-www-form-urlencoded;charset=UTF-8"})
    public Student create(MultivaluedMap<String, String> params) {
        try {
            return dao().create(
                    new Student(
                            Integer.parseInt(params.getFirst("id_user")),
                            params.getFirst("matricule"),
                            params.getFirst("firstname"),
                            params.getFirst("lastname"),
                            params.getFirst("address"),
                            params.getFirst("mobile_phone"),
                            params.getFirst("email"),
                            Tools.convertStringToDate(
                                    params.getFirst("birthday")
                            )
                    )
            );
        } catch (ParseException ex) {
            Logger.getLogger(StudentRest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }

    @PUT
    @Override
    @Consumes({"application/xml", "application/json", "application/x-www-form-urlencoded;charset=UTF-8"})
    public Student update(MultivaluedMap<String, String> params) {
        try {
            return dao().update(
                    new Student(
                            Integer.parseInt(params.getFirst("id")),
                            Integer.parseInt(params.getFirst("id_user")),
                            params.getFirst("matricule"),
                            params.getFirst("firstname"),
                            params.getFirst("lastname"),
                            params.getFirst("address"),
                            params.getFirst("mobile_phone"),
                            params.getFirst("email"),
                            Tools.convertStringToDate(
                                    params.getFirst("birthday")
                            )
                    )
            );
        } catch (ParseException ex) {
            Logger.getLogger(StudentRest.class.getName()).log(Level.SEVERE, null, ex);
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
    public Student read(@PathParam("id") int id) {
        return dao().get(id);
    }

    @GET
    @Override
    public List<Student> readAll() {
        return dao().getAll();
    }
}
