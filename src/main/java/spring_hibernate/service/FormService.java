package spring_hibernate.service;

import spring_hibernate.model.Form;

import java.util.List;

public interface FormService {

    public List getListUser();

    public void saveOrUpdate(Form user);

    public void deleteUser(int id);

    public Form findUserById(int id);
    

    

    }

