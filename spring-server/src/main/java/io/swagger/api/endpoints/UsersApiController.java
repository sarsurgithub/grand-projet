package io.swagger.api.endpoints;

import io.swagger.articles.api.UsersApi;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import io.swagger.articles.api.model.CreateUser;
import io.swagger.articles.api.model.GetUser;
import io.swagger.articles.api.model.UpdateUser;
import io.swagger.entities.ArticleEntity;
import io.swagger.entities.UserEntity;
import io.swagger.repositories.ArticleRepository;
import io.swagger.repositories.UserRepository;
import io.swagger.services.utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-04-08T14:21:38.963Z[GMT]")
@Controller
public class UsersApiController implements UsersApi {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ArticleRepository articleRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private static final Logger log = LoggerFactory.getLogger(UsersApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public UsersApiController(ObjectMapper objectMapper, HttpServletRequest request, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public ResponseEntity<Void> deleteUser(@ApiParam(value = "The name that needs to be deleted",required=true) @PathVariable("userId") Long userId) {

        UserEntity author = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("This user does not exist"));

        String id = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        Long idLong = Long.parseLong(id);

        if (userId == idLong) {

            List<ArticleEntity> articles = articleRepository.findAllByAuthor(author);

            for( ArticleEntity article : articles) {
                article.setAuthor(null);
            }

            articleRepository.saveAll(articles);
            userRepository.deleteById(userId);

            return ResponseEntity.ok().build();

        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    public ResponseEntity<GetUser> getUserById(@ApiParam(value = "The name that needs to be fetched.",required=true) @PathVariable("userId") Long userId) {
        UserEntity userEntity = userRepository.findById(userId).get();
        GetUser user = utils.toGetUser(userEntity);
        return  ResponseEntity.ok(user);

    }

    public ResponseEntity<List<GetUser>> getUsers() {
        List<GetUser> users = new ArrayList<>();

        for (UserEntity userEntity : userRepository.findAll()) {
            users.add(utils.toGetUser(userEntity));
        }

        return ResponseEntity.ok(users);

    }

    public ResponseEntity<Void> registerUser(@ApiParam(value = "Created user object" ,required=true )  @Valid @RequestBody CreateUser createUser) {

        UserEntity userEntity = utils.fromCreateUsertoUserEntity(createUser);

        userEntity.setPassword(bCryptPasswordEncoder.encode(userEntity.getPassword()));

        userRepository.save(userEntity);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(userEntity.getId()).toUri();

        return ResponseEntity.created(location).build();

    }

    public ResponseEntity<Void> updateUser(@ApiParam(value = "name that need to be updated",required=true) @PathVariable("userId") Long userId,@ApiParam(value = "Updated user object" ,required=true )  @Valid @RequestBody UpdateUser updateUser) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("This user does not exist"));

        String id = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        Long idLong = Long.parseLong(id);

        if (userId == idLong) {

            userEntity = utils.updateUser(updateUser, userEntity);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest().build().toUri();

            userRepository.save(userEntity);

            return ResponseEntity.created(location).build();

        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

}
