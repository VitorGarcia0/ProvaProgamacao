package model.vo;

public class Carro {

	private int id;
	private Montadora montadora;
	private String modelo;
	private int ano;
	private double valor;
	private String placa;
	private Proprietario proprietario;

	public Carro(int id, Montadora montadora, String modelo, int ano, double valor, String placa,
			Proprietario proprietario) {
		super();
		this.id = id;
		this.montadora = montadora;
		this.modelo = modelo;
		this.ano = ano;
		this.valor = valor;
		this.placa = placa;
		this.proprietario = proprietario;

	}

	public Carro() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Montadora getMontadora() {
		return montadora;
	}

	public void setMontadora(Montadora montadora) {
		this.montadora = montadora;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public Proprietario getProprietario() {
		return proprietario;
	}

	public void setProprietario(Proprietario proprietario) {
		this.proprietario = proprietario;
	}

	@Override
	public String toString() {
		return "Carro id: " + id + " montadora: " + montadora + " modelo: " + modelo + " ano: " + ano + " valor: "
				+ valor + " placa: " + placa + " proprietario: " + proprietario + "\n";
	}
	
	

}