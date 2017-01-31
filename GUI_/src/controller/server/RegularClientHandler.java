package controller.server;

import java.io.BufferedReader;

import java.io.PrintWriter;

import view.MyView;

public class RegularClientHandler implements ClientHandler {

	
	
	private MyView v;
	
	public RegularClientHandler (MyView v)
	{
		
		this.v=v;
		
	}

		private void readInputsAndSend(BufferedReader in, PrintWriter out)
		{
			v.setReader(in);
			v.setWriter(out);
			v.start();
		
		}
		
		
		
		public Thread aSyncReadInputsAndSend(BufferedReader in, PrintWriter out)
		{ 
		Thread t=new Thread(new Runnable() 
		{ public void run()
		{ readInputsAndSend(in, out); } 
		});
		t.start(); 
		return t; }
}



