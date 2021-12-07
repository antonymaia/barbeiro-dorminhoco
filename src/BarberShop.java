import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class BarberShop extends Thread {
	private int id;
	private Semaphore semaphore;

	public BarberShop(int nome, Semaphore semaphore) {
		this.id = nome;
		this.semaphore = semaphore;
	}

	public void atender() {
		try {
			System.out.println("Cliente #" + this.id + " está sendo atendido...");
			Thread.sleep((long) (Math.random() * 3000));
			System.out.println("Cliente #" + this.id + " finalizado !");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public synchronized void run() {
		try {
			this.semaphore.acquire();
			atender();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			semaphore.release();
		}
	}

	public static void main(String[] args) {
		int numeroDeBarbeiros = 0;
		int numeroDeClientes = 0;

		Scanner scanner = new Scanner(System.in);

		System.out.println("\nQuantos barbeiros vão trabalhar hoje ?");
		numeroDeBarbeiros = scanner.nextInt();

		System.out.println("\nChegaram quantos clientes ?");
		numeroDeClientes = scanner.nextInt();

		Semaphore semaphore = new Semaphore(numeroDeBarbeiros);
		BarberShop[] testes = new BarberShop[numeroDeClientes];

		for (int i = 0; i < numeroDeClientes; i++) {
			testes[i] = new BarberShop(i, semaphore);
			testes[i].start();
		}
	}
}
