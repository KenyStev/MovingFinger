package com.ZeroStudio.MovingFinger.Actor;


import com.ZeroStudio.MovingFinger.MovingFinger;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * Esta Clase Corresponde a la creacion de las nves que salen aleatoriamente en la pantalla
 * */

public class NuveActor extends Image {

	/***VARIABLES DE INSTANCIA***/
	private Texture nuveText;
	private TextureRegion Nuve;
	private Vector2 velocidad = new Vector2(0, 0);
	private int type=0, cant = 1;
	/***********************************************/
	
	/*CONSTRUCTOR DE LAS NUVES*/
	public NuveActor(int type) {
		this.type=type;
		nuveText = MovingFinger.MANAGER.get("nuve.png", Texture.class); //toma la Textura
		Nuve = new TextureRegion(nuveText, 160, 96); //recorta la Textura
		setSize(Nuve.getRegionWidth(), Nuve.getRegionHeight()); //toma el tama�o del Recorte
		
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		translate(velocidad.x * delta, 0); //Asigna el Movimiento segun la Velocidad
		
		
	}
	
	//Metodo para Setear la Velocidad en que se moveran las nuves
	public void setVecolidad(int velocidad){
		this.velocidad.x=velocidad;
	}

	//Dibuja la nuve
	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		batch.draw(Nuve, getX(), getY(), getOriginX(), getOriginY(),
				getWidth(), getHeight(), getScaleX(), getScaleY(),
				getRotation());
	}
	
	//procedimiento que asigna el tipo de coin que llevara la nuve
	public void setType(int type){
		this.type=type;
	}
	
	/**
	 * Retorna el entero del tipo de coin que lleva la nuve
	 * @return tipo de coin que lleva la nuve
	 * */
	public int getType(){
		return type;
	}
	
	/**
	 * Retorna el entero de la cantidad de coins que lleva la nuve
	 * @return cantidad de coins que lleva la nuve
	 * */
	public int getCant(){
		return cant;
	}

}
