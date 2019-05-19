package client;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) {
		Client client = new Client();
		client.start();
	}
	private void start() {
		try {
			Scanner console = new Scanner(System.in);
			Socket server = new Socket("localhost", 9091);
			PrintWriter toServer = new PrintWriter(server.getOutputStream());
			Scanner fromServer = new Scanner(server.getInputStream());
			do {
				System.out.println("==> ");
				String comando = console.nextLine();
				toServer.println(comando);
				toServer.flush();
				String resposta = fromServer.nextLine();
				System.out.println("Resposta do server: " + resposta);
			} while (true);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
