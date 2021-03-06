import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Scanner;

import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class SearchRecipe extends GridPane {
	private TextArea nameTF;
	private TextArea ingredTF;
	private CheckBox byName;
	private CheckBox byingre;
	private Button search = new Button("���");
	private Stage myStage;
	private String str;

	public SearchRecipe(Stage myStage) {
		initVars(myStage);
		initWindow();
	}

	private void initWindow() {
		this.add(byName, 0, 0);
		this.add(nameTF, 0, 1);
		this.add(byingre, 1, 0);
		this.add(ingredTF, 1, 1);
		this.add(search, 0, 3);
		this.setPrefSize(500, 200);
		this.setPadding(new Insets(15));
		this.setHgap(12);
	}

	private void initVars(Stage myStage) {
		this.myStage = myStage;
		handleNameTF();
		hendleIngerTF();
		byName = new CheckBox("�� ������");
		byingre = new CheckBox("�������");

		search.setOnAction(e -> search());
	}

	private void hendleIngerTF() {
		ingredTF = new TextArea();
		ingredTF.setPromptText("������ :�����, ������ ����...");
		ingredTF.setMaxHeight(150);
		ingredTF.setWrapText(true);
	}

	private void handleNameTF() {
		nameTF = new TextArea();
		nameTF.setPromptText("������ : ���� ������");
		nameTF.setMaxHeight(150);
		nameTF.setWrapText(true);
	}

	public void trackScriptProcess(Process p) {
		new Thread(() -> {
			try {
				getExitCode(p);
				System.out.println(this.str);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();
	}

	private String getExitCode(Process p) throws IOException {
		this.str = null;
		BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
		while (true) {
			this.str = stdInput.readLine();
			System.out.println(this.str);
			if (str.equals("0")) {
				break;
			}
		}

		return str;
	}

	protected void getFullRecipeFromDB(String recipeName) {
		Process p = null;
		DateFormat df = new SimpleDateFormat("ddMMyy_");
		Date dateobj = new Date();
		File tmpFile = new File(System.getProperty("user.dir"));
		try {
			tmpFile = File.createTempFile(df.format(dateobj.getTime()), ".txt", tmpFile);

			p = Runtime.getRuntime().exec("cmd /c start cmd.exe /K \"python DataBase.py \"" + recipeName + "\" >> "
					+ tmpFile.toString() + " && exit\" ");
			Thread.sleep(500);
			openRecipeFromFile(tmpFile);

		} catch (Exception e2) {
			e2.printStackTrace();
		} finally {
			p.destroy();
			p.destroyForcibly();
		}
	}

	private void openRecipeFromFile(File tmpFile) throws FileNotFoundException {
		Scanner s = new Scanner(tmpFile);
		while (s.hasNextLine()) {
			String recipeName = s.nextLine();
			String recipeIngre = s.nextLine();
			recipeIngre = SearchRecipe.cleanLine(recipeIngre);
			String ingreQuants = s.nextLine();
			ingreQuants = SearchRecipe.cleanLine(ingreQuants);
			String recipePrepe = s.nextLine();
			Hashtable<String, Double> ingredientsTable = HomeScreen.makeIngredientsTable(recipeIngre.split(","),
					ingreQuants.split(","));
			Recipe r = new Recipe(recipeName, ingredientsTable, recipePrepe);
			r.ShowRecipe();
		}
		s.close();
	}

	public void search() {
		if ((getNameTF().isEmpty() && getIngreTF().isEmpty()) || (!byingre.isSelected() && !byName.isSelected())) {
			Alert error = new Alert(AlertType.ERROR);
			error.setContentText("�� ����� ���� ������");
			error.show();
			return;
		} else {
			String recName = !byName.isSelected() ? "~noTitle~" : getNameTF();
			String ingredients = !byingre.isSelected() ? "" : getIngreTF();
			Process p = null;
			File f = new File(System.getProperty("user.dir"));
			;
			try {
				f = File.createTempFile("tmp1", ".txt", f);
				if (!ingredients.isEmpty()) {
					System.out.println("!ingredients.isEmpty()");
					p = Runtime.getRuntime().exec("cmd /c start cmd.exe /K \"python SearchRecipe.py \"" + recName
							+ "\" \"" + ingredients + "\" >> " + f.toString() + " && exit\" ");
					Thread.sleep(500);
					openRecipeFromFile(f);
//					p = Runtime.getRuntime().exec("cmd /c start cmd.exe /K \"python SearchRecipe.py \"" + recName
//							+ "\" \"" + ingredients + "\" >> \"" + f.toString() + "\"\" && exit\"");
				} else {
					getFullRecipeFromDB(recName);
				}
//				Thread.sleep(300);
//				Scanner s = new Scanner(f);
//				while (s.hasNext()) {
//					System.out.println(s.nextLine());
//				}
//				s.close();
//				p = Runtime.getRuntime()
//						.exec("cmd /c start cmd.exe /K \"DEL /Q \""+ f.getCanonicalPath()+"\" \" && exit\" ");
			} catch (Exception e) {
				e.getMessage();
			} finally {
				myStage.close();
			}
		}

	}

	public String getNameTF() {
		return nameTF.getText();
	}

	public String getIngreTF() {
		return ingredTF.getText();
	}

	public static String cleanLine(String line) {
		line = line.replaceAll("\\{", "");
		line = line.replaceAll("}", "");
		line = line.replace("]", "");
		line = line.replace("[", "");
		line = line.replace("'", "");
		return line;
	}

}
