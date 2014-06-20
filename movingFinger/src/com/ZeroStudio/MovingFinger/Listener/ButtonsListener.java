package com.ZeroStudio.MovingFinger.Listener;


import com.ZeroStudio.MovingFinger.MovingFinger;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

public class ButtonsListener extends InputListener {
	
	private int screen;
	private Image button;
	private MovingFinger game;

	public ButtonsListener(Image button, int screen, MovingFinger game) {
		this.button=button;
		this.screen=screen;
		this.game=game;
	}
	
	@Override
	public boolean touchDown(InputEvent event, float x, float y, int pointer,
			int button) {
		this.button.setColor(1f, 1f, 1f, 0.5f);
		return true;
	}
	
	@Override
	public void touchUp(InputEvent event, float x, float y, int pointer,
			int button) {
		this.button.setColor(1f, 1f, 1f, 1f);
		switch(this.screen){
		case 1: game.setScreen(game.GAMEPLAY); break;
		case 2: game.setScreen(game.MAIN); break;
		case 3:
			//BLU
			try{
				delayDispose(); //found more
			}catch(Exception e){
				
			}
			delayExit();
			/****/
			
//			delayDispose();
//			delayExit();
			
//			delayDispose(); //found more
//			//with this
//			try{
////				game.dispose();
//				delayExit();
//			}catch(Exception e){ 
//				
//			}
			
//			game.dispose();
//			Gdx.app.exit();
			
			break;
		}
		
		super.touchUp(event, x, y, pointer, button);
	}
	
	private void delayExit() {
		float delay = 0.1f; // seconds

		Timer.schedule(new Task() {
			@Override
			public void run() {
				// Do your work
				Gdx.app.exit();
			}
		}, delay);
	}

	private void delayDispose() {
		float delay = 0.5f; // seconds

		Timer.schedule(new Task() {
			@Override
			public void run() {
				// Do your work
				game.dispose();
			}
		}, delay);
	}
}
