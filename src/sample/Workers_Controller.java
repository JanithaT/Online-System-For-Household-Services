package sample;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.swing.*;

public class Workers_Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane ancAdminSidebar;

    @FXML
    private JFXButton btnDashboard;

    @FXML
    private JFXButton btnAddWorkers;

    @FXML
    private JFXButton btnViewWorkers;

    @FXML
    private Label wellbl;

    @FXML
    private AnchorPane ancHouseownerSidebar;

    @FXML
    private JFXButton btnViewWorkers_HO;

    @FXML
    private JFXButton btnMenuHome_HO;

    @FXML
    private JFXButton btnMenuMyReservation_HO;

    @FXML
    private JFXButton btnMenuMyProfile_HO;

    @FXML
    private JFXButton btnMenuChangePw_HO;

    @FXML
    private JFXButton btnMenuChangePw_admin;

    @FXML
    private JFXButton btnMenuHouseOwners_admin;

    @FXML
    private JFXButton btnCleaners;

    @FXML
    private JFXButton btnCarpenters;

    @FXML
    private JFXButton btnElectricians;

    @FXML
    private JFXButton btnPlumbers;

    @FXML
    private JFXButton btnMechanics;

    @FXML
    private JFXButton btnPainters;

    @FXML
    private JFXButton btnBackToWorkerRolls;

    @FXML
    private JFXButton btnLogout;

    @FXML
    private AnchorPane ancWorkerRoll;

    @FXML
    private AnchorPane ancAllWorkers;

    @FXML
    private Label lblWorkerroll;

    @FXML
    private TableView<WorkersTable> tabWorkers;

    @FXML
    private TableColumn<WorkersTable, Integer> colWorkerId;

    @FXML
    private TableColumn<WorkersTable, String> colFirstName;

    @FXML
    private TableColumn<WorkersTable, String> colLastName;

    @FXML
    private TableColumn<WorkersTable, Integer> colMobileno;

    @FXML
    private TableColumn<WorkersTable, Integer> colHomeno;


    @FXML
    private AnchorPane ancAdminEdit;

    @FXML
    private JFXButton btnUpdateWorker;

    @FXML
    private JFXButton btnDeleteWorker;

    @FXML
    private AnchorPane ancHouseownerEdit;

    @FXML
    private JFXButton btnReserveWorker;

    @FXML
    private AnchorPane ancAdminEdit2;

    @FXML
    private JFXTextField txtWorkerId;

    @FXML
    private JFXTextField txtFirstname;

    @FXML
    private JFXTextField txtLastname;

    @FXML
    private JFXTextField txtMobileno;

    @FXML
    private JFXTextField txtHomeno;

    @FXML
    private JFXComboBox<String> cmbWorkerroll;

    @FXML
    private JFXButton btnMenuFeedback_Ho;

    @FXML
    void actLogout(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));
        stage.setTitle("Household Service");

        Controller controller = loader.getController();

        stage.show();
        btnLogout.getScene().getWindow().hide();
    }

    @FXML
    void actmenuDashboard_admin(ActionEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("admin.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));
        stage.setTitle("Admin");

        admin Admin = loader.getController();
        stage.show();
        Admin.actMenuDashboard();
        btnDashboard.getScene().getWindow().hide();
    }

    @FXML
    void actMenuChangePw_HO(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("HouseOwner_Index.fxml"));
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(loader.load()));
        stage1.setTitle("House Owner");

        HouseOwner_Index_Controller houseOwner_index_controller = loader.getController();
        houseOwner_index_controller.Details(username,"house_owners");
        stage1.show();
        houseOwner_index_controller.actMenuChangePw_HO();
        btnMenuChangePw_HO.getScene().getWindow().hide();
    }

    @FXML
    void actMenuFeedback_Ho(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("HouseOwner_Index.fxml"));
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(loader.load()));
        stage1.setTitle("House Owner");

        HouseOwner_Index_Controller houseOwner_index_controller = loader.getController();
        houseOwner_index_controller.Details(username,"house_owners");
        stage1.show();
        houseOwner_index_controller.actMenuFeedback_Ho();
        btnMenuMyProfile_HO.getScene().getWindow().hide();
    }

    @FXML
    void actMenuChangePw_admin(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("admin.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));
        stage.setTitle("Admin");

        admin Admin = loader.getController();
        stage.show();
        Admin.actMenuChangePw_admin();
        btnMenuChangePw_admin.getScene().getWindow().hide();
    }

    @FXML
    void actMenuHouseOwners_admin(ActionEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("admin.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));
        stage.setTitle("Admin");

        admin Admin = loader.getController();
        stage.show();
        Admin.actMenuHouseOwners();
        btnMenuHouseOwners_admin.getScene().getWindow().hide();
    }

    @FXML
    void actMenuHome_HO(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("HouseOwner_Index.fxml"));
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(loader.load()));
        stage1.setTitle("House Owner");

        HouseOwner_Index_Controller houseOwner_index_controller = loader.getController();
        houseOwner_index_controller.Details(username,"house_owners");
        stage1.show();
        houseOwner_index_controller.actMenuHome_HO();
        btnMenuMyProfile_HO.getScene().getWindow().hide();
    }

    @FXML
    void actMenuMyReservation_HO(ActionEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("HouseOwner_Index.fxml"));
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(loader.load()));
        stage1.setTitle("House Owner");

        HouseOwner_Index_Controller houseOwner_index_controller = loader.getController();
        houseOwner_index_controller.Details(username,"house_owners");
        stage1.show();
        houseOwner_index_controller.actMenuMyReservation_HO();
        btnMenuMyProfile_HO.getScene().getWindow().hide();
    }

    @FXML
    void actMenuMyProfile_HO(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("HouseOwner_Index.fxml"));
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(loader.load()));
        stage1.setTitle("House Owner");

        HouseOwner_Index_Controller houseOwner_index_controller = loader.getController();
        houseOwner_index_controller.Details(username,"house_owners");
        stage1.show();
        houseOwner_index_controller.actMenuMyProfile_HO();
        btnMenuMyProfile_HO.getScene().getWindow().hide();
    }


    @FXML
    void actBackToWorkerRolls(ActionEvent event) {
        ancWorkerRoll.setVisible(false);
        ancAllWorkers.setVisible(true);
    }


    Connection conn;
    PreparedStatement pst, pst2, pst3, pst4;
    ResultSet rs;
    String userroll, workerroll, username;

    ObservableList<WorkersTable> oblist = FXCollections.observableArrayList();

    @FXML
    void viewCarpenters(ActionEvent event) throws SQLException {
        String sqlCarpenter = "SELECT * FROM workers WHERE `workerroll`='Carpenter'";
        workerDetailsCall(sqlCarpenter, "Carpenter");
    }

    @FXML
    void viewCleaners(ActionEvent event) {
        String sqlCleaner = "SELECT * FROM workers WHERE `workerroll`='Cleaner'";
        workerDetailsCall(sqlCleaner, "Cleaner");
    }

    @FXML
    void viewElectricians(ActionEvent event) {
        String sqlElectrician = "SELECT * FROM workers WHERE `workerroll`='Electrician'";
        workerDetailsCall(sqlElectrician, "Electrician");
    }

    @FXML
    void viewMechanics(ActionEvent event) {
        String sqlMechanic = "SELECT * FROM workers WHERE `workerroll`='Mechanic'";
        workerDetailsCall(sqlMechanic, "Mechanic");
    }

    @FXML
    void viewPainters(ActionEvent event) {
        String sqlPainter = "SELECT * FROM workers WHERE `workerroll`='Painter'";
        workerDetailsCall(sqlPainter, "Painter");
    }


    @FXML
    void viewPlumbers(ActionEvent event) {
        String sqlPlumber = "SELECT * FROM workers WHERE `workerroll`='Plumber'";
        workerDetailsCall(sqlPlumber, "Plumber");

    }

    public void getUserroll(String uname, String uroll) {
        this.userroll = uroll;
        this.username = uname;

        if (userroll.equals("admin")) {
            ancAdminSidebar.setVisible(true);
        } else if (userroll.equals("house_owners")) {
            ancHouseownerSidebar.setVisible(true);
        }
    }
    String query;

    public void workerDetailsCall(String sql, String workerroll) {
        this.query=sql;

        clearFieldDetails();
        btnReserveWorker.setDisable(true);
        btnUpdateWorker.setDisable(true);
        btnDeleteWorker.setDisable(true);

        this.workerroll = workerroll;
        lblWorkerroll.setText(workerroll + "s Details");

        getUserroll(username, userroll);
        ancAllWorkers.setVisible(false);
        ancWorkerRoll.setVisible(true);
        tabWorkers.getItems().clear();

        if (userroll.equals("admin")) {
            ancAdminEdit.setVisible(true);
            cmbWorkerroll.setVisible(true);
            setEditableFieldDetails();
        } else if (userroll.equals("house_owners")) {
            ancHouseownerEdit.setVisible(true);
        }

        tableView(query);
    }

    void tableView(String query){
        //Table view//
        try {
            Connection con = mysqlconnect.ConnectDb();
            ResultSet rs = con.createStatement().executeQuery(query);

            while (rs.next()) {
                oblist.addAll(new WorkersTable(rs.getInt("workerID"), rs.getString("firstname"), rs.getString("lastname"), rs.getInt("personalno"), rs.getInt("homeno")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);

        }
        colWorkerId.setCellValueFactory(new PropertyValueFactory<>("workerID"));
        colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        colLastName.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        colMobileno.setCellValueFactory(new PropertyValueFactory<>("personalno"));
        colHomeno.setCellValueFactory(new PropertyValueFactory<>("homeno"));

        tabWorkers.setItems(oblist);
    }

    WorkersTable selected;
    int wid, wmobile, whome, ho_id;
    String wfname, wlname, wroll, wuname, ho_title, ho_firstname, ho_lastname, ho_fullname;

    void clearFieldDetails() {
        txtWorkerId.setText("");
        txtFirstname.setText("");
        txtLastname.setText("");
        txtMobileno.setText("");
        txtHomeno.setText("");
        cmbWorkerroll.getSelectionModel().select(null);
    }

    void setEditableFieldDetails() {
        txtFirstname.setEditable(true);
        txtLastname.setEditable(true);
        txtMobileno.setEditable(true);
        txtHomeno.setEditable(true);
    }

    @FXML
    void clickRow(MouseEvent event) {
        selected = tabWorkers.getSelectionModel().getSelectedItem();
        this.wid=Integer.parseInt(selected.getWorkerID().toString());

        txtWorkerId.setText(selected.getWorkerID().toString());
        txtFirstname.setText(selected.getFirstname());
        txtLastname.setText(selected.getLastname());
        txtMobileno.setText(selected.getPersonalno().toString());
        txtHomeno.setText(selected.getHomeno().toString());
        cmbWorkerroll.getSelectionModel().select(workerroll);

        btnReserveWorker.setDisable(false);
        btnUpdateWorker.setDisable(false);
        btnDeleteWorker.setDisable(false);
    }


    @FXML
    void initialize() {
        cmbWorkerroll.getItems().addAll("Plumber", "Cleaner", "Mechanic", "Painter", "Carpenter", "Electrician");
        wellbl.setText("Welcome ");
    }


    @FXML
    void viewWorkers(ActionEvent event) {
        ancAllWorkers.setVisible(true);
        ancWorkerRoll.setVisible(false);
    }


    @FXML
    void workadd(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("admin.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));
        stage.setTitle("Admin");

        admin Admin = loader.getController();
        stage.show();
        Admin.clickAddWorkersMenu();
        btnAddWorkers.getScene().getWindow().hide();
    }



    @FXML
    void actUpdateWorker(ActionEvent event) throws SQLException {
        this.wid = Integer.parseInt(txtWorkerId.getText());
        this.wfname = txtFirstname.getText();
        this.wlname = txtLastname.getText();
        this.wmobile = Integer.parseInt(txtMobileno.getText());
        this.whome = Integer.parseInt(txtHomeno.getText());
        this.wroll = cmbWorkerroll.getValue().toString();

        try {
            conn = mysqlconnect.ConnectDb();
            String sql = "update workers set firstname='" + wfname + "',lastname='" + wlname + "',personalno=" + wmobile + ", homeno=" + whome + ", workerroll='" + wroll + "' where workerID=" + wid + "";

            pst = conn.prepareStatement(sql);
            pst.executeUpdate();

            JOptionPane.showMessageDialog(null, "The Details have updated Successfully.");
            tabWorkers.getItems().clear();
            tableView(query);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }

    @FXML
    void actDeleteWorker(ActionEvent event) {
        this.wid = Integer.parseInt(txtWorkerId.getText());
        try {
            conn = mysqlconnect.ConnectDb();

            String sql1 = "DELETE FROM workers where workerID=" + wid + "";
            pst = conn.prepareStatement(sql1);
            pst.execute();

            String sql2 = "SELECT * FROM users as u LEFT JOIN workers as w ON  u.username=w.username where workerID=" + wid + "";
            pst2 = conn.prepareStatement(sql2);
            rs = pst2.executeQuery();
            if (rs.next()) {
                this.wuname = rs.getString("username");
            }

            String sql3 = "DELETE FROM users where username='" + wuname + "'";
            pst3 = conn.prepareStatement(sql3);
            pst3.execute();

            JOptionPane.showMessageDialog(null, "The worker has deleted successfully.");
            tabWorkers.getItems().clear();
            tableView(query);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }

    @FXML
    private AnchorPane ancReservation;

    @FXML
    private Label lblReservationTitle;

    @FXML
    private JFXButton btnReserveSubmit;

    @FXML
    private JFXTextField txtWorkerName;

    @FXML
    private JFXTextField txtHouseowner;

    @FXML
    private JFXButton btnReserveClear;

    @FXML
    private JFXButton btnReserveBack;

    @FXML
    private JFXDatePicker txtReserveDate;

    @FXML
    private JFXTimePicker txtReserveStartTime;

    @FXML
    private JFXTextArea txtReserveDetails;

    @FXML
    private JFXTimePicker txtReserveEndTime;

    @FXML
    void actReserveWorker(ActionEvent event) throws SQLException {
        try {
            conn = mysqlconnect.ConnectDb();
            String sql4 = "SELECT * FROM home_owners as h LEFT JOIN users as u ON  h.username=u.username where u.username='" + username + "'";
            pst4 = conn.prepareStatement(sql4);
            rs = pst4.executeQuery();
            if (rs.next()) {

                this.ho_id = Integer.parseInt(rs.getString("house_ownersID"));
                this.ho_title = rs.getString("title");
                this.ho_firstname = rs.getString("firstname");
                this.ho_lastname = rs.getString("lastname");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        this.ho_fullname = ho_title + ". " + ho_firstname + " " + ho_lastname;
        ancReservation.setVisible(true);
        lblReservationTitle.setText("Reservation - "+workerroll);
        txtHouseowner.setText(ho_fullname);
        txtWorkerName.setText(selected.getFirstname() + " " + selected.getLastname());
    }

    @FXML
    void actReserveBack(ActionEvent event) {
        ancWorkerRoll.setVisible(true);
        ancHouseownerEdit.setVisible(true);
        ancReservation.setVisible(false);
    }

    @FXML
    void actReserveClear(ActionEvent event) {
        reserveClear();
    }

    void reserveClear(){
        txtReserveDate.setValue(null);
        txtReserveStartTime.setValue(null);
        txtReserveEndTime.setValue(null);
        txtReserveDetails.setText("");
    }

    @FXML
    void actReserveSubmit(ActionEvent event) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        conn = mysqlconnect.ConnectDb();
        String sql="insert into reservation(home_owner_id,worker_id,reservation_date,reservation_start_time,reservation_end_time,reservation_details,submit_dateTime)values(?,?,?,?,?,?,?)";
        try{
            pst =conn.prepareStatement(sql);
            pst.setString(1,String.valueOf(ho_id));
            pst.setString(2,String.valueOf(wid));
            pst.setString(3, String.valueOf(txtReserveDate.getValue()));
            pst.setString(4, String.valueOf(txtReserveStartTime.getValue()));
            pst.setString(5,String.valueOf(txtReserveEndTime.getValue()));
            pst.setString(6,txtReserveDetails.getText());
            pst.setString(7,dtf.format(now));

            boolean result=pst.execute();

            JOptionPane.showMessageDialog(null, "Your reservation is completed successfully.");
            reserveClear();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }



    }

}
