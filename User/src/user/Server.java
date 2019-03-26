package user;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author trash
 */
public class Server extends Application {

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        new Thread(() -> connectToClient()).start();
    }

    public void connectToClient() {
        try {
            // Create a server socket
            ServerSocket serverSocket = new ServerSocket(8000);
            Socket connectToClient = serverSocket.accept();

            // Create data input and output streams
            DataInputStream isFromClient = new DataInputStream(connectToClient.getInputStream());
            DataOutputStream osToClient = new DataOutputStream(connectToClient.getOutputStream());

            // Continuously serve the client
            while (true) {
                if(isFromClient.readInt() > 0){
                    String name = isFromClient.readUTF();
                    String email = isFromClient.readUTF();
                    String password = isFromClient.readUTF();

                    try {
                        updateUser(name,email,password);
                        osToClient.writeBoolean(Boolean.TRUE);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                if(isFromClient.readInt()==0){
                    String user = isFromClient.readUTF();
                    String password = isFromClient.readUTF();
                    try {
                       login(user,password);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if(isFromClient.readInt() == 3){

                        String message = isFromClient.readUTF();
                        osToClient.writeUTF(message);
                        System.out.println(message);

                }
                
            }
        }
        catch(IOException e) {
            System.err.println(e);
        }
    }

    private void updateUser(String name, String email, String password) throws IOException {
        String newUser = String.join(" ",name,email,password);
        BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt", true));
        try {
            writer.newLine();
            writer.write(newUser);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }
    }
    public static void main(String[] args) {
        launch(args);
    }

    private boolean login(String user, String password)throws IOException {
       boolean login=false;
        List<String> words = new ArrayList<String>();
        File sourceFile = new File("users.txt");//read from txt file 
        Scanner input = new Scanner(sourceFile);
        if(sourceFile.exists()){
            while(input.hasNext()){
                String s=input.next();
                words.add(s);
            }
        }
        else{
            System.out.println("FILE NOT FOUND");
        }
        if(words.contains(user) && words.contains(password)){//if both are found, Login
            login=true;
        }
        return login;
    }

}

