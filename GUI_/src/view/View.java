package view;


import model.data.Level;

public interface View {
	
	public void displayLevel(Level l,Display d);
	public void displayMessage(String string);
	public void start();
	public void stop();
	public void setcontrols(String up ,String down ,String left ,String right);

}