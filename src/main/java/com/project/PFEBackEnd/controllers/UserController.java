package com.project.PFEBackEnd.controllers;

import com.project.PFEBackEnd.detailsService.JwtService;
import com.project.PFEBackEnd.entities.AuthRequest;
import com.project.PFEBackEnd.entities.Utilisateur;
import com.project.PFEBackEnd.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private IUserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;


    @PostMapping("/login")
    public String addUser(@RequestBody AuthRequest authRequest){
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
        if(authenticate.isAuthenticated()){
            return jwtService.generateToken(authRequest.getUserName());
        }else {
            throw new UsernameNotFoundException("Invalid user request");
        }
    }

    public UserController(IUserService userService) {
        this.userService = userService;
    }


    @GetMapping(path= "/getAll")
    //@PreAuthorize("hasAuthority('USER')")
    public List<Utilisateur> getAll(){
        return userService.getAllUsers();
    }

    @GetMapping(path= "/getUser/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Utilisateur> getUserById(@PathVariable Long id){
        Utilisateur utilisateur= userService.findById(id);
        return (utilisateur== null)
                ? new ResponseEntity<Utilisateur>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<Utilisateur>(utilisateur,HttpStatus.OK);
    }


    @PostMapping(path = "/addUser")
    //@PreAuthorize("hasAuthority('ADMIN')")
    public Utilisateur addUser(@RequestBody Utilisateur user){
        return userService.createUser(user);
    }

    @PutMapping(path = "/updateUser")
    public Utilisateur updateUser(@RequestBody Utilisateur user){
        return userService.updateUser(user);
    }

    @DeleteMapping(path = "/deleteUser/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }

}
