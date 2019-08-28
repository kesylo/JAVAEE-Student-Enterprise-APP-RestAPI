/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package isib.ejb.services;

import isib.ejb.dao.AnswerDAO;
import isib.ejb.entity.Answer;
import isib.ejb.services.interfaces.IAnswerServices;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author LOIC
 */
@Stateless(name = "AnswerEJB")
public class AnswersServices implements IAnswerServices {

    @Override
    public AnswerDAO dao() {
        return new AnswerDAO();
    }
    
    @Override
    public Answer create(Answer obj) {
        return dao().create(obj);
    }

    @Override
    public Answer createWithRelation(Answer obj) {
        return dao().createWithRelation(obj);
    }

    @Override
    public Answer update(Answer obj) {
        return dao().update(obj);
    }

    @Override
    public boolean delete(int id) {
        return dao().delete(id);
    }

    @Override
    public Answer read(int id) {
        return dao().get(id);
    }

    @Override
    public List<Answer> readAll() {
        return dao().getAll();
    }

    @Override
    public List<Answer> readAllByQuestion(int id_question) {
        return dao().getAllByQuestion(id_question);
    }
}
