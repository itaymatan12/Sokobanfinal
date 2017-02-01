package controller.server;


import java.io.InputStream;
import java.io.OutputStream;


public interface ClientHandler {
	
	public Thread aSyncReadInputsAndSend(InputStream in, OutputStream out);


}