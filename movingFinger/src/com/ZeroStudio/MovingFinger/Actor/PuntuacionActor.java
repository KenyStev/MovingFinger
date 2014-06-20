package com.ZeroStudio.MovingFinger.Actor;


import com.ZeroStudio.MovingFinger.MovingFinger;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class PuntuacionActor extends Actor {
	
	private int puntuacion;
	private int cont = 0;
	private Music score = MovingFinger.MANAGER.get("sounds/drag.wav", Music.class);
	
	private BitmapFont font;

	public PuntuacionActor(BitmapFont font) {
		this.font=font;
		this.font.setColor(1f, 0.4f, 0f, 1f);
		font.setScale(1.5f);
	}
	
	public void setPuntuacion(int puntos){
		puntuacion = puntos;
	}
	
	public void plus(){
		cont++;
		if(cont==5){
			score.play();
			puntuacion++;
			cont=0;
		}
	}
	
	public int getPuntos(){
		return puntuacion;
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha){
		font.draw(batch, Integer.toString(puntuacion), getX(), getY());
	}

}
