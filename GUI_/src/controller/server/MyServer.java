package controller.server;


import java.io.IOException;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class MyServer {
	
	private int port;
	private ClientHandler ch;
	private volatile boolean stop;
	private ServerSocket server;
	
	public MyServer(int port,ClientHandler ch)
	{
		this.port=port;
		this.ch=ch;
		stop=false;
	}
	
	
	public void Start()
	{
			new Thread(new Runnable() {
			public void run()
			{
				try
				{
					runServer();
				}
				
				catch (Exception e){}
			}
			}).start();
	}
	
	
	public void stop()
	{
		stop=true;
	} 
	
	
	private void runServer()throws Exception {
		this.server =new ServerSocket(port);
		//server.setSoTimeout(1000);
		while(!stop)
		{
			try{
				
				 Socket aClient=server.accept(); // blocking call
				Thread t1 = new Thread(new Runnable()
				 { //WOW! we should control the number of threads!
					public void run()
					{
					  try {
						 System.out.println(aClient.getLocalPort());
						  	Thread t2=ch.aSyncReadInputsAndSend(aClient.getInputStream(),aClient.getOutputStream());
						  	t2.join();
						  	aClient.getInputStream().close();
						  	aClient.getOutputStream().close();
						  	aClient.close();
					  	  }
					  
					  catch (IOException e) {/*...*/}
					  
					  catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
						}
		});
				t1.start();
				t1.join();
				
				 
		}
			catch(SocketTimeoutException e) {/*...*/}
		}
		server.close(); //WOW! we should wait for all threads before closing!
		}

	//Getters and Setters
	public int getPort() {
		return port;
	}


	public void setPort(int port) {
		this.port = port;
	}


	public ServerSocket getServer() {
		return server;
	}

	public void setServer(ServerSocket server) {
		this.server = server;
	}
}