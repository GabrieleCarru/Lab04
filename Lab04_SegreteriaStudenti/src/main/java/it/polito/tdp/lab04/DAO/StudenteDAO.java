package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

//import javax.naming.spi.DirStateFactory.Result;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {

	public List<Corso> getCorsibyStudente(Studente s) {
		
		final String sql = "select c.* " + 
				"from iscrizione as i, corso as c " + 
				"where i.codins = c.codins and i.matricola = ?";
		
		List<Corso> corsiIscrittoStudente = new LinkedList<>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setString(1, s.getMatricola().toString());
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				String codins = rs.getString("codins");
				Integer crediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				Integer pd = rs.getInt("pd");
				
				Corso c = new Corso(codins, crediti, nome, pd);
				corsiIscrittoStudente.add(c);
			}
			
			conn.close();
			return corsiIscrittoStudente;
			
		} catch (SQLException e) {
			throw new RuntimeException("Errore Db", e);
		}
		
	}
	
	public Studente getStudentebyMatricola (String matricola) {
		
		final String sql = "select * from studente where matricola = ?";
		
		Integer matricolaID = Integer.parseInt(matricola);
		Studente s = new Studente(matricolaID);
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setInt(1, matricolaID);
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				s.setNome(rs.getString("nome"));
				s.setCognome(rs.getString("cognome"));
				s.setCDS(rs.getString("CDS"));
			}
			
			conn.close();
			
		} catch (SQLException e) {
			throw new RuntimeException("Errore Db", e);
		}
		
		return s;
		
	}
	
	public boolean isStudenteIscrittoAlCorso (Corso c, Studente s) {
		
		final String sql = "select i.matricola " + 
				"from corso as c, iscrizione as i " + 
				"where c.codins = i.codins and c.codins = ? and i.matricola = ?";
		// c.codins = "01KSUPG" and i.matricola = "161245"
		
		// strutture dati che mi serviranno
		Integer matricolaStudenteIscrittoCorso = 0;
		
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setString(1, c.getCodins());
			st.setInt(2, s.getMatricola());
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				matricolaStudenteIscrittoCorso = rs.getInt("matricola");
			}
			
			conn.close();
			
			if(matricolaStudenteIscrittoCorso != 0) {
				return true;
			} else {
				return false;
			}
			
		} catch (SQLException e) {
			throw new RuntimeException("Errore Db", e);
		}
		
	}
	
	
	
}

