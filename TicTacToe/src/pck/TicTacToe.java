package pck;
import pck.myButton;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.fxml.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.event.*;
import javafx.scene.input.*;
import java.lang.Math; 
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Arrays;


public class TicTacToe extends Application {
	String player1,player2;
	int btnnumber = 9;
	int[]winList = new int[3];
	TicTacToe(String player1, String player2){
		this.player1=player1;
		this.player2=player2;
	}
	
    public boolean testWinner(myButton[] buttons, myButton button) {
    	boolean test=false;
    	int length = 0;
    	for (myButton btn : buttons) {
    		if (btn.player == button.player && btn.clicked)
				length++;
    	}
    	if (length<3) return false;
    	else {
    		int winComb1[] = {0,1,2};
    		int winComb2[] = {3,4,5};
    		int winComb3[] = {6,7,8};
    		int winComb4[] = {0,3,6};
    		int winComb5[] = {1,4,7};
    		int winComb6[] = {2,5,8};
    		int winComb7[] = {0,4,8};
    		int winComb8[] = {2,4,6};
    		ArrayList<int[]> list = new ArrayList();
    		list.add(winComb1); list.add(winComb1); list.add(winComb2); list.add(winComb3); list.add(winComb4);
    		list.add(winComb5); list.add(winComb6); list.add(winComb7); list.add(winComb8);

    		int pos[] = new int[length];
        	int i = 0;
        	for (myButton btn : buttons) 
        		if (btn.clicked) 
        			if (btn.player == button.player)
        				pos[i++] =btn.id;

        	for (int[] l : list) {
        		int help[] = compare(l, pos);
        		if (help !=null) {
        			setWinList(help);
        			test = true;
        		}
        	}
    	}
    	
    	return test;
    }
    
    int clickCounter = 0;
    myButton buttons[];
    myButton help = new myButton();
    int t = 0;
	Rectangle r = new Rectangle(340,20);
	Rectangle r1 = new Rectangle(340,20);
	Rectangle r2 = new Rectangle(20,300);
	Rectangle r3 = new Rectangle(20,300);

