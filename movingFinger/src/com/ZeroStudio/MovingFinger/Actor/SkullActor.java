package com.ZeroStudio.MovingFinger.Actor;


import com.ZeroStudio.MovingFinger.MovingFinger;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Esta Clase se encarga de Crear las calaberas y coins segun el indice que se le pase en los
 * argumentos del constructor; tiene todos los datos que necesita la skull
 * @author Keny J. Stev (Zero Kull)
 * */

public class SkullActor extends Actor {
	
	/*********** VARIABLES DE INSTANCIA ***********/
	private int x;
	private Texture skullTexture;
	private TextureRegion[] skull;
	public Rectangle bb;
	private Vector2 velocidad = new Vector2(0,0);
	/**********************************************/

	/****** CONSTRUCTOR ******/
	public SkullActor(int x) {
		this.x=x;
		skullTexture = MovingFinger.MANAGER.get("skulls.png", Texture.class);
//		skullTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear); //Filtro para quitar Pixeliado
		skull = TextureRegion.split(skullTexture, 45, 45)[0];
		setSize(skull[x].getRegionWidth(), skull[x].getRegionHeight());
		setOrigin(getWidth()/2, getHeight()/2);
//		setScale(1.2f);
		bb = new Rectangle(getX(), getY(), getWidth(), getHeight()); //crea el boalding box de la calabera
	}
	
	@Override
	public void act(float delta){
		translate(0, velocidad.y * delta);
		
		// asocia el bolding box de la calabera
		// a su poscicion de origen y su tamaño
		bb.x=getX();
		bb.y=getY();
		bb.width=getWidth();
		bb.height=getHeight();
	}
	
	/* *
	 * setea la velocidad a la que se movera la calabera
	 * identificando si la aplicacion es de tipo Desktop o Android
	 * y asigando una rotacion para cuando es de tipo android
	 * */
	public void setVelocidad(float velocidadY){
		velocidad.y=velocidadY;
		
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha){
		batch.draw(skull[x], getX(), getY(), getOriginX(), getOriginY(), 
				getWidth(), getHeight(), getScaleX(), getScaleY(), 
				getRotation());
	}

}
