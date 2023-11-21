package Prova_POO;

import javax.swing.JOptionPane;

public class Rodovia {
	private String sigla;
	String grau;
	private static int num = 1;
	private int cod = 1;

	// Cadastra uma rodovia
	public void CadastraRodovia() {

		do {
			setSigla(validaSigla(JOptionPane.showInputDialog(null, "Qual a sigla da rodovia?")));
		} while (getSigla() == null);

		do {
			setGrau(validaEFormataGrau(JOptionPane.showInputDialog(null,
					"Qual o grau de periculosidade da rodovia? \n" + "Alto \n" + "Médio \n" + "Baixo")));
		} while (getGrau() == null);

		// Adicona um cod para cada rodovia cadastrada em ordem crescente sequencial
		this.cod = num++;
	}

	// Gets and setters
	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getGrau() {
		return grau;
	}

	public void setGrau(String grau) {
		this.grau = grau;
	}

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	// Converte construtor para uma string
	@Override
	public String toString() {
		return getCod() + "-" + sigla.toUpperCase() + "\nGrau de periculosidade: " + grau + "\n";
	}

	public String getTotalBike() {
		return null;
	}

	// Valida e formata o grau da rodovia
	private String validaEFormataGrau(String grau) {

		grau = grau.toLowerCase();
		if (grau.equals("alto") || grau.equals("médio") || grau.equals("baixo")) {
			return grau.substring(0, 1).toUpperCase() + grau.substring(1);
		} else {
			JOptionPane.showMessageDialog(null, "Grau inválido. Insira 'Alto', 'Médio' ou 'Baixo'.");
			return null;
		}
	}

	// Valida se sigla não esta vazia
	private String validaSigla(String sigla) {
		if (sigla.trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Sigla não pode estar vazia");
			return null;
		}
		return sigla;
	}

}
