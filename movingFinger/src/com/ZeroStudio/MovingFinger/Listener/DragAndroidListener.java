package com.ZeroStudio.MovingFinger.Listener;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;

public class DragAndroidListener extends DragListener {
	
	//Variable de Asiganicon del Listener del Personaje
	private Actor ball;
	private int y = 76;

	/*CONSTRUCTOR DEL LISTENER PARA EL PERSONAJE*/
	public DragAndroidListener(Actor ball) {
		this.ball = ball;
	}

	@Override
	public boolean touchDown(InputEvent event, float x, float y, int pointer,
			int button) {
		ball.setPosition(ball.getX(), this.y + ball.getHeight());
		return true;
	}

	@Override
	public void touchUp(InputEvent event, float x, float y, int pointer,
			int button) {
		ball.setPosition(ball.getX(), this.y);
	}

	@Override
	public void touchDragged(InputEvent event, float x, float y, int pointer) {
		//Mover a todos lados
//		ball.translate(x - ball.getWidth()/2, y - ball.getHeight()/2);
		ball.translate(x - ball.getWidth() / 2, 0); // Mover solo eje x

	}
}