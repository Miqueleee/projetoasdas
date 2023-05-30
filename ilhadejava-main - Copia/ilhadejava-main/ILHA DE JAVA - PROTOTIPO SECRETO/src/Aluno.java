public class Aluno implements ElementoDeJogo {
	
	@Override
	public String Simbolo() {
		return "☺";
	}

	@Override
	public int getValor() {
		return 10;
	}

	@Override
	public int getTipo() {
		return 1; // representa o Aluno
	}
	
	
}
