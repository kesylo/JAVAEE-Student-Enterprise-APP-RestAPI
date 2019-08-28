/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package isib.ejb.services;

import isib.ejb.services.interfaces.IServices;
import isib.ejb.dao.EvaluationDAO;
import isib.ejb.dao.IDAO;
import isib.ejb.entity.Evaluation;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author LOIC
 */
@Stateless(name = "EvaluationEJB")
public class EvaluationsServices implements IServices<Evaluation> {

    @Override
    public IDAO<Evaluation> dao() {
        return new EvaluationDAO();
    }
    
    @Override
    public Evaluation create(Evaluation obj) {
        return dao().create(obj);
    }

    @Override
    public Evaluation createWithRelation(Evaluation obj) {
        return dao().createWithRelation(obj);
    }

    @Override
    public Evaluation update(Evaluation obj) {
        return dao().update(obj);
    }

    @Override
    public boolean delete(int id) {
        return dao().delete(id);
    }

    @Override
    public Evaluation read(int id) {
        return dao().get(id);
    }

    @Override
    public List<Evaluation> readAll() {
        return dao().getAll();
    }
}
