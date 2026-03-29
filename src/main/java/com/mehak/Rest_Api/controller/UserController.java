package com.mehak.Rest_Api.controller;

import com.mehak.Rest_Api.custom_exception.BusinessException;
import com.mehak.Rest_Api.custom_exception.ControllerException;
import com.mehak.Rest_Api.entity.User;
import com.mehak.Rest_Api.service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserServiceImp userServiceImp;

    @PostMapping("/saveUser")
    public ResponseEntity<?> addUser(@RequestBody User user){
        try{
            User Saveuser=userServiceImp.addUser(user);
            return new ResponseEntity<User>(Saveuser, HttpStatus.CREATED);
        }catch(BusinessException e){
            ControllerException ce=new ControllerException(e.getErrorCode(),e.getErrorMessage());
            return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
        }catch(Exception e){
            ControllerException ce=new ControllerException("611","Something went wrong in controller");
            return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAll(){
        List<User>listOfAll=userServiceImp.getAllUsers();
        return new ResponseEntity<List<User>>(listOfAll, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long myId){
        try{
            User retrieved=userServiceImp.getUserById(myId);
            return new ResponseEntity<User>(retrieved, HttpStatus.OK);
        }catch(BusinessException e){
            ControllerException ce=new ControllerException(e.getErrorCode(),e.getErrorMessage());
            return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
        }catch(Exception e){
            ControllerException ce=new ControllerException("612","Something went wrong in controller do not find anything");
            return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long myId){
        userServiceImp.delete(myId);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody User user){
        User Saveuser=userServiceImp.addUser(user);
        return new ResponseEntity<User>(Saveuser, HttpStatus.OK);
    }

    @PatchMapping("/partialUpdate/{myid}")
    public ResponseEntity<?> partialUpdate(@PathVariable("myid") Long id , @RequestBody User user){
    try {
        User updatedUser = userServiceImp.partialUpdate(id, user);
        return new ResponseEntity<User>(updatedUser, HttpStatus.OK);
    }catch(BusinessException e){
        ControllerException ce = new ControllerException(e.getErrorCode(),e.getErrorMessage());
        return new ResponseEntity<>(ce , HttpStatus.BAD_REQUEST);
    }catch(Exception e){
        ControllerException ce = new ControllerException("613", "Something went wrong in partial update");
        return new ResponseEntity<>(ce , HttpStatus.BAD_REQUEST);
    }
    }
}

//ResponseEntity is Extension of HttpEntity
//return ResponseEntity.status(HttpStatus.CREATED).body(saveUser);
//return new ResponseEntity<User>(saveUser, HttpStatus.CREATED);
//path parameter and query parameter
//validation should done in try block
//Throws Used for Checked Exception
//Custom Exception Handling And Global Exception Handling
