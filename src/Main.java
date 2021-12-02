
public class Main {

	public static void main(String[] args) {
		Barbearia barbearia = new Barbearia(5);
		Thread thread = new Thread(barbearia);
		thread.start();
	}

}
