package com.mehak.Rest_Api.service;

import com.mehak.Rest_Api.custom_exception.BusinessException;
import com.mehak.Rest_Api.custom_exception.EmptyInputException;
import com.mehak.Rest_Api.entity.User;
import com.mehak.Rest_Api.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.ExecutionException;

@Service
public class UserService implements UserServiceImp{

    @Autowired
    private UserRepo userRepo;

    @Override
    public User addUser(User user) {
        if(user.getName()==null||user.getName().trim().isEmpty()){
            throw new EmptyInputException("601","Input field is Empty");
        }
        try{
            User savedUser=userRepo.save(user);
            return savedUser;
        }catch(IllegalArgumentException e){
            throw new BusinessException("602","given User is null"+e.getMessage());
        }catch(Exception e){
            throw new BusinessException("603","Something went Wrong in service layer while saving"+e.getMessage());
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User>all=userRepo.findAll();
        if(all.isEmpty()){
            throw new BusinessException("604","Not Found Anything");
        }
        try{
            return all;
        }catch(Exception e){
            throw new BusinessException("605","Something went Wrong in service layer while fetching"+e.getMessage());
        }
    }

    @Override
    public User getUserById(Long myId) {
        try {
            return userRepo.findById(myId)
                    .orElseThrow(() -> new BusinessException("607", "User not found"));
        } catch (IllegalArgumentException e) {
            throw new BusinessException("606", "Given User id is null " + e.getMessage());
        }
    }

    @Override
    public void delete(Long myId) {
       try{
           userRepo.deleteById(myId);
       }catch (IllegalArgumentException e){
           throw new BusinessException("608","Given User id is null , please send id for delete"+e.getMessage());
       }
    }

    @Override
    public User partialUpdate(Long id, User user) {

        User existingUser;

        try {
            existingUser = userRepo.findById(id)
                    .orElseThrow(() -> new BusinessException("609", "User not found"));
        } catch (Exception e) {
            throw new BusinessException("610", "Error while fetching User " + e.getMessage());
        }

        // update only non-null fields

        if (user.getName() != null && !user.getName().trim().isEmpty()) {
            existingUser.setName(user.getName());
        }

        if (user.getAge() != 0) {
            existingUser.setAge(user.getAge());
        }

        if (user.getGender() != null) {
            existingUser.setGender(user.getGender());
        }

        if (user.getDob() != null) {
            existingUser.setDob(user.getDob());
        }

        return userRepo.save(existingUser);
    }
}
// save method return and object
// findById return Optional
