package it.polito.tdp.lab04.model;

import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	
	CorsoDAO cdao = new CorsoDAO();
	StudenteDAO sdao = new StudenteDAO();

	public List<Corso> getTuttiICorsi() {
		return cdao.getTuttiICorsi();
	}

	public List<Studente> getStudentiIscrittiAlCorso(Corso corso) {
		return cdao.getStudentiIscrittiAlCorso(corso);
	}
	
	public List<Corso> getCorsibyStudente(Studente s) {
		return sdao.getCorsibyStudente(s);
	}
	
	public Studente getStudentebyMatricola (String matricola) {
		return sdao.getStudentebyMatricola(matricola);
	}
	
	public boolean isStudenteIscrittoAlCorso (Corso c, Studente s) {
		return sdao.isStudenteIscrittoAlCorso(c, s);
	}
	
	public boolean iscriviStudenteACorso(Studente studente, Corso corso) {
		return cdao.iscriviStudenteACorso(studente, corso);
	}
}
