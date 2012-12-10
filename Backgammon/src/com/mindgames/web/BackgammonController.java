package com.mindgames.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.mindgames.gammon.GammonModelHandler;

public class BackgammonController implements Controller{

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// stub backgammon model
		int[] gammonModel = GammonModelHandler.getModel().getModel();
		
		ModelAndView gammonModelAndView = new ModelAndView("backgammon");
		gammonModelAndView.addObject("gammonModel", gammonModel);
		
		return gammonModelAndView;
	}
	
}
