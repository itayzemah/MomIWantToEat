import java.util.ArrayList;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class MyTable extends HBox {
	private ArrayList<Recipe> currentRecipes;
	private boolean created = false;

	public MyTable() {
		this.getChildren().add(new Label("טרם נקבעה מערכת מהתכונים שלך"));

	}

	public void setNewTable(ArrayList<Recipe> randomRecipes) {
		this.setCurrentRecipes(randomRecipes);
		this.getChildren().clear();
		for (int i = 0; i < randomRecipes.size(); i++) {
			this.getChildren().add(randomRecipes.get(i).toPane());

		}
		setCreated(true);
	}

	public ArrayList<Recipe> getCurrentRecipes() {
		return currentRecipes;
	}

	public void setCurrentRecipes(ArrayList<Recipe> currentRecipes) {
		this.currentRecipes = currentRecipes;
	}

	public boolean isCreated() {
		return created;
	}

	public void setCreated(boolean created) {
		this.created = created;
	}

}
