package controller;

import java.util.Observer;
import java.util.concurrent.BlockingQueue;

public abstract class Controller implements Observer
{
	protected BlockingQueue<Command> queue;
	protected boolean stop = false;
	
	public void Start()
	{
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while (!stop) {
					try 
					{
						Command cmd = queue.take();
						if (cmd != null)
						{	
							if(cmd instanceof CommandExit)
							{
								Stop();
							}
							
							cmd.execute();
						}
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		});
		
		thread.start();		
		
	}
	
	public void Stop()
	{
		this.stop = true;
	}
}	