package com.HowlingWolfe.HowlingWolfe;

import com.HowlingWolfe.HowlingWolfe.email.SendEmail;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class HowlingWolfeApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(HowlingWolfeApplication.class, args);
	}

}
