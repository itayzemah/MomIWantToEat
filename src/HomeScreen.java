import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class HomeScreen extends BorderPane {
	private Button addRecipe;
	private Button showAllRecepipeInList;
	private Button showCalendar;
	private Button showShoppingList;
	private Button makeSchedule;
	private Button searchRecipe;
	private MyTable myTable= new MyTable();
	private HBox buttons = new HBox();


	public HomeScreen() {
		this.setMinWidth(900);
		this.setMinHeight(600);
		this.setPadding(new Insets(10));
		this.setBottom(buttons);
		createButtonsBar();
		this.setCenter(myTable);
		addRecipe.setOnAction(e -> {
			
			Stage addStage = new Stage();
			addRecipe adder = new addRecipe(addStage);
			Scene addScene = new Scene(adder);
				
			addStage.setScene(addScene);
			addStage.show();
//			String[] ingreArr = ingre.split(",");
//			getFullRecipeFromDB("p");

		});
		
		
		searchRecipe.setOnAction(e -> {
			Stage searchStage = new Stage();
			SearchRecipe seacher = new SearchRecipe(searchStage);
			Scene searchScene = new Scene(seacher);
			searchStage.setScene(searchScene);
			searchStage.show();

		});
		
		
		makeSchedule.setOnAction(e -> {
			
		});
	}

	

		

	private void createButtonsBar() {
		addRecipe = new Button("הוסף מתכון");
		showAllRecepipeInList = new Button("הצג רשימת מתכונים");
		showCalendar = new Button("הצג לוח שנה");
		showShoppingList = new Button("הצג רשימת קניות");
		makeSchedule = new Button("הכן מערכת שבועית");
		searchRecipe = new Button("חפש מתכון");
		this.buttons.getChildren().add(this.addRecipe);
		this.buttons.getChildren().add(this.showAllRecepipeInList);
		this.buttons.getChildren().add(this.showCalendar);
		this.buttons.getChildren().add(this.showShoppingList);
		this.buttons.getChildren().addAll(this.makeSchedule, this.searchRecipe);
		buttons.setSpacing(15);

	}
}
