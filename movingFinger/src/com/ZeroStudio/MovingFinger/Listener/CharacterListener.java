package com.ZeroStudio.MovingFinger.Listener;

import com.ZeroStudio.MovingFinger.MovingFinger;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class CharacterListener extends InputListener {

	private int x;
	
	public CharacterListener(int X) {
		this.x=X;
	}
	
	@Override
	public boolean touchDown(InputEvent event, float x, float y, int pointer,
			int button) {
			MovingFinger.CHARACTER = this.x;
		return true;
	}

}
