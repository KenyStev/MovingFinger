package com.ZeroStudio.MovingFinger.Listener;


import com.ZeroStudio.MovingFinger.MovingFinger;
import static com.ZeroStudio.MovingFinger.AnimationControl.*;
import com.ZeroStudio.MovingFinger.screen.GameplayScreen;
import com.ZeroStudio.MovingFinger.screen.MainScreen;
import com.badlogic.gdx.Gdx;
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
		case 1: UP=true; screenSelect=1; /*game.setScreen(game.GAMEPLAY);*/ break;
		case 2: UP=true; screenSelect=2; break;
		case 4: game.setScreen(game.MAIN); break;
		case 3: Gdx.app.exit(); break;
		}
		
		super.touchUp(event, x, y, pointer, button);
	}
}
