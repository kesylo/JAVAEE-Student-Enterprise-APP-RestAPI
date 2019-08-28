/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package isib.ejb.dao;

import isib.ejb.entity.Answer;
import isib.ejb.entity.Student_Answer;
import java.util.List;
import isib.ejb.tools.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author LOIC
 */
public class Student_AnswerDAO implements IDAO<Student_Answer> {

    private static final Logger log4j = Logger.getLogger(Student_AnswerDAO.class);

    private Session session;    
    private Transaction transaction;    
    private String tableName = "Student_Answer";
    
    @Override
    public Student_Answer create(Student_Answer obj) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            
            transaction = session.beginTransaction();
            
            session.save(obj);
            
            transaction.commit();
        } 
        catch(Exception ex) 
        {
            transaction.rollback();
            log4j.error(ex);
            obj = null;
        }
        finally {
            session.close();
        }
        
        return obj;
    }
    
    public boolean create(Student_Answer[] obj) {
        boolean state = false;
        try {
            for(Student_Answer val : obj){
                if (get(val.getStudent().getId(), val.getAnswer().getId()) != null){
                    throw new Exception("Answer already gived");
                }
            }
            
            session = HibernateUtil.getSessionFactory().openSession();
            
            transaction = session.beginTransaction();
            
            for(Student_Answer val : obj){
                session.save(val);
            }
            
            transaction.commit();
            state = true;
        } 
        catch(Exception ex) 
        {
            transaction.rollback();
            log4j.error(ex);
            obj = null;
        }
        finally {
            session.close();
        }
        
        return state;
    }

    @Override
    public Student_Answer update(Student_Answer obj) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            
            transaction = session.beginTransaction();
            
            session.update(obj);
            
            transaction.commit();
        } 
        catch(Exception ex) 
        {
            transaction.rollback();
            log4j.error(ex);
            obj = null;
        }
        finally {
            session.close();
        }
        
        return obj;
    }

    @Override
    public boolean delete(int id) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            
            transaction = session.beginTransaction();
            
            session.delete(
                session.createQuery("from " + tableName + " where id = " + id).list().get(0)
            );
            
            transaction.commit();
        } 
        catch(Exception ex) 
        {
            transaction.rollback();
            log4j.error(ex);
        }
        finally {
            session.close();
            
            return get(id) == null;
        }
    }

    @Override
    public Student_Answer get(int id) {
       Student_Answer results = null;
       
       try {
            session = HibernateUtil.getSessionFactory().openSession();
            
            transaction = session.beginTransaction();
            
            results = (Student_Answer)session.createQuery("from " + tableName + " where id = " + id).list().get(0);
            
            transaction.commit();
        } 
        catch(Exception ex) 
        {
            transaction.rollback();
            log4j.error(ex);
        }
        finally {
            session.close();
        }
        
        return results;
    }
    
    public Student_Answer get(int id_student, int id_answer) {
       Student_Answer results = null;
       
       try {
            session = HibernateUtil.getSessionFactory().openSession();
            
            transaction = session.beginTransaction();
            
            results = (Student_Answer)session.createQuery("from " + tableName + " where id_student = " + id_student + " and id_answer = " + id_answer).list().get(0);
            
            transaction.commit();
        } 
        catch(Exception ex) 
        {
            transaction.rollback();
            log4j.error(ex);
        }
        finally {
            session.close();
        }
        
        return results;
    }
    
    public Student_Answer getByAnswer(int id_answer) {
       Student_Answer results = null;
       
       try {
            session = HibernateUtil.getSessionFactory().openSession();
            
            transaction = session.beginTransaction();
            
            results = (Student_Answer)session.createQuery("from " + tableName + " where id_answer = " + id_answer).list().get(0);
            
            transaction.commit();
        } 
        catch(Exception ex) 
        {
            transaction.rollback();
            log4j.error(ex);
        }
        finally {
            session.close();
        }
        
        return results;
    }
    
    public List<Student_Answer> getAnswers(int id_student, int id_evaluation) {
       List<Student_Answer> results = null;
       
       try {
            session = HibernateUtil.getSessionFactory().openSession();
            
            transaction = session.beginTransaction();
            
            results = session.createQuery(
//                      "select tbStudent_Answer " +
                        "from " + tableName + " tbStudent_Answer " + 
                            "join fetch tbStudent_Answer.answer tbAnswer " + 
                            "join fetch tbAnswer.question tbQuestion " + 
                            "join fetch tbQuestion.evaluation tbEvaluation " + 
                        "where tbStudent_Answer.student.id = " + id_student + " and tbEvaluation.id = " + id_evaluation
                    ).list();
            
            log4j.info(results);
            
            transaction.commit();
        } 
        catch(HibernateException ex) 
        {
            transaction.rollback();
            log4j.error(ex);
        }
        finally {
            session.close();
        }
        
        return results;
    }

    @Override
    public List<Student_Answer> getAll() {
       List<Student_Answer> results = null;
       
       try {
            session = HibernateUtil.getSessionFactory().openSession();
            
            transaction = session.beginTransaction();
            
            results = session.createQuery("from " + tableName).list();
            
            transaction.commit();
        } 
        catch(Exception ex) 
        {
            transaction.rollback();
            log4j.error(ex);
        }
        finally {
            session.close();
        }
            
       return results;
    }

    public List<Student_Answer> readAllByStudent(int id_student) {
       List<Student_Answer> results = null;
       
       try {
            session = HibernateUtil.getSessionFactory().openSession();
            
            transaction = session.beginTransaction();
            
            results = session.createQuery("from " + tableName + " where id_student = " + id_student).list();
            
            transaction.commit();
        } 
        catch(Exception ex) 
        {
            transaction.rollback();
            log4j.error(ex);
        }
        finally {
            session.close();
        }
            
       return results;
    }

    @Override
    public Student_Answer createWithRelation(Student_Answer obj) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            
            transaction = session.beginTransaction();
            
            session.save(obj.getAnswer());
            session.save(obj.getStudent());
            session.save(obj);
            
            transaction.commit();
        } 
        catch(Exception ex) 
        {
            transaction.rollback();
            log4j.error(ex);
            obj = null;
        }
        finally {
            session.close();
        }
        
        return obj;
    }
    
}
