package com.ZeroStudio.MovingFinger.Actor;


import com.ZeroStudio.MovingFinger.MovingFinger;
import com.ZeroStudio.MovingFinger.Listener.DragAndroidListener;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class BallImage extends Image{
	public Rectangle bb;
	
	public BallImage() {
		super(MovingFinger.MANAGER.get("balls.png", Texture.class));
		addListener(new DragAndroidListener(this));
		bb = new Rectangle(getX(), getY(), getWidth(), getHeight()); //crea el boalding box de la bola
	}

	@Override
	public void act(float delta){
		super.act(delta);
		
		bb.x=getX();
		bb.y=getY();
		bb.width=getWidth();
		bb.height=getHeight();
	}
	
}
