import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class MyTable extends TableView<String> {
	public MyTable() {
        this.setEditable(true);
        TableColumn<String,String> sunday = new TableColumn<String, String>("��� �����");
        TableColumn<String,String> monday = new TableColumn<String, String>("��� ���");
        TableColumn<String,String> tuesday = new TableColumn<String, String>("��� �����");
        TableColumn<String,String> wednesday = new TableColumn<String, String>("��� �����");
        TableColumn<String,String> thursday = new TableColumn<String, String>("��� �����");
        TableColumn<String,String> frifday = new TableColumn<String, String>("��� ����");

        this.getColumns().addAll(frifday,thursday,wednesday,tuesday,monday,sunday );

	}
}
