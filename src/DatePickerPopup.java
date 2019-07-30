import java.time.LocalDate;

import com.sun.javafx.scene.control.skin.DatePickerSkin;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class DatePickerPopup extends BorderPane {

	public DatePickerPopup(Stage stage) {
		 try {
	            Scene scene = new Scene(this, 400, 400);
//	            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

	            DatePicker datePicker = new DatePicker(LocalDate.now());
	            DatePickerSkin datePickerSkin = new DatePickerSkin(datePicker);
	            Node popupContent = datePickerSkin.getPopupContent();

	            this.setCenter(popupContent);

	            stage.setScene(scene);
	            stage.show();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	}



}
