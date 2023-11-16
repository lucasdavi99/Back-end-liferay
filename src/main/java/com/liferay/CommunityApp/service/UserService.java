package com.liferay.CommunityApp.service;

import com.liferay.CommunityApp.models.CommunityModel;
import com.liferay.CommunityApp.models.UserModel;
import com.liferay.CommunityApp.repositories.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void saveUser(UserModel userModel){
        userRepository.save(userModel);
    }

    public List<UserModel> getAll() {
        return userRepository.findAll();
    }

    public UserModel getById(UUID id) {
        Optional<UserModel> obj = userRepository.findById(id);
        return obj.get();
    }

    public void deleteUser(UserModel userModel){
        userRepository.delete(userModel);
    }

    public void deleteAllUsers(){
        userRepository.deleteAll();
    }

    public UserModel findByName(String name) {
        if (userRepository.findByName(name).isPresent()){
            return userRepository.findByName(name).get();
        }
        return null;
    }
}
