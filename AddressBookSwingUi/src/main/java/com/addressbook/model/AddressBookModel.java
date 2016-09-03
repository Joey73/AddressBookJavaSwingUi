package com.addressbook.model;

import java.util.List;

import com.addressbook.data.ContactDataEntry;
import com.addressbook.data.ContactDataEntryList;
import com.addressbook.restclient.ContactDataServiceConsumer;

public class AddressBookModel {
	private ContactDataServiceConsumer contactDataServiceConsumer;
	private List<ContactDataEntry> contactDataEntries;

	public void refreshContactDataEntries(){
		this.contactDataServiceConsumer = new ContactDataServiceConsumer();
		ContactDataEntryList contactDataList = this.contactDataServiceConsumer.getAllContacts();
		this.contactDataEntries = contactDataList.getContactDataEntryList();
	}

	public List<ContactDataEntry> getContactDataEntries(){
		if(this.contactDataEntries == null){
			refreshContactDataEntries();
		}
		return this.contactDataEntries;
	}

	public ContactDataEntry getContactDataEntry(Long currentContactDataEntryId){
		// this.contactDataServiceConsumer = new ContactDataServiceConsumer();
		// return this.contactDataServiceConsumer.getContact(currentContactDataEntryId);
		for (ContactDataEntry contactDataEntry : this.contactDataEntries) {
			if(contactDataEntry.getId() == currentContactDataEntryId){
				return contactDataEntry;
			}
		}
		return null;
	}

	public ContactDataEntry saveContactDataEntry(ContactDataEntry contactDataEntry){
		this.contactDataServiceConsumer = new ContactDataServiceConsumer();
		return this.contactDataServiceConsumer.saveContact(contactDataEntry);
	}
	
	public void deleteContactDataEntry(Long currentContactDataEntryId){
		this.contactDataServiceConsumer = new ContactDataServiceConsumer();
		this.contactDataServiceConsumer.deleteContact(currentContactDataEntryId);
	}
}
