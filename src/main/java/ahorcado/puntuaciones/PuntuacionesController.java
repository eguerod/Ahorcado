package ahorcado.puntuaciones;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

public class PuntuacionesController implements Initializable {
	
	private ListProperty<String> puntuacionesList = new SimpleListProperty<>(FXCollections.observableArrayList());
	
    @FXML
    private TableColumn<String, String> letrasColumn;

    @FXML
    private TableColumn<String, String> nombreColumn;

    @FXML
    private TableColumn<String, String> palabrasColumn;

    @FXML
    private TableView<String> puntTable;

    @FXML
    private BorderPane view;

	public PuntuacionesController() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PuntuacionesView.fxml"));
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		puntTable.itemsProperty().bind(puntuacionesList);
		
		nombreColumn.setCellValueFactory(cellDataFeatures -> {
            String values = cellDataFeatures.getValue();
            String[] cells = values.split(",");
            int columnIndex = cellDataFeatures.getTableView().getColumns().indexOf(cellDataFeatures.getTableColumn());
            if (columnIndex >= cells.length) {
                return new SimpleStringProperty("");
            } else {
                return new SimpleStringProperty(cells[columnIndex]);
            }
        });
		
		palabrasColumn.setCellValueFactory(cellDataFeatures -> {
            String values = cellDataFeatures.getValue();
            String[] cells = values.split(",");
            int columnIndex = cellDataFeatures.getTableView().getColumns().indexOf(cellDataFeatures.getTableColumn());
            if (columnIndex >= cells.length) {
                return new SimpleStringProperty("");
            } else {
                return new SimpleStringProperty(cells[columnIndex]);
            }
        });
		
		letrasColumn.setCellValueFactory(cellDataFeatures -> {
            String values = cellDataFeatures.getValue();
            String[] cells = values.split(",");
            int columnIndex = cellDataFeatures.getTableView().getColumns().indexOf(cellDataFeatures.getTableColumn());
            if (columnIndex >= cells.length) {
                return new SimpleStringProperty("");
            } else {
                return new SimpleStringProperty(cells[columnIndex]);
            }
        });
	}
	
	public BorderPane getView() {
		return view;
	}

	public final ListProperty<String> puntuacionesListProperty() {
		return this.puntuacionesList;
	}
	

	public final ObservableList<String> getPuntuacionesList() {
		return this.puntuacionesListProperty().get();
	}
	

	public final void setPuntuacionesList(final ObservableList<String> puntuacionesList) {
		this.puntuacionesListProperty().set(puntuacionesList);
	}
	

}
