package it.polito.tdp.lab04.model;

public class Corso implements Comparable<Corso>{
	private String codins;
	private Integer crediti;
	private String nome;
	private Integer pd;
	
	
	public Corso(String codins, Integer crediti, String nome, Integer pd) {
		super();
		this.codins = codins;
		this.crediti = crediti;
		this.nome = nome;
		this.pd = pd;
	}


	public String getCodins() {
		return codins;
	}


	public void setCodins(String codins) {
		this.codins = codins;
	}


	public Integer getCrediti() {
		return crediti;
	}


	public void setCrediti(Integer crediti) {
		this.crediti = crediti;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public Integer getPd() {
		return pd;
	}


	public void setPd(Integer pd) {
		this.pd = pd;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Corso other = (Corso) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return nome ;
	}


	@Override
	public int compareTo(Corso o) {
		return this.nome.compareTo(o.nome);
	}
	
	
	
	
}
