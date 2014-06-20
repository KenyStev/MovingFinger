package com.ZeroStudio.MovingFinger.screen;

import java.util.ArrayList;
import java.util.List;


import com.ZeroStudio.MovingFinger.MovingFinger;
import com.ZeroStudio.MovingFinger.Actor.BallActor;
import com.ZeroStudio.MovingFinger.Actor.BallImage;
import com.ZeroStudio.MovingFinger.Actor.NuveActor;
import com.ZeroStudio.MovingFinger.Actor.PuntuacionActor;
import com.ZeroStudio.MovingFinger.Actor.SkullActor;
import com.ZeroStudio.MovingFinger.Listener.DragAndroidListener;
import com.ZeroStudio.MovingFinger.Listener.InputDesktopListener;
import com.ZeroStudio.MovingFinger.Listener.NuveListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL11;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/********************* PANTALLA DE JUEGO PRINCIPAL *********************/
/************************************************************************
 * Esta Es la Pantalla donde se controla toda la logica del Juego, 		*
 * Donde se Definen las Reglas, y practicamente la pantalla donde 		*
 * el Usuario comienza a jugar.											*
 * Aqui se establece el comportamiento de los personajes del juego,		*
 * y las reglas en que el juego se pierde.								*
 * @author Keny J. Stev (Zero Kull)										*
 * **********************************************************************/

public class GameplayScreen extends AbstractScreen {
	
	/*********** VARIABLES DE INSTANCIA ***********/
	private Stage stage;
	private BallActor ball;
	private float timer;
	private PuntuacionActor puntuacion;
	private TextureRegion font_region;
	private Music start, destroy;
	
	private List<NuveActor> nuves;
	private List<SkullActor> skulls, coins, incoins;
//	BallImage bi;
	/**********************************************/
	
	
	/***** CONSTRUCTOR DE LA PANTALLA DE JUEGO ******/
	public GameplayScreen(MovingFinger game) {
		super(game);
	}

	@Override
	public void show() {
		//Inicializamos nuestras Listas enlazadas
		skulls = new ArrayList<SkullActor>();
		coins = new ArrayList<SkullActor>();
		incoins = new ArrayList<SkullActor>();
		nuves = new ArrayList<NuveActor>();

		// Creamos un nuevo esenario y lo asociamos a la entrada
		stage = new Stage(512, 700, true, game.getSpriteBatch());
		Gdx.input.setInputProcessor(stage);

		// Creamos el sonidos de start y destroy
		start = MovingFinger.MANAGER.get("sounds/start.wav", Music.class);
		destroy = game.MANAGER.get("sounds/destroy.wav", Music.class);
		start.play();

		// Crear Fondo
		TextureRegion RegionFondo = new TextureRegion(MovingFinger.MANAGER.get(
				"fondo.png", Texture.class), 512, 1024);
		Image imgFondo = new Image(RegionFondo);
		stage.addActor(imgFondo);

		// Creamos una Nueva Bola
		ball = new BallActor();
		stage.setKeyboardFocus(ball);
		ball.addListener(new InputDesktopListener(ball));
		ball.setPosition(stage.getWidth() / 2 - ball.getWidth() / 2, 76);
		stage.setScrollFocus(ball);
		ball.addListener(new DragAndroidListener(ball));
		//le añadimos el boalding box a la bola (ball)
		ball.bb.x = ball.getX();
		ball.bb.y = ball.getY();
		stage.addActor(ball);

		createNuve();

		// Inicializamos Puntuacion
		Texture text = new Texture(Gdx.files.internal("fonts/Vectroid.png"));
		text.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		font_region = new TextureRegion(text);

		puntuacion = new PuntuacionActor(new BitmapFont(
				Gdx.files.internal("fonts/Vectroid.fnt"), font_region));
		puntuacion.setPosition(stage.getWidth() / 2, stage.getHeight() - 100);
		puntuacion.setPuntuacion(0);
		stage.addActor(puntuacion);

		// Inicializamos el contador de Tiempo
		timer = 2 + (float) Math.random();
	}
	
	@Override
	public void hide() {
		if(puntuacion.getPuntos()>game.maxima.getPuntos()){
			game.maxima.setPuntuacionMaxima(puntuacion.getPuntos());
		}
		game.pref.putInteger("pts-temp", puntuacion.getPuntos());
		game.pref.flush();
		
		Gdx.input.setInputProcessor(null);
	}

