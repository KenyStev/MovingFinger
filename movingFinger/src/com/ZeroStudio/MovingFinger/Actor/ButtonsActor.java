package com.ZeroStudio.MovingFinger.Actor;


import com.ZeroStudio.MovingFinger.MovingFinger;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Scaling;

public class ButtonsActor extends Image {
	
	int x;
	private Texture text;
	private TextureRegion[] pads;
	
	public ButtonsActor(int x) {
		this.x=x;
		text = MovingFinger.MANAGER.get("botones.png", Texture.class);
		pads = TextureRegion.split(text, 142, 142)[0];
		setSize(pads[x].getRegionWidth(), pads[x].getRegionHeight());
	}
	
	
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		batch.draw(pads[x], getX(), getY(), getOriginX(), getOriginY(), 
				getWidth(), getHeight(), getScaleX(), getScaleY(), 
				getRotation());
	}

}
