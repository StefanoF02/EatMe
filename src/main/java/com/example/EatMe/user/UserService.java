package com.example.EatMe.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<User> addUser(@RequestBody User newUser){
        var userInDB = userRepository.findByMail(newUser.getMail());
        if(userInDB.isPresent()){
            return new ResponseEntity("Email is already used.", HttpStatus.BAD_REQUEST);
        }else{
            userRepository.save(newUser);
            return new ResponseEntity<User>(newUser, HttpStatus.OK);
        }

    }

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

    public ResponseEntity<User> setPassword(@RequestParam(value = "id") int id, @RequestParam(value = "oldPassword") String oldPassword, @RequestParam(value = "newPassword") String newPassword){
        Optional<User> userToEdit = userRepository.findById(id);
        if(userToEdit.isPresent()){
            if(userToEdit.get().getPassword().equals(oldPassword)) {
                userToEdit.get().setPassword(newPassword);
                User userToSave = userRepository.save(userToEdit.get());
                return new ResponseEntity("Password was changed", HttpStatus.OK);
            }else{
                return new ResponseEntity("Wrong password", HttpStatus.UNAUTHORIZED);
            }
        }else{
            return new ResponseEntity("User not found", HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<User> deleteUser(@RequestParam(value = "id") int id){
        Optional<User> userToDelete = userRepository.findById(id);
        if(userToDelete.isPresent()){
            userRepository.deleteById(id);
            return new ResponseEntity("User deleted" , HttpStatus.OK);
        }else {
            return new ResponseEntity("User not found", HttpStatus.NOT_FOUND);
        }
    }

}
