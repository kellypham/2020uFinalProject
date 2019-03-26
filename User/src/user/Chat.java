package sample;

import java.io.*;
import java.net.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sun.font.TrueTypeFont;

public class Chat extends Application {

    PrintWriter isToServer;
    BufferedReader isFromServer;
    Socket connectToServer;
    TextArea messageArea = new TextArea();


    @Override
    public void start(Stage stage) throws IOException {


        // Create the Message
        final TextField input = new TextField();
        input.setPromptText("Input your message here");

        final TextField displayName = new TextField();
        displayName.setPromptText("Display Name");


        // Set the Prompt and Size of the TextArea
        messageArea.setPromptText("Messages:");
        messageArea.setPrefColumnCount(20);
        messageArea.setPrefRowCount(10);

        // Create the Print Button
        Button send = new Button("Send");

        // Add an EvenetHandler to the Button
        send.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                try {
                    connectToServer = new Socket("localhost", 8000);
                    isFromServer = new BufferedReader(new InputStreamReader(connectToServer.getInputStream()));
                    isToServer = new PrintWriter(connectToServer.getOutputStream(), true);


                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                String message = input.getText();
                isToServer.println(3);
                isToServer.println(message);
                isToServer.flush();

            }
        });


        // Create the VBox
        VBox root = new VBox();
        // Add Labels, TextArea and TextField to the VBox
        root.getChildren().addAll(new Label("Display Name:"), displayName,new Label("Input:"), input,  new Label("Messages:"), messageArea, send);
        // Set the Size of the VBox
        root.setMinSize(350, 250);


        // Create the Scene
        Scene scene = new Scene(root);
        // Add the scene to the Stage
        stage.setScene(scene);
        // Set the title of the Stage
        stage.setTitle("Chat");
        // Display the Stage
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
