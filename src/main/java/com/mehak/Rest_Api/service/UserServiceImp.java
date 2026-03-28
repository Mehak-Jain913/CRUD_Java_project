package com.mehak.Rest_Api.service;

import com.mehak.Rest_Api.entity.User;

import java.util.List;

public interface UserServiceImp {

    public User addUser(User user);

    public List<User> getAllUsers();

    public User getUserById(Long myId);

    public void delete(Long myId);

    public User partialUpdate(Long id , User user);
}
