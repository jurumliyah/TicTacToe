package pck;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class myButton extends Button{
	int id;
	int player;
	boolean clicked = false;
	String name;
	void configure() {
		this.setMinSize(100, 100);
	}
	void clear() {
		this.setGraphic(null);
		this.clicked=false;
		this.player=0;
	}
	public myButton(int id) {
		super();
		this.id=id;
		configure();
	}
	public myButton() {
		super();
		configure();
	}

}
