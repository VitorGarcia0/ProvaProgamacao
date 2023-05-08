package model.vo;

import java.util.List;

public class Proprietario {

	private int id;
	private String nomeCompleto;
	private String CNH;
	private String CPF;
	private List<Carro> carros;

	public Proprietario(int id, String nomeCompleto, String cNH, String cPF, List<Carro> carros) {
		super();
		this.id = id;
		this.nomeCompleto = nomeCompleto;
		CNH = cNH;
		CPF = cPF;
		this.carros = carros;
	}

	public Proprietario() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getCNH() {
		return CNH;
	}

	public void setCNH(String cNH) {
		CNH = cNH;
	}

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String cPF) {
		CPF = cPF;
	}

	public List<Carro> getCarros() {
		return carros;
	}

	public void setCarros(List<Carro> carros) {
		this.carros = carros;
	}

}
