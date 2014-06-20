package com.ZeroStudio.MovingFinger.screen;

import com.ZeroStudio.MovingFinger.IntroAnimation;
import com.ZeroStudio.MovingFinger.MovingFinger;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class IntroScreen extends AbstractScreen {
	
	IntroAnimation intro;
	Stage stage;

	public IntroScreen(MovingFinger game) {
		super(game);
		
	}

	@Override
	public void render(float delta) {
		stage.act();
		if(Gdx.input.isTouched()){
			game.setScreen(game.MAIN);
		}
		stage.draw();
	}

	@Override
	public void show() {
		stage = new Stage(256, 460, true, game.getSpriteBatch());
		Gdx.input.setInputProcessor(stage);
		
		intro = new IntroAnimation();
		intro.setPosition(stage.getWidth()/2 - intro.getWidth()/2, 0);
		stage.addActor(intro);
		

	}

	@Override
	public void hide() {
		Gdx.input.setInputProcessor(null);

	}

	@Override
	public void dispose() {
		

	}
	
	@Override
	public void resize(int width, int height) {
		stage.setViewport(256, 460, true);
	}

}
