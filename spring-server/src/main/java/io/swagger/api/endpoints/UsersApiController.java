package io.swagger.api.endpoints;

import io.swagger.articles.api.ApiUtil;
import io.swagger.articles.api.UsersApi;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import io.swagger.articles.api.model.CreateUser;
import io.swagger.articles.api.model.GetUser;
import io.swagger.articles.api.model.UpdateUser;
import io.swagger.entities.ArticleEntity;
import io.swagger.entities.UserEntity;
import io.swagger.repositories.UserRepository;
import org.h2.engine.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-04-08T14:21:38.963Z[GMT]")
@Controller
public class UsersApiController implements UsersApi {
    @Autowired
    UserRepository userRepository;

    private static final Logger log = LoggerFactory.getLogger(UsersApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public UsersApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> deleteUser(@ApiParam(value = "The name that needs to be deleted",required=true) @PathVariable("userId") Long userId) {
        userRepository.deleteById(userId);
        return ResponseEntity.ok().build();

    }

    public ResponseEntity<GetUser> getUserById(@ApiParam(value = "The name that needs to be fetched.",required=true) @PathVariable("userId") Long userId) {
        UserEntity userEntity = userRepository.findById(userId).get();
        GetUser user = toGetUser(userEntity);
        return  ResponseEntity.ok(user);

    }

    public ResponseEntity<List<GetUser>> getUsers() {
        List<GetUser> users = new ArrayList<>();

        for (UserEntity userEntity : userRepository.findAll()) {
            users.add(toGetUser(userEntity));
        }

        return ResponseEntity.ok(users);

    }

    public ResponseEntity<Void> registerUser(@ApiParam(value = "Created user object" ,required=true )  @Valid @RequestBody CreateUser createUser) {

        UserEntity userEntity = fromCreateUsertoUserEntity(createUser);

        userRepository.save(userEntity);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(userEntity.getId()).toUri();

        return ResponseEntity.created(location).build();

    }

    public ResponseEntity<Void> updateUser(@ApiParam(value = "name that need to be updated",required=true) @PathVariable("userId") Long userId,@ApiParam(value = "Updated user object" ,required=true )  @Valid @RequestBody UpdateUser updateUser) {
        UserEntity userEntity = userRepository.findById(userId).get();

        userEntity = updateUser(updateUser, userEntity);


        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().build().toUri();

        userRepository.save(userEntity);

        return ResponseEntity.created(location).build();
    }

    private GetUser toGetUser(UserEntity entity){
        GetUser user = new GetUser();
        user.setId(entity.getId());
        user.setUsername(entity.getUsername());
        user.setLastName(entity.getLastName());
        user.setFirstName(entity.getFirstName());
        user.setEmail(entity.getEmail());
        return user;
    }

    private CreateUser toCreateUser(UserEntity entity) {
        CreateUser user = new CreateUser();
        user.setUsername(entity.getUsername());
        user.setFirstName(entity.getFirstName());
        user.setLastName(entity.getLastName());
        user.setEmail(entity.getEmail());
        user.setPassword(entity.getPassword());
        return user;
    }

    private UserEntity fromCreateUsertoUserEntity(CreateUser user){
        UserEntity entity = new UserEntity();
        entity.setPassword(user.getPassword());
        entity.setUsername(user.getUsername());
        entity.setLastName(user.getLastName());
        entity.setFirstName(user.getFirstName());
        entity.setEmail(user.getEmail());
        return entity;
    }

    private UserEntity fromGetUserToUserEntity(GetUser user){
        UserEntity entity = new UserEntity();
        entity.setUsername(user.getUsername());
        entity.setLastName(user.getLastName());
        entity.setFirstName(user.getFirstName());
        entity.setEmail(user.getEmail());
        return entity;
    }

    private UserEntity updateUser(UpdateUser user, UserEntity entity){
        entity.setUsername(user.getUsername());
        entity.setLastName(user.getLastName());
        entity.setFirstName(user.getFirstName());
        entity.setEmail(user.getEmail());
        entity.setPassword(user.getPassword());
        return entity;
    }

}
