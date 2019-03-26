package user;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.*;

/**
 *
 * @author trash
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    Label label;
    @FXML
    private TextField UserName=new TextField();
    @FXML
    private PasswordField UserPassword=new PasswordField();
    @FXML
    private Button CreateButton;
    @FXML
    private Button cButton;
    @FXML
    private Button cButton2;
    @FXML
    private Button Done;
    @FXML
    private Button ErrorOK;
    @FXML
    private Button OK;
    @FXML
    private TextField CUserName=new TextField();
    @FXML
    private TextField CEmail=new TextField();
    @FXML
    private TextField CPassword=new TextField();
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        String UserN=UserName.getText();
        String UserP=UserPassword.getText();
        if(login(UserN,UserP)==true){//when login is true
            System.out.println("LOGIN");
            /*
            CALL THE CHAT WINDOW HERE
            LOOK AT HOW HANDLE CREATE WAS USED TO MAKE A NEW WINDOW
            DESIGN IT ON SCENEBUILDER
            */
        }
        else{
            System.out.println("LOGIN FAILED");
        }
        
    }
    @FXML
    private void handleCreate(ActionEvent event) throws IOException {//opens new user window
        Stage stage=(Stage)CreateButton.getScene().getWindow();
        Parent root=FXMLLoader.load(getClass().getResource("FXMLCreateAccount.fxml"));
        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.show();       
    } 
    
    @FXML
    private void handleNewUser(ActionEvent event) throws IOException {
        String CUserN=CUserName.getText();
        String CUserE=CEmail.getText();        
        String CUserP=CPassword.getText();
        if(CUserName.getText().isEmpty() || CEmail.getText().isEmpty() || CPassword.getText().isEmpty() ){//won't call CreateUser if information is missing
            Stage stage=(Stage)Done.getScene().getWindow();
            Parent root=FXMLLoader.load(getClass().getResource("FXMLERROR.fxml"));
            Scene scene=new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else{
            CreateUser(CUserN,CUserE,CUserP);//calls createUser to create new user        
        }
        
    }    
    @FXML
    private void handleReturnToMain(ActionEvent event) throws IOException {        
        Stage stage=(Stage)OK.getScene().getWindow();
        Parent root=FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.show();  
    } 
    @FXML
    private void handleReturnToCreate(ActionEvent event) throws IOException {    
        Stage stage=(Stage)ErrorOK.getScene().getWindow();
        Parent root=FXMLLoader.load(getClass().getResource("FXMLCreateAccount.fxml"));
        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void handleCancel(ActionEvent event)throws IOException {    
        Stage stage = (Stage) cButton.getScene().getWindow();    
        stage.close();
    }
    @FXML
    private void handleCancel2(ActionEvent event)throws IOException {    
        Stage stage = (Stage) cButton2.getScene().getWindow();    
        stage.close();
    } 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    } 
    
    
    private Boolean login(String UserName,String UserPassword) throws IOException {     
        Boolean login2=false;
        DataOutputStream osToServer;
        DataInputStream isFromServer;

        try {

            Socket connectToServer = new Socket("localhost", 8000);
            isFromServer = new DataInputStream(connectToServer.getInputStream());
            osToServer = new DataOutputStream(connectToServer.getOutputStream());
            osToServer.writeInt(0);
            osToServer.writeUTF(UserName);
            osToServer.writeUTF(UserPassword);
            osToServer.flush();

            if (isFromServer.readBoolean() == Boolean.TRUE){//Registration Complete window will popup if everything goes right
                login2=true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return login2;
    }

    private void CreateUser(String CUserN, String CUserE, String CUserP) throws IOException{
        DataOutputStream osToServer;
        DataInputStream isFromServer;
        try {

                Socket connectToServer = new Socket("localhost", 8000);
                isFromServer = new DataInputStream(connectToServer.getInputStream());
                osToServer = new DataOutputStream(connectToServer.getOutputStream());
                osToServer.writeInt(1);
                osToServer.writeUTF(CUserN);
                osToServer.writeUTF(CUserE);
                osToServer.writeUTF(CUserP);
                osToServer.flush();
                if (isFromServer.readBoolean() == Boolean.TRUE){//Registration Complete window will popup if everything goes right
                    Stage stage=(Stage)Done.getScene().getWindow();
                    Parent root=FXMLLoader.load(getClass().getResource("FXMLRegiComplete.fxml"));
                    Scene scene=new Scene(root);
                    stage.setScene(scene);
                    stage.show();    
                }
                } catch (IOException e) {
                    e.printStackTrace();
                }
    }
    

    
    
}
