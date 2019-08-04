package javafx.view;

import javafx.MainApp;
import javafx.fxml.FXML;
import javafx.model.Person;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.DateUtil;

public class PersonOverviewController {

	@FXML
	private TableView<Person> personTable;
	@FXML
	private TableColumn<Person, String> firstNameColumn;
	@FXML
	private TableColumn<Person, String> lastNameColumn;

	@FXML
	private Label firstNameLabel;
	@FXML
	private Label lastNameLabel;
	@FXML
	private Label streetLabel;
	@FXML
	private Label postalCodeLabel;
	@FXML
	private Label cityLabel;
	@FXML
	private Label birthdayLabel;

	private MainApp mainApp;

	public PersonOverviewController() {

	}

	@FXML
	private void initialize() {
		firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
		lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
		
		showPersonDetails(null);
		
		 personTable.getSelectionModel().selectedItemProperty().addListener(
		            (observable, oldValue, newValue) -> showPersonDetails(newValue));	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;

		personTable.setItems(mainApp.getPersonData());
	}
	
	
	private void showPersonDetails(Person person) {
		if(person != null) {
			firstNameLabel.setText(person.getFirstName().toString());
			lastNameLabel.setText(person.getLastName().toString());
			streetLabel.setText(person.getStreet().toString());
			postalCodeLabel.setText(Integer.toString(person.getPostalCode()));
			cityLabel.setText(person.getCity().toString());
			birthdayLabel.setText(DateUtil.format(person.getBirthday()));
			
		}else {
			firstNameLabel.setText("");
			lastNameLabel.setText("");
			streetLabel.setText("");
			postalCodeLabel.setText("");
			cityLabel.setText("");
			birthdayLabel.setText("");
			
		}
	}
	@FXML
	private void handleDeletePerson() {
		int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
		if(selectedIndex >= 0) {
			personTable.getItems().remove(selectedIndex);
			
		}else {
		}
	}
	
	@FXML
	private void handleNewPerson() {
	    Person tempPerson = new Person();
	    boolean okClicked = mainApp.showPersonEditDialog(tempPerson);
	    if (okClicked) {
	        mainApp.getPersonData().add(tempPerson);
	    }
	}


	@FXML
	private void handleEditPerson() {
	    Person selectedPerson = personTable.getSelectionModel().getSelectedItem();
	    if (selectedPerson != null) {
	        boolean okClicked = mainApp.showPersonEditDialog(selectedPerson);
	        if (okClicked) {
	            showPersonDetails(selectedPerson);
	        }

	    } else {
	        // Nothing selected.
	    }
	}
	

	

}
