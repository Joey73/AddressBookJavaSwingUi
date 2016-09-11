package com.addressbook;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class AddressBookSwingUiApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(AddressBookSwingUiApplication.class)
			.headless(false)
			.web(false)
			.run(args);
	}
}
