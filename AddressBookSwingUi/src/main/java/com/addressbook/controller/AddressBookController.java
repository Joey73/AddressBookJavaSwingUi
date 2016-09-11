package com.addressbook.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.springframework.stereotype.Component;

import com.addressbook.data.ContactDataEntry;
import com.addressbook.model.AddressBookModel;
import com.addressbook.view.AddressBookView;

@Component
public class AddressBookController implements ActionListener{
	private ResourceBundle bundle = null;
	private DefaultTableModel contactTableModel;
	
	private AddressBookModel addressBookModel;
	private AddressBookView addressBookView;
	
	public AddressBookController(){
		bundle = ResourceBundle.getBundle("messages", Locale.ENGLISH);
		this.addressBookModel = new AddressBookModel();
		this.addressBookView = new AddressBookView(bundle);
		createFilledJTableComponent();
		this.addressBookView.createGui();
		addNameTableSelectionEvent();
		this.addressBookView.centerWindow();
		addActionListener();
	}

	private void createFilledJTableComponent() {
		fillContactTableModel();
		JTable jTable = new JTable(this.contactTableModel);
		hideIdColumn(jTable);
		setNameTableComponent(jTable);
	}

	private void fillContactTableModel() {
		Object[] columnNames = {"id", "Firstname", "Lastname"};
		this.contactTableModel = new DefaultTableModel(new Object[0][0], columnNames);
		for (ContactDataEntry contactDataEntry : this.addressBookModel.getContactDataEntries()) {
			Object[] o = new Object[9];
			o[0] = contactDataEntry.getId();
			o[1] = contactDataEntry.getFirstName();
			o[2] = contactDataEntry.getLastName();
			this.contactTableModel.addRow(o);
		}
	}

	private void hideIdColumn() {
		JTable nameTable = this.addressBookView.getNameTable();
		nameTable.setModel(this.contactTableModel);
		nameTable.getColumnModel().removeColumn(nameTable.getColumnModel().getColumn(0));
	}

	private void hideIdColumn(JTable jTable) {
		jTable.getColumnModel().removeColumn(jTable.getColumnModel().getColumn(0));
	}

	private void setNameTableComponent(JTable jTable) {
		this.addressBookView.setNameTable(jTable);
		this.addressBookView.getNameTable().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.addressBookView.setNameTableComponents();
	}

	public void addNameTableSelectionEvent(){
		ListSelectionModel nameTableSelectionModel = this.addressBookView.getNameTable().getSelectionModel();
		nameTableSelectionModel.addListSelectionListener(new ListSelectionListener(){
			@Override
			public void valueChanged(ListSelectionEvent listSelectionEvent) {
				if(AddressBookController.this.getSelectedContactDataEntryId() == null){
					AddressBookController.this.addressBookView.getNameTable().clearSelection();
				}else{
					long selectedContactDataEntryId = AddressBookController.this.getSelectedContactDataEntryId();
					ContactDataEntry contactDataEntry = AddressBookController.this.addressBookModel.getContactDataEntry(selectedContactDataEntryId);
					AddressBookController.this.setTextFields(contactDataEntry);
				}
			}
		});
	}
	
	private int getSelectedRow(){
		return this.addressBookView.getNameTable().getSelectedRow();
	}
	
	private Long getSelectedContactDataEntryId(){
		int selectedRow = AddressBookController.this.getSelectedRow();
		if(selectedRow == -1){
			return null;
		}
		return (Long)AddressBookController.this.contactTableModel.getValueAt(selectedRow, 0);
	}
	
	public void setTextFields(ContactDataEntry contactDataEntry){
		this.addressBookView.getTfFirstName().setText(contactDataEntry.getFirstName());
		this.addressBookView.getTfLastName().setText(contactDataEntry.getLastName());
		this.addressBookView.getTfAddress1().setText(contactDataEntry.getAddress1());
		this.addressBookView.getTfAddress2().setText(contactDataEntry.getAddress2());
		this.addressBookView.getTfCity().setText(contactDataEntry.getCity());
		this.addressBookView.getTfState().setText(contactDataEntry.getState());
		this.addressBookView.getTfZip().setText(contactDataEntry.getZip());
		this.addressBookView.getTfCountry().setText(contactDataEntry.getCountry());
		
		setAllTextFieldsEditable(false);
	}
	
	public void addActionListener(){
		this.addressBookView.getButtonNew().addActionListener(this);
		this.addressBookView.getButtonEdit().addActionListener(this);
		this.addressBookView.getButtonDelete().addActionListener(this);
		this.addressBookView.getButtonSave().addActionListener(this);
		
		this.addressBookView.getButtonEnglish().addActionListener(this);
		this.addressBookView.getButtonGerman().addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source == this.addressBookView.getButtonNew()){
			performButtonNewAction();
		}else if(source == this.addressBookView.getButtonEdit()){
			performButtonEditAction();
		}else if(source == this.addressBookView.getButtonDelete()){
			performButtonDeleteAction();
		}else if(source == this.addressBookView.getButtonSave()){
			performButtonSaveAction();
		}else if(source == this.addressBookView.getButtonEnglish()){
			performButtonEnglishAction();
		}else if(source == this.addressBookView.getButtonGerman()){
			performButtonGermanAction();
		}else{
			// ?
		}
	}
	
	public void performButtonNewAction(){
		emptyAllTextFields();
		this.addressBookView.getNameTable().clearSelection();
		setAllTextFieldsEditable(true);
	}
	
	public void performButtonEditAction(){
		setAllTextFieldsEditable(true);
	}
	
	public void performButtonDeleteAction(){
		if(getSelectedContactDataEntryId() != null){
			this.addressBookModel.deleteContactDataEntry(getSelectedContactDataEntryId());
			
			emptyAllTextFields();
			this.addressBookView.getNameTable().clearSelection();
			setAllTextFieldsEditable(false);
			
			this.addressBookModel.refreshContactDataEntries();
			fillContactTableModel();
			hideIdColumn();
		}
	}
	
	public void performButtonSaveAction(){
		setAllTextFieldsEditable(false);
		ContactDataEntry contactDataEntry = createContactDataEntryWithCurrentTextfieldContents();
		
		// In case of an update, add the ID
		if(getSelectedContactDataEntryId() != null){
			contactDataEntry.setId(getSelectedContactDataEntryId());
		}
		ContactDataEntry contactDataEntrySaved = this.addressBookModel.saveContactDataEntry(contactDataEntry);
		emptyAllTextFields();

		this.addressBookModel.refreshContactDataEntries();
		fillContactTableModel();
		hideIdColumn();

		selectRow(contactDataEntrySaved);
	}

	public void performButtonEnglishAction(){
		bundle = ResourceBundle.getBundle("messages", Locale.ENGLISH);
//		this.addressBookView.setBundle(bundle);
//		this.addressBookView.createGui();
		this.addressBookView = new AddressBookView(bundle);
		createFilledJTableComponent();
		this.addressBookView.createGui();
		addNameTableSelectionEvent();
		this.addressBookView.centerWindow();
		addActionListener();
	}

	public void performButtonGermanAction(){
		bundle = ResourceBundle.getBundle("messages", Locale.GERMANY);
		this.addressBookView = new AddressBookView(bundle);
		createFilledJTableComponent();
		this.addressBookView.createGui();
		addNameTableSelectionEvent();
		this.addressBookView.centerWindow();
		addActionListener();
	}

	private void selectRow(ContactDataEntry contactDataEntrySaved) {
		if(contactDataEntrySaved != null){
			Integer rowIndex = getRowIndex(contactDataEntrySaved.getId());
			this.addressBookView.getNameTable().setRowSelectionInterval(rowIndex, rowIndex);
		}
	}
	
	private Integer getRowIndex(long currentContactDataEntryId){
		TableModel model = this.addressBookView.getNameTable().getModel();
		for(int i = 0; i < model.getRowCount(); i++){
			if(model.getValueAt(i, 0).equals(currentContactDataEntryId)){
				return i;
			}
		}
		return null;
	}
	
	private ContactDataEntry createContactDataEntryWithCurrentTextfieldContents(){
		ContactDataEntry contactDataEntry = new ContactDataEntry();
		
		contactDataEntry.setFirstName(this.addressBookView.getTfFirstName().getText());
		contactDataEntry.setLastName(this.addressBookView.getTfLastName().getText());
		contactDataEntry.setAddress1(this.addressBookView.getTfAddress1().getText());
		contactDataEntry.setAddress2(this.addressBookView.getTfAddress2().getText());
		contactDataEntry.setCity(this.addressBookView.getTfCity().getText());
		contactDataEntry.setState(this.addressBookView.getTfState().getText());
		contactDataEntry.setZip(this.addressBookView.getTfZip().getText());
		contactDataEntry.setCountry(this.addressBookView.getTfCountry().getText());
		
		return contactDataEntry;
	}
	
	private void emptyAllTextFields() {
		this.addressBookView.getTfFirstName().setText(null);
		this.addressBookView.getTfLastName().setText(null);
		this.addressBookView.getTfAddress1().setText(null);
		this.addressBookView.getTfAddress2().setText(null);
		this.addressBookView.getTfCity().setText(null);
		this.addressBookView.getTfState().setText(null);
		this.addressBookView.getTfZip().setText(null);
		this.addressBookView.getTfCountry().setText(null);
	}
	
	private void setAllTextFieldsEditable(boolean editable) {
		this.addressBookView.getTfFirstName().setEditable(editable);
		this.addressBookView.getTfLastName().setEditable(editable);
		this.addressBookView.getTfAddress1().setEditable(editable);
		this.addressBookView.getTfAddress2().setEditable(editable);
		this.addressBookView.getTfCity().setEditable(editable);
		this.addressBookView.getTfState().setEditable(editable);
		this.addressBookView.getTfZip().setEditable(editable);
		this.addressBookView.getTfCountry().setEditable(editable);
	}
}
