package controller.server;

import java.io.BufferedReader;
import java.io.PrintWriter;

public interface ClientHandler {
	
	public Thread aSyncReadInputsAndSend(BufferedReader in, PrintWriter out);


}