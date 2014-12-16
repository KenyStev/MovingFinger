package com.ZeroStudio.MovingFinger.screen;


import com.ZeroStudio.MovingFinger.MovingFinger;
import static com.ZeroStudio.MovingFinger.AnimationControl.*;
import com.ZeroStudio.MovingFinger.Actor.ButtonsActor;
import com.ZeroStudio.MovingFinger.Listener.ButtonsListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
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
	private Image select;
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
		
		velocidad=-500;
		UP=false;
		DOWN=true;
		
		//Prueba de BackMenu
		Texture text= new Texture(Gdx.files.internal("backMenuPrin.png"));
		text.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		TextureRegion backMenu = new TextureRegion(text,423,428 );
		backMenu1 = new Image(backMenu);
		backMenu1.setPosition(stage.getWidth()/2 - backMenu1.getWidth()/2, stage.getHeight()/2 - (backMenu1.getHeight()-76)/2 + stage.getHeight());
		stage.addActor(backMenu1);
		
		Texture scoretxt= new Texture(Gdx.files.internal("hiscore.png"));
		scoretxt.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		TextureRegion hiscore = new TextureRegion(scoretxt, 223, 78 );
		hi_score = new Image(hiscore);
		hi_score.setPosition(stage.getWidth()/2 - hi_score.getWidth()/2, backMenu1.getY() - hi_score.getHeight());
		stage.addActor(hi_score);
		
		play = new ButtonsActor(0);
		play.setPosition(stage.getWidth()/2 + play.getWidth()*0.1f, stage.getHeight()/2 - play.getHeight() + stage.getHeight());
		play.addListener(new ButtonsListener(play, 1, game));
		stage.addActor(play);
		
		exit = new ButtonsActor(2);
		exit.setPosition(stage.getWidth()/2 - exit.getWidth()*1.1f, stage.getHeight()/2 - play.getHeight() + stage.getHeight());
		exit.addListener(new ButtonsListener(exit, 3, game));
		stage.addActor(exit);
		
		Texture Name = new Texture(Gdx.files.internal("MovingFinger.png"));
//		TextureRegion Logo = new TextureRegion(Name, 300, 120);
		Image NameGame = new Image(Name);
//		NameGame.setOrigin(NameGame.getWidth()/2, NameGame.getHeight()/2);
//		NameGame.setScale(1.3f);
//		NameGame.setPosition(stage.getWidth()/2 - NameGame.getWidth()/2, (float) (stage.getHeight() - NameGame.getHeight()*2.2));
		NameGame.setPosition(stage.getWidth()/2 - NameGame.getWidth()/2, 50);
//		stage.addActor(NameGame);
		
		//Click temporal
		select = new Image(new TextureRegion(MovingFinger.MANAGER.get("ball"+MovingFinger.CHARACTER+".png", Texture.class), 60,60));
		select.setPosition(stage.getWidth()/2 - select.getWidth()/2, 76);
		select.addListener(new InputListener(){
			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				super.touchUp(event, x, y, pointer, button);
				game.setScreen(new SelectCharacterScreen(game));
			}
			
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				select.setColor(1, 1, 1, .5f);
				return true;
			}
		});
		stage.addActor(select);
		//fin del temporal click
		
		game.maxima.setPosition(hi_score.getX() + hi_score.getWidth()/3, hi_score.getY() + hi_score.getHeight()/2);
		stage.addActor(game.maxima);
	}

	@Override
	public void render(float delta) {
		stage.act();
		upLabel();
		downLabel();
		stage.draw();
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
	
	private void upLabel(){
		if(UP){
			acelerar();
			exch();
			if(hi_score.getY()>stage.getHeight()){
				UP=false;
				velocidad=500;
				aceleracion=0;
				game.setS(game.GAMEPLAY);
			}
		}
	}
	
	private void downLabel(){
		if(DOWN){
			marchaAtras();
			exch();
			if(backMenu1.getY()<stage.getHeight()/2 - backMenu1.getHeight()/3){
				DOWN=false;
				velocidad=500;
				aceleracion=0;
			}
		}
	}
	
	private void exch(){
		float pos = actualizarPosicion();
		backMenu1.setY(backMenu1.getY()+pos);
		exit.setY(exit.getY()+pos);
		play.setY(play.getY()+pos);
		hi_score.setY(hi_score.getY()+pos);
		game.maxima.setY(game.maxima.getY()+pos);
	}

	@Override
	public void resize(int width, int height) {
		//Escalar a la Pantalla
		stage.setViewport(512, 700, true);
		
		game.maxima.setPosition(hi_score.getX() + hi_score.getWidth()/3, hi_score.getY() + hi_score.getHeight()/2);
	}

}
