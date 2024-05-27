package socketclient;

import chatroom.ChatroomController;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class HomeController {
    private SocketClient app;

    @FXML
    private TextField tfName;

    public void setApp(SocketClient app) {
        this.app = app;
    }

    @FXML
    private void initialize() {
        tfName.setFocusTraversable(false);
    }

    @FXML
    private void handleEnterChatRoom() {
        String username = tfName.getText();
        if (!username.isEmpty()) {
            try {
                System.out.println("Loading chat.fxml");
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/chatroom/chat.fxml"));
                Scene scene = new Scene(loader.load());

                System.out.println("Setting username in ChatroomController");
                ChatroomController chatroomController = loader.getController();
                chatroomController.setUserName(username);

                System.out.println("Switching scene to chat room");
                Stage primaryStage = app.getPrimaryStage();
                primaryStage.setScene(scene);
                primaryStage.setTitle("Chat Room");
                primaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Username is empty");
        }
    }
}