	@Override
	public void dispose() {
		stage.dispose();
	}
	
	@Override
	public void resize(int width, int height){
		//Escalar a la Pantalla
		stage.setViewport(512, 700, true);
		
		puntuacion.setPosition(stage.getWidth()/2 - puntuacion.getWidth()/2, stage.getHeight()-100);
	}



	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
		Gdx.gl.glClear(GL11.GL_COLOR_BUFFER_BIT);
	    
		stage.act();
		
		timer -= delta;
		if(timer<0){
			crearSkull(5, 0,0,0);
			
			if(puntuacion.getPuntos()>=100){
				crearSkull(-5, 0,0,0);
				crearSkull(10, 0,0,0);
				crearSkull(-10, 0,0,0);
				crearSkull(-1, 0,0,0);
			}else if(puntuacion.getPuntos()>=60){
				crearSkull(-5, 0,0,0);
				crearSkull(10, 0,0,0);
				crearSkull(-10, 0,0,0);
			}else if(puntuacion.getPuntos()>=40){
				crearSkull(-5, 0,0,0);
				crearSkull(10, 0,0,0);
				crearSkull(-10, 0,0,0);//
			}else if(puntuacion.getPuntos()>=20){
				crearSkull(0, 0,0,0);
				crearSkull(-5, 0,0,0);
				crearSkull(5, 0,0,0);
			}else if(puntuacion.getPuntos()>=0){
				crearSkull(0, 0,0,0);
				crearSkull(5, 0,0,0);
				crearSkull(-5, 0,0,0);//
			}
			
			timer = (float) (0.4 + (float)Math.random());
		}
		
		if(game.getCant()>0 && game.getInCoin()==1){
			crearSkull(0, game.getInCoin(),(int) game.getNuvePos().x, (int) game.getNuvePos().y);
			game.setCant(0);
		}
		
		if(game.getCant()>0 && game.getInCoin()==2){
			crearSkull(0, game.getInCoin(),(int) game.getNuvePos().x, (int) game.getNuvePos().y);
			game.setCant(0);
		}
		
		checkLists();
		checkColisiones();
		
