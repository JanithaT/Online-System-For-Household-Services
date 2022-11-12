package sample;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.swing.*;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXTextField txtUsername;

    @FXML
    private JFXPasswordField pwdPassword;

    @FXML
    private TextField txtUsername1;

    @FXML
    private PasswordField pwdPassword1;

    @FXML
    private JFXButton btnLogin;

    @FXML
    private JFXButton btnSignup;

    @FXML
    private AnchorPane ancSignup;

    @FXML
    private JFXButton btnSignupsubmit;

    @FXML
    private JFXTextField addhlastname;

    @FXML
    private JFXPasswordField addhpassword;

    @FXML
    private JFXTextField addhfirstname;

    @FXML
    private JFXComboBox<String> addhtitle;

    @FXML
    private JFXTextField addhhomeno;

    @FXML
    private JFXTextField addhpersonalno;

    @FXML
    private JFXTextField addhusername;

    @FXML
    private JFXTextField addhaddress;

    @FXML
    private JFXTextField addhemail;

    @FXML
    private JFXButton btnSignupclear;

    @FXML
    private JFXButton btnBack;

    @FXML
    private AnchorPane ancSignin;

    @FXML
    void actSignup(ActionEvent event) {
        ancSignup.setVisible(true);
        ancSignin.setVisible(false);
    }


    Connection conn;
    PreparedStatement pst,pst2;
    ResultSet rs;

    String userroll,username;

    @FXML
    void actLogin(ActionEvent event) {

        //JOptionPane.showMessageDialog(null,"Hi");
        String uname = txtUsername.getText();
        String pass = pwdPassword.getText();

        if (uname.equals("") || pass.equals(""))
        {
            JOptionPane.showMessageDialog(null, "please Enter Username and Password");
        }
        else
        {
            try {
                conn =mysqlconnect.ConnectDb();
                pst = conn.prepareStatement("select * from users where username=? and password=?");
                pst.setString(1, uname);
                pst.setString(2,pass);

                rs = pst.executeQuery();
                if (rs.next())
                {
                   // JOptionPane.showMessageDialog(null, "Login Successfully");

                    this.userroll=rs.getString("userroll");
                    this.username=rs.getString("username");

                    if(userroll.equals("admin")) {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("admin.fxml"));
                        Stage stage = new Stage();
                        stage.setScene(new Scene(loader.load()));
                        stage.setTitle("Admin");

                        admin Admin = loader.getController();
                        Admin.Details(uname,userroll);
                        stage.show();
                    }
                    else if(userroll.equals("house_owners")){
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("HouseOwner_Index.fxml"));
                        Stage stage1 = new Stage();
                        stage1.setScene(new Scene(loader.load()));
                        stage1.setTitle("House Owners");

                        HouseOwner_Index_Controller houseOwner_index_controller = loader.getController();
                        houseOwner_index_controller.Details(uname,userroll);
                        stage1.show();
                    }
                    else if(userroll.equals("workers")){
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("Workers_Index.fxml"));
                        Stage stage2 = new Stage();
                        stage2.setScene(new Scene(loader.load()));
                        stage2.setTitle("Workers");

                        Workers_Index_Controller workers_index_controller = loader.getController();
                        workers_index_controller.Details(uname,userroll);
                        stage2.show();

                    }
                    btnLogin.getScene().getWindow().hide();

                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Check your  Username and password");
                    txtUsername.setText("");
                    pwdPassword.setText("");
                    txtUsername.requestFocus();
                }

            }  catch (SQLException | IOException ex)
            {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    void clear(){
        addhfirstname.clear();
        addhlastname.clear();
        addhpersonalno.clear();
        addhhomeno.clear();
        addhusername.clear();
        addhpassword.clear();
        addhtitle.getItems().clear();
        addhemail.clear();
        addhaddress.clear();
        addhtitle.getItems().addAll("Mr","Miss","Mrs");
        addhtitle.requestFocus();
    }
    @FXML
    void actSignupClear(ActionEvent event) {
        clear();
    }

    @FXML
    void actSignupSubmit(ActionEvent event) {
        conn =mysqlconnect.ConnectDb();

        String sql="insert into home_owners(title,firstname,lastname,personal_TelephoneNO,home_telephone_No,username,email,address)values(?,?,?,?,?,?,?,?)";
        try{
            pst =conn.prepareStatement(sql);
            pst.setString(1,addhtitle.getValue().toString());
            pst.setString(2,addhfirstname.getText());
            pst.setString(3,addhlastname.getText());
            pst.setString(4,addhpersonalno.getText());
            pst.setString(5,addhhomeno.getText());
            pst.setString(6,addhusername.getText());
            pst.setString(7,addhemail.getText().trim());
            //pst.setString(8,addhaddress.getText());
            pst.setString(8,addhaddress.getText().trim());

            pst.execute();

            String sql2="insert into users(username,password,userroll)values(?,?,?)";

            pst2 =conn.prepareStatement(sql2);
            pst2.setString(1,addhusername.getText());
            pst2.setString(2,addhpassword.getText().trim());
            pst2.setString(3,"house_owners");
            pst2.execute();
            clear();
            JOptionPane.showMessageDialog(null,"Your account has been Successfully Created.You can login for further information");
            ancSignup.setVisible(false);
            ancSignin.setVisible(true);

        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }

    }

    @FXML
    void actBack(ActionEvent event) {
        ancSignup.setVisible(false);
        ancSignin.setVisible(true);
    }


    @FXML
    void initialize() {
        addhtitle.getItems().addAll("Mr","Miss","Mrs");

    }



}
