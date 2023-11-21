package Prova_POO;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Veiculos {
	private int ano;
	private Pessoas pessoa;
	Condutor Motorista;
	private int qtdPessoas;
	Acidente acidente;

	ArrayList<Pessoas> pessoas = new ArrayList<Pessoas>();
	ArrayList<Condutor> condutores = new ArrayList<>();

	// Metodo para cadastro de Veiculos
	// Chamando dentro de acidentes
	public void cadastraVeiculos() {

		// Pergunta ano do carro
		int ano;
		do {
			try {
				ano = Integer.parseInt(JOptionPane.showInputDialog(null, "Qual o ano do carro?"));
				// Verifica se o ano tem pelo menos 4 dígitos
				if (String.valueOf(ano).length() < 4 || String.valueOf(ano).length() > 5) {
					JOptionPane.showMessageDialog(null, "Por favor, insira um ano com 4 dígitos.");
					ano = -1;
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Por favor, insira um ano valido.");
				ano = -1;
			}
		} while (ano == -1);
		setAno(ano);

		// Pergunta quantidade de pessoas dentro do carro
		int qtdPessoas;
		do {
			try {
				qtdPessoas = Integer.parseInt(JOptionPane.showInputDialog(null,
						"Quantas pessoas estão no veículo?"));
				if (qtdPessoas < 1) {
					JOptionPane.showMessageDialog(null, "A quantidade de pessoas precisa ser no minimo 1 pessoa");
				}
				if (qtdPessoas > 5) {
					JOptionPane.showMessageDialog(null, "A quantidade de pessoas pode ser de no maximo 5");
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Por favor, insira um número valido.");
				qtdPessoas = -1;
			}
		} while (qtdPessoas == -1);
		setQtdPessoas(qtdPessoas);

		// Cadastra o condutor do veículo
		Condutor cond = new Condutor();
		cond.cadastraCondutor();
		condutores.add(cond);

		// Chama o método de cadastro de pessoas X vezes
		// -1 que é o condutor do veículo
		for (int p = 1; p <= (getQtdPessoas() - 1); p++) {
			Pessoas a = new Pessoas();
			a.cadastraPessoas();
		}
	}

	private String getEhBike() {
		return null;
	}

	// Construtor de Veiculos
	public Veiculos() {
	}

	// Converte construtor em String
	@Override
	public String toString() {
		return "O veiculo do ano: " + ano + "\n";
	}

	// Gets ands Setters
	public int getQtdPessoas() {
		return qtdPessoas;
	}

	public void setQtdPessoas(int qtdPessoas) {
		this.qtdPessoas = qtdPessoas;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public Pessoas getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoas pessoa) {
		this.pessoa = pessoa;
	}

	// Metodo de validação
	// se o codutor esta embriagado
	public boolean condutorEmb() {
		if (Motorista.getEmbriagado().equalsIgnoreCase("S")) {
			return true;
		}
		return false;
	}

	public String estaEmbrigado() {
		for (Condutor e : condutores) {
			if (e.getEmbriagado().equalsIgnoreCase("s")) {
				return "s";
			}
		}
		return "n";
	}

}
