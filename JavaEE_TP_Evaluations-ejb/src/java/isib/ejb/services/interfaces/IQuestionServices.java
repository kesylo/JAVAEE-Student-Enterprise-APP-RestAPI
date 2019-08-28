/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package isib.ejb.services.interfaces;

import isib.ejb.dao.IDAO;
import isib.ejb.dao.QuestionDAO;
import isib.ejb.entity.Question;
import java.util.List;

/**
 *
 * @author LOIC
 */
public interface IQuestionServices {
    
    QuestionDAO dao();
    Question create(Question obj);
    Question createWithRelation(Question obj);
    Question update(Question obj);
    boolean delete(int id);
    Question read(int id);
    List<Question> readAll();
    List<Question> readAllByEvaluation(int id_evaluation);
    
}
