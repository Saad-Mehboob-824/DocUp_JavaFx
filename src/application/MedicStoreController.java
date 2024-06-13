package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.sql.SQLException;
import java.util.List;

public class MedicStoreController {
    private static MedicStoreController instance;
    private static int currentPatientId;
    
    @FXML
    private TextField MedicineSearchName;
    
    @FXML
    private TextField Medicine_Id;
    
    @FXML
    private TableView<Medicine> medicineTable;
    
    @FXML
    private TableColumn<Medicine, Integer> medicineIdColumn;
    
    @FXML
    private TableColumn<Medicine, String> medicineNameColumn;
    
    @FXML
    private TableColumn<Medicine, Double> medicinePriceColumn;
    
    @FXML
    private Button searchButton;
    
    @FXML
    private Button buyButton;
    
    private MedicStoreDao medicStoreDao;
    private PatientDao patientDao;

    public static MedicStoreController getInstance() {
        if (instance == null) {
            instance = new MedicStoreController();
        }
        return instance;
    }

    @FXML
    public void initialize() {
        medicineIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        medicineNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        medicinePriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        try {
            List<Medicine> medicines = medicStoreDao.getAllMedicines();
            populateTable(medicines);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void populateTable(List<Medicine> medicines) {
        ObservableList<Medicine> medicineList = FXCollections.observableArrayList(medicines);
        medicineTable.setItems(medicineList);
    }

    public MedicStoreController() {
        this.medicStoreDao = new MedicStoreDao();
        this.patientDao = new PatientDao();
    }

    @FXML
    void searchMedicine(ActionEvent event) {
        String name = MedicineSearchName.getText();
        if (name.isEmpty()) {
            // Handle empty input
            return;
        }

        try {
            List<Medicine> searchedMedicines = medicStoreDao.getMedicinesByName(name);
            populateTable(searchedMedicines);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
  private void buyMedicine(ActionEvent event) {
    String id = Medicine_Id.getText();
    if (id.isEmpty()) {
        return;
    }

    try {
        Medicine medicine = medicStoreDao.getMedicineById(Integer.parseInt(id));
        if (medicine == null) {
            return;
        }

        String name = medicine.getName();
        
        String currentMedicines = patientDao.getMedicines(currentPatientId);
        
        if (!currentMedicines.isEmpty()) {
            currentMedicines += ", ";
        }
        currentMedicines += name;

        boolean value = patientDao.storeMedicines(currentPatientId, currentMedicines);
        if(value==false) {
        	Medicine_Id.setStyle("-fx-background-color: red ;");
        }
        
        loadAllMedicines();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    private void loadAllMedicines() {
        try {
            List<Medicine> allMedicines = medicStoreDao.getAllMedicines();
            medicineTable.getItems().setAll(allMedicines);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
