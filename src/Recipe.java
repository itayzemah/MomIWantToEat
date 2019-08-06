import java.util.Hashtable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Recipe {
	private String title;
	private Hashtable<String,Double> ingredients = new Hashtable<>();
	private String instraction;
	private Image image;
	public final static int NUMBER_OF_ELEMENTS_IN_RECIPE = 4;
	
	public Recipe(String title,Hashtable<String,Double> ingredients,String instraction) {
		this.setTitle(title);
		this.ingredients = ingredients;
//		for (int i = 0; i < ingredients.size(); i++) {
//			this.ingredients.put(ingredients.get(i).split("$")[0], Double.valueOf(ingredients.get(i).split("$")[1]));
//		}
		this.instraction = instraction;
	}
	
	public Recipe(String line) {
		String[] r = line.split("\n");
		this.title = r[0];
		String[] ingre = r[1].split(",");
		String[] quant = r[2].split(",");

		for (int i = 0; i < ingre.length; i++) {
			this.ingredients.put(ingre[i], Double.valueOf(quant[i]));
			}
		this.instraction = r[3];

	}

	public Hashtable<String,Double> getIngredients(){
		return ingredients;
	}
	
	public void ShowRecipe() {
		GridPane myRecipe = new GridPane();
		Stage s = new Stage();
		Scene sc = new Scene(this.toPane());
		myRecipe.add(new Label(this.title),0, 0);
		myRecipe.add(new Label(this.ingredients.toString()), 0, 1);
		myRecipe.add(new Label(this.instraction), 0, 2);
		myRecipe.setVgap(10);
		s.setScene(sc);
		s.show();
	}
	
	public VBox toPane() {
		VBox myBox = new VBox();
		TextArea nameTxt = new TextArea();
		TextArea ingreTxt = new TextArea();
		TextArea instraTxt = new TextArea();
		nameTxt.setText(title);
		nameTxt.setEditable(false);
		ingreTxt.setText(getIngredientsToString());
		ingreTxt.setEditable(false);
		instraTxt.setText(instraction);
		instraTxt.setEditable(false);
		instraTxt.setWrapText(true);
		myBox.getChildren().add(nameTxt);
		myBox.getChildren().addAll(new Label("מרכיבים:\n" ), ingreTxt);
		myBox.getChildren().addAll(new Label("אופן הכנה:\n"),instraTxt);
		return myBox;
	}
	private String getIngredientsToString() {
		String tmp = ingredients.toString();
		tmp = SearchRecipe.cleanLine(tmp);
//		tmp = tmp.replaceAll("\\{", "");
//		tmp = tmp.replaceAll("}", "");
		String[] ingredientsAsList = tmp.split(",");
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < ingredientsAsList.length; i++) {
			sb.append(ingredientsAsList[i].split("=")[1]+ " ");
			sb.append(ingredientsAsList[i].split("=")[0]);
			sb.append("\n");
		}
		return sb.toString();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(title);
		builder.append("\n");
		builder.append(getIngredients().toString());
		builder.append("\n");
		builder.append(instraction);
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
//	public String getIngre() {
//		StringBuilder builder = new StringBuilder();
//		for (Ingredient value : this.ingredients) {
//		    builder.append(value + "\n");
//		}
//		return builder.toString();
//	}
}
