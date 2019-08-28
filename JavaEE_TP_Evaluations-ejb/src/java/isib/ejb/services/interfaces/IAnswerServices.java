/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package isib.ejb.services.interfaces;

import isib.ejb.dao.IDAO;
import isib.ejb.dao.AnswerDAO;
import isib.ejb.entity.Answer;
import java.util.List;

/**
 *
 * @author LOIC
 */
public interface IAnswerServices {
    
    AnswerDAO dao();
    Answer create(Answer obj);
    Answer createWithRelation(Answer obj);
    Answer update(Answer obj);
    boolean delete(int id);
    Answer read(int id);
    List<Answer> readAll();
    List<Answer> readAllByQuestion(int id_question);
    
}
