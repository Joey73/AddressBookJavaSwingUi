package com.addressbook;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class AddressBookSwingUiApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(AddressBookSwingUiApplication.class)
			.headless(false)
			.web(false)
			.run(args);
	}
}
