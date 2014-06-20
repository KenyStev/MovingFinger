package com.ZeroStudio.MovingFinger;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Scaling;

public class IntroAnimation extends Image {

	public IntroAnimation() {
		super(new AnimationDrawable(8, 1, 256, 512, 0,"animation.png", 0.4f));
		((AnimationDrawable) this.getDrawable()).animateRow(0,true);
	}

	@Override
	public void act(float delta)
	{
		if(isVisible())
		{
			((AnimationDrawable) this.getDrawable()).act(delta);
			super.act(delta);
		}
	}

}
