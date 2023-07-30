package spring_hibernate.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring_hibernate.dao.FormDao;
import spring_hibernate.model.Form;


@Service
@Transactional
public class FormServiceImpl implements FormService{

    @Autowired
    FormDao formDao;

    public void setUserDao(FormDao formDao) {
        this.formDao = formDao;
    }

    @SuppressWarnings("rawtypes")
	public List getListUser() {
        return formDao.getListUser();
    }

    public void saveOrUpdate(Form user) {
        formDao.saveOrUpdate(user);
    }

    public void deleteUser(int id) {
        formDao.deleteUser(id);
    }

    public Form findUserById(int id) {
        return formDao.findUserById(id);
    }

    }

  


