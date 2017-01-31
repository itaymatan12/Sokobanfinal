package controller;

import java.util.List;

import model.Model;

public class CommandClearLevel implements Command {

	private  Model m;
	@Override
	public void execute() throws Exception {
		// TODO Auto-generated method stub
			m.CleanLevel();
	}

	
	@Override
	public void setParams(List<Object> params) {
		// TODO Auto-generated method stub
		this.m=(Model)params.get(0);
	}

}
