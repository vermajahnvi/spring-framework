package spring_hibernate.dao;

import spring_hibernate.model.Form;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FormDaoImp implements FormDao{
	
	private List<Form> users;

    @Autowired
    private SessionFactory factory;

    private Session getSession() {
        Session session = null;
        try {
            session = factory.getCurrentSession();
        } catch (HibernateException ex) {
            session = factory.openSession();
        }
        return session;
    }

  
    @SuppressWarnings("rawtypes")
	public List getListUser() {
		Criteria criteria = getSession().createCriteria(Form.class);
        return (List)criteria.list();
        
        
    }

    public void saveOrUpdate(Form user) {

        getSession().saveOrUpdate(user);
    }

    public void deleteUser(int id) {
        Form user = (Form) getSession().get(Form.class, id);
        getSession().delete(user);
    }

    public Form findUserById(int id) {
    	return (Form) getSession().get(Form.class, id);
   
    }
}



