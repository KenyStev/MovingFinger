package com.ZeroStudio.MovingFinger.screen;


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
	private Image nowscore, imgFondo, backMenu1;
	/********************************************************/
	
	/***********CONSTRUCTOR DE LA CLASE***********/
	public GameOverScreen(MovingFinger game){
		super(game);
	}

	@Override
	public void show() {
		
		stage = new Stage(512, 700, true, game.getSpriteBatch());
		Gdx.input.setInputProcessor(stage);
		
		//Crear Fondo
		TextureRegion RegionFondo = new TextureRegion(MovingFinger.MANAGER.get("fondo.png", Texture.class), 512, 1024); //change se cambio el corte y roto imagen
		Image imgFondoGame = new Image(RegionFondo);
		stage.addActor(imgFondoGame);
		
		//Creamos una Nueva Bola
		BallActor ball = new BallActor();
		ball.setPosition(game.positionsActor.x, 76);
		stage.addActor(ball);
		
		//Creamos la Skull que Colisiono
		SkullActor skull = new SkullActor(0);
		skull.setPosition(game.positionsActor.y, game.positionsActor.z);
		stage.addActor(skull);
		
		gameover = MovingFinger.MANAGER.get("gameover.png", Texture.class);
		imgFondo = new Image(gameover);
		stage.addActor(imgFondo);
		
		//Prueba de BackMenu
		Texture text= new Texture(Gdx.files.internal("backMenu.png"));
		text.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		TextureRegion backMenu = new TextureRegion(text,377,287 );
		backMenu1 = new Image(backMenu);
		backMenu1.setPosition(stage.getWidth()/2 - backMenu1.getWidth()/2, stage.getHeight()/2 - (backMenu1.getHeight()-74)/2);
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
		
		ButtonsActor retry = new ButtonsActor(1);
		retry.setPosition(stage.getWidth()/2 + retry.getWidth()*0.1f, stage.getHeight()/2 - retry.getHeight()/2);
		retry.addListener(new ButtonsListener(retry, 1, game));
		stage.addActor(retry);
		
		ButtonsActor main = new ButtonsActor(3);
		main.setPosition(stage.getWidth()/2 - main.getWidth()*1.1f, stage.getHeight()/2 - main.getHeight()/2);
		main.addListener(new ButtonsListener(main, 2, game));
		stage.addActor(main);
		
		BestScoreAnimation best = new BestScoreAnimation();
		best.setPosition(stage.getWidth()/2 - best.getWidth()/2, 50);
		best.setVisible(false);
		stage.addActor(best);
		
		Texture gameovertxt = new Texture(Gdx.files.internal("gameovertxt.png"));
		Image GOtxt = new Image(gameovertxt);
		GOtxt.setOrigin(GOtxt.getWidth()/2, GOtxt.getHeight()/2);
		GOtxt.setScale(1.5f);
		GOtxt.setPosition(stage.getWidth()/2 - GOtxt.getWidth()/2, (float) (stage.getHeight() - GOtxt.getHeight()*1.2));
		stage.addActor(GOtxt);
		
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
		
		
	}
	
	@Override
	public void resize(int width, int height) {
		stage.setViewport(512, 700, true);
		
		game.score.setPosition(nowscore.getX() + (nowscore.getWidth()/4)*2.6f, nowscore.getY() + nowscore.getHeight()/2);
		game.maxima.setPosition(nowscore.getX() + nowscore.getWidth()*0.06f, nowscore.getY() + nowscore.getHeight()/2);
	}

}