	@Override
	public void start(Stage primaryStage) throws Exception {
		int width = 300;
		int height = 300;
		boolean test = true;
		VBox vbox = new VBox();
		HBox hbox = new HBox();
	    Label label = new Label(player1 + " playing");
	    label.setFont(new Font(15));
	    
	    Button button = new Button ("Restart");
	    
		buttons = new myButton[btnnumber];
		for (int i = 0; i<btnnumber; i++) {
			buttons[i] = new myButton(i);
		}
	    
		class myEventHandler implements EventHandler{

	    	@Override
	    	public void handle(Event event) {
	    		myButton btn = (myButton) event.getSource();
	    		System.out.println("clicked button id: " + btn.id);
	    		ImageView img=null;
	    		if (!btn.clicked ) {
	    			if (clickCounter % 2 == 0) {
		    			img = new ImageView("letterX.png");
		    			btn.player=1;
	    				}
	    			else if(clickCounter % 2 == 1) {
	    				img = new ImageView("letterO.png");
		    			btn.player=2;
	    				}
	    			img.setFitHeight(80);
		    		img.setFitWidth(80);
		    		btn.setGraphic(img);
		    		btn.clicked=!btn.clicked;
		    		clickCounter+=1;
	    			if (testWinner(buttons, btn)) {
	    				label.setText(getPlayerName(btn.player) + " WON");
	    				block();
	    				borderGreen();
	    				celebrate();
	    				//restart();
	    				System.out.println("Winner: " +getNextPlayerName(btn.player));
	    				}
	    			else
	    				label.setText(getNextPlayerName(btn.player) + " playing:");

		    		}
	    		else if (btn.clicked) {
	    			System.out.println("button already clicked");
	    		}
	    						
	    	}
	    }

		for (myButton btn : buttons) {
			btn.setOnAction(new myEventHandler());
		}
		button.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event) {restart();}
			});
		
		
		
		myHBox hboxes[] = new myHBox[(int) Math.sqrt(buttons.length)];
		int btncounter = 0;
		for (int i = 0; i<3; i++) {
			hboxes[i] = new myHBox(i);
			for (int j = 0; j< Math.sqrt(buttons.length); j++) {
				hboxes[i].getChildren().add(buttons[btncounter]);
				//System.out.println("In hbox" + i + ", button " + buttons[btncounter].id);
				btncounter++;
			}	
		}

		label.setPrefSize(120, 30);
		label.setTranslateX(width/2-label.getPrefWidth()/2);
		button.setPrefWidth(340);
		//canvas.setTranslateY(-200);
		VBox vbox1 = new VBox();	

		r.setFill(Color.BLACK);
		r.fillProperty();
		r2.setFill(Color.BLACK);
		r2.fillProperty();
		r3.setFill(Color.BLACK);
		r3.fillProperty();	
		r1.setFill(Color.BLACK);
		r1.fillProperty();	
		
		vbox1.getChildren().addAll(hboxes);
		HBox hbox1 = new HBox();
		hbox1.getChildren().add(r2);
		hbox1.getChildren().add(vbox1);
		hbox1.getChildren().add(r3);

		label.setAlignment(Pos.CENTER);
		button.setAlignment(Pos.CENTER);
		vbox.getChildren().add(label);
		vbox.getChildren().add(r);
		vbox.getChildren().add(hbox1);
		vbox.getChildren().add(r1);
		vbox.getChildren().add(button);
		//hbox.getChildren().add(r3);

		

		primaryStage.setTitle("TikTok");
		Scene scene = new Scene(vbox);
	    primaryStage.setScene(scene);
	    primaryStage.setResizable(false);
	    primaryStage.show();
				
	}
	public void restart() {
		for (myButton btn: buttons) {
			btn.clear();
			borderBlack();
		}
		clickCounter=0;
		unblock();
		System.out.println("restarted");

	}
		
	public void block() {
		System.out.println("Fields blocked");
		for (myButton btn: buttons) {
			//btn.setDisable(true);
		}
	}
	public void unblock() {
		System.out.println("Fields unblocked");
		for (myButton btn: buttons) {
			//btn.setDisable(false);
		}
	}
	public void borderGreen() {
		r.setFill(Color.GREEN);
		r1.setFill(Color.GREEN);
		r2.setFill(Color.GREEN);
		r3.setFill(Color.GREEN);
	}
	public void borderBlack() {
		r.setFill(Color.BLACK);
		r1.setFill(Color.BLACK);
		r2.setFill(Color.BLACK);
		r3.setFill(Color.BLACK);
	}
	public int[] compare(int list1[], int list2[]) {
		
		int summ = 0;
		String str = Arrays. toString(list1);
		String str2 = Arrays. toString(list1);
		
		for (int i : list1)
			for (int j : list2) {
				if(i == j) 
				{
					summ++;
					
				}
			}
		if (summ == 3)
			return list1;
		else
			return null;
	}
	public void setWinList(int[] list) {
		int k = 0;
		for (int i : list)
			winList[k++] = i;
		
	}
	public String getNextPlayerName(int i) {
		return (i%2==0) ? player1 : player2;
	}
	public String getPlayerName(int i) {
		return (i%2==1) ? player1 : player2;
	}
	
	public void celebrate() {
		ImageView img;;
	
		for (int i = 0 ; i<winList.length; i++) {
			img = (buttons[winList[0]].player==1) ? new ImageView("letterXstroke.png") : new ImageView("letterOstroke.png");
			img.setFitHeight(90);
			img.setFitWidth(90);
			buttons[winList[i]].setGraphic(img);
			System.out.println(buttons[winList[i]].id + "clearing");
		}
	
	}	
}
