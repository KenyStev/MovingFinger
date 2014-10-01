package com.ZeroStudio.MovingFinger.screen;

import com.ZeroStudio.MovingFinger.MovingFinger;
import com.ZeroStudio.MovingFinger.Actor.ButtonsActor;
import com.ZeroStudio.MovingFinger.Listener.ButtonsListener;
import com.ZeroStudio.MovingFinger.Listener.CharacterListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class SelectCharacterScreen extends AbstractScreen {
	private Stage stage;
	private Image[] characters = new Image[5];
	private ButtonsActor back;
	private int he = 0, ii = 0;
	private int w=80, limit=5, rpw=70, rph=450;

	public SelectCharacterScreen(MovingFinger game) {
		super(game);
		
	}

	@Override
	public void show() {
		stage = new Stage(512, 700, true, game.getSpriteBatch());
		Gdx.input.setInputProcessor(stage);
		
		inicialiceCharacter();
		
		back = new ButtonsActor(2);
		stage.addActor(back);
		back.addListener(new ButtonsListener(back, 2, game));
		back.setPosition(50, 50);
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl10.glClearColor(1, 1, 1, 1);
		Gdx.gl10.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		stage.act();
			setAlpha(MovingFinger.CHARACTER);
		stage.draw();
			
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		stage.setViewport(512, 700, true);
	}

	@Override
	public void hide() {
		Gdx.input.setInputProcessor(null);
	}

	@Override
	public void dispose() {
		stage.dispose();
	}
	
	/**
	 * Inicializa el arreglo de los Personajes
	 */
	private void inicialiceCharacter() {
		for (int i = 0; i < characters.length; i++) {
			
			int wi = ii * w; //105
			
			characters[i] = new Image(new TextureRegion(MovingFinger.MANAGER.get("ball"+i+".png", Texture.class), 60, 60));
			stage.addActor(characters[i]);
			
			if(wi == w*limit){ //315
				he += (w+3);
				wi = 0;
				ii = 0;
			}
			
			characters[i].setPosition(rpw + wi, rph - he); //90
			characters[i].addListener(new CharacterListener(i));
			ii++;
		
		}
		he=0; ii=0;
	}

	/**
	 * Asigna la transparencia a los personajes que no esten <br/>
	 * seleccionados y full color al que este seleccionado
	 * @author Kenshi Zhekaru (zerokull);
	 * @param x : Es el personaje que esta seleccionado;
	 */
	private void setAlpha(int x){
		for(int i=0; i<characters.length; i++){
			if(i==x) continue;
			characters[i].setColor(1, 1, 1, .3f);
		}
		characters[x].setColor(1, 1, 1, 1);
	}

}
