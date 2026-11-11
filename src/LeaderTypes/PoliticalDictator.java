package LeaderTypes;

public class PoliticalDictator extends Leader implements PowerActions {
    private String partyName;

    public PoliticalDictator(String name, String country, int yearsInPower, String partyName) {
        super(name, country, yearsInPower);
        this.partyName = partyName;
    }

    @Override
    public void giveSpeech() {
        System.out.println("Political dictator " + getName() + " is giving a political speech: 'Our country is the strongest democracy in the world! All abroad people are jealous of our glorious political system!'");
    }

    @Override
    public void implementPolicy() {
        System.out.println("Political dictator " + getName() + " is implementing political policies, much to the horror of his opposition");
    }

    @Override
    public void repressOpposition() {
        System.out.println("Political dictator " + getName() + " is politically repressing his opposition");
    }

    @Override
    public void holdParade() {
        System.out.println("Political dictator " + getName() + " is holding a political parade");
    }

    @Override
    public void printDetails() {
        super.printDetails();
        System.out.println("Political party: " + partyName);
    }
}
