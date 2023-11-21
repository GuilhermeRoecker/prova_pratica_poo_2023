package Prova_POO;

import javax.swing.JOptionPane;

public class Pessoas {
	private String nome;
	private int idade;
	private String sexo;

	// Metodo de cadastro de pessoas
	// Chamado dento do cadastro de Veiculos
	//Com validações
	public void cadastraPessoas() {
		boolean inputValido = false;

		do {
			try {
				String cadastraNome = JOptionPane.showInputDialog(null, "Qual o nome?");
				validarNome(cadastraNome);
				setNome(cadastraNome);

				String cadastraIdade = JOptionPane.showInputDialog(null, "Qual a idade?");
				setIdade(validarIdade(cadastraIdade));

				String cadastraSexo = JOptionPane.showInputDialog(null, "Qual o sexo? M/F");
				setSexo(validarSexo(cadastraSexo));

				inputValido = true;
			} catch (IllegalArgumentException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		} while (!inputValido);
	}

	// Construtores de Pessoas
	public Pessoas() {
	}

	public Pessoas(String nome, int idade, String sexo) {
		super();
		this.nome = nome;
		this.idade = idade;
		this.sexo = sexo;
	}

	// Gets and Setters
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	// Converte construtor em uma String
	@Override
	public String toString() {
		return "nome: " + nome + ", idade:" + idade + ", sexo:" + sexo;
	}
	//Valida se Idade é numero
	private int validarIdade(String idadeInput) {
		try {
			int idade = Integer.parseInt(idadeInput);

			if (idade >= 0) {
				return idade;
			} else {
				throw new IllegalArgumentException("Idade inválida. Por favor, insira uma Idade.");
			}
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Idade inválida. Por favor, insira uma Idade.");
		}
	}
	//Valida se não esta em branco
	private void validarNome(String nome) {
		if (nome == null || nome.trim().isEmpty()) {
			throw new IllegalArgumentException("O nome não pode estar vazio.");
		}
	}
	//Valida se esta M ou F
	private String validarSexo(String sexo) {
		String sexoFormatado = sexo.trim();

		if (sexoFormatado.equalsIgnoreCase("M") || sexoFormatado.equalsIgnoreCase("F")) {
			return sexoFormatado;
		} else {
			throw new IllegalArgumentException("Sexo inválido.");
		}
	}
}