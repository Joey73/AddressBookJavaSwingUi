package com.addressbook.data;

import java.util.List;

public class ContactDataEntryList {
	private List<ContactDataEntry> contactDataEntryList;

	public ContactDataEntryList(){
	}

	public ContactDataEntryList(List<ContactDataEntry> contactDataEntryList){
		this.contactDataEntryList = contactDataEntryList;
	}

	public List<ContactDataEntry> getContactDataEntryList() {
		return contactDataEntryList;
	}

	public void setContactDataEntryList(List<ContactDataEntry> contactDataEntryList) {
		this.contactDataEntryList = contactDataEntryList;
	}

}