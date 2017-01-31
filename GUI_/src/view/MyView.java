package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Observable;

import model.data.Level;

public class MyView extends Observable implements View {
	
	private BufferedReader reader;
	private PrintWriter writer;
	private boolean stop = false;

	@Override
	public void displayMessage(String string)
	{
		System.out.println(string);
	}

	@Override
	public void displayLevel(Level l,Display d)
	{
		System.out.println(d.display(l));	
	}

	@Override
	public void start() 
	{
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				String commandLine = "";
				do {
					writer.print("Enter command: " );
					writer.flush();
					try {
						commandLine = reader.readLine();
						String[] arr = commandLine.split(" ");
						LinkedList<String> params = new LinkedList<String>();
						for (String param : arr) {
							params.add(param);
						}
						setChanged();
						notifyObservers(params);
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} while (!stop);				
			}
		}).start();	
		
		
	}

	public BufferedReader getReader() {
		return reader;
	}

	public void setReader(BufferedReader reader) {
		this.reader = reader;
	}

	public PrintWriter getWriter() {
		return writer;
	}

	public void setWriter(PrintWriter writer) {
		this.writer = writer;
	}

	@Override
	public void stop() {
		this.stop = true;
		
	}

	@Override
	public void setcontrols(String up, String down, String left, String right) {
		// TODO Auto-generated method stub
		
	}
}