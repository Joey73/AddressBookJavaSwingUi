package com.addressbook.restclient;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.addressbook.data.ContactDataEntry;
import com.addressbook.data.ContactDataEntryList;

public class ContactDataServiceConsumer {
	private static final String BASE_URL = "http://localhost:8080/contacts/";

	public ContactDataServiceConsumer() {
	}

	public ContactDataEntryList getAllContacts(){
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<ContactDataEntryList> responseEntity = restTemplate.getForEntity(BASE_URL + "getall", ContactDataEntryList.class);
		ContactDataEntryList contactDataList = responseEntity.getBody();
		
		return contactDataList;
	}
	
	public ContactDataEntry getContact(long currentContactDataEntryId){
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<ContactDataEntry> responseEntity = restTemplate.getForEntity(BASE_URL + "get/{id}", ContactDataEntry.class, currentContactDataEntryId);
		return responseEntity.getBody();
	}
	
	public ContactDataEntry saveContact(ContactDataEntry contactDataEntry){
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.postForObject(BASE_URL + "save", contactDataEntry, ContactDataEntry.class);
	}

	public void deleteContact(Long currentContactDataEntryId){
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete(BASE_URL + "delete/{id}", currentContactDataEntryId);
	}
}
