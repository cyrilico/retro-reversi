package feup.lpoo.reversi;

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
        public String getMatchData();
        public void takeTurn(String data);
}
