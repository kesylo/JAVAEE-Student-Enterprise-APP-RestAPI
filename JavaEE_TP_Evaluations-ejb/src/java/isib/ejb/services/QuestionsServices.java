/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package isib.ejb.services;

import isib.ejb.services.interfaces.IServices;
import isib.ejb.dao.QuestionDAO;
import isib.ejb.dao.IDAO;
import isib.ejb.entity.Question;
import isib.ejb.services.interfaces.IQuestionServices;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author LOIC
 */
@Stateless(name = "QuestionEJB")
public class QuestionsServices implements IQuestionServices {

    @Override
    public QuestionDAO dao() {
        return new QuestionDAO();
    }
    
    @Override
    public Question create(Question obj) {
        return dao().create(obj);
    }

    @Override
    public Question createWithRelation(Question obj) {
        return dao().createWithRelation(obj);
    }

    @Override
    public Question update(Question obj) {
        return dao().update(obj);
    }

    @Override
    public boolean delete(int id) {
        return dao().delete(id);
    }

    @Override
    public Question read(int id) {
        return dao().get(id);
    }

    @Override
    public List<Question> readAll() {
        return dao().getAll();
    }

    @Override
    public List<Question> readAllByEvaluation(int id_evaluation) {
        return dao().getAllByEvaluation(id_evaluation);
    }
}