		stage.draw();
		
	}



	/**
	 * Metodo para Disparar Crear Nuevas Calaberas
	 * Crea una Nueva Calabera en una posicion aleatoria del eje Y
	 * y segun 'select' define si es un define si es un 'coin', 'incoin' o un 'skull'
	 * y lo agrega a la lista correspondiente, siendo:
	 * 0 = skull; List(skulls);
	 * 1 = coin; List(coins);
	 * 2 = incoin; List(incoins);
	 * */
	
	private void crearSkull(int plusPos, int Select, int x, int y) {
		SkullActor skull = new SkullActor(Select); // Crea nueva calabera

		if (Select == 0) {

			// Aumenta la dificultad segun la puntuacion que tiene
			if (puntuacion.getPuntos() >= 100) {
				skull.setVelocidad(-710);// 700
			} else if (puntuacion.getPuntos() >= 50) {
				skull.setVelocidad(-650);// 650
			} else if (puntuacion.getPuntos() >= 20) {
				skull.setVelocidad(-625);// 600
			} else if (puntuacion.getPuntos() >= 10) {
				skull.setVelocidad(-600);// 575
			} else if (puntuacion.getPuntos() >= 0) {
				skull.setVelocidad(-550);
			}

			if (plusPos != -1) {
				skull.setPosition(
						(float) (0f * stage.getWidth() + 1f * stage.getWidth()
								* (float) Math.random() + plusPos),
						stage.getHeight());
			} else {
				skull.setPosition((float) ball.getX(), stage.getHeight());
			}
		} else {
			switch (Select) {
			case 1:
			case 2:
				skull.setVelocidad(-250);
				skull.setPosition(x, y);
				break;
			}
		}

		skull.bb.x = skull.getX();
		skull.bb.y = skull.getY();
		stage.addActor(skull);
		
		switch (Select) {
		case 1:
			coins.add(skull);
			break;
		case 2:
			incoins.add(skull);
			break;
		default:
			skulls.add(skull);
			break;
		}

	}
	
	/**
	 * Crea una Nueva nuve con las Caracteristicas necesarias y la asigna al esenario. 
	 * */
	private void createNuve(){
		NuveActor nuve;
		
		//genera pa posicion 'y' random de la nuve
		float y = (float) (0.5f * stage.getHeight() + 1f * stage.getHeight() * (float) Math.random());
		
		//genera un numero random para 'select' y asi evaluar el tipo de coin que llevara la nuve
		int type=0, select = (int)(10 * Math.random()); 
		
		//evalua 'select' para asignar a 'type' el tipo
		//de coin que llevara la nuve
		if(select >=8){
			type=2;
		}else if(select>2){
			type=0;
		}else if(select>=0){
			type=1;
		}
		
		//crea una nueva nuve con el coin especificado antes 'type'
		nuve = new NuveActor(type);
		
		if(y>(stage.getHeight() - nuve.getHeight()/2))
			y -= nuve.getHeight();
		
		/**
		 * asigna la posicion, velocidad y el Listener de la Nuve
		 * "setPosition(x,y)"; "setVelocidad(int x)"; "addListener(Image nuve, MovingFinger game, int type, int cant)".
		 * luego lo asigna al esenario
		 * */
		nuve.setPosition(0 - nuve.getWidth(), y);
		nuve.setVecolidad(100);
		stage.setKeyboardFocus(nuve);
		nuve.addListener(new NuveListener(nuve, game, nuve.getType(), nuve.getCant()));
		stage.addActor(nuve);
		nuves.add(nuve);
	}

	/**
	 * Revisa las Listas de las Calaberas y de las Nuves.
	 * La de las Calaberas para evaluar cuando salen de la pantalla, y asi removerlas de la lista y el esenario.
	 * La de las Nuves para evaluar cuando sale de la pantalla y asi removerla de la lista y el esenario, y crear una nueva.
	 * */
	private void checkLists() {
		// Ciclo para Eliminar Calaberas
		for (int i = 0; i < skulls.size(); i++) {
			if (skulls.get(i).getTop() < skulls.get(i).getHeight() * -2) {
				// Remover calabera al salir de la pantalla
				skulls.get(i).remove();
				skulls.remove(i);
				puntuacion.plus();
			}
		}

		// Ciclo para eliminar Nuves
		for (int i = 0; i < nuves.size(); i++) {
			if (nuves.get(i).getX() > stage.getWidth()) {
				// Remover nuve al salir de la pantalla
				nuves.get(i).remove();
				nuves.remove(i);
				// crea una nueva al eliminar la anterior
				createNuve();
			}
		}

	}


	/**
	 * Comprueba la Colision entre la bola y las Calaberas
	 * Si hay una Colision, entonces se pierde la partida (GameOver).
	 * */
	private void checkColisiones() {
		SkullActor skull, coin;
		for(int i=0; i < skulls.size(); i++){
			skull = skulls.get(i);
			if(skull.bb.overlaps(ball.bb)){
				//Colision skull-ball
				destroy.play();
				game.positionsActor.set(ball.getX(),skull.getX(), skull.getY());
				game.score.setPuntuacion(puntuacion.getPuntos());
				game.setScreen(game.GAMEOVER);
				
			}
		}
		
		/**
		 * Comprueba si existe una colicion entre un coin y la ball
		 * si la hay, entonces aumenta 10% de la puntuacion actual
		 * y luego remueve el coin.
		 * */
		for(int i=0; i < coins.size(); i++){
			coin = coins.get(i);
			if(coin.bb.overlaps(ball.bb)){
				//Colision coin-ball
				coins.get(i).remove();
				coins.remove(i);
				puntuacion.setPuntuacion((int)(puntuacion.getPuntos()+5f));
			}
		}
		
		/**
		 * Comprueba si existe una colicion entre un incoin y la ball
		 * si la hay, entonces disminulle 10% de la puntuacion actual;
		 * y luego remueve el incoin.
		 * */
		for(int i=0; i < incoins.size(); i++){
			coin = incoins.get(i);
			if(coin.bb.overlaps(ball.bb)){
				//Colision incoin-ball
				incoins.get(i).remove();
				incoins.remove(i);
				puntuacion.setPuntuacion((int)(puntuacion.getPuntos() - (int)(puntuacion.getPuntos()*0.2f)));
			}
		}
	}
}
