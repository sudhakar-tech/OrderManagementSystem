package com.wellsfargo.springbootdatajpa.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wellsfargo.springbootdatajpa.model.AuthRequest;
import com.wellsfargo.springbootdatajpa.util.JwtUtil;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationController {
	
    @Autowired
    private JwtUtil jwtUtil;
    
	@Autowired
	private AuthenticationManager authenticationManager;

    @GetMapping("/")
    public String welcome() {
        return "Welcome to hello world !!";
    }
    
    @PostMapping("/authenticate")
	public String generateToken(@RequestBody AuthRequest authRequest,HttpServletRequest request) throws Exception {
		try {
			request.getSession().setAttribute("user", authRequest.getUserName());
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
		} catch (Exception ex) {
			throw new Exception("inavalid username/password");
		}
		return jwtUtil.generateToken(authRequest.getUserName());
	}
}
