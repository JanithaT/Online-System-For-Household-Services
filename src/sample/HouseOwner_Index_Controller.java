package sample;

import com.jfoenix.controls.*;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.swing.*;

public class HouseOwner_Index_Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton btnLogout;

    @FXML
    private Label wellbl;

    @FXML
    private AnchorPane ancHouseownerSidebar;

    @FXML
    private JFXButton btnMenuViewWorkers_HO;

    @FXML
    private JFXButton btnMenuHome_HO;

    @FXML
    private JFXButton btnMenuMyReservation_HO;

    @FXML
    private JFXButton btnMenuMyProfile_HO;

    @FXML
    private JFXButton btnMenuChangePw_HO;

    @FXML
    private AnchorPane ancHome_HO;

    @FXML
    private AnchorPane ancMyReservation_HO;

    @FXML
    private AnchorPane ancMenuChangePw_HO;

    @FXML
    private TableView<ReservationTable> tabMyReservation;

    @FXML
    private TableColumn<ReservationTable,Integer> colResID;

    @FXML
    private TableColumn<ReservationTable, Integer> colResWorkerName;

    @FXML
    private TableColumn<ReservationTable, String> colResDate;

    @FXML
    private TableColumn<ReservationTable, String> colResStartTime;

    @FXML
    private TableColumn<ReservationTable, String> colResEndTime;

    @FXML
    private TableColumn<ReservationTable, String> colResDetails;

    @FXML
    private TableColumn<ReservationTable, String> colResWorkerRemark;

    @FXML
    private TableColumn<ReservationTable, String> colResWorkerReply;

    @FXML
    private JFXButton btnResSubmitReply;

    @FXML
    private JFXButton btnResCancel;

    @FXML
    private JFXTextArea txtResMyReply;

    @FXML
    private JFXTextArea txtResWorkerReply;

    @FXML
    private JFXTextField txtResApplyDate;

    @FXML
    private JFXTextField txtResID;

    @FXML
    private AnchorPane ancMyProfile_HO;

    @FXML
    private JFXButton btnMyprofileUpdate;

    @FXML
    private JFXTextField txtLastname;

    @FXML
    private JFXTextField txtFirstname;

    @FXML
    private JFXComboBox<String> cmbTitle;

    @FXML
    private JFXTextField txtHomeno;

    @FXML
    private JFXTextField txtMobileno;

    @FXML
    private JFXTextField txtUsername;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXPasswordField pwdCurrentPw;

    @FXML
    private JFXPasswordField pwdConfirmPw;

    @FXML
    private JFXPasswordField pwdNewpw;

    @FXML
    private JFXButton btnChangwPwSubmit;

    @FXML
    private Label lblAlertCurrentPw;

    @FXML
    private Label lblAlertMatchPw;

    String userroll,username,ho_id;
    Connection conn=mysqlconnect.ConnectDb();
    PreparedStatement pst, pst1,pst2;
    ResultSet rs;

    ObservableList<ReservationTable> oblist = FXCollections.observableArrayList();

    public void Details(String name, String userroll){
        //wellbl.setText("WELCOME"+" "+name);
        this.userroll=userroll;
        this.username=name;
    }

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
    void actMenuHome_HO(ActionEvent event) {
        actMenuHome_HO();
    }

    @FXML
    void actMenuMyProfile_HO(ActionEvent event) {
        actMenuMyProfile_HO();
    }

    ReservationTable selected;
    int res_id;

    @FXML
    void actMenuMyReservation_HO(ActionEvent event) throws SQLException {
        actMenuMyReservation_HO();
    }

    void tableView() throws SQLException {
        //Connection conn = mysqlconnect.ConnectDb();
        String wTitle,wFname,wLname,wFullname;

        String sql = "SELECT * FROM home_owners where username='" + username + "'";
        pst = conn.prepareStatement(sql);
        rs = pst.executeQuery();
        if (rs.next()) {
            this.ho_id = rs.getString("house_ownersID");
        }

        //Table view//
        String sql1 = "SELECT * FROM reservation as r LEFT JOIN workers as w ON r.worker_id=w.workerID WHERE r.home_owner_id="+ ho_id + "";
        try {
            ResultSet rs = conn.createStatement().executeQuery(sql1);

            while (rs.next()) {
                wTitle= rs.getString("title");
                wFname=rs.getString("firstname");
                wLname=rs.getString("lastname");
                wFullname=wTitle+". "+wFname+" "+wLname;

                oblist.addAll(new ReservationTable(rs.getInt("reservation_id"),(rs.getInt("home_owner_id")),wFullname, rs.getString("reservation_date"), rs.getString("reservation_start_time"), rs.getString("reservation_end_time"), rs.getString("reservation_details"),rs.getString("submit_dateTime"), rs.getString("worker_remark"),rs.getString("worker_reply"), rs.getString("homeowner_reply")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);

        }
        colResID.setCellValueFactory(new PropertyValueFactory<>("reservation_id"));
        colResWorkerName.setCellValueFactory(new PropertyValueFactory<>("worker_name"));
        colResDate.setCellValueFactory(new PropertyValueFactory<>("reservation_date"));
        colResStartTime.setCellValueFactory(new PropertyValueFactory<>("reservation_start_time"));
        colResEndTime.setCellValueFactory(new PropertyValueFactory<>("reservation_end_time"));
        colResDetails.setCellValueFactory(new PropertyValueFactory<>("reservation_details"));
        colResWorkerRemark.setCellValueFactory(new PropertyValueFactory<>("worker_remark"));
        //colResWorkerReply.setCellValueFactory(new PropertyValueFactory<>("worker_reply"));

        tabMyReservation.setItems(oblist);
    }

    @FXML
    void clickResRow(MouseEvent event) {
        txtResMyReply.setDisable(true);
        btnResSubmitReply.setDisable(true);
        if(!(tabMyReservation.getSelectionModel().getSelectedItem()==null)){
            btnResCancel.setDisable(false);
        }

        txtResMyReply.setText("");
        selected = tabMyReservation.getSelectionModel().getSelectedItem();
        this.res_id=Integer.parseInt(selected.getReservation_id().toString());
        String workerReply=selected.getWorker_reply();

        txtResID.setText(String.valueOf(res_id));
        txtResApplyDate.setText(selected.getSubmit_dateTime());
        txtResWorkerReply.setText(workerReply);

        if(!(workerReply.equals("Wait For Reply"))){
            txtResMyReply.setDisable(false);
            btnResSubmitReply.setDisable(false);
        }
    }

    @FXML
    void actMenuViewWorkers_HO(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("workers.fxml"));
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(loader.load()));
        stage1.setTitle("House Owners");

        Workers_Controller workers_Controller = loader.getController();
        workers_Controller.getUserroll(username,"house_owners");
        stage1.show();
        btnMenuViewWorkers_HO.getScene().getWindow().hide();

    }

    String ho_title,ho_fname,ho_lname,ho_address,ho_email;
    int ho_mobile,ho_home;
    @FXML
    void actMyprofileUpdate(ActionEvent event) {
        this.ho_title=cmbTitle.getValue().toString();
        this.ho_fname = txtFirstname.getText();
        this.ho_lname = txtLastname.getText();
        this.ho_mobile= Integer.parseInt(txtMobileno.getText());
        this.ho_home = Integer.parseInt(txtHomeno.getText());
        this.ho_address = txtAddress.getText();
        this.ho_email=txtEmail.getText();

        try {
            String sql = "update home_owners set title='" + ho_title + "',firstname='" + ho_fname + "',lastname='" + ho_lname + "',personal_TelephoneNO=" + ho_mobile + ", home_telephone_No=" + ho_home + ", address='" + ho_address + "', email='" + ho_email + "' where username='" + username + "'";
            pst = conn.prepareStatement(sql);
            pst.executeUpdate();

            JOptionPane.showMessageDialog(null, "Your Details have been updated Successfully.");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }

        actMenuMyProfile_HO();
    }

    @FXML
    void actResCancel(ActionEvent event) {
        try {
            conn = mysqlconnect.ConnectDb();
            String sql = "DELETE FROM reservation where reservation_id=" + res_id + "";

            pst = conn.prepareStatement(sql);
            pst.executeUpdate();

            JOptionPane.showMessageDialog(null, "Your reservation has been cancelled successfully.");
            tabMyReservation.getItems().clear();
            tableView();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }

    @FXML
    void actResSubmitReply(ActionEvent event) {
        String HO_reply=txtResMyReply.getText();
        try {
            conn = mysqlconnect.ConnectDb();
            String sql = "update reservation set homeowner_reply='" + HO_reply + "' where reservation_id=" + res_id + "";

            pst = conn.prepareStatement(sql);
            pst.executeUpdate();

            JOptionPane.showMessageDialog(null, "Your reply has been sent successfully.");
            txtResMyReply.setText("");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e);
        }

    }

    public void actMenuMyProfile_HO() {
        ancMyProfile_HO.setVisible(true);
        ancHome_HO.setVisible(false);
        ancMyReservation_HO.setVisible(false);
        ancMenuChangePw_HO.setVisible(false);
        ancFeedback_Ho.setVisible(false);

        cmbTitle.getItems().addAll("Mr","Miss","Mrs");
        try {
            pst = conn.prepareStatement("select * from home_owners where username='" + username + "'");
            rs = pst.executeQuery();
            if (rs.next()) {
                txtUsername.setText(rs.getString("username"));
                cmbTitle.getSelectionModel().select(rs.getString("title"));
                txtFirstname.setText(rs.getString("firstname"));
                txtLastname.setText(rs.getString("lastname"));
                txtMobileno.setText(rs.getString("personal_TelephoneNO"));
                txtHomeno.setText(rs.getString("home_telephone_No"));
                txtAddress.setText(rs.getString("address"));
                txtEmail.setText(rs.getString("email"));

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }

    }

    public void actMenuMyReservation_HO() throws SQLException {
        ancMyReservation_HO.setVisible(true);
        ancHome_HO.setVisible(false);
        ancMyProfile_HO.setVisible(false);
        ancMenuChangePw_HO.setVisible(false);
        ancFeedback_Ho.setVisible(false);

        tabMyReservation.getItems().clear();
        txtResID.setText("");
        txtResApplyDate.setText("");
        txtResWorkerReply.setText("");
        txtResMyReply.setText("");
        txtResMyReply.setDisable(true);
        btnResCancel.setDisable(true);
        btnResSubmitReply.setDisable(true);

        tableView();
    }

    String curPwtyped,newPw,confirmPw,oldPw;

    @FXML
    void actMenuChangePw_HO(ActionEvent event) {
        actMenuChangePw_HO();
    }

    @FXML
    void checkOldPwWithDb(MouseEvent event){
        lblAlertCurrentPw.setText("");
        pwdNewpw.setDisable(true);
        pwdConfirmPw.setDisable(true);
        lblAlertMatchPw.setText("");
        btnChangwPwSubmit.setDisable(true);

        curPwtyped=pwdCurrentPw.getText();
        String sql="Select * from users where username='"+username+"'";
        try {
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                this.oldPw = rs.getString("password");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e);
        }

        if(!curPwtyped.equals(oldPw)){
            lblAlertCurrentPw.setText("Not match with your current password");
        }
        else{
            pwdNewpw.setDisable(false);
            pwdConfirmPw.setDisable(false);
            btnChangwPwSubmit.setDisable(false);
        }

    }

    @FXML
    void actChangwPwSubmit(ActionEvent event) {
        newPw=pwdNewpw.getText();
        confirmPw=pwdConfirmPw.getText();
        if(pwdNewpw.getText().equals("") || pwdConfirmPw.getText().equals("") || (!pwdCurrentPw.getText().equals(oldPw)) ){
            lblAlertMatchPw.setText("Fill all required fields correctly.");
        }
        else if(newPw.equals(confirmPw)){
            String sql="Update users SET password='"+newPw+"' where username='"+username+"'";
            try {
                pst = conn.prepareStatement(sql);
                pst.executeUpdate();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,e);
            }
            lblAlertMatchPw.setText("Your new password updated successfully.");
            JOptionPane.showMessageDialog(null, "Your new password updated successfully.");
            pwdCurrentPw.setText("");
            pwdNewpw.setText("");
            pwdConfirmPw.setText("");
            pwdNewpw.setDisable(true);
            pwdConfirmPw.setDisable(true);
        }
        else{
            lblAlertMatchPw.setText("New password does not match with confirm password.");
        }

    }

    public void actMenuHome_HO() {
        ancHome_HO.setVisible(true);
        ancMenuChangePw_HO.setVisible(false);
        ancMyReservation_HO.setVisible(false);
        ancMyProfile_HO.setVisible(false);
        ancFeedback_Ho.setVisible(false);
    }


    @FXML
    void initialize() {
        wellbl.setText("Welcome");
    }

    public void actMenuChangePw_HO() {
        ancMenuChangePw_HO.setVisible(true);
        ancHome_HO.setVisible(false);
        ancMyProfile_HO.setVisible(false);
        ancMyReservation_HO.setVisible(false);
        ancFeedback_Ho.setVisible(false);
        lblAlertCurrentPw.setText("");
        lblAlertMatchPw.setText("");
        pwdCurrentPw.setText("");
        pwdNewpw.setText("");
        pwdConfirmPw.setText("");
        pwdNewpw.setDisable(true);
        pwdConfirmPw.setDisable(true);
        btnChangwPwSubmit.setDisable(true);
    }

    @FXML
    private AnchorPane ancFeedback_Ho;

    @FXML
    private JFXTextField txtFeedHoName;

    @FXML
    private TextArea txtFeedMessage;

    @FXML
    private JFXButton btnFeedbackSubmit_Ho;

    @FXML
    private JFXButton btnMenuFeedback_Ho;

    @FXML
    void actMenuFeedback_Ho(ActionEvent event) {
        actMenuFeedback_Ho();
    }

    String hotitle,hofname,holname,hofullname,hofeedback;

    void actMenuFeedback_Ho(){
        ancFeedback_Ho.setVisible(true);
        ancMenuChangePw_HO.setVisible(false);
        ancHome_HO.setVisible(false);
        ancMyProfile_HO.setVisible(false);
        ancMyReservation_HO.setVisible(false);

        try {
            pst = conn.prepareStatement("select * from home_owners where username='" + username + "'");
            rs = pst.executeQuery();
            if (rs.next()) {
                this.hotitle=rs.getString("title");
                this.hofname=rs.getString("firstname");
                this.holname=rs.getString("lastname");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        hofullname=hotitle+". "+hofname+" "+holname;
        txtFeedHoName.setText(hofullname);
    }

    @FXML
    void actFeedbackSubmit_Ho(ActionEvent event) {
        hofeedback=txtFeedMessage.getText();

        String sql="insert into feedback(username,message)values(?,?)";
        try {
            pst =conn.prepareStatement(sql);
            pst.setString(1,username);
            pst.setString(2,hofeedback);
            pst.execute();
            JOptionPane.showMessageDialog(null,"Your feedback has been sent successfully");
            txtFeedMessage.setText("");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }

    }
}
