import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Recipe {
	private String title;
	private ArrayList<String> ingredients;
	private String instraction;
	private Image image;
	
	public Recipe(String title,ArrayList<String> ingredients,String instraction) {
		this.setTitle(title);
		this.ingredients = ingredients;
		this.instraction = instraction;
	}
	
	public Recipe(String line) {
		String[] splited = line.split("\n") ;
		this.setTitle(splited[0]);
		this.ingredients = new ArrayList<>(Arrays.asList(splited[1].split(",")));
		this.instraction = splited[2];
	}

	public Set<String> getIngredients(){
		TreeSet<String> myIngredients = new TreeSet<>();
		myIngredients.addAll(ingredients);
		return myIngredients;
	}
	
	public void ShowRecipe() {
		GridPane myRecipe = new GridPane();
		Stage s = new Stage();
		Scene sc = new Scene(myRecipe);
		myRecipe.add(new Label(this.title),0, 0);
		myRecipe.add(new Label(this.ingredients.toString()), 0, 1);
		myRecipe.add(new Label(this.instraction), 0, 2);
		myRecipe.setVgap(10);
		s.setScene(sc);
		s.show();
	}
	
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(title);
		builder.append(",|,");
		builder.append(ingredients);
		builder.append(",|,");
		builder.append(instraction);
		builder.append("]");
		return builder.toString();
	}

	public String instractionsToString() {
		return instraction;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
}
