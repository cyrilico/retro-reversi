package feup.lpoo.reversi;

import feup.lpoo.reversi.model.GameModel;

public interface PlayServices {
        public void signIn();
        public void signOut();
        public void rateGame();
        public void unlockAchievement();
        public void matchCompleted(boolean victory);
        public void submitScore(int highScore);
        public void showAchievements();
        public void showScore();
        public boolean isSignedIn();
        public void checkGames();
        public void quickMatch();
        public GameModel getMatchData();
        public void takeTurn(GameModel data);
        public void takeLastTurn(GameModel data);
        public void finishMatch();
        public void rematch();
}
