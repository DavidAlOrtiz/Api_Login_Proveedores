package mx.edu.app.uno.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import mx.edu.app.uno.web.firebase.FirebaseConfirg;

@SpringBootApplication
public class LoginSpringBootApplication {

	public static void main(String[] args) {

		SpringApplication.run(LoginSpringBootApplication.class, args);
	}

}
