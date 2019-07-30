import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		HomeScreen main = new HomeScreen();
		Scene scene = new Scene(main);
		primaryStage.setScene(scene);
		primaryStage.show();

	}

}
