package Prova_POO;

import javax.swing.JOptionPane;

public class Condutor extends Pessoas {
	private String Embriagado;

	// Metodo para cadastro de condutor
	// Chama metodo Pai de cadastro de pessoas
	// e adiciona a pergunta se esta embriagado S/N
	public void cadastraCondutor() {
		boolean inputValido = false;

		do {
			try {
				super.cadastraPessoas();

				String embragadoEntrada = JOptionPane.showInputDialog(null, "O condutor está embriagado? S/N");
				setEmbriagado(validaEmbragado(embragadoEntrada));

				inputValido = true;
			} catch (IllegalArgumentException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		} while (!inputValido);
	}

	// Construtor de Condutor
	public Condutor() {
	}

	public Condutor(String nome, int idade, String sexo, String embriagado) {
		super(nome, idade, sexo);
		Embriagado = embriagado;
	}

	// Converte construtor em String
	@Override
	public String toString() {
		return super.toString() + "\nMotorista esta Embriagado? " + Embriagado;
	}


	//Valida se o condutor esta embreagado ou não
	private String validaEmbragado(String embragadoEntrada) {
		String embriagadoFormatado = embragadoEntrada.trim().toUpperCase();

		if (embriagadoFormatado.equals("S") || embriagadoFormatado.equals("N")) {
			return embriagadoFormatado;
		} else {
			throw new IllegalArgumentException(
					"Resposta invalida. S/N");
		}
	}

	// Gets and Setters
	public String getEmbriagado() {
		return Embriagado;
	}

	public void setEmbriagado(String embriagado) {
		Embriagado = embriagado;
	}

}
