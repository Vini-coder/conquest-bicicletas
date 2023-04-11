package com.conquestbicicletas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.conquestbicicletas.model.dao.AuthenticateUserBackOfficeRequestDAO;
import com.conquestbicicletas.model.dao.AuthenticateUserBackOfficeResponseDAO;
import com.conquestbicicletas.service.AuthenticateUserBackOfficeService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin(value="*")
@RequestMapping("/conquest")
public class AuthenticateUserBackOfficeController {
	
	@Autowired
	private AuthenticateUserBackOfficeService authenticateUserBackOfficeService;

	/**
	 * Recebe email e senha do usuario via Json do front-end
	 * 
	 * @param requestLoginUser
	 * @return O grupo que o usuario pertence 
	 */
	@PostMapping(value = "/user/loginbackoffice", consumes = "application/json", produces = "application/json")
	public ResponseEntity<AuthenticateUserBackOfficeResponseDAO> login(@RequestBody AuthenticateUserBackOfficeRequestDAO requestLoginUser) {
		
		AuthenticateUserBackOfficeResponseDAO response = authenticateUserBackOfficeService.authenticateUserBackOffice(requestLoginUser);
		
		if(response != null) {
			log.info("[INFO] Login successful");
			return  ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
		}
		log.error("[ERROR] Error when logging in");
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new AuthenticateUserBackOfficeResponseDAO());
	}
}
