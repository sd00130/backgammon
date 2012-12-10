package com.mindgames.gammon;

public class GammonGame {

	public static void move(int from, int to){
		GammonModelHandler.getModel().set(from-1, GammonModelHandler.getModel().getCount(from-1)-1);
		GammonModelHandler.getModel().set(to-1, GammonModelHandler.getModel().getCount(to-1)+1);
	}
}
