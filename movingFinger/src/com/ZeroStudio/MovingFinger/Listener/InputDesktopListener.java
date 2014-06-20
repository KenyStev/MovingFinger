package com.ZeroStudio.MovingFinger.Listener;


import com.ZeroStudio.MovingFinger.Actor.BallActor;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class InputDesktopListener extends InputListener {
	
	private BallActor ball;

	public InputDesktopListener(BallActor ball) {
		this.ball=ball;
	}
	
	@Override
	public boolean keyDown(InputEvent event, int keycode){
		switch(keycode){
//		case Input.Keys.UP:
//			ball.velocidad.y = 250;
//			return true;
//		case Input.Keys.DOWN:
//			ball.velocidad.y = -250;
//			return true;
		case Input.Keys.RIGHT:
			ball.velocidad.x = 300;
			return true;
		case Input.Keys.LEFT:
			ball.velocidad.x = -300;
			return true;
		default:
			return false;
		}
	}

	@Override
	public boolean keyUp(InputEvent event, int keycode){
		switch(keycode){
//		case Input.Keys.UP:
//		case Input.Keys.DOWN:
//			ball.velocidad.y = 0;
//			return true;
		case Input.Keys.RIGHT:
		case Input.Keys.LEFT:
			ball.velocidad.x = 0;
			return true;
		default:
			return false;
		}
	}

}
