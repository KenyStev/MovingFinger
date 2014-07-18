package com.ZeroStudio.MovingFinger.screen;


import com.ZeroStudio.MovingFinger.MovingFinger;
import com.badlogic.gdx.Screen;

public abstract class AbstractScreen implements Screen {
	
	protected MovingFinger game;
	
	public AbstractScreen(MovingFinger game){
		this.game = game;
	}
	
	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

}
