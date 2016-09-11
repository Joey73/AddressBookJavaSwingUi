package com.addressbook.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class AddressBookView{
	private ResourceBundle bundle;

	private JFrame addressBookFrame;
	private Container contentPane;
	
	private JPanel nameListPanel;
	private JPanel detailsPanel;
	private JPanel buttonsPanel;
	
	private JList<String> nameList;
	private JTable nameTable;

	private JLabel labelFirstName, labelLastName, labelAddress1, labelAddress2, labelCity, labelZip, labelState, labelCountry;
	private JTextField tfFirstName, tfLastName, tfAddress1, tfAddress2, tfCity, tfState, tfZip, tfCountry;
	private JButton buttonNew, buttonEdit, buttonDelete, buttonSave, buttonEnglish, buttonGerman;
	
	public AddressBookView(ResourceBundle bundle){
		this.bundle = bundle;
		addressBookFrame = new JFrame("AddressBook");
		contentPane = addressBookFrame.getContentPane();
		contentPane.setLayout(new BorderLayout());

		nameListPanel = new JPanel(new BorderLayout());
		nameListPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 5));
		detailsPanel = new JPanel(new GridBagLayout());
		buttonsPanel = new JPanel(new FlowLayout());

		centerWindow();
	}
	
	public void createGui(){
		initComponents();
		setNameTableComponents();
		setDetailComponents();
		setButtonComponents();

		contentPane.add(nameListPanel, BorderLayout.WEST);
		contentPane.add(detailsPanel, BorderLayout.CENTER);
		contentPane.add(buttonsPanel, BorderLayout.SOUTH);
	
		addressBookFrame.setSize(900, 400);
		addressBookFrame.setResizable(true);
		addressBookFrame.setVisible(true);
		addressBookFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
	private void initComponents(){
		initLabels();
		initTextFields();
		initButtons();
	}

	private void initButtons() {
		buttonNew = new JButton(bundle.getString("com.addressbook.view.new"));
		buttonEdit = new JButton(bundle.getString("com.addressbook.view.edit"));
		buttonDelete = new JButton(bundle.getString("com.addressbook.view.delete"));
		buttonSave = new JButton(bundle.getString("com.addressbook.view.save"));

		buttonEnglish = new JButton(bundle.getString("com.addressbook.view.english"));
		buttonGerman = new JButton(bundle.getString("com.addressbook.view.german"));
	}

	private void initTextFields() {
		tfFirstName = new JTextField(20);
		tfLastName = new JTextField(20);
		tfAddress1 = new JTextField(20);
		tfAddress2 = new JTextField(20);
		tfCity = new JTextField(20);
		tfState = new JTextField(20);
		tfZip = new JTextField(5);
		tfCountry = new JTextField(20);
	}

	private void initLabels() {
		this.labelFirstName = new JLabel(bundle.getString("com.addressbook.view.firstname"));
		this.labelLastName = new JLabel(bundle.getString("com.addressbook.view.lastname"));
		this.labelAddress1 = new JLabel(bundle.getString("com.addressbook.view.address1"));
		this.labelAddress2 = new JLabel(bundle.getString("com.addressbook.view.address2"));
		this.labelCity = new JLabel(bundle.getString("com.addressbook.view.city"));
		this.labelState = new JLabel(bundle.getString("com.addressbook.view.state"));
		this.labelZip = new JLabel(bundle.getString("com.addressbook.view.zip"));
		this.labelCountry = new JLabel(bundle.getString("com.addressbook.view.country"));
	}

	public void setNameTableComponents(){
		JScrollPane scrollPane = new JScrollPane(this.nameTable);
		scrollPane.setPreferredSize(new Dimension(200, 100));
		nameListPanel.add(scrollPane, BorderLayout.CENTER);
	}

	private void setDetailComponents(){
		detailsPanel.removeAll();
		// First Name
		GridBagConstraints gbc00 = getGridBagConstraints(0, 0, 5, 5, 5, 5);
		detailsPanel.add(labelFirstName, gbc00);
		
		GridBagConstraints gbc10 = getGridBagConstraints(1, 0, 5, 5, 5, 5);
		gbc10.gridwidth = 2;
		gbc10.fill = GridBagConstraints.BOTH;
		detailsPanel.add(tfFirstName, gbc10);
		
		// Last Name
		GridBagConstraints gbc30 = getGridBagConstraints(3, 0, 5, 5, 5, 5);
		detailsPanel.add(labelLastName, gbc30);
		
		GridBagConstraints gbc40 = getGridBagConstraints(4, 0, 5, 5, 5, 5);
		gbc40.gridwidth = 2;
		gbc40.fill = GridBagConstraints.BOTH;
		detailsPanel.add(tfLastName, gbc40);

		// Address1
		GridBagConstraints gbc02 = getGridBagConstraints(0, 2, 5, 5, 5, 5);
		detailsPanel.add(labelAddress1, gbc02);
		
		GridBagConstraints gbc12 = getGridBagConstraints(1, 2, 5, 5, 5, 5);
		gbc12.gridwidth = 2;
		gbc12.fill = GridBagConstraints.BOTH;
		detailsPanel.add(tfAddress1, gbc12);
		
		// Address2
		GridBagConstraints gbc03 = getGridBagConstraints(0, 3, 5, 5, 5, 5);
		detailsPanel.add(labelAddress2, gbc03);
		
		GridBagConstraints gbc13 = getGridBagConstraints(1, 3, 5, 5, 5, 5);
		gbc13.gridwidth = 2;
		gbc13.fill = GridBagConstraints.BOTH;
		detailsPanel.add(tfAddress2, gbc13);

		// City
		GridBagConstraints gbc04 = getGridBagConstraints(0, 4, 5, 5, 5, 5);
		detailsPanel.add(labelCity, gbc04);
		
		GridBagConstraints gbc14 = getGridBagConstraints(1, 4, 5, 5, 5, 5);
		gbc14.gridwidth = 2;
		gbc14.fill = GridBagConstraints.BOTH;
		detailsPanel.add(tfCity, gbc14);

		// State
		GridBagConstraints gbc05 = getGridBagConstraints(0, 5, 5, 5, 5, 5);
		detailsPanel.add(labelState, gbc05);
		
		GridBagConstraints gbc15 = getGridBagConstraints(1, 5, 5, 5, 5, 5);
		gbc15.gridwidth = 2;
		gbc15.fill = GridBagConstraints.BOTH;
		detailsPanel.add(tfState, gbc15);

		// ZIP
		GridBagConstraints gbc35 = getGridBagConstraints(3, 5, 5, 5, 5, 5);
		detailsPanel.add(labelZip, gbc35);
		
		GridBagConstraints gbc45 = getGridBagConstraints(4, 5, 5, 5, 5, 5);
		detailsPanel.add(tfZip, gbc45);

		// Country
		GridBagConstraints gbc06 = getGridBagConstraints(0, 6, 5, 5, 5, 5);
		detailsPanel.add(labelCountry, gbc06);
		
		GridBagConstraints gbc16 = getGridBagConstraints(1, 6, 5, 5, 5, 5);
		gbc16.gridwidth = 2;
		gbc16.fill = GridBagConstraints.BOTH;
		detailsPanel.add(tfCountry, gbc16);
	}
	
	private void setButtonComponents(){
		buttonsPanel.add(buttonNew);
		buttonsPanel.add(buttonEdit);
		buttonsPanel.add(buttonDelete);
		buttonsPanel.add(buttonSave);
		
		buttonsPanel.add(buttonEnglish);
		buttonsPanel.add(buttonGerman);
	}
	
	private GridBagConstraints getGridBagConstraints(int x, int y, int insetTop, int insetLeft, int insetBottom, int insetRight){
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = x;
		gridBagConstraints.gridy = y;
		gridBagConstraints.insets = new Insets(insetTop, insetBottom, insetLeft, insetRight);
		return gridBagConstraints;
	}
	
	public void centerWindow() {
	    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - this.addressBookFrame.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - this.addressBookFrame.getHeight()) / 2);
	    this.addressBookFrame.setLocation(x, y);
	}

	public JTextField getTfFirstName() {
		return tfFirstName;
	}

	public void setTfFirstName(JTextField tfFirstName) {
		this.tfFirstName = tfFirstName;
	}

	public JTextField getTfLastName() {
		return tfLastName;
	}

	public void setTfLastName(JTextField tfLastName) {
		this.tfLastName = tfLastName;
	}

	public JTextField getTfAddress1() {
		return tfAddress1;
	}

	public void setTfAddress1(JTextField tfAddress1) {
		this.tfAddress1 = tfAddress1;
	}

	public JTextField getTfAddress2() {
		return tfAddress2;
	}

	public void setTfAddress2(JTextField tfAddress2) {
		this.tfAddress2 = tfAddress2;
	}

	public JTextField getTfCity() {
		return tfCity;
	}

	public void setTfCity(JTextField tfCity) {
		this.tfCity = tfCity;
	}

	public JTextField getTfState() {
		return tfState;
	}

	public void setTfState(JTextField tfState) {
		this.tfState = tfState;
	}

	public JTextField getTfZip() {
		return tfZip;
	}

	public void setTfZip(JTextField tfZip) {
		this.tfZip = tfZip;
	}

	public JTextField getTfCountry() {
		return tfCountry;
	}

	public void setTfCountry(JTextField tfCountry) {
		this.tfCountry = tfCountry;
	}

	public JList<String> getNameList() {
		return nameList;
	}

	public void setNameList(JList<String> nameList) {
		this.nameList = nameList;
	}

	public JTable getNameTable() {
		return nameTable;
	}

	public void setNameTable(JTable nameTable) {
		this.nameTable = nameTable;
	}

	public JFrame getAddressBookFrame() {
		return addressBookFrame;
	}

	public void setAddressBookFrame(JFrame addressBookFrame) {
		this.addressBookFrame = addressBookFrame;
	}

	public JButton getButtonNew() {
		return buttonNew;
	}

	public void setButtonNew(JButton buttonNew) {
		this.buttonNew = buttonNew;
	}

	public JButton getButtonEdit() {
		return buttonEdit;
	}

	public void setButtonEdit(JButton buttonEdit) {
		this.buttonEdit = buttonEdit;
	}

	public JButton getButtonDelete() {
		return buttonDelete;
	}

	public void setButtonDelete(JButton buttonDelete) {
		this.buttonDelete = buttonDelete;
	}

	public JButton getButtonSave() {
		return buttonSave;
	}

	public void setButtonSave(JButton buttonSave) {
		this.buttonSave = buttonSave;
	}
	
	public JButton getButtonEnglish() {
		return buttonEnglish;
	}

	public void setButtonEnglish(JButton buttonEnglish) {
		this.buttonEnglish = buttonEnglish;
	}
	
	public JButton getButtonGerman() {
		return buttonGerman;
	}

	public void setButtonGerman(JButton buttonGerman) {
		this.buttonGerman = buttonGerman;
	}
	
	public ResourceBundle getBundle() {
		return bundle;
	}

	public void setBundle(ResourceBundle bundle) {
		this.bundle = bundle;
	}
}
