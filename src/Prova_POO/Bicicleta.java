package Prova_POO;

public class Bicicleta extends Veiculos {

	public Bicicleta() {
	}

	public void cadastraBicicleta() {
		super.cadastraVeiculos();

		// Metodo de cadastro de condutor
		Condutor cond = new Condutor();
		cond.cadastraCondutor();
		condutores.add(cond);

		// Metodo para cadastro de pessoas
		// -1 que é condutor
		for (int p = 1; p <= (getQtdPessoas() - 1); p++) {
			Pessoas a = new Pessoas();
			a.cadastraPessoas();
		}
	}

	//Metodo para verificar se o condutor
	//Esta embreagado ou não
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
