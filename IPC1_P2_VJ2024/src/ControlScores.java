import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ControlScores {
/////////////////////////////////////////////////////////////////////////////////////////////////////////////  

    private static final int MAX_SCORES = 5;
    private static final String SCORES_FILE = Paths.get(System.getProperty("user.dir"), "scores.txt").toString();
    private static ArrayList<Score> scores = new ArrayList<>();
////////////////////////////////////////////////////////////////////////////////////////////////////////////    
    static {
        loadScores();
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////    
    
    public static void addScore(String name, int score) {
        scores.add(new Score(name, score));
        Collections.sort(scores, Comparator.comparingInt(Score::getScore).reversed());
        if (scores.size() > MAX_SCORES) {
            scores.remove(scores.size() - 1);
        }
        saveScores();
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////   
    
    public static ArrayList<Score> getScores() {
        return new ArrayList<>(scores);
    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////

    private static void saveScores() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(SCORES_FILE))) {
            for (Score score : scores) {
                writer.println(score.getName() + "," + score.getScore());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////

    private static void loadScores() {
        try (BufferedReader reader = new BufferedReader(new FileReader(SCORES_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String name = parts[0];
                    int score = Integer.parseInt(parts[1]);
                    scores.add(new Score(name, score));
                }
            }
            Collections.sort(scores, Comparator.comparingInt(Score::getScore).reversed());
        } catch (IOException e) {
            e.printStackTrace();
        }     
    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    public static class Score {
        private String name;
        private int score;
    
        public Score(String name, int score) {
            this.name = name;
            this.score = score;
        }
    
        public String getName() {
            return name;
        }
    
        public int getScore() {
            return score;
        }
    
        @Override
        public String toString() {
            return name + " - " + score;
        }
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////
}
