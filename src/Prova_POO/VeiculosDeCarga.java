package Prova_POO;

import javax.swing.JOptionPane;

public class VeiculosDeCarga extends Veiculos {
	private Double carga;

	// Metodo de cadastro de veiculos de Carga
	public void cadastraVeiculosDeCarga() {
		super.cadastraVeiculos();
		setCarga(Double.parseDouble(JOptionPane.showInputDialog(null, "Qual qual a quantidade em KG da carga?")));
	}

	// Construtor de Veiculos De Cagra
	public VeiculosDeCarga() {
	}

	public VeiculosDeCarga(int ano, int qt, String bike, String nome, int idade, String sexo, char embriagado,
			Double carga) {
		super();
		this.carga = carga;
	}

	// Converte contrutor em string
	@Override
	public String toString() {
		return super.toString() + "Com a carga de: " + carga;
	}

	// Gets and Setters
	public Double getCarga() {
		return carga;
	}

	public void setCarga(Double carga) {
		this.carga = carga;
	}

	@Override
	public String estaEmbrigado() {
		for (Condutor e : condutores) {
			if (e.getEmbriagado().equalsIgnoreCase("s")) {
				return "s";
			}
		}
		return "n";
	}
}
