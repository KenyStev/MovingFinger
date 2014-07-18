package com.ZeroStudio.MovingFinger.screen;


import com.ZeroStudio.MovingFinger.MovingFinger;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

/* ******************************************************** *
 * LoadingScreen es la clase encargar de crear la pantalla loading,	*
 * donde aparece el Desarrollador: Zero Kull de Zero Studio,*
 * y el Dise�ador de los Graficos: John Mackay.				*
 * ******************************************************** */

public class LoadingScreen extends AbstractScreen {

	/**************VARIABLES DE INSTANCIA**************/
	private Texture fondo;
	private Stage stage;
	private Image fondoAct;
	private int cont = 0, cant = 1;
	/**************************************************/

	/***********CONSTRUCTOR DE LA CLASE***********/
	public LoadingScreen(MovingFinger game) {
		super(game);
	}

	@Override
	public void show() {
		stage = new Stage(320, 640, true, game.getSpriteBatch());
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		stage.act();
		if (MovingFinger.MANAGER.isLoaded("zeroStudio.png", Texture.class)
				&& cont < cant) {
			if (cont < cant) {
				loadImage(1);
				delay(1);
			}
		}

		if (MovingFinger.MANAGER.isLoaded("RosalilaStudio.png", Texture.class)
				&& cont == cant) {
			fondoAct.remove();
			if (cont == cant) {
				loadImage(2);
				delay(2);
			}
		}

		stage.draw();

		if ((MovingFinger.MANAGER.update()) 
				&& (cont > cant)) {
//			game.setScreen(game.MAIN);
			game.setScreen(game.INTRO);
		}
	}

	private void loadImage(int indice) {
		switch (indice) {
		case 1:
			fondo = MovingFinger.MANAGER.get("zeroStudio.png", Texture.class);
			fondo.setFilter(TextureFilter.Linear, TextureFilter.Linear);
			fondoAct = new Image(fondo);
			fondoAct.setPosition(
					stage.getWidth() / 2 - fondoAct.getWidth() / 2,
					stage.getHeight() / 2 - fondoAct.getHeight() / 2);
			stage.addActor(fondoAct);
			break;
		case 2:
			stage.clear();
			fondo = MovingFinger.MANAGER.get("RosalilaStudio.png", Texture.class);
			fondo.setFilter(TextureFilter.Linear, TextureFilter.Linear);
			fondoAct = new Image(fondo);
			fondoAct.setPosition(
					stage.getWidth() / 2 - fondoAct.getWidth() / 2,
					stage.getHeight() / 2 - fondoAct.getHeight() / 2);
			stage.addActor(fondoAct);
			break;
		}
	}

	private void delay(final int indice) {
		float delay = 3f; // seconds

		Timer.schedule(new Task() {
			@Override
			public void run() {
				// Do your work
				cont = indice;
			}
		}, delay);
	}

	@Override
	public void hide() {
		Gdx.input.setInputProcessor(null);
		stage.clear();
	}

	@Override
	public void dispose() {
		stage.dispose();
	}

	@Override
	public void resize(int width, int height) {
		stage.setViewport(320, 640, true);
	}

}
