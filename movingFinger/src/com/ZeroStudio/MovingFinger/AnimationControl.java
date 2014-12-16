package com.ZeroStudio.MovingFinger;

import static com.ZeroStudio.MovingFinger.AnimationControl.aceleracion;
import static com.ZeroStudio.MovingFinger.AnimationControl.velocidad;

import com.badlogic.gdx.Gdx;

public class AnimationControl {
	/**********************************************/
	public static boolean UP=false, DOWN=true;
	public static float velocidad = -500;
	public static float aceleracion = 0;
	
	public static void acelerar() {
		if (aceleracion <= 240f)
			aceleracion += 60f;
	}

	public static void marchaAtras() {
		if (aceleracion >= -240f)
			aceleracion -= 60f;
	}
	
	public static float actualizarPosicion() {
		float posicion = 0;
		float tiempo = Gdx.graphics.getDeltaTime();
		velocidad += aceleracion * tiempo;
		posicion += velocidad * tiempo + 0.5 * aceleracion * (tiempo * tiempo);
		return posicion;
	}
}
