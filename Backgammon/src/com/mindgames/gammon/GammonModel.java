package com.mindgames.gammon;

public class GammonModel {

	private int[] modelArray;
	
	public synchronized void set(int pos, int count){
		modelArray[pos] = count;
	}
	
	public synchronized int getCount(int pos){
		return modelArray[pos];
	}
	
	public synchronized int[] getModel(){
		return modelArray;
	}
	
	public synchronized void setModel(int[] gammonModel){
		this.modelArray = gammonModel;
	}
}
