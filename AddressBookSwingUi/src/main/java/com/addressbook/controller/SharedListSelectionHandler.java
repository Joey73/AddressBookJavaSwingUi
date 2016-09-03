package com.addressbook.controller;

import java.util.List;

import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.addressbook.data.ContactDataEntry;

public class SharedListSelectionHandler implements ListSelectionListener {
	private List<ContactDataEntry> contactDataEntries;
	private ContactDataEntry contactDataEntry = null;
	private Integer index = null;
	private JTextField tfFirstName, tfLastName, tfAddress1, tfAddress2, tfCity, tfZip, tfState, tfCountry;

	
	public SharedListSelectionHandler(List<ContactDataEntry> contactDataEntries) {
		this.contactDataEntries = contactDataEntries;
	}
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		ListSelectionModel lsm = (ListSelectionModel)e.getSource();
		// int firstIndex = e.getFirstIndex();
		this.index = e.getLastIndex();
		// boolean isAdjusting = e.getValueIsAdjusting();
		
		if (lsm.isSelectionEmpty()) {
			System.out.println("Keine Auswahl");
		} else {
            System.out.println("Index: " + index);
            this.contactDataEntry = this.contactDataEntries.get(index);
		}
	}
	
	public Integer getIndex() {
		return index;
	}
	public void setIndex(Integer index) {
		this.index = index;
	}

	public ContactDataEntry getContactDataEntry() {
		return contactDataEntry;
	}

	public void setContactDataEntry(ContactDataEntry contactDataEntry) {
		this.contactDataEntry = contactDataEntry;
	}
}
