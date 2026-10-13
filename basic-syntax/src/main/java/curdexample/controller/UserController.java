package curdexample.controller;

import curdexample.dto.User;
import curdexample.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    // a) Get all users
    @GetMapping()
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    // b) Delete user
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    // c) Update user
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(
            @PathVariable Long id,
            @RequestBody User user){

        return ResponseEntity.ok(
            userService.updateUser(id,user)
        );
    }

    // d) Create user
    @PostMapping()
    public ResponseEntity<User> createUser(
            @RequestBody User user){
        User cretedUser = userService.createUser(user);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(cretedUser);
    }

    // e) Get user by ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(
            @PathVariable Long id){
        return ResponseEntity.ok(
            userService.getUserById(id)
        );
    }

    @GetMapping(
            value = "",
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE
            }
    )
    public User getCustomer(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @PostMapping(
            value = "/file",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<?> uploadfile(
            @PathVariable("files") List<MultipartFile> files
    ){
        for(MultipartFile file:files){

        }
        return ResponseEntity.ok("File uploaded successfully");
    }

}
