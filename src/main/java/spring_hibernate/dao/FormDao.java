package spring_hibernate.dao;

import spring_hibernate.model.Form;

import java.util.List;

public interface FormDao {
    public List getListUser();

    public void saveOrUpdate(Form user);

    public void deleteUser(int id);

    public Form findUserById(int id);
    

}

