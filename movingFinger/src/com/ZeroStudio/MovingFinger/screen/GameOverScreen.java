package com.ZeroStudio.MovingFinger.screen;


import static com.ZeroStudio.MovingFinger.AnimationControl.*;

import com.ZeroStudio.MovingFinger.BestScoreAnimation;
import com.ZeroStudio.MovingFinger.MovingFinger;
import com.ZeroStudio.MovingFinger.Actor.BallActor;
import com.ZeroStudio.MovingFinger.Actor.ButtonsActor;
import com.ZeroStudio.MovingFinger.Actor.SkullActor;
import com.ZeroStudio.MovingFinger.Listener.ButtonsListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/* ************************************************************************ *
 * GameOverScreen es la Clase escargada de mostrar un menu con los botones,	*
 * retry y menu, solo aparece cuando se pierde el juego.					*
 * tambien muestra el score hecho en esa partida.							*
 * ************************************************************************ */

public class GameOverScreen extends AbstractScreen{
	
	/***********VARIABLES DE INSTANCIA DE LA CLASE***********/
	private Texture gameover;
	private Stage stage;
	private Image nowscore, imgFondo, backMenu1, GOtxt;
	private ButtonsActor retry, main;
	/********************************************************/
	
	/***********CONSTRUCTOR DE LA CLASE***********/
	public GameOverScreen(MovingFinger game){
		super(game);
	}

	@Override
	public void show() {
		game.funcion.prueba();
		
		stage = new Stage(512, 700, true, game.getSpriteBatch());
		Gdx.input.setInputProcessor(stage);
		
		//Crear Fondo
		TextureRegion RegionFondo = new TextureRegion(MovingFinger.MANAGER.get("fondo.png", Texture.class), 512, 1024); //change se cambio el corte y roto imagen
		Image imgFondoGame = new Image(RegionFondo);
		stage.addActor(imgFondoGame); System.out.println("Cargo el fondo");
		
		init();
		
		//Creamos una Nueva Bola
		BallActor ball = new BallActor();
		ball.setPosition(game.positionsActor.x, game.positionsActor.y);//y = 76 default
		stage.addActor(ball);
		
		//Creamos la Skull que Colisiono
		SkullActor skull = new SkullActor(0);
		skull.setPosition(game.positionsActor2.x, game.positionsActor2.y);
		stage.addActor(skull);
		
		gameover = MovingFinger.MANAGER.get("gameover.png", Texture.class);
		imgFondo = new Image(gameover);
		stage.addActor(imgFondo);
		
		//Prueba de BackMenu
		Texture text= new Texture(Gdx.files.internal("backMenu.png"));
		text.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		TextureRegion backMenu = new TextureRegion(text,377,287 );
		backMenu1 = new Image(backMenu);
		backMenu1.setPosition(stage.getWidth()/2 - backMenu1.getWidth()/2, stage.getHeight()/2 - (backMenu1.getHeight()-74)/2 + stage.getHeight());
		stage.addActor(backMenu1);
		
		Texture scoretxt= new Texture(Gdx.files.internal("nowscore.png"));
		scoretxt.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		TextureRegion hiscore = new TextureRegion(scoretxt, 223, 78 );
		nowscore = new Image(hiscore);
		nowscore.setPosition(stage.getWidth()/2 - nowscore.getWidth()/2, backMenu1.getY() - nowscore.getHeight());
		stage.addActor(nowscore);
		
		//Inicializamos Puntuacion
		game.score.setPosition(nowscore.getX() + (nowscore.getWidth()/4)*2.6f, nowscore.getY() + nowscore.getHeight()/2);
		stage.addActor(game.score);
		game.maxima.setPosition(nowscore.getX() + nowscore.getWidth()*0.6f, nowscore.getY() + nowscore.getHeight()/2);
		stage.addActor(game.maxima);
		
		retry = new ButtonsActor(1);
		retry.setPosition(stage.getWidth()/2 + retry.getWidth()*0.1f, stage.getHeight()/2 - retry.getHeight()/2 + stage.getHeight());
		retry.addListener(new ButtonsListener(retry, 1, game));
		stage.addActor(retry);
		
		main = new ButtonsActor(3);
		main.setPosition(stage.getWidth()/2 - main.getWidth()*1.1f, stage.getHeight()/2 - main.getHeight()/2 + stage.getHeight());
		main.addListener(new ButtonsListener(main, 2, game));
		stage.addActor(main);
		
		BestScoreAnimation best = new BestScoreAnimation();
		best.setPosition(stage.getWidth()/2 - best.getWidth()/2, 50);
		best.setVisible(false);
		stage.addActor(best);
		
		Texture gameovertxt = new Texture(Gdx.files.internal("gameovertxt.png"));
		GOtxt = new Image(gameovertxt);
		GOtxt.setOrigin(GOtxt.getWidth()/2, GOtxt.getHeight()/2);
		GOtxt.setScale(1.5f);
		GOtxt.setPosition(stage.getWidth()/2 - GOtxt.getWidth()/2, (float) (stage.getHeight() - GOtxt.getHeight()*1.2) + stage.getHeight());
		stage.addActor(GOtxt);
		
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
//		stage.clear();
		backMenu1.remove();
//		imgFondo.remove();
		nowscore.remove();
		
		Gdx.input.setInputProcessor(null);
	}
	
	private void upLabel(){
		if(UP){
			acelerar();
			exch();
			if(nowscore.getY()>stage.getHeight()){
				UP=false;
				velocidad=500;
				aceleracion=0;
				switch (screenSelect) {
				case 1:
					game.setS(game.GAMEPLAY);
					break;
				case 2:
					game.setS(game.MAIN);
					break;
				}
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
		main.setY(main.getY()+pos);
		retry.setY(retry.getY()+pos);
		nowscore.setY(nowscore.getY()+pos);
		game.maxima.setY(game.maxima.getY()+pos);
		game.score.setY(game.score.getY()+pos);
	}

	@Override
	public void dispose() {
		stage.dispose();
//		gameover.dispose();
	}
	
	@Override
	public void resize(int width, int height) {
		stage.setViewport(512, 700, true);
		
		game.score.setPosition(nowscore.getX() + (nowscore.getWidth()/4)*2.6f, nowscore.getY() + nowscore.getHeight()/2);
		game.maxima.setPosition(nowscore.getX() + nowscore.getWidth()*0.06f, nowscore.getY() + nowscore.getHeight()/2);
	}

}
