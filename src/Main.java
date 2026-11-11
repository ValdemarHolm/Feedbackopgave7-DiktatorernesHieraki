import LeaderTypes.AIDictator;
import LeaderTypes.Leader;
import LeaderTypes.MilitaryDictator;
import LeaderTypes.PoliticalDictator;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Leader> leaders = LeaderManager.createLeaders();

        LeaderManager.addLeader(new AIDictator("Sam Altman", "USA", 11, "OpenAI", "ChatGPT"), leaders);
        LeaderManager.performActions(leaders);
    }
}
