package com.ZeroStudio.MovingFinger.Listener;


import com.ZeroStudio.MovingFinger.MovingFinger;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class NuveListener extends InputListener {
	
	/***VARIABLES DE INSTANCIA***/
	private MovingFinger game;
	private int type=0, cant=0;
	private Image nuve;
	private Vector2 nuvePos = new Vector2();
	/***************************************/

	/*CONSTRUCTOR DEL LISTENER DE LAS NUVES*/
	public NuveListener(Image nuve, MovingFinger game, int type, int cant) {
		this.nuve=nuve;
		this.game=game;
		this.type=type;
		this.cant=cant;
		
	}
	
	/**
	 * Si es tocada, setea en la variable global cant, la cantidad de 1,
	 * y el tipo de coin, que lo toma de la propia nuve.
	 * setea tambien en la variable global de tipo vector NuvePos la posicion en 
	 * que sera creado el coin, segun la posicion de la nuve en el momento que fue tocada.
	 * */
	@Override
	public boolean touchDown(InputEvent event, float x, float y, int pointer,
			int button) {
		nuvePos.set(nuve.getX() + nuve.getWidth()/2, nuve.getY() + nuve.getHeight()/2);
		game.setNuvPos(nuvePos);
		game.setCant(cant);
		game.setInCoin(type);
		nuve.setColor(Color.CYAN);
		
			return true;
	}
	
	@Override
	public void touchUp(InputEvent event, float x, float y, int pointer,
			int button) {
		nuve.setColor(Color.WHITE);
		cant=0;
		super.touchUp(event, x, y, pointer, button);
	}
	
	@Override
	public boolean keyDown(InputEvent event, int keycode) {
		switch(keycode){
		case Input.Keys.SPACE:
			nuvePos.set(nuve.getX() + nuve.getWidth()/2, nuve.getY() + nuve.getHeight()/2);
			game.setNuvPos(nuvePos);
			game.setCant(cant);
			game.setInCoin(type);
			nuve.setColor(Color.CYAN);
			break;
		}
		return true;
	}
	
	@Override
	public boolean keyUp(InputEvent event, int keycode) {
		switch (keycode) {
		case Input.Keys.SPACE:
			nuve.setColor(Color.WHITE);
			cant = 0;
			break;
		}
		return super.keyUp(event, keycode);
	}
}
