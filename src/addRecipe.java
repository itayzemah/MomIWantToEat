import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class addRecipe extends GridPane {
	public static int numOfInstances = 0;
	private Label RName;
	private Label Ringre;
	private Label Rinstac;
	private TextArea nameTF;
	private TextArea ingreTF;
	private TextArea instracTF;
	private Button add;
	private Stage myStage;

	public addRecipe(Stage myStage) {
		RName = new Label("שם המתכון");
		Ringre = new Label("מרכיבים");
		Rinstac = new Label("הוראות הכנה");
		nameTF = new TextArea();
		nameTF.setWrapText(true);
		nameTF.setMaxHeight(40);
		ingreTF = new TextArea();
		ingreTF.setWrapText(true);
		instracTF = new TextArea();
		instracTF.setWrapText(true);
		this.myStage = myStage;
		add = new Button("הוסף");
		makeWindow();

	}

	private void makeWindow() {
		gridSizes();
		this.instracTF.setMinHeight(90);
		this.add(RName, 2, 2);
		this.add(Ringre, 2, 3);
		this.add(Rinstac, 2, 4);
		this.add(nameTF, 1, 2);
		this.add(ingreTF, 1, 3);
		this.add(instracTF, 1, 4);
		add.setOnAction(e -> {addToDB(getNameTF(), getIngreTF(), getInstracTF());
				this.myStage.close();		
		});
		this.add(add, 1, 6);

	}

	private void gridSizes() {
		this.setMinHeight(250);
		this.setMinWidth(300);
		this.setPadding(new Insets(10));
		this.setVgap(8);
		this.setHgap(5);
	}

	private void addToDB(String name, String ingre, String pre) {
		Process p = null;
		try {
			p = Runtime.getRuntime().exec("cmd /c start cmd.exe /K \"python InsertRecipe.py \"" + name + "\" \" +"
					+ ingre + "\" \"" + pre + "\" && exit\" ");
			p.waitFor();
		} catch (Exception e) {
			e.getMessage();
		}
		finally {
			
			p.destroy();
		}

	}

	public String getNameTF() {
		return nameTF.getText();
	}

	public String getIngreTF() {
		return ingreTF.getText();
	}

	public String getInstracTF() {
		return instracTF.getText();
	}
}
