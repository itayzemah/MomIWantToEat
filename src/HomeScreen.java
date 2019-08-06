import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class HomeScreen extends BorderPane {
	private Button addRecipe;
	private Button showAllRecepipeInList;
	private Button showCalendar;
	private Button showShoppingList;
	private Button makeSchedule;
	private Button searchRecipe;
	private MyTable myTable = new MyTable();
	private HBox buttons = new HBox();
	public final int WEEK_LENGTH = 6;

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

		});
		
		showAllRecepipeInList.setOnAction(e -> {
			try {
				MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
				DB database = mongoClient.getDB("mydb");
				DBCollection collection = database.getCollection("recipe");
				DBCursor cursor = collection.find();
				while (cursor.hasNext()) {
				   DBObject obj = cursor.next();
				   System.out.println(obj);
				}
			} catch (UnknownHostException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		});
		showShoppingList.setOnAction(e -> {
			if (myTable.isCreated() == true) {
				ShoppingList shoppingList = new ShoppingList(myTable.getCurrentRecipes());
				Stage shoppingListStage = new Stage();
				Pane showShoppingList = shoppingList.toPane();
				Scene shoppingListScene = new Scene(showShoppingList);
				shoppingListStage.setScene(shoppingListScene);
				shoppingListStage.show();
				
			}
			
		});
		searchRecipe.setOnAction(e -> {
			Stage searchStage = new Stage();
			SearchRecipe seacher = new SearchRecipe(searchStage);
			Scene searchScene = new Scene(seacher);
			searchStage.setScene(searchScene);
			searchStage.show();

		});

		makeSchedule.setOnAction(e -> {
			ArrayList<Recipe> randomRecipes = randRecepies();

			myTable.setNewTable(randomRecipes);

		});
		
		showCalendar.setOnAction(e -> {
			Stage calender = new Stage();
			DatePickerPopup datePickerPopup = new DatePickerPopup(calender);
			
		});
	}

	private ArrayList<Recipe> randRecepies() {
		File tmpFile = createRandomFile();
		ArrayList<Recipe> forTable = new ArrayList<>();
		String allScript = createScript(tmpFile.toString());
		try {
			Process p = Runtime.getRuntime().exec("cmd /c start /wait cmd.exe /K \"" + allScript + " && exit \" ");
			p.waitFor();

			readRecepiesFromFile(tmpFile.toString(), forTable);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}

//		System.out.println(forTable);
		return forTable;
	}

	private void readRecepiesFromFile(String tmpFile, ArrayList<Recipe> forTable) throws IOException {
		File readRecipe = new File(tmpFile);
		BufferedReader br = new BufferedReader(new FileReader(readRecipe));
		for (int i = 0; i < WEEK_LENGTH; i++) {
			String recipeName = br.readLine();
			String recipeIngre = br.readLine();
			recipeIngre = SearchRecipe.cleanLine(recipeIngre);
			String ingreQuants = br.readLine();
			ingreQuants = SearchRecipe.cleanLine(ingreQuants);
//			recipeIngre = recipeIngre.replace("]", "");
//			recipeIngre = recipeIngre.replace("[", "");
//			recipeIngre = recipeIngre.replace("'", "");
//			ingreQuants = ingreQuants.replace("]", "");
//			ingreQuants = ingreQuants.replace("[", "");
//			ingreQuants = ingreQuants.replace("'", "");
			String recipePrepe = br.readLine();
			Hashtable<String, Double> ingredientsTable = makeIngredientsTable(recipeIngre.split(","),
					ingreQuants.split(","));
			Recipe r = new Recipe(recipeName, ingredientsTable, recipePrepe);
			forTable.add(r);
		}
		br.close();
	}

	public static Hashtable<String, Double> makeIngredientsTable(String[] ingre, String[] quants) {
		Hashtable<String, Double> table = new Hashtable<String, Double>();
		for (int i = 0; i < quants.length; i++) {
			table.put(ingre[i].trim(), Double.valueOf(quants[i].trim()));
		}
		return table;
	}

	private String createScript(String tmpFile) {
		String script = "python RandomRecipe.py >> " + tmpFile.toString();
		String allScript = script;

		for (int i = 0; i < WEEK_LENGTH - 1; i++) {
			allScript += ">> " + tmpFile + " && " + script;
		}
		return allScript;
	}

	public File createRandomFile() {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
		File file = new File(dateFormat.format(date) + ".txt");
		return file;
	}

//	private String getRandomRecipe() {
//		File tmpFile = new File(System.getProperty("user.dir"));
//		Recipe r = null;
//		try {
//			Runtime.getRuntime().exec("cmd /c start cmd.exe /K \"python RandomRecipe.py \" \" > " + tmpFile.toString()
//					+ "\\tmp.txt" + " && exit\" ");
//			Thread.sleep(800);
//			File read = new File(tmpFile.toPath() + "\\tmp.txt");
//			Scanner s = new Scanner(read);
//			StringBuffer line = new StringBuffer();
//			while (s.hasNextLine()) {
//				line.append(s.nextLine() + "\n");
//				System.out.println(line);
//			}
//			s.close();
//			r = new Recipe(line.toString());
//			Runtime.getRuntime()
//					.exec("cmd /c start cmd.exe /K \"del /Q " + tmpFile.toString() + "\\tmp.txt" + " && exit \" ");
//
//		} catch (Exception e2) {
//			e2.printStackTrace();
//		}
//		return r.toString();
//	}

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
