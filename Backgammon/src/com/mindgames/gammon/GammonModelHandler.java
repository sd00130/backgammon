package com.mindgames.gammon;


public class GammonModelHandler {
	
	public static GammonModel model;
	
	public static GammonModel getModel(){
		if (model == null){
			model = createModel();	
			return model;
		}
		return model;
	}
	
	
	public static int[] getModelArray(){
		return model.getModel();
	}
	
	private static GammonModel createModel(){
		GammonModel model = new GammonModel();
		int[] modelArray = new int[24];	
		modelArray[0] = 1;
		modelArray[1] = 1;		
		modelArray[2] = 1;
		modelArray[3] = 1;
		modelArray[4] = 1;
		modelArray[5] = 1;
		
		modelArray[6] = 1;
		modelArray[7] = 1;
		modelArray[8] = 1;
		modelArray[9] = 1;
		modelArray[10] = 1;
		modelArray[11] = 1;
		
		modelArray[23] = 1;
		modelArray[22] = 1;
		modelArray[21] = 1;
		modelArray[20] = 1;
		modelArray[19] = 1;
		modelArray[18] = 1;
		
		modelArray[17] = 1;
		modelArray[16] = 1;
		modelArray[15] = 1;
		modelArray[14] = 1;
		modelArray[13] = 1;
		modelArray[12] = 1;
		
		model.setModel(modelArray);
		
		return model;
	}
}
