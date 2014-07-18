package com.ZeroStudio.MovingFinger.screen;

import java.util.ArrayList;
import java.util.List;

import com.ZeroStudio.MovingFinger.IntroAnimation;
import com.ZeroStudio.MovingFinger.MovingFinger;
import com.ZeroStudio.MovingFinger.Actor.BallActor;
import com.ZeroStudio.MovingFinger.Actor.SkullActor;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class IntroScreen extends AbstractScreen {
	
	private IntroAnimation intro;
	private Stage stage;
	
	private BallActor ball;
	private float timer;
	
	private List<SkullActor> skulls;

	public IntroScreen(MovingFinger game) {
		super(game);
		
	}

	@Override
	public void show() {
		
		stage = new Stage(512, 700, true, game.getSpriteBatch());
		Gdx.input.setInputProcessor(stage);
		
		skulls = new ArrayList<SkullActor>();
		
		// Crear Fondo
		TextureRegion RegionFondo = new TextureRegion(MovingFinger.MANAGER.get(
				"fondo.png", Texture.class), 512, 1024);
		Image imgFondo = new Image(RegionFondo);
		stage.addActor(imgFondo);
		
		ball = new BallActor();
		ball.setPosition(stage.getWidth() / 2 - ball.getWidth() / 2, 76);
		stage.addActor(ball);
		
//		intro = new IntroAnimation();
//		intro.setPosition(stage.getWidth()/2 - intro.getWidth()/2, 0);
//		stage.addActor(intro);
		
		// Inicializamos el contador de Tiempo
		timer = 2 + (float) Math.random();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		stage.act();
		if(Gdx.input.isTouched()){
			game.setScreen(game.MAIN);
		}
		
		checkTime(delta);
		moveBall();
		checkList();
		checkColisiones();
		
		stage.draw();
	}

	@Override
	public void hide() {
		stage.clear();
		//porque se traba?
		skulls.clear();
		
		Gdx.input.setInputProcessor(null);
	}

	@Override
	public void dispose() {
		stage.dispose();
	}
	
	@Override
	public void resize(int width, int height) {
		stage.setViewport(512, 700, true);//256,460
	}
	
	private void crearSkull(int plusPos, int Select, int x, int y) {
		SkullActor skull = new SkullActor(Select); // Crea nueva calabera

		if (Select == 0) {
				skull.setVelocidad(-500);

			if (plusPos != -1 && plusPos != -2 && plusPos != -3 && plusPos != -4) {
				skull.setPosition(
						(float) (0f * stage.getWidth() + 1f * stage.getWidth()
								* (float) Math.random() + plusPos),
						stage.getHeight());
			} else {
				if(plusPos==-1){
					skull.setPosition((float) ball.getX() + ball.getWidth()/2 + 2, stage.getHeight());
				}else if (plusPos == -2) {
					skull.setPosition((float) ball.getX() + 2, stage.getHeight());
				}else if (plusPos == -3 || plusPos == -4) {
					skull.setPosition(x, y);
				}
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
		skulls.add(skull);
		stage.addActor(skull);

	}
	
	private void checkTime(float delta) {
		timer -= delta;
		if(timer<0){
			crearSkull(-3, 0,0,(int)stage.getHeight());
			crearSkull(-4, 0,(int)(stage.getWidth() - skulls.get(0).getWidth()),(int)stage.getHeight());
			int n = (int) (3 * Math.random());
			if(n==1){
				crearSkull(-1, 0, 0, 0);
			}else if(n==2){
				crearSkull(-2, 0, 0, 0);
			}else{
				crearSkull(10, 0, 0, 0);
			}	
			timer = (float) (0.4 + (float)Math.random());
		}
	}
	
	private void checkList(){
		// Ciclo para Eliminar Calaberas
		for (int i = 0; i < skulls.size(); i++) {
			if (skulls.get(i).getTop() < skulls.get(i).getHeight() * -2) {
				// Remover calabera al salir de la pantalla
				skulls.get(i).remove();
				skulls.remove(i);
			}
		}
	}
	
	private void moveBall() {
		SkullActor skull;
		for(int i=0; i < skulls.size(); i++){
			skull = skulls.get(i);
			float xS = skull.getX() + skull.getWidth()/2;
			float xB = ball.getX() + ball.getWidth()/2;
			
			if (skull.getY() < stage.getHeight() / 2) {
				if (xS > ball.getX() && xS < ball.getRight() && xS > xB) {
//				 ball.velocidad.x=-550;
					ball.setPosition(xS - ball.getWidth() * 1.5f, ball.getY());// Bueno!
				} else if (xS < ball.getRight() && xS > ball.getX() && xS < xB) {
//				 ball.velocidad.x=550;
					ball.setPosition(xS + ball.getWidth(), ball.getY());// Bueno!
				} else if(skull.getX() > ball.getRight() || skull.getRight() < ball.getX()){
//					ball.velocidad.x = 0;
				}
			}
			
		}
	}
	
	private void checkColisiones() {
		SkullActor skull, coin;
		for(int i=0; i < skulls.size(); i++){
			skull = skulls.get(i);
			if(skull.bb.overlaps(ball.bb)){
				//Colision skull-ball
				game.setScreen(game.INTRO);
				
			}
		}
	}
}
