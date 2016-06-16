package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {

	/**
	 * Server Port
	 */
	int SERVER_PORT;

	/**
	 * Z�hlt die Aufrufe des Workers
	 */
	int zaehler;

	/**
	 * Aktive Verbindungen
	 */
	int activ;

	/**
	 * Main Server Socket
	 */
	ServerSocket socket;

	/**
	 * Constructor
	 * 
	 * @param SERVER_PORT
	 */
	public Server(int SERVER_PORT) {
		this.SERVER_PORT = SERVER_PORT;
		this.zaehler = 0;
		this.setName("Server_MainThread");
	}

	/**
	 * Run Funktion nimmt eingehende Verbindungsanfragen an und startet einen
	 * Worker
	 */
	@Override
	public void run() {

		ServerManager manager = new ServerManager();

		try {
			socket = new ServerSocket(this.SERVER_PORT);

			while (true) {
				System.out.println("Waiting for Connection!!  -  " + zaehler);
				Socket client = socket.accept();
				zaehler++;

				System.out.println("Connection get Create Thread!");
				Thread t = new WorkerThread(client, zaehler, manager);
				t.start();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
