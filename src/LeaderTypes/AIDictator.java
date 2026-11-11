package LeaderTypes;

public class AIDictator extends Leader {
    private String companyName;
    private String companyAI;

    public AIDictator(String name, String country, int yearsInpower, String companyName, String companyAI) {
        super(name, country, yearsInpower);
        this.companyName = companyName;
        this.companyAI = companyAI;
    }

    @Override
    public void giveSpeech() {
        System.out.println("AI dictator " + getName() + " is giving a speech: 'Nobody should have RAM at home, we need it all in the datacenters!'");
    }


}
