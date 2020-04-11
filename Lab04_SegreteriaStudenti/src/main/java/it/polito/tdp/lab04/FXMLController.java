/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.lab04;

import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	private Model model;	
	private List<Corso> corsi;
		
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="comboCorso"
    private ComboBox<Corso> comboCorso; // Value injected by FXMLLoader

    @FXML // fx:id="btnCercaIscrittiCorso"
    private Button btnCercaIscrittiCorso; // Value injected by FXMLLoader

    @FXML // fx:id="txtMatricola"
    private TextField txtMatricola; // Value injected by FXMLLoader

    @FXML // fx:id="btnCercaNome"
    private Button btnCercaNome; // Value injected by FXMLLoader

    @FXML // fx:id="txtNome"
    private TextField txtNome; // Value injected by FXMLLoader

    @FXML // fx:id="txtCognome"
    private TextField txtCognome; // Value injected by FXMLLoader

    @FXML // fx:id="btnCerca"
    private Button btnCerca; // Value injected by FXMLLoader

    @FXML // fx:id="btnIscrivi"
    private Button btnIscrivi; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML // fx:id="btnReset"
    private Button btnReset; // Value injected by FXMLLoader

    @FXML
    void doCercaCorsi(ActionEvent event) {
    	txtResult.clear();
    	
    	String matricola = txtMatricola.getText();
    	
    	try {
    		
    		Studente s = this.model.getStudentebyMatricola(matricola);
    		
    		if(s.getNome() == null) {
    			txtResult.setText("Matricola studente non trovata.");
    			return;
    		}
    		
    		List<Corso> corsiIscrittoStudente = this.model.getCorsibyStudente(s);
    		
    		txtNome.setText(s.getNome());
    		txtCognome.setText(s.getCognome());
    		
    		String stampaCorso;
    		
    		for(Corso c : corsiIscrittoStudente ) {
    			stampaCorso = c.getCodins() + " " + c.getCrediti() + " " + c.getNome() + " " + c.getPd() + "\n";
    			txtResult.appendText(stampaCorso);
    		}
    		
    	} catch(NumberFormatException e) {
    		txtResult.setText("Inserire una matricola valida!");
    		return;
    	}
    }

    @FXML
    void doCercaIscrittiCorso(ActionEvent event) {
    	txtResult.clear();
    	
    	boolean isIscritto = false;
    	
    	Corso c = comboCorso.getValue();
    	if(c == null) {
    		txtResult.setText("Scegliere un corso!");
    		return;
    	}
    	
    	if(txtMatricola.getText().isEmpty()) {
    		List<Studente> studentiIscritti = this.model.getStudentiIscrittiAlCorso(c);
        	String stampaStudente;
        	
        	// controllare soluzione per una formattazione più gradevole 
        	for(Studente s : studentiIscritti) {
        		stampaStudente = s.getMatricola() + " " + s.getNome() + " " + s.getCognome() + " " + s.getCDS() + "\n";
        		txtResult.appendText(stampaStudente);
        	}
    	} else {
    		
    		String matricola = txtMatricola.getText();
    		Studente s = this.model.getStudentebyMatricola(matricola);
    		
    		// controllo che la matricola corrisponda effettivamente ad un studente
    		if(s.getNome() == null) {
    			txtResult.setText("La matricola inserita non è valida o non esiste.");
    			return;
    		}
    		
    		isIscritto = this.model.isStudenteIscrittoAlCorso(c, s);
        	
        	if(isIscritto) {
        		txtResult.setText("Lo studente '" + s.getMatricola() + "' è iscritto al corso " 
        				+ c.getCodins() + " - " + c.getNome() + ".");
        	} else {
        		txtResult.setText("Lo studente '" + s.getMatricola() + "' non risulta essere"
        				+ " iscritto al corso " + c.getCodins() + " - " + c.getNome() + ".");
        	}
    	}
    	
    }

    @FXML
    void doCercaNome(ActionEvent event) {

    	txtResult.clear();
    	
    	String matricola = txtMatricola.getText();
    	Studente s = this.model.getStudentebyMatricola(matricola);
    	
    	if(s.getNome() != null) {
    		txtNome.setText(s.getNome());
    		txtCognome.setText(s.getCognome());
    	}
    	
    }

    @FXML
    void doIscrivi(ActionEvent event) {
    	
    	txtResult.clear();
    	
    	try {
    		
    		if (txtMatricola.getText().isEmpty()) {
    			txtResult.setText("Inserire una matricola.");
    			return;
    		}
    		
    		if (comboCorso.getValue() == null) {
    			txtResult.setText("Selezionare un corso.");
    			return;
    		}
    		
    		String matricola = txtMatricola.getText();
    		Studente s = this.model.getStudentebyMatricola(matricola);
    		
    		if(s.getNome() == null) {
    			txtResult.appendText("Nessun risultato: matricola inesistente");
    			return;
    		}
    		
    		txtNome.setText(s.getNome());
    		txtCognome.setText(s.getCognome());
    		
    		Corso c = comboCorso.getValue();
    		
    		if(model.isStudenteIscrittoAlCorso(c, s)) {
    			txtResult.setText("Lo studente è già iscritto a questo corso.");
    			return;
    		}
    		
    		// Iscrivo lo studente al corso!
    		if(!model.iscriviStudenteACorso(s, c)) {
    			txtResult.setText("Errore durante l'iscrizione al corso.");
    			return;
    		} else {
    			txtResult.setText("Lo studente è stato iscritto correttamente al corso.");
    		}
    		
    		
    	} catch (NumberFormatException e) {
			txtResult.setText("Inserire una matricola nel formato corretto.");
		} catch (RuntimeException e) {
			txtResult.setText("ERRORE DI CONNESSIONE AL DATABASE!");
		}
    		
    }

    @FXML
    void doReset(ActionEvent event) {
    	txtMatricola.clear();
    	txtNome.clear();
    	txtCognome.clear();
    	txtResult.clear();
    	comboCorso.getSelectionModel().clearSelection();
    }

    private void setComboItems() {
    	//ottieni tutti i corsi del model
    	corsi = model.getTuttiICorsi();
    	
    	//Aggiungi tutti i corsi alla ComboBox. 
    	Collections.sort(corsi); // per avere maggiore ordine li sorto alfabeticamente
    	comboCorso.getItems().addAll(corsi); // richiama il toString dell'oggetto Corso
    }
    
    
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert comboCorso != null : "fx:id=\"comboCorso\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercaIscrittiCorso != null : "fx:id=\"btnCercaIscrittiCorso\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercaNome != null : "fx:id=\"btnCercaNome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCerca != null : "fx:id=\"btnCerca\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Scene.fxml'.";
        
       //ho controllato il tipo di font direttamente nell'fxml scegliendo "Courier New". Un'alternativa poteva essere usare questo setStyle
       //per ottenere un font che permetta di incolonnare correttamente i dati
       //txtResult.setStyle("-fx-font-family: monospace");
    }
    
    public void setModel(Model model) {
    	this.model = model;
    	setComboItems();
    }
    
}