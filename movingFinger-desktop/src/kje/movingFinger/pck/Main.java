package kje.movingFinger.pck;

import com.ZeroStudio.MovingFinger.MovingFinger;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Moving Finger";
		cfg.useGL20 = false;
		cfg.width = 420;
		cfg.height = 640;
		cfg.resizable = false;
		
		new LwjglApplication(new MovingFinger(), cfg);
	}
}
