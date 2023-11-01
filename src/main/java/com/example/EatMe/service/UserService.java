package com.example.EatMe.service;

import com.example.EatMe.model.User;
import com.example.EatMe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<User> setEmail(@RequestParam(value = "id") int id,@RequestParam(value = "mail") String mail){
        Optional<User> userToEdit = userRepository.findById(id);
            if(userToEdit.isPresent()){
                userToEdit.get().setMail(mail);
                User userToSave = userRepository.save(userToEdit.get());
                return new ResponseEntity("Email was edited to " + userToSave.getMail(), HttpStatus.OK);
            }else{
                return new ResponseEntity("User not found", HttpStatus.NOT_FOUND);
            }
    }

    public HttpStatus setPassword(@RequestParam(value = "id") int id, @RequestParam(value = "oldPassword") String oldPassword, @RequestParam(value = "newPassword") String newPassword){
        Optional<User> userToEdit = userRepository.findById(id);
        if(userToEdit.isPresent()){
            if(userToEdit.get().getPassword().equals(oldPassword)) {
                userToEdit.get().setPassword(newPassword);
                User userToSave = userRepository.save(userToEdit.get());
                return HttpStatus.OK;
            }else{
                return HttpStatus.UNAUTHORIZED;
            }
        }else{
            return HttpStatus.NOT_FOUND;
        }
    }

}
