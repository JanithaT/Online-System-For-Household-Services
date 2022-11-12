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

public class Workers_Index_Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton btnLogout;

    @FXML
    private Label wellbl;
    @FXML
    private Label welbl1;

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
    private TableView<ReservationTable_ForWorker> tabMyReservation;

    @FXML
    private TableColumn<ReservationTable_ForWorker,Integer> colResID;

    @FXML
    private TableColumn<ReservationTable_ForWorker, String> colHoName;

    @FXML
    private TableColumn<ReservationTable_ForWorker, String> colResDate;

    @FXML
    private TableColumn<ReservationTable_ForWorker, String> colResStartTime;

    @FXML
    private TableColumn<ReservationTable_ForWorker, String> colResEndTime;

    @FXML
    private TableColumn<ReservationTable_ForWorker, String> colResDetails;

    @FXML
    private TableColumn<ReservationTable_ForWorker, String> colResWorkerRemark;

    @FXML
    private TableColumn<ReservationTable_ForWorker, String> colResWorkerReply;

    @FXML
    private JFXButton btnResSubmitReply;


    @FXML
    private JFXTextArea txtResMyReply;

    @FXML
    private JFXTextArea txtResHOReply;

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

    @FXML
    private AnchorPane ancFeedback_W;

    @FXML
    private JFXTextField txtFeedWorkerName;

    @FXML
    private TextArea txtFeedMessage;

    @FXML
    private JFXButton btnMenuFeedback_W;

    @FXML
    private JFXButton btnFeedbackSubmit_W;

    @FXML
    private AnchorPane ancMenuHouseOwners;

    @FXML
    private JFXButton btnMenuHouseOwners;

    @FXML
    private TableView<HouseOwnersTable> tabHouseOwners;

    @FXML
    private TableColumn<HouseOwnersTable, Integer> colHoid;

    @FXML
    private TableColumn<HouseOwnersTable, String> colHoFullname;

    @FXML
    private TableColumn<HouseOwnersTable, Integer> colHoMobileno;

    @FXML
    private TableColumn<HouseOwnersTable, Integer> colHoHomeno;

    @FXML
    private TableColumn<HouseOwnersTable, String> colHoEmail;

    @FXML
    private TableColumn<HouseOwnersTable, String> colHoAddress;


    String userroll,username,ho_id;
    Connection conn=mysqlconnect.ConnectDb();
    PreparedStatement pst, pst1,pst2;
    ResultSet rs;

    ObservableList<ReservationTable_ForWorker> oblist = FXCollections.observableArrayList();
    ObservableList<HouseOwnersTable> HOoblist = FXCollections.observableArrayList();


    public void Details(String name, String userroll){
        //wellbl.setText("WELCOME"+" "+name);
        this.userroll=userroll;
        this.username=name;
        welbl1.setText("Welcome"+" "+name);
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

    ReservationTable_ForWorker selected;
    int res_id;

    @FXML
    void actMenuMyReservation_HO(ActionEvent event) throws SQLException {
        actMenuMyReservation_HO();


    }
    String wid;
    void tableView() throws SQLException {
        //Connection conn = mysqlconnect.ConnectDb();
        String hfullname,htitle,hlname,hfname;

        String sql = "SELECT * FROM workers where username='" + username + "'";
        pst = conn.prepareStatement(sql);
        rs = pst.executeQuery();
        if (rs.next()) {
            this.wid = rs.getString("workerID");
        }

        //Table view//
        String sql1 = "SELECT * FROM reservation as r LEFT JOIN home_owners as h ON r.home_owner_id=h.house_ownersID WHERE r.worker_id="+ wid + "";

        try {
            ResultSet rs = conn.createStatement().executeQuery(sql1);

            while (rs.next()) {
                htitle= rs.getString("title");
                hfname=rs.getString("firstname");
                hlname=rs.getString("lastname");
                hfullname=htitle+". "+hfname+" "+hlname;


                oblist.addAll(new ReservationTable_ForWorker(rs.getInt("reservation_id"),(rs.getInt("home_owner_id")),(rs.getInt("worker_id")),hfullname,rs.getString("reservation_date"), rs.getString("reservation_start_time"), rs.getString("reservation_end_time"), rs.getString("reservation_details"),rs.getString("submit_dateTime"), rs.getString("worker_remark"),rs.getString("worker_reply"), rs.getString("homeowner_reply")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);

        }
        colResID.setCellValueFactory(new PropertyValueFactory<>("reservation_id"));
        colHoName.setCellValueFactory(new PropertyValueFactory<>("home_ownername"));
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
        btnResSubmitReply.setDisable(false);
        cmbRemark.setDisable(false);
        txtResMyReply.setText("");

        selected = tabMyReservation.getSelectionModel().getSelectedItem();
        this.res_id=Integer.parseInt(selected.getReservation_id().toString());
        String HoReply=selected.getHomeowner_reply();
        this.remark=selected.getWorker_remark();
        cmbRemark.getSelectionModel().select(remark) ;
        txtResID.setText(String.valueOf(res_id));
        txtResApplyDate.setText(selected.getSubmit_dateTime());
        txtResHOReply.setText(HoReply);


    }

    @FXML
    void actMenuViewWorkers_HO(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("workers.fxml"));
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(loader.load()));
        stage1.setTitle("Workers");

        Workers_Controller workers_Controller = loader.getController();
        workers_Controller.getUserroll(username,"Workers");
        stage1.show();
        btnMenuViewWorkers_HO.getScene().getWindow().hide();

    }

    String ho_title,ho_fname,ho_lname;
    int ho_mobile,ho_home;
    @FXML
    void actMyprofileUpdate(ActionEvent event) {
        this.ho_title=cmbTitle.getValue().toString();
        this.ho_fname = txtFirstname.getText();
        this.ho_lname = txtLastname.getText();
        this.ho_mobile= Integer.parseInt(txtMobileno.getText());
        this.ho_home = Integer.parseInt(txtHomeno.getText());


        try {
            String sql = "update workers set title='" + ho_title + "',firstname='" + ho_fname + "',lastname='" + ho_lname + "',personalno=" + ho_mobile + ", homeno=" + ho_home + "' where username='" + username + "'";
            pst = conn.prepareStatement(sql);
            pst.executeUpdate();

            JOptionPane.showMessageDialog(null, "Your Details have been updated Successfully.");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }

        actMenuMyProfile_HO();
    }
    String remark;

    @FXML
    void actResSubmitReply(ActionEvent event) {
        String W_reply=txtResMyReply.getText();
        this.remark=cmbRemark.getValue().toString();
        try {
            conn = mysqlconnect.ConnectDb();
            String sql = "update reservation set worker_reply='" + W_reply + "',worker_remark='"+remark+"' where reservation_id=" + res_id + "";

            pst = conn.prepareStatement(sql);
            pst.executeUpdate();

            JOptionPane.showMessageDialog(null, "Your reply has been sent successfully.");
            txtResMyReply.setText("");
            tabMyReservation.getItems().clear();
            tableView();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e);
        }

    }

    public void actMenuMyProfile_HO() {
        ancMyProfile_HO.setVisible(true);
        ancHome_HO.setVisible(false);
        ancMyReservation_HO.setVisible(false);
        ancMenuChangePw_HO.setVisible(false);
        ancFeedback_W.setVisible(false);
        ancMenuHouseOwners.setVisible(false);

        txtUsername.setText(username);
        cmbTitle.getItems().addAll("Mr","Miss","Mrs");
        try {
            pst = conn.prepareStatement("select * from workers where username='" + username + "'");
            rs = pst.executeQuery();
            if (rs.next()) {
                txtUsername.setText(rs.getString("username"));
                cmbTitle.getSelectionModel().select(rs.getString("title"));
                txtFirstname.setText(rs.getString("firstname"));
                txtLastname.setText(rs.getString("lastname"));
                txtMobileno.setText(rs.getString("personalno"));
                txtHomeno.setText(rs.getString("homeno"));


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
        ancFeedback_W.setVisible(false);
        ancMenuHouseOwners.setVisible(false);

        tabMyReservation.getItems().clear();
        txtResID.setText("");
        txtResApplyDate.setText("");
        txtResHOReply.setText("");
        txtResMyReply.setText("");
        cmbRemark.setDisable(true);
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
            } catch (Exception e) {
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
        ancFeedback_W.setVisible(false);
        ancMenuHouseOwners.setVisible(false);
    }


    @FXML
    void initialize() {
        wellbl.setText("Welcome");
        cmbRemark.getItems().addAll("Waiting For Approval","Accepted","Rejected");
    }

    public void actMenuChangePw_HO() {
        ancMenuChangePw_HO.setVisible(true);
        ancHome_HO.setVisible(false);
        ancMyProfile_HO.setVisible(false);
        ancMyReservation_HO.setVisible(false);
        ancFeedback_W.setVisible(false);
        ancMenuHouseOwners.setVisible(false);

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
    void actMenuFeedback_W(ActionEvent event) {
        actMenuFeedback_W();
    }

    String wtitle,wfname,wlname,wfullname,wfeedback;

    void actMenuFeedback_W(){
        ancFeedback_W.setVisible(true);
        ancMenuChangePw_HO.setVisible(false);
        ancHome_HO.setVisible(false);
        ancMyProfile_HO.setVisible(false);
        ancMyReservation_HO.setVisible(false);
        ancMenuHouseOwners.setVisible(false);

        try {
            pst = conn.prepareStatement("select * from workers where username='" + username + "'");
            rs = pst.executeQuery();
            if (rs.next()) {
                this.wtitle=rs.getString("title");
                this.wfname=rs.getString("firstname");
                this.wlname=rs.getString("lastname");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        wfullname=wtitle+". "+wfname+" "+wlname;
        txtFeedWorkerName.setText(wfullname);

    }

    @FXML
    void actFeedbackSubmit_W(ActionEvent event) {
        wfeedback=txtFeedMessage.getText();

        String sql="insert into feedback(username,message)values(?,?)";
        try {
            pst =conn.prepareStatement(sql);
            pst.setString(1,username);
            pst.setString(2,wfeedback);
            pst.execute();
            JOptionPane.showMessageDialog(null,"Your feedback has been sent successfully");
            txtFeedMessage.setText("");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }

    @FXML
    void actMenuHouseOwnersDetails(ActionEvent event) throws SQLException {
        actMenuHouseOwnersDetails();
    }

    void actMenuHouseOwnersDetails() throws SQLException {
        ancMenuHouseOwners.setVisible(true);
        ancMyProfile_HO.setVisible(false);
        ancHome_HO.setVisible(false);
        ancMyReservation_HO.setVisible(false);
        ancMenuChangePw_HO.setVisible(false);
        ancFeedback_W.setVisible(false);
        HOtableView();
    }

    void HOtableView() throws SQLException {
        //Connection conn = mysqlconnect.ConnectDb();
        String hoTitle,hoFname,hoLname,hoFullname;

        //Table view//
        String sql1 = "SELECT * FROM home_owners";
        try {
            ResultSet rs = conn.createStatement().executeQuery(sql1);

            while (rs.next()) {
                hoTitle= rs.getString("title");
                hoFname=rs.getString("firstname");
                hoLname=rs.getString("lastname");
                hoFullname=hoTitle+". "+hoFname+" "+hoLname;

                HOoblist.addAll(new HouseOwnersTable(rs.getInt("house_ownersID"),hoFullname, rs.getInt("personal_TelephoneNO"), rs.getInt("home_telephone_No"), rs.getString("email"), rs.getString("address")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);

        }
        colHoid.setCellValueFactory(new PropertyValueFactory<>("ho_id"));
        colHoFullname.setCellValueFactory(new PropertyValueFactory<>("ho_fullname"));
        colHoMobileno.setCellValueFactory(new PropertyValueFactory<>("ho_mobile"));
        colHoHomeno.setCellValueFactory(new PropertyValueFactory<>("ho_homeno"));
        colHoEmail.setCellValueFactory(new PropertyValueFactory<>("ho_email"));
        colHoAddress.setCellValueFactory(new PropertyValueFactory<>("ho_address"));

        tabHouseOwners.setItems(HOoblist);
    }
    @FXML
    private JFXComboBox<String> cmbRemark;
}
