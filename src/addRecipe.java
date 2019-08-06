import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class addRecipe extends GridPane {
	public static int numOfInstances = 0;
	private Label RName;
	private Label Ringre;
	private Label Rinstac;
	private TextArea nameTF;
	private GridPane ingreGP = new GridPane();
	private TextField ingredient = new TextField();
	private TextField ingredient1 = new TextField();
	private TextField ingredient2 = new TextField();
	private TextField ingredient3 = new TextField();
	private TextField ingredient4 = new TextField();
	private TextField ingredient5 = new TextField();
	private TextField ingredient6 = new TextField();
	private TextField ingredient7 = new TextField();
	private TextField ingredient8 = new TextField();
	private TextField ingredient9 = new TextField();
	private TextField ingredient10 = new TextField();
	private TextField ingredient11 = new TextField();
	private TextField ingredient12 = new TextField();
	private TextField ingredient13 = new TextField();
	private TextField ingredient14 = new TextField();
	private TextField ingredient15 = new TextField();
	private TextField ingredient16 = new TextField();
	private TextField ingredient17 = new TextField();
	private TextField ingredient18 = new TextField();
	private TextField ingredient19 = new TextField();
	private TextField quantity = new TextField();
	private TextField quantity1 = new TextField();
	private TextField quantity2 = new TextField();
	private TextField quantity3 = new TextField();
	private TextField quantity4 = new TextField();
	private TextField quantity5 = new TextField();
	private TextField quantity6 = new TextField();
	private TextField quantity7 = new TextField();
	private TextField quantity8 = new TextField();
	private TextField quantity9 = new TextField();
	private TextField quantity10 = new TextField();
	private TextField quantity11 = new TextField();
	private TextField quantity12 = new TextField();
	private TextField quantity13 = new TextField();
	private TextField quantity14 = new TextField();
	private TextField quantity15 = new TextField();
	private TextField quantity16 = new TextField();
	private TextField quantity17 = new TextField();
	private TextField quantity18 = new TextField();
	private TextField quantity19 = new TextField();
	private TextArea instracTF;
	private Button add;
	private Button moreIngre = new Button("עוד מרכיבים");
	private int MAX_INGREDIENTS = 20;
	private int numOfIngredients = 5;
	private TextField[] ingredTFArr = { ingredient, ingredient1, ingredient2, ingredient3, ingredient4, ingredient5, ingredient6,
			ingredient7, ingredient8, ingredient9, ingredient10, ingredient11, ingredient12, ingredient13,
			ingredient14, ingredient15, ingredient16, ingredient17, ingredient18, ingredient19 };
	private TextField[] quanTFArr = { quantity, quantity1, quantity2, quantity3, quantity4, quantity5, quantity6, quantity7,
			quantity8, quantity9, quantity10, quantity11, quantity12, quantity13, quantity14, quantity15,
			quantity16, quantity17, quantity18, quantity19 };
	private Stage myStage;

	public addRecipe(Stage myStage) {
		RName = new Label("שם המתכון");
		Ringre = new Label("מרכיבים");
		Rinstac = new Label("הוראות הכנה");
		nameTF = new TextArea();
		nameTF.setWrapText(true);
		nameTF.setMaxHeight(40);
		instracTF = new TextArea();
		instracTF.setWrapText(true);
		this.myStage = myStage;
		add = new Button("הוסף");
		makeWindow();

	}

	private void makeWindow() {
		gridSizes();
		gridIngre();
		this.instracTF.setMinHeight(90);
		this.add(RName, 2, 2);
		this.add(Ringre, 2, 3);
		this.add(Rinstac, 2, 4);
		this.add(nameTF, 1, 2);
		this.add(ingreGP, 1, 3);
		this.add(instracTF, 1, 4);
		add.setOnAction(e -> {
			addToDB(getNameTF(), getIngreTFNames(),getIngredTFQuan(), getInstracTF());
			this.myStage.close();
		});
		this.add(add, 1, 6);

	}

	

	private void gridIngre() {
		
		for (int i = 0; i < ingredTFArr.length; i++) {
			ingredTFArr[i].setPromptText("מרכיב");
			ingredTFArr[i].setPrefWidth(310);
		}
		
		for (int i = 0; i < quanTFArr.length; i++) {
			quanTFArr[i].setPromptText("כמות:");
			quanTFArr[i].setPrefWidth(90);
		}

		ingreGP.add(ingredient, 0, 0);
		ingreGP.add(quantity, 1, 0);
		ingreGP.add(ingredient1, 0, 1);
		ingreGP.add(quantity1, 1, 1);
		ingreGP.add(ingredient2, 0, 2);
		ingreGP.add(quantity2, 1, 2);
		ingreGP.add(ingredient3, 0, 3);
		ingreGP.add(quantity3, 1, 3);
		ingreGP.add(ingredient4, 0, 4);
		ingreGP.add(quantity4, 1, 4);
		ingreGP.add(moreIngre, 4, 4);
		moreIngre.setOnAction(e -> {
			if(numOfIngredients < MAX_INGREDIENTS) {
				moreIngredients();
				numOfIngredients++;
			}
		});
	}

	private void moreIngredients() {
		this.myStage.setMinHeight(numOfIngredients * 20 +550);
		ingreGP.add(ingredTFArr[numOfIngredients], 0, numOfIngredients);
		ingreGP.add(quanTFArr[numOfIngredients], 1, numOfIngredients);
	}

	private void gridSizes() {
		this.setMinHeight(250);
		this.setMinWidth(400);
		this.setPadding(new Insets(10));
		this.setVgap(8);
		this.setHgap(5);
	}

	private void addToDB(String name, String ingreNames,String ingredQuan, String pre) {
		Process p = null;
		try {
			p = Runtime.getRuntime().exec("cmd /c start cmd.exe /K \"python InsertRecipe.py \"" + name + "\""
					+ " \"" + ingreNames + "\" \""+ingredQuan+"\" \"" + pre + "\" && exit\" ");
			p.waitFor();
		} catch (Exception e) {
			e.getMessage();
		} finally {
			p.destroy();
		}

	}

	public String getNameTF() {
		return nameTF.getText();
	}

	public String getIngreTFNames() {
		StringBuffer names = new StringBuffer();
		for (int TF = 0; TF < ingredTFArr.length-1; TF++) {
			if (ingredTFArr[TF].getText().isEmpty()) {
				continue;
			}
			names.append(ingredTFArr[TF].getText() + ",");
		}
		names.append(ingredTFArr[ingredTFArr.length-1].getText());
		String str = names.toString().replaceAll("\"", ".");
		System.out.println(str);
		return str;
	}
	
	private String getIngredTFQuan() {
		StringBuffer quans = new StringBuffer();
		for (int TF = 0; TF < quanTFArr.length-1; TF++) {
			quans.append(quanTFArr[TF].getText()+ ",");
		}
		quans.append(quanTFArr[quanTFArr.length-1].getText());
		System.out.println(quans.toString());
		return quans.toString();
	}

	public String getInstracTF() {
		return instracTF.getText();
	}
}
