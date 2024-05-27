package socketclient;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import socketclient.HomeController;

public class SocketClient extends Application {
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        showHomeScreen();
    }

    public void showHomeScreen() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
        Scene scene = new Scene(loader.load(), 360, 640);
        
        HomeController homeController = loader.getController();
        homeController.setApp(this);
        
        primaryStage.setTitle("Welcome");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void showChatRoom() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("chat.fxml"));
        Scene scene = new Scene(loader.load(), 360, 640);

        primaryStage.setScene(scene);
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
