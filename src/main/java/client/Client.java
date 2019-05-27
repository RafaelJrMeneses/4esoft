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
		Socket socket = null;
		PrintWriter toServer = null;
		Scanner fromServer = null;
		Scanner console = new Scanner(System.in);
		String comando = null;
		String resposta = null;
		try {
			do {
				System.out.println("==> ");
				comando = console.nextLine();
				if (comando.equals("connect")) {
					socket = new Socket("localhost", 9091);
					toServer = new PrintWriter(socket.getOutputStream());
					fromServer = new Scanner(socket.getInputStream());
					System.out.println(fromServer.nextLine());
				} else if (comando.equals("disconnect")) {
					if (socket != null && socket.isConnected()) {
						toServer.println(comando);
						toServer.flush();
						socket.close();
					}
					else if(comando.startsWith("ls")) {
						toServer.println(comando);
						toServer.flush();
						resposta = fromServer.nextLine();
						System.out.println(resposta);
					}
				}
			} while (comando.equals("sair"));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
