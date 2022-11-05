package ahorcado.partida;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PartidaModel {
	private StringProperty respuesta = new SimpleStringProperty();
	private StringProperty aciertosPalabras = new SimpleStringProperty();
	private StringProperty aciertosLetras = new SimpleStringProperty();
	private ListProperty<String> palabras = new SimpleListProperty<>(FXCollections.observableArrayList());
	private ListProperty<String> puntuacionesList = new SimpleListProperty<>(FXCollections.observableArrayList());

	public final StringProperty respuestaProperty() {
		return this.respuesta;
	}

	public final String getRespuesta() {
		return this.respuestaProperty().get();
	}

	public final void setRespuesta(final String respuesta) {
		this.respuestaProperty().set(respuesta);
	}

	public final StringProperty aciertosPalabrasProperty() {
		return this.aciertosPalabras;
	}

	public final String getAciertosPalabras() {
		return this.aciertosPalabrasProperty().get();
	}

	public final void setAciertosPalabras(final String aciertosPalabras) {
		this.aciertosPalabrasProperty().set(aciertosPalabras);
	}

	public final StringProperty aciertosLetrasProperty() {
		return this.aciertosLetras;
	}

	public final String getAciertosLetras() {
		return this.aciertosLetrasProperty().get();
	}

	public final void setAciertosLetras(final String aciertosLetras) {
		this.aciertosLetrasProperty().set(aciertosLetras);
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
	

}
