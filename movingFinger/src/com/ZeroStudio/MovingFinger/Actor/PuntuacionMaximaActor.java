package com.ZeroStudio.MovingFinger.Actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class PuntuacionMaximaActor extends Actor {
	
	private Preferences pref;
	private BitmapFont font; 

	public PuntuacionMaximaActor(BitmapFont font) {
		this.font=font;
		pref = Gdx.app.getPreferences("movingFinger-Pref");
		this.font.setColor(0.6f, 0.2f, 0f, 1f);
//		this.font.setScale(0.7f);
	}
	
	public void setPuntuacionMaxima(int puntos){
		pref.putInteger("Maxima-Puntuacion", puntos);
		pref.flush();
	}
	
	public int getPuntos(){
		int MaximaPuntos = pref.getInteger("Maxima-Puntuacion", 0);
		return MaximaPuntos;
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha){
		font.draw(batch, Integer.toString(getPuntos()), getX(), getY());
	}

}
