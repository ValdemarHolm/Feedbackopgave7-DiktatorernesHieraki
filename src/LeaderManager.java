import LeaderTypes.Leader;
import LeaderTypes.MilitaryDictator;
import LeaderTypes.PoliticalDictator;
import LeaderTypes.PowerActions;
import java.util.ArrayList;

public class LeaderManager {
    public static ArrayList<Leader> createLeaders() {
        MilitaryDictator videla = new MilitaryDictator("Jorge Rafael Videla", "Argentina", 5, "Lieutenant General");
        PoliticalDictator tito = new PoliticalDictator("Josip Tito", "Yugoslavia", 35, "League of Communists of Yugoslavia");
        MilitaryDictator pinochet = new MilitaryDictator("Augusto José Ramón Pinochet Ugarte", "Chile", 17, "Captain General");
        PoliticalDictator mao = new PoliticalDictator("Mao Zedong", "China", 27, "China's Communist Party");

        ArrayList<Leader> leaders = new ArrayList<>();
        leaders.add(videla);
        leaders.add(tito);
        leaders.add(pinochet);
        leaders.add(mao);

        return leaders;
    }

    public static void addLeader(Leader newLeader, ArrayList<Leader> leaders) {
        leaders.add(newLeader);
    }

    public static void performActions(ArrayList<Leader> leaders) {
        for (Leader leader : leaders) {
            leader.printDetails();
            leader.giveSpeech();
            if (leader instanceof PowerActions) {
                ((PowerActions) leader).implementPolicy();
                ((PowerActions) leader).repressOpposition();
                ((PowerActions) leader).holdParade();
            }
            System.out.println();
        }
    }
}
