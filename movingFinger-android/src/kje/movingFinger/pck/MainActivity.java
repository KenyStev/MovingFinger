package kje.movingFinger.pck;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.ZeroStudio.MovingFinger.FunctionAds;
import com.ZeroStudio.MovingFinger.MovingFinger;
import com.badlogic.gdx.backends.android.AndroidApplication;
//import com.google.android.gms.ads.*;


public class MainActivity extends AndroidApplication implements FunctionAds {
//	private AdView adView;

	// Admob
//	private InterstitialAd interstitial;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Create the layout
		RelativeLayout layout = new RelativeLayout(this);
		//
		// Do the stuff that initialize() would do for you
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getWindow().clearFlags(
				WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);

//		// Admob
//	    // Create the interstitial.
//	    interstitial = new InterstitialAd(this);
//	    interstitial.setAdUnitId("ca-app-pub-6122860219631416/4909180685");
//
//	    // Create ad request.
//	    AdRequest adRequest = new AdRequest.Builder().build();
//
//	    // Begin loading your interstitial.
//	    interstitial.loadAd(adRequest);


		// Create the libgdx View
		View gameView = initializeForView(new MovingFinger(this), false);

		// // Create and setup the AdMob view
		// adView = new AdView(this, AdSize.BANNER, "a15376a32d290be"); // Put
		// in your secret key here
		// adView.loadAd(new AdRequest());
		//
		// Add the libgdx view
		layout.addView(gameView);
		//
		// // Add the AdMob view
		// RelativeLayout.LayoutParams adParams =
		// new
		// RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
		// RelativeLayout.LayoutParams.WRAP_CONTENT);
		// adParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		// adParams.addRule(RelativeLayout.CENTER_IN_PARENT);
		// //ALIGN_PARENT_LEFT
		//
		// layout.addView(adView, adParams);
		//
		// Hook it all up
		setContentView(layout);
	}

	@Override
	public void prueba() {
//		runOnUiThread(new Runnable() {
//			@Override
//			public void run() {
//			displayInterstitial();
//			}
//			});
	}

	@Override
	public void displayInterstitial() {
//		if (interstitial.isLoaded()) {
//			interstitial.show();
//			// Create the interstitial.
//			interstitial = new InterstitialAd(this);
//			interstitial.setAdUnitId("ca-app-pub-6122860219631416/4909180685");
//			// Create ad request.
//			AdRequest adRequest = new AdRequest.Builder().build();
//			// Begin loading your interstitial.
//			interstitial.loadAd(adRequest);
//		} else {
//			Log.e("My AdMob", "Ad not loaded");
//		}
	}

}