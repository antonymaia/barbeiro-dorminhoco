import java.util.Scanner;

public class Barbearia implements Runnable{

	int cadeirasEspera;
	boolean cadeiraBarbeiro;
	int clientesEspera;
	
	public Barbearia() {}
	
	public Barbearia(int cadeirasEspera) {
		super();
		this.cadeirasEspera = cadeirasEspera;
		this.cadeiraBarbeiro = false;
		this.clientesEspera = 0;
	}
	
	public void barbeiroDorme() throws InterruptedException {
		System.out.println("\n\nNão há Clientes para ser atendido");
		System.out.println("Barbeiro dormindo...");
		Thread.sleep(5000);
	}
	
	public void barbeiroAtende() throws InterruptedException{
		System.out.println("\n\nHá "+this.clientesEspera+" clientes em espera!");
		for(int i = 0; i < this.clientesEspera; i++ ) {
			System.out.println("Atendendo o cliente "+(i+1)+" ...");
			Thread.sleep(3000);
			System.out.println("cliente "+(i+1)+" atendido!");
		}
		this.clientesEspera = 0;
	}

	@Override
	public void run() {
		try {
			Scanner scanner = new Scanner(System.in);
			System.out.println("\n\nA barbearia está aberta!");
			
			while(true) {
				System.out.println("\n\nEntrou quantos clientes?");
				int entrouClientes = scanner.nextInt();
				
				if(entrouClientes > 0) {
					int cadeiraLivres = this.cadeirasEspera - this.clientesEspera;
					
					if(entrouClientes > cadeiraLivres) {
						System.out.println((entrouClientes-cadeiraLivres)+" clientes foram embora!");
					}
					this.clientesEspera += cadeiraLivres>entrouClientes?entrouClientes:((cadeiraLivres-entrouClientes)+entrouClientes);

					barbeiroAtende();
				}
				barbeiroDorme();
			}
		}catch(Exception e) {
			
		}
	}		
}	
