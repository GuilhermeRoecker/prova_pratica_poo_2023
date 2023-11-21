package Prova_POO;

import java.util.ArrayList;


import javax.swing.JOptionPane;

public class Acidente {

	Rodovia rodovia;
	Veiculos veiculo;
	private int fatal;
	private int feridos;
	private String mes;
	private int qt;
	private int rodoviaAcidente;
	private String nomeRodovia = "";
	private String ehDeCarga;
	private static int alto = 0;
	private static int medio = 0;
	private static int baixo = 0;
	private String contaAcidente = "";
	private String ehBike = "";
	private int totalPessoasEnvolvidas = 0;

	ArrayList<Veiculos> veiculos = new ArrayList<>();
	ArrayList<VeiculosDeCarga> veiculosDeCargas = new ArrayList<>();
	ArrayList<Bicicleta> bicicleta = new ArrayList<>();

	//Metodo de cadastro de acidentes
	public void CadastrarAcidente(ArrayList<Rodovia> rodovias) {

		// Percorre o array de rodovias para exibir as ja cadastradas
		// para que o usuario escolha em qual ocorreu o acidente
		for (Rodovia r : rodovias) {
			nomeRodovia += r.getCod() + " - " + r.getSigla().toUpperCase() + " - Risco: " + r.getGrau() + "\n";
		}

		// Pede para o usuario selecionar em qual rodovia da Lista exibida
		// em que ocorreu o acidente e valida se ela existe
		do {
			try {
				rodoviaAcidente = Integer
						.parseInt(JOptionPane.showInputDialog(
								"Qual rodovia ocorreu o acidente? \nSelecione pelo codigo  \n " + nomeRodovia));
				if (!codigoRodoviaValido(rodovias, rodoviaAcidente)) {
					throw new NumberFormatException();
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Código de rodovia inválido. Por favor, insira um código válido.");
			}
		} while (!codigoRodoviaValido(rodovias, rodoviaAcidente));

		// Pergunta quantos veiculos foram envolvidos para se utilizar no cadastra
		// veiculo
		// e se são de carga, se sim qual a quantidade para subtrair de veiculo
		do {
			try {
				qt = Integer.parseInt(JOptionPane.showInputDialog(null, "Quantidade de Veículos envolvidos?"));
				if (qt < 1) {
					JOptionPane.showMessageDialog(null, "A quantidade de veículos deve ser no mínimo 1.");
					qt = -1;
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Por favor, insira um número válido.");
				qt = -1;
			}
		} while (qt == -1);

		for (int q = 1; q <= qt; q++) {

			//Pergunta se o veiculo é de carga
			//Para chamar o metodo de cadastro
			ehDeCarga = JOptionPane.showInputDialog(null, "O veiculo é de carga? S/N");
			if (ehDeCarga.equalsIgnoreCase("s")) {
				VeiculosDeCarga Vc = new VeiculosDeCarga();
				Vc.cadastraVeiculosDeCarga();
				veiculosDeCargas.add(Vc);
			}

			//Se o veiculo não for de carga ira perguntar se é Bicicleta
			//Para chamar o metodo de cadastro
			if (ehDeCarga.equalsIgnoreCase("n")) {
				ehBike = JOptionPane.showInputDialog(null, "O veiculo é uma bicicleta? S/N");

				if (ehBike.equalsIgnoreCase("S")) {
					Bicicleta b1 = new Bicicleta();
					b1.cadastraBicicleta();
					bicicleta.add(b1);
				}
				//Se o veicilo não for de carga ou bicicleta
			//Ira chamar o metodo de cadastro de veiculo
				if (ehBike.equalsIgnoreCase("N")) {
					Veiculos v = new Veiculos();
					v.cadastraVeiculos();
					veiculos.add(v);
				}
			}
		}

		//Loops para validar a quantidade de pessoas nos veiculos
		for (Veiculos veiculo : veiculos) {
			totalPessoasEnvolvidas += veiculo.getQtdPessoas();
		}

		for (VeiculosDeCarga veiculoCarga : veiculosDeCargas) {
			totalPessoasEnvolvidas += veiculoCarga.getQtdPessoas();
		}

		for (Bicicleta bike : bicicleta) {
			totalPessoasEnvolvidas += bike.getQtdPessoas();
		}

		//Com base no numero de pessoas, chama metodo de validação
		//Para verificar se o numero total
		//Não é maior que o de envolvidos
		do {
			try {
				setFatal(Integer.parseInt(JOptionPane.showInputDialog(null, "Quantidade de vítimas fatais?")));

				if (getFatal() > totalPessoasEnvolvidas) {
					JOptionPane.showMessageDialog(null,
							"Quantidade de vítimas fatais não pode ser maior que a quantidade total de pessoas nos veículos.");
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Por favor, insira um número válido.");
			}
		} while (getFatal() > totalPessoasEnvolvidas);

		do {
			try {
				setFeridos(Integer.parseInt(JOptionPane.showInputDialog(null, "Quantidade de vítimas feridas?")));

				if (getFeridos() + getFatal() > totalPessoasEnvolvidas) {
					JOptionPane.showMessageDialog(null,
							"A soma de vítimas fatais e feridas não pode ser maior que a quantidade total de pessoas nos veículos.");
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Por favor, insira um número válido.");
			}
		} while (getFeridos() + getFatal() > totalPessoasEnvolvidas);


		//Lista de meses
		String[] mesesValidos = { "janeiro", "fevereiro", "março", "abril", "maio", "junho", "julho", "agosto",
				"setembro", "outubro", "novembro", "dezembro" };

		//Validado se o mes escrito é valido		
		do {
			setMes(JOptionPane.showInputDialog(null, "Qual é o mês?"));
			if (!mesValido(getMes(), mesesValidos)) {
				JOptionPane.showMessageDialog(null, "Mês inválido. Por favor, insira um mês válido.");
			}
		} while (!mesValido(getMes(), mesesValidos));
		for (Rodovia r : rodovias) {
			if (r.getCod() == rodoviaAcidente) {
				this.rodovia = r;

				// Adiciona +1 para cada acidente registrado naquea via
				String grauLowerCase = rodovia.getGrau().toLowerCase();
				if ("alto".equals(grauLowerCase)) {
					alto++;
				} else if ("medio".equals(grauLowerCase)) {
					medio++;
				} else if ("baixo".equals(grauLowerCase)) {
					baixo++;
				}

				break;

			}
		}
	}

	// Construtor de rodovovia
	public Acidente(int fatal, int feridos, String mes, int qt) {
		this.fatal = fatal;
		this.feridos = feridos;
		this.mes = mes;
		this.qt = qt;
		this.rodovia = new Rodovia();
	}

	public Acidente() {
	}

	// Verifica se o codigo da rodovia existem dentro do array
	private boolean codigoRodoviaValido(ArrayList<Rodovia> rodovias, int codigo) {
		for (Rodovia r : rodovias) {
			if (r.getCod() == codigo) {
				return true; 
			}
		}
		return false;
	}

	//Metodos para verificar se condutores
	//Estavam embreagados ou não
	public String embragado() {
		for (Veiculos e : veiculos) {
			return e.estaEmbrigado();
		}
		return "n";
	}

	public String cargaEmbreagado() {
		for (VeiculosDeCarga e1 : veiculosDeCargas) {
			return e1.estaEmbrigado();
		}
		return "n";
	}

	public String bikeEmbreagado() {
		for (Bicicleta e2 : bicicleta) {
			return e2.estaEmbrigado();
		}
		return "n";
	}

	//Puxa a lista dos veiculos de carga
	public String VeiculosDeCargaLista() {
		StringBuilder resultadoEhDeCarga = new StringBuilder();

		for (VeiculosDeCarga VC : veiculosDeCargas) {
			resultadoEhDeCarga.append(VC.toString()).append("\n");
		}

		return resultadoEhDeCarga.toString().trim();
	}

	//Faz a contagem dos acidentes por grau
	//De periculosidade da via
	public static String obterContagemPorGrau() {
		return "Acidentes com grau de periculosidade alto: " + alto + "\n" +
				"Acidentes com grau de periculosidade medio: " + medio + "\n" +
				"Acidentes com grau de periculosidade baixo: " + baixo;
	}

	//Metodo para obter a rodovia
	//Com mais acidnetes com bicicletas envolvidas
	public static Rodovia obterRodoviaComMaisBicicletas(ArrayList<Acidente> acidentes, ArrayList<Rodovia> rodovias) {
		int maxBicicletas = 0;
		Rodovia rodoviaMaisBicicletas = null;

		for (Rodovia rodovia : rodovias) {
			int bicicletasEnvolvidas = contarBicicletasEnvolvidas(rodovia.getCod(), acidentes);
			if (bicicletasEnvolvidas > maxBicicletas) {
				maxBicicletas = bicicletasEnvolvidas;
				rodoviaMaisBicicletas = rodovia;
			}
		}
		if (rodoviaMaisBicicletas == null) {
			JOptionPane.showMessageDialog(null, "Nenhuma rodovia encontrada com acidente de bicicletas");
		}
		return rodoviaMaisBicicletas;
	}
	
	//Conta quantos acidentes teve em cada rodovia
	private static int contarBicicletasEnvolvidas(int codRodovia, ArrayList<Acidente> acidentes) {
		int bicicletas = 0;

		for (Acidente acidente : acidentes) {
			if (acidente.getRodovia().getCod() == codRodovia) {
				for (Bicicleta bicicleta : acidente.bicicleta) {
					bicicletas += bicicleta.getQtdPessoas();
				}
			}
		}
		return bicicletas;
	}

	//Metodo para pegar a rodovia
	//Com mais acidentes fatais
	public static Rodovia obterRodoviaComMaisAcidentesFatais(ArrayList<Acidente> acidentes,
			ArrayList<Rodovia> rodovias) {
		int maxAcidentesFatais = 0;
		Rodovia rodoviaMaisAcidentesFatais = null;

		for (Rodovia rodovia : rodovias) {
			int acidentesFatais = contarAcidentesFatais(rodovia.getCod(), acidentes);
			if (acidentesFatais > maxAcidentesFatais) {
				maxAcidentesFatais = acidentesFatais;
				rodoviaMaisAcidentesFatais = rodovia;
			}
		}
		return rodoviaMaisAcidentesFatais;
	}

	//Contagem de aciddente ppor rodovia
	private static int contarAcidentesFatais(int codRodovia, ArrayList<Acidente> acidentes) {
		int acidenteFatal = 0;
		for (Acidente acidente : acidentes) {
			if (acidente.getRodovia().getCod() == codRodovia && acidente.getFatal() > 0) {
				acidenteFatal++;
			}
		}
		return acidenteFatal;
	}

	//Pega todos os carros das 3 listas >= 2013
	//E conta, retornando o resultado
	public int qtdCarrosNovos(ArrayList<Acidente> acidentes) {
		int acidentesComCarrosNovos = 0;

		for (Acidente acidente : acidentes) {
			boolean acidenteContado = false;

			for (Veiculos v : acidente.veiculos) {
				if (v.getAno() >= 2013) {
					if (!acidenteContado) {
						acidentesComCarrosNovos++;
						acidenteContado = true;
					}
					break;
				}
			}
			if (!acidenteContado) {
				for (VeiculosDeCarga v1 : acidente.veiculosDeCargas) {
					if (v1.getAno() >= 2013) {
						acidentesComCarrosNovos++;
						acidenteContado = true;
						break;
					}
				}
			}
			if (!acidenteContado) {
				for (Bicicleta v3 : acidente.bicicleta) {
					if (v3.getAno() >= 2013) {
						acidentesComCarrosNovos++;
						break;
					}
				}
			}
		}
		return acidentesComCarrosNovos;
	}

	//Pega todos os acidentes com o mes de fevereiro
	public static String obterRodoviasComAcidentesNoCarnaval(ArrayList<Acidente> acidentes, ArrayList<Rodovia> rodovias) {
		StringBuilder resultado = new StringBuilder();
		boolean encontrouAcidenteNoCarnaval = false;
	
		for (Rodovia rodovia : rodovias) {
			for (Acidente acidente : acidentes) {
				if (acidente.getRodovia().getCod() == rodovia.getCod() && acidente.getMes().equalsIgnoreCase("fevereiro")) {
					if (resultado.indexOf(String.valueOf(rodovia.getCod())) == -1) {
						resultado.append(rodovia.getCod()).append(" - ").append(rodovia.getSigla()).append("\n");
					}
					encontrouAcidenteNoCarnaval = true;
				}
			}
		}
	
		if (!encontrouAcidenteNoCarnaval) {
			return "Nenhuma rodovia registrou acidentes no carnaval.";
		} else {
			return resultado.toString();
		}
	}
	

	//Verifica se o mes é valido
	private boolean mesValido(String mes, String[] mesesValidos) {
		for (String mesValido : mesesValidos) {
			if (mes.equalsIgnoreCase(mesValido)) {
				return true;
			}
		}
		return false;
	}

	// Gets and Setters
	public int getFatal() {
		return fatal;
	}

	public void setFatal(int fatal) {
		this.fatal = fatal;
	}

	public int getFeridos() {
		return feridos;
	}

	public void setFeridos(int feridos) {
		this.feridos = feridos;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public int getQt() {
		return qt;
	}

	public void setQt(int qt) {
		this.qt = qt;
	}

	public int getAlto() {
		return alto;
	}

	public void setAlto(int alto) {
		this.alto = alto;
	}

	public int getMedio() {
		return medio;
	}

	public void setMedio(int medio) {
		this.medio = medio;
	}

	public int getBaixo() {
		return baixo;
	}

	public void setBaixo(int baixo) {
		this.baixo = baixo;
	}

	public String getContaAcidente() {
		return contaAcidente;
	}

	public Rodovia getRodovia() {
		return rodovia;
	}

	public void setRodovia(Rodovia rodovia) {
		this.rodovia = rodovia;
	}

	public void setContaAcidente(String contaAcidente) {
		this.contaAcidente = contaAcidente;
	}

	public String getSigla() {
		return null;
	}

	public String getEhBike() {
		return ehBike;
	}

	public void setEhBike(String ehBike) {
		this.ehBike = ehBike;
	}

	

}