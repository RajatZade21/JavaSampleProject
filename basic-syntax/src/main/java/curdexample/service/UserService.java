package curdexample.service;

import curdexample.dto.User;
import curdexample.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUserById(Long id){
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User Not Found"));
    }

    public User createUser(User user){
        return userRepository.save(user);
    }

    public User updateUser(Long id,User user){
        User existingUser = getUserById(id);

        existingUser.setEmail(user.getEmail());
        existingUser.setName(user.getName());

        return userRepository.save(existingUser);
    }

    public void deleteUser(Long id){
        User existingUser = getUserById(id);
        userRepository.delete(existingUser);
    }
}
