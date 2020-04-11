package it.polito.tdp.lab04.model;

public class TestModel {

	public static void main(String[] args) {

		Model model = new Model();
		
		System.out.println(model.getTuttiICorsi());
		
		System.out.println("Get Studenti Iscritti al Corso: \n");
		System.out.println(model.getStudentiIscrittiAlCorso(new Corso("01KSUPG", null, null, null)));
		
		System.out.println("Get Corsi a cui è iscritto lo Studente: \n");
		System.out.println(model.getCorsibyStudente(new Studente(161245, null, null, null)));
		
		

	}

}
