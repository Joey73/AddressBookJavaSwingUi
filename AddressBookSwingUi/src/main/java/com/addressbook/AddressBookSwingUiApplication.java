package com.addressbook;

//import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class AddressBookSwingUiApplication {

	public static void main(String[] args) {
		//SpringApplication.run(AddressBookSwingUiApplication.class, args);
		new SpringApplicationBuilder(AddressBookSwingUiApplication.class)
			.headless(false)
			.web(false)
			.run(args);
	}
}
