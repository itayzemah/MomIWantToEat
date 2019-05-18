import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class MyTable extends TableView<String> {
	public MyTable() {
        this.setEditable(true);
        TableColumn<String,String> sunday = new TableColumn<String, String>("יום ראשון");
        TableColumn<String,String> monday = new TableColumn<String, String>("יום שני");
        TableColumn<String,String> tuesday = new TableColumn<String, String>("יום שלישי");
        TableColumn<String,String> wednesday = new TableColumn<String, String>("יום רביעי");
        TableColumn<String,String> thursday = new TableColumn<String, String>("יום חמישי");
        TableColumn<String,String> frifday = new TableColumn<String, String>("יום שישי");

        this.getColumns().addAll(frifday,thursday,wednesday,tuesday,monday,sunday );

	}
}
