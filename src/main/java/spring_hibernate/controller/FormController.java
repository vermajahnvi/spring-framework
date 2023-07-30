package spring_hibernate.controller;

import java.util.List;


import spring_hibernate.model.Form;
import spring_hibernate.service.FormService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


@RestController
public class FormController {

    private final Logger LOG = LoggerFactory.getLogger(FormController.class);

    @Autowired
    FormService formService;

	@RequestMapping(value="/user", method=RequestMethod.GET, headers="Accept=application/json")
    public ResponseEntity<List<Form>> getListUser(){
		LOG.info("getting all users");
        List<Form> users = formService.getListUser();
        if (users == null || users.isEmpty()){
            LOG.info("no users found");
            return new ResponseEntity<List<Form>>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<Form>>(users, HttpStatus.OK);
    }

    @RequestMapping(value="/add", method=RequestMethod.POST)
    public ResponseEntity<Void> add(@RequestBody Form user){
    	LOG.info("creating new user: {}", user);

        formService.saveOrUpdate(user);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
    

    @RequestMapping(value="/update/{id}", method=RequestMethod.PUT)
    public ResponseEntity<Form> update(@PathVariable("id") int id, @RequestBody Form user)
    		throws UserNotFoundByIdException{
    	LOG.info("updating user: {}", user);
        Form userinfo = formService.findUserById(id);
        if (userinfo == null){
            LOG.info("User with id {} not found", id);
            throw new UserNotFoundByIdException("Resource not found for " + id);
            //return new ResponseEntity<Form>(HttpStatus.NOT_FOUND);
        }
        
        user.setId(id);
        formService.saveOrUpdate(user);
        return new ResponseEntity<Form>(HttpStatus.OK);
    }

    @RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
    	LOG.info("deleting user with id: {}", id);
        Form user = formService.findUserById(id);
        if (user == null){
            LOG.info("Unable to delete. User with id {} not found", id);
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
            formService.deleteUser(id);
            return new ResponseEntity<Void>(HttpStatus.OK);
    } 
}
