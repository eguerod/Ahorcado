package ahorcado;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import ahorcado.palabras.PalabrasController;
import ahorcado.partida.PartidaController;
import ahorcado.puntuaciones.PuntuacionesController;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class RootController implements Initializable {

	// controllers

	private PalabrasController palabrasController = new PalabrasController();
	private PartidaController partidaController = new PartidaController();
	private PuntuacionesController puntuacionesController = new PuntuacionesController();

	// model

	private ListProperty<String> palabras = new SimpleListProperty<>(FXCollections.observableArrayList());
	private ListProperty<String> puntuacionesList = new SimpleListProperty<>(FXCollections.observableArrayList());
	
	// view

	@FXML
	private TabPane view;

	@FXML
	private Tab partidaTab, palabrasTab, puntuacionesTab;

	public RootController() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RootView.fxml"));
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// bindings

		palabrasController.palabrasProperty().bind(palabras);
		getPartidaController().getModel().palabrasProperty().bind(palabras);
		
		puntuacionesController.puntuacionesListProperty().bind(puntuacionesList);
		getPartidaController().getModel().puntuacionesListProperty().bind(puntuacionesList);
		
		palabrasTab.setContent(palabrasController.getView());
		partidaTab.setContent(getPartidaController().getView());
		puntuacionesTab.setContent(puntuacionesController.getView());
	}

	public TabPane getView() {
		return view;
	}

	public final ListProperty<String> palabrasProperty() {
		return this.palabras;
	}

	public final ObservableList<String> getPalabras() {
		return this.palabrasProperty().get();
	}

	public final void setPalabras(final ObservableList<String> palabras) {
		this.palabrasProperty().set(palabras);
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

	public PartidaController getPartidaController() {
		return partidaController;
	}
	

	
}
