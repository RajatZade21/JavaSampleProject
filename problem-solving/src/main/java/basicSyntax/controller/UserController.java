package basicSyntax.controller;

import basicSyntax.dto.User;
import basicSyntax.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.net.URI;
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

    /*
        Response Type
            HTTP/1.1 204 No Content
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    /*
        Response Type
            HTTP/1.1 200 OK
    */
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


    /*
        Response
            HTTP/1.1 201 Created
            Location: /users/123
            Content-Type: application/json
    * */
    @PostMapping()
    public ResponseEntity<User> createUser1(
            @RequestBody User user){
        User cretedUser = userService.createUser(user);
        URI location = URI.create("/users/" + cretedUser.getId());
        return ResponseEntity.created(location).body(cretedUser);
    }

    // e) Get user by ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        return ResponseEntity.ok(
            userService.getUserById(id)
        );
    }

    /*
        Response Type
            HTTP/1.1 200 OK for success
            HTTP/1.1 404 Not Found with no response body.
    */
    @GetMapping("/{id}")
    public ResponseEntity<User> get(@PathVariable long id) {
        return userService.find(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
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
