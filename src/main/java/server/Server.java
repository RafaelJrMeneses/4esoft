package server;

import java.io.File;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
	private static final int PORT = 9092;

	public static void main(String[] args) {
		Server server = new Server();
		server.start();
	}

	private void start() {
		try {
			ServerSocket socket = new ServerSocket(9091);
			Socket client = socket.accept();
			Scanner fromClient = new Scanner(client.getInputStream());
			PrintWriter toClient = new PrintWriter(client.getOutputStream());
			String comandoDoClient = "";
			do {
				comandoDoClient = fromClient.nextLine();
				toClient.println("ECHO FROM SERVER: [" + comandoDoClient + "]");

				if (comandoDoClient.equals("sair")) {
					System.out.println("Server Finalizado!");
				}
				toClient.flush();
			} while (!comandoDoClient.equals("sair"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void percorreRecursivo(File entrada) {
		System.out.println(entrada.getName());
		if (entrada.isDirectory()) {
			for (File arquivo : entrada.listFiles()) {
				percorreRecursivo(arquivo);
			}
		}
	}
}
