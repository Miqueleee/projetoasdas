import java.util.Scanner;

public class Jogo {
	public String nome;
	public int tamanhoX;
	public int tamanhoY;
	public int qtdAlunos;
	public int qtdBugs;
	public Plano p;
	public ServicosJogo servicos;
	Scanner scan = new Scanner(System.in);
	
	public R_Andador andador = new R_Andador();
	public R_Peao peao = new R_Peao();
	public R_Torre torre = new R_Torre();
	public R_Bispo bispo = new R_Bispo();
	public R_Cavalo cavalo = new R_Cavalo();
	public R_Rei rei = new R_Rei();
	public R_Rainha rainha = new R_Rainha();

//	public Jogo(String nome, int tamanhoX, int tamanhoY, int qtdAlunos, int qtdBugs) {
//		this.nome = nome;
//		this.tamanhoX = tamanhoX;
//		this.tamanhoY = tamanhoY;
//		this.qtdAlunos = qtdAlunos;
//		this.qtdBugs = qtdBugs;
//	}
	
	public Jogo(String nome, int tamanhoX, int tamanhoY) {
		this.nome = nome;
		this.tamanhoX = tamanhoX;
		this.tamanhoY = tamanhoY;
//		this.qtdAlunos = qtdAlunos;
//		this.qtdBugs = setQtdBugs(qtdBugs);
	}

	//limitar a quantidade de bugs
	public void setQtdBugs(int qtdBugs) {
		int qtdCelulas = ((this.tamanhoX*this.tamanhoY)/2)-1;
		if (qtdBugs < qtdCelulas) {
			this.qtdBugs = qtdBugs;
		} else {
			System.out.println("\nO número máximo de Bugs permitido é : " + qtdCelulas);
			System.out.printf("Quantos BUGs estarão aterrorizando a ilha?\n");
			int bugs = scan.nextInt();
			setQtdBugs(bugs);
		}
	}
	//limitar a quantidade de alunos
		public void setQtdAlunos(int qtdAlunos) {
			int qtdCelulas = ((this.tamanhoX*this.tamanhoY)/2)-1;
			if (qtdAlunos <= qtdCelulas) {
				this.qtdAlunos = qtdAlunos;
			} else {
				System.out.println("\nO número máximo de Alunos permitido é : " + qtdCelulas);
				System.out.printf("Quantos alunos estão perdidos na ilha?\n");
				int alunos = scan.nextInt();
				setQtdAlunos(alunos);
			}
		
	}
		
	public void prepararJogo() {
		p = new Plano(tamanhoX, tamanhoY);
		p.montarPlano();
		servicos = new ServicosJogo(p, qtdAlunos, qtdBugs);
		servicos.povoarPlano();

		
		servicos.adicionarRobo(andador);
		servicos.adicionarRobo(peao);
		servicos.adicionarRobo(torre);
		servicos.adicionarRobo(bispo);
		servicos.adicionarRobo(cavalo);
		servicos.adicionarRobo(rei);
		servicos.adicionarRobo(rainha);
	}
	
	public void acaoRobo(Robo robo) {
		int acao;
		int qtdCelulas = 1;
		
		System.out.println("Você deseja avançar[1] ou retroceder[2]? [1/2]");
		acao = scan.nextInt();
		System.out.println("Quantas células você deseja mover?");
		qtdCelulas = scan.nextInt();
//		switch(numCelulasMaxima) {
//			case 1:
//				qtdCelulas = 1;
//				break;
//			case 2:
//				while(qtdCelulas > 2) {
//					System.out.println("Quantas células você deseja mover? (Até 2)");
//					qtdCelulas = scan.nextInt();
//				}
//				break;
//			case 4:
//				while(qtdCelulas > 4) {
//					System.out.println("Quantas células você deseja mover? (Até 4)");
//					qtdCelulas = scan.nextInt();
//				}
//				break;
//			default:
//				System.out.println("Quantas células você deseja mover?");
//				qtdCelulas = scan.nextInt();
//				break;
//		}
		
		if (acao == 1) {
			robo.avancar(qtdCelulas);
		} else if (acao ==2) {
			robo.retroceder(qtdCelulas);
		} else {
			System.out.println("Digite uma opção válida! [1/2]:");
		}
		
		
		
//		switch(acao) {
//			case 1:
//				robo.avancar(qtdCelulas);
////				break;
//			case 2:
//				robo.retroceder(qtdCelulas);
////				break;
//			default:
//				System.out.println("Digite uma opção válida! [1/2]:");
////				break;
//		}
	
		servicos.atualizarPosicaoRobo(robo);
		servicos.verificarColisao(robo);
		
	}

	public void Jogar() {
		this.prepararJogo();
		
		String sair = "n";
		
		while(sair.equals("n") || servicos.qtdAlunos==0) {
			System.out.println("\n====================================");
			servicos.plano.retornarCelulasMontadas();
			System.out.println("====================================\n");
			System.out.println("Alunos |☺| restantes: " + servicos.qtdAlunos);
			System.out.println("Bugs   |☿| restantes: " + servicos.qtdBugs + "\n");
			System.out.println("QUAL ROBÔ VOCÊ DESEJA USAR?");
			System.out.println("|1| Robô Andador |☂| ("+ andador.posicaoX +", "+ andador.posicaoY +") ("+ andador.pontos +" pontos)");
			System.out.println("|2| Robô Peão    |♟| ("+ peao.posicaoX +", "+ peao.posicaoY +") ("+ peao.pontos +" pontos)");
			System.out.println("|3| Robô Torre   |♜| ("+ torre.posicaoX +", "+ torre.posicaoY +") ("+ torre.pontos +" pontos)");
			System.out.println("|4| Robô Bispo   |♗| ("+ bispo.posicaoX +", "+ bispo.posicaoY +") ("+ bispo.pontos + " pontos)");
			System.out.println("|5| Robô Cavalo  |♞| ("+ cavalo.posicaoX +", "+ cavalo.posicaoY +") ("+ cavalo.pontos +" pontos)");
			System.out.println("|6| Robô Rei     |♚| ("+ rei.posicaoX +", "+ rei.posicaoY +") ("+ rei.pontos +" pontos)");
			System.out.println("|7| Robô Rainha  |♛| ("+ rainha.posicaoX +", "+ rainha.posicaoY +") ("+ rainha.pontos +" pontos)");
			
			
			int resposta = 0;
			System.out.print("\nSua resposta: ");
			resposta = scan.nextInt();
			scan.nextLine(); // Consumir nova linha em branco para evitar erros
			
			switch(resposta) {
				case 1:
					this.acaoRobo(andador);
					break;
				case 2:
					this.acaoRobo(peao);
					break;
				case 3:
					this.acaoRobo(torre);
					break;
				case 4:
					this.acaoRobo(bispo);
					break;
				case 5:
					this.acaoRobo(cavalo);
					break;
				case 6:
					this.acaoRobo(rei);
					break;
				case 7:
					this.acaoRobo(rainha);
					break;
				default:
					System.out.println("O Robô inserido não existe!");
					break;
			}
			scan.nextLine(); // Consumir nova linha em branco para evitar erros
			
			System.out.println("Deseja parar de jogar? [s/n]");

			sair = scan.nextLine();	
			
			
		}
	}
}
