package com.ZeroStudio.MovingFinger.Actor;


import com.ZeroStudio.MovingFinger.MovingFinger;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

/****************************************************************************
 * Esta Clase es la encargada de crear el personaje del cubito,				*
 * aplicarle los recortes necesarios, a�adir el boalding box al personaje.	*
 * **************************************************************************/

public class BallActor extends Actor {
	
	/*********** VARIABLES DE INSTANCIA ***********/
	private Texture ballTexture;
	private TextureRegion ball;
	public Vector2 velocidad = new Vector2(0,0);
	public Rectangle bb;
	/**********************************************/

	/****** CONSTRUCTOR ******/
	public BallActor() {
		ballTexture = MovingFinger.MANAGER.get("ball"+MovingFinger.CHARACTER+".png", Texture.class);
		ballTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest); //Filtro para quitar Pixeliado
		ball = new TextureRegion(ballTexture, 60, 60);
		setSize(ball.getRegionWidth(), ball.getRegionHeight());
		setScale(1.12f);
		setOrigin(this.getWidth()/2, this.getHeight()/2);
		bb = new Rectangle(getX(), getY(), getWidth(), getHeight()); //crea el boalding box de la bola
	}
	
	
	
	private void checkPosition() {
		/****************************************************
		 * Metodo para Chequear la Posicion y Evitar que	*
		 * la bola salga de los limites del Esenario		*
		 * **************************************************/
		
		if(getX()<0){
			setX(0);
			velocidad.x=0;
		}else if(getRight() > getStage().getWidth()){
			setX(getStage().getWidth() - getWidth());
			velocidad.x = 0;
		}
		
		if(getY()<0){
			setY(0);
			velocidad.y=0;
		}else if(getTop() > getStage().getHeight()){
			setY(getStage().getHeight() - getHeight());
			velocidad.y = 0;
		}
	}

	@Override
	public void act(float delta){
		super.act(delta);
		translate(velocidad.x * delta, velocidad.y * delta);
		
		// asocia el bolding box de la bola
		// a su poscicion de origen y su tama�o
		bb.x=getX();
		bb.y=getY();
		bb.width=getWidth();
		bb.height=getHeight();
		
		checkPosition();
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha){
		super.draw(batch, parentAlpha);
		batch.draw(ball, getX(), getY(), getOriginX(), getOriginY(), 
				getWidth(), getHeight(), getScaleX(), getScaleY(), 
				getRotation());
	}

}
