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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

}
