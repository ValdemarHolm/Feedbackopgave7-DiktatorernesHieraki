package LeaderTypes;

public class MilitaryDictator extends Leader implements PowerActions {
    private String militaryRank;

    public MilitaryDictator(String name, String country, int yearsInPower, String militaryRank) {
        super(name, country, yearsInPower);
        this.militaryRank = militaryRank;
    }

    @Override
    public void giveSpeech() {
        System.out.println("Military dictator " + getName() + " is giving a military speech: 'Our military might is the greatest in the world! All will tremble in fear of us!'");
    }

    @Override
    public void implementPolicy() {
        System.out.println("Military dictator " + getName() + " the " + militaryRank + " is implementing military policies, giving himself more power!");
    }

    @Override
    public void repressOpposition() {
        System.out.println("Military dictator " + getName() + " is repressing the opposition of the military policies");
    }

    @Override
    public void holdParade() {
        System.out.println("Military dictator " + getName() + " is holding a glorious military parade (not performative at all, wink wink)");
    }

    @Override
    public void printDetails() {
        super.printDetails();
        System.out.println("Military rank: " + militaryRank);
    }
}
