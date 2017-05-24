package feup.lpoo.reversi;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.games.Games;
import com.google.example.games.basegameutils.GameHelper;

public class AndroidLauncher extends AndroidApplication implements GameHelper.GameHelperListener, PlayServices {
	private GameHelper gameHelper;
    private int requestCode = -1;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		gameHelper = new GameHelper(this, GameHelper.CLIENT_GAMES);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new Reversi(this), config);
		gameHelper.setup(this);
	}

	@Override
	protected void onStart() {
		super.onStart();
		gameHelper.onStart(this);
	}

	@Override protected void onStop() {
		super.onStop();
		gameHelper.onStop();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		gameHelper.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void onSignInFailed() {

	}

	@Override
	public void onSignInSucceeded() {

	}

	@Override
	public void signIn() {
		try {
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					gameHelper.beginUserInitiatedSignIn();
				}
			});
		}
		catch (Exception e) {
			Gdx.app.log("MainActivity", "Log in failed: " + e.getMessage() + ".");
		}
	}

	@Override
	public void signOut() {
		try {
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					gameHelper.signOut();
				}
			});
		}
		catch (Exception e) {
			Gdx.app.log("MainActivity", "Log out failed: " + e.getMessage() + ".");
		}
	}

	@Override
	public void rateGame() {
		String str = "Your PlayStore Link";
		startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(str)));
	}

	@Override
	public void matchCompleted(boolean victory) {
		Games.Achievements.unlock(gameHelper.getApiClient(),
				getString(R.string.achievement_your_first_match));
		Games.Achievements.unlock(gameHelper.getApiClient(),
				getString(R.string.achievement_5_matches));
		Games.Achievements.unlock(gameHelper.getApiClient(),
				getString(R.string.achievement_25_matches));
		Games.Achievements.unlock(gameHelper.getApiClient(),
				getString(R.string.achievement_50_matches));

		if(victory) {
			Games.Achievements.unlock(gameHelper.getApiClient(),
					getString(R.string.achievement_10_wins));
			Games.Achievements.unlock(gameHelper.getApiClient(),
					getString(R.string.achievement_25_wins));
			Games.Achievements.unlock(gameHelper.getApiClient(),
					getString(R.string.achievement_50_wins));
		}
	}

	@Override
	public void unlockAchievement() {
		Games.Achievements.unlock(gameHelper.getApiClient(),
				getString(R.string.achievement_your_first_match));
	}

	@Override
	public void submitScore(int highScore) {
		if (isSignedIn())
			Games.Leaderboards.submitScore(gameHelper.getApiClient(),
					getString(R.string.leaderboard_easy), highScore);
	}

	@Override
	public void showAchievements() {
		if (isSignedIn())
			startActivityForResult(Games.Achievements.getAchievementsIntent(gameHelper.getApiClient()), 5001);
		else
			signIn();
	}

	@Override
	public void showScore() {
		if(isSignedIn())
			startActivityForResult(Games.Leaderboards.getLeaderboardIntent(gameHelper.getApiClient(),
					getString(R.string.leaderboard_easy)), requestCode);
		else
			signIn();
	}

	@Override
	public boolean isSignedIn() {
		return gameHelper.isSignedIn();
	}
}
