package com.ZeroStudio.MovingFinger;



import com.ZeroStudio.MovingFinger.Actor.PuntuacionMaximaActor;
import com.ZeroStudio.MovingFinger.Actor.ScoreActor;
import com.ZeroStudio.MovingFinger.screen.AbstractScreen;
import com.ZeroStudio.MovingFinger.screen.GameOverScreen;
import com.ZeroStudio.MovingFinger.screen.GameplayScreen;
import com.ZeroStudio.MovingFinger.screen.IntroScreen;
import com.ZeroStudio.MovingFinger.screen.LoadingScreen;
import com.ZeroStudio.MovingFinger.screen.MainScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/****************************************************************************************
 * Esta es la Clase principal del Juego </br>											*
 * aqui se cargan todos los assets necesarios para el juego, y se mandan a llamar</br>	*
 * la primer pantalla para empezar el Juego.											*
 * @author Kevin Javier Estevez (Keny Stev)												*
 * @author Zero Studio																	*
 * @coWork Rosalila Studio																*
 * @authorGrafico John Mackay de Rosalila Studio										*
 * @version 1.1																			*
 * @datePublic 2/Oct/2014																*
 * Este proyecto esta publicado bajo GPL, con sus cuarto libertades permitidas,			*
 * Respetendo lo descrito por la Free Software Fundation								*
 ****************************************************************************************/
public class MovingFinger extends Game{
	
	public static AssetManager MANAGER;
	private SpriteBatch sb; //change public to private
	public final AbstractScreen GAMEPLAY, GAMEOVER, LOADING, MAIN, INTRO;
	public PuntuacionMaximaActor maxima;
	public Preferences pref;
	public Vector2 positionsActor= new Vector2();
	public Vector2 positionsActor2= new Vector2();
	public ScoreActor score;
	public static int CHARACTER=0;
	public boolean thereAre=false;
	
	private Vector2 nuvePos= new Vector2();
	private int cant=0, type=0;
	
	public FunctionAds funcion;
	
//	private OrthographicCamera camera; //change new camera

	
	public MovingFinger(FunctionAds funcion){
		//instancia las cinco pantallas
		GAMEPLAY = new GameplayScreen(this);
		GAMEOVER = new GameOverScreen(this);
		LOADING = new LoadingScreen(this);
		MAIN = new MainScreen(this);
		INTRO = new IntroScreen(this);
		this.funcion=funcion;
	}

	@Override
	public void create() {
		funcion.prueba();
		
		sb = new SpriteBatch();
//		camera = new OrthographicCamera(); //change
		
		pref = Gdx.app.getPreferences("movingFinger-Pref-positions");
		
		maxima = new PuntuacionMaximaActor(new BitmapFont(Gdx.files.internal("fonts/ArialBlack.fnt")));
		score = new ScoreActor(new BitmapFont(Gdx.files.internal("fonts/ArialBlack.fnt")));
		
		//Cargando todos los Assets al Manager
		MANAGER = new AssetManager();
		
		MANAGER.load("zeroStudio.png", Texture.class);
		MANAGER.load("RosalilaStudio.png", Texture.class);
		MANAGER.load("John.png", Texture.class);
		MANAGER.load("gameover.png", Texture.class);
		MANAGER.load("ball0.png", Texture.class);
		MANAGER.load("ball1.png", Texture.class);
		MANAGER.load("ball2.png", Texture.class);
		MANAGER.load("ball3.png", Texture.class);
		MANAGER.load("ball4.png", Texture.class);
		MANAGER.load("skulls.png", Texture.class);
		MANAGER.load("nuve.png", Texture.class);
		MANAGER.load("fondo.png", Texture.class);
		MANAGER.load("botones.png", Texture.class);
		MANAGER.load("fonts/Digital2.fnt", BitmapFont.class);
		MANAGER.load("fonts/ArialBlack.fnt", BitmapFont.class);
		MANAGER.load("sounds/drag.wav", Music.class);
		MANAGER.load("sounds/destroy.wav", Music.class);
		MANAGER.load("sounds/start.wav", Music.class);
		MANAGER.load("sounds/moving_finger.ogg", Music.class);
		
		setScreen(LOADING);
	}
	
	/**
	 * Recupera la instancia compartida de SpriteBatch
	 * @return SpriteBatch en uso por el juego
	 */
	public SpriteBatch getSpriteBatch() {
		return sb;
	}
	
	public int getInCoin(){
		return type;
	}
	
	public void setInCoin(int type){
		this.type=type;
	}
	
	public int getCant(){
		return cant;
	}
	
	public void setCant(int cant){
		this.cant=cant;
	}
	
	public void setNuvPos(Vector2 pos){
		nuvePos.set(pos);
	}
	
	public Vector2 getNuvePos(){
		return nuvePos;
	}
	
	/**
	 * Recupera la instancia compartida de OrthograhpicCamera
	 * @return cámara en uso por el juego.
	 */
//	public OrthographicCamera getCamera() {
//		return camera;
//	}
	
	@Override
	public void resize(int width, int height) {
		super.resize(320, 640);	// llamada a super obligatoria
										// (no sabemos si el estado actual
										// podría necesitarlo)
//		getCamera().setToOrtho(false, width, height);
	}
	
	public void setS(Screen screen){
		this.getScreen().dispose();
		setScreen(screen);
	}
	
	@Override
	public void dispose(){
		super.dispose();
		MANAGER.dispose();
		getSpriteBatch().dispose();
		
//		GAMEPLAY.dispose();
//		GAMEOVER.dispose();
//		LOADING.dispose();
//		INTRO.dispose();
//		MAIN.dispose();
	}
	
}
