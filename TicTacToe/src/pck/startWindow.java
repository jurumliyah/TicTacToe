package pck;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.lang.reflect.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class startWindow extends Application {
	VBox root = new VBox();
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtField1;

    @FXML
    private TextField txtField2;

    @FXML
    private Button playButton;

    @FXML
    void handlePlay(ActionEvent event) throws Exception {
        ((Stage) (((Button)event.getSource()).getScene().getWindow())).close();
        String player1 = txtField1.getText(); 
        String player2 = txtField2.getText();
        player1 = ( player1.length() != 0) ? player1 : "Player1";
        player2 = ( player2.length() != 0) ? player2 : "Player2";
        System.out.println(player1 + "\n" + player2);
        
    	startgame(player1,player2);
    }

    @FXML
    void initialize() {
        assert txtField1 != null : "fx:id=\"txtField1\" was not injected: check your FXML file 'startWindow.fxml'.";
        assert txtField2 != null : "fx:id=\"txtField2\" was not injected: check your FXML file 'startWindow.fxml'.";
        assert playButton != null : "fx:id=\"playButton\" was not injected: check your FXML file 'startWindow.fxml'.";

    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);

	}
	
	public void start(Stage primaryStage) {
		try {
			root = FXMLLoader.load(getClass().getResource("/startWindow.fxml")); 
			primaryStage.setScene(new Scene(root));
		    primaryStage.setResizable(false);
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.print("Exception");
			e.printStackTrace();
		}

	}
	public void startgame(String player1, String player2) {
		try {
			new TicTacToe(player1,player2).start(new Stage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
