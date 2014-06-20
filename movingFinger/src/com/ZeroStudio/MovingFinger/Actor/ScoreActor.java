package com.ZeroStudio.MovingFinger.Actor;


import com.ZeroStudio.MovingFinger.MovingFinger;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Scaling;

public class ScoreActor extends Image {

	private int puntuacion;
	
	private BitmapFont font;

	public ScoreActor(BitmapFont font) {
		this.font=font;
		this.font.setColor(1f, 0.4f, 0f, 1f);
//		font.setScale(2);
	}
	
	public void setPuntuacion(int puntos){
		puntuacion = puntos;
	}
	
	public void plus(int pts){
		puntuacion=pts;
	}
	
	public int getPuntos(){
		return puntuacion;
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha){
		font.draw(batch, Integer.toString(puntuacion), getX(), getY());
	}

}
