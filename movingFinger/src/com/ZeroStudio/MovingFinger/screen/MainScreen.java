package com.ZeroStudio.MovingFinger.screen;


import com.ZeroStudio.MovingFinger.MovingFinger;
import com.ZeroStudio.MovingFinger.Actor.ButtonsActor;
import com.ZeroStudio.MovingFinger.Listener.ButtonsListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/* ************************************************************************ *
 * MainScreen es la clase encargada de crear el menu principal de Juego,	*
 * con los botones de Jugar y Salir.										*
 * ************************************************************************ */

public class MainScreen extends AbstractScreen{
	
	/************VARIABLES DE INSTANCIA************/
	private Stage stage;
	private Texture titulo;
	private Image imgFondo, hi_score, backMenu1;
	private ButtonsActor play, exit;
	/**********************************************/

	/****************CONSTRUCTOR****************/
	public MainScreen(MovingFinger game){
		super(game);
	}

	@Override
	public void show() {
		titulo = MovingFinger.MANAGER.get("fondo.png", Texture.class);
		stage = new Stage(512, 700, true, game.getSpriteBatch());
		Gdx.input.setInputProcessor(stage);
		
		imgFondo = new Image(titulo);
		stage.addActor(imgFondo);
		
		//Prueba de BackMenu
		Texture text= new Texture(Gdx.files.internal("backMenuPrin.png"));
		text.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		TextureRegion backMenu = new TextureRegion(text,377,295 );
		backMenu1 = new Image(backMenu);
		backMenu1.setPosition(stage.getWidth()/2 - backMenu1.getWidth()/2, stage.getHeight()/2 - (backMenu1.getHeight()-76)/2);
		stage.addActor(backMenu1);
		
		Texture scoretxt= new Texture(Gdx.files.internal("hiscore.png"));
		scoretxt.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		TextureRegion hiscore = new TextureRegion(scoretxt, 223, 78 );
		hi_score = new Image(hiscore);
		hi_score.setPosition(stage.getWidth()/2 - hi_score.getWidth()/2, backMenu1.getY() - hi_score.getHeight());
		stage.addActor(hi_score);
		
		play = new ButtonsActor(0);
		play.setPosition(stage.getWidth()/2 + play.getWidth()*0.1f, stage.getHeight()/2 - play.getHeight()/2);
		play.addListener(new ButtonsListener(play, 1, game));
		stage.addActor(play);
		
		exit = new ButtonsActor(2);
		exit.setPosition(stage.getWidth()/2 - exit.getWidth()*1.1f, stage.getHeight()/2 - play.getHeight()/2);
		exit.addListener(new ButtonsListener(exit, 3, game));
		stage.addActor(exit);
		
		Texture Name = new Texture(Gdx.files.internal("MovingFinger.png"));
		Image NameGame = new Image(Name);
		NameGame.setOrigin(NameGame.getWidth()/2, NameGame.getHeight()/2);
		NameGame.setScale(1.5f);
		NameGame.setPosition(stage.getWidth()/2 - NameGame.getWidth()/2, (float) (stage.getHeight() - NameGame.getHeight()*1.2));
		stage.addActor(NameGame);
		
		game.maxima.setPosition(hi_score.getX() + hi_score.getWidth()/3, hi_score.getY() + hi_score.getHeight()/2);
		stage.addActor(game.maxima);
	}

	@Override
	public void render(float delta) {
		stage.act();
		stage.draw();
	}

	@Override
	public void hide() {
		Gdx.input.setInputProcessor(null);
	}

	@Override
	public void dispose() {
		stage.dispose();
	}

	@Override
	public void resize(int width, int height) {
		//Escalar a la Pantalla
		stage.setViewport(512, 700, true);
		
		game.maxima.setPosition(hi_score.getX() + hi_score.getWidth()/3, hi_score.getY() + hi_score.getHeight()/2);
	}

}
