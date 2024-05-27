package chatroom;

//import simpleserver.SimpleServer;
import java.io.*;
import java.net.*;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ChatroomController {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    @FXML
    private TextArea taChat;
    @FXML
    private TextField tfMessage;

    public ChatroomController(TextField tfMessage) {
        this.tfMessage = tfMessage;
    }

    public ChatroomController() {
    }

    
    

    @FXML
    private void initialize() {
        System.out.println("taChat: " + taChat);
        System.out.println("tfMessage: " + tfMessage);
    }


    public void setUserName(String userName) {
        try {
            connectToServer(userName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void connectToServer(String userName) throws IOException {
        String host = "localhost"; 
        int port = 12345;

        socket = new Socket(host, port);
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        new Thread(() -> {
            try {
                String serverMessage;
                while ((serverMessage = in.readLine()) != null) {
                    if (taChat != null) {
                        taChat.appendText(serverMessage + "\n");
                    } else {
                        System.err.println("taChat is null!");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        out.println(userName);

    }

    @FXML
    private void sendMessage() {
        String message = tfMessage.getText();
        if (out != null) {
            out.println(message);
        } else {
            System.err.println("PrintWriter is not initialized!");
        }
        tfMessage.clear();
    }
}
