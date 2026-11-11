package LeaderTypes;

public abstract class Leader {
    private String name;
    private String country;
    private int yearsInPower;

    public Leader(String name, String country, int yearsInPower) {
        this.name = name;
        this.country = country;
        this.yearsInPower = yearsInPower;
    }

    public String getName() {
        return name;
    }
    public String getCountry() {
        return country;
    }

    public int getYearsInPower() {
        return yearsInPower;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setYearsInPower(int yearsInPower) {
        this.yearsInPower = yearsInPower;
    }

    public abstract void giveSpeech();

    public void printDetails() {
        System.out.println("----------------------------------------------");
        System.out.println("-------------Diktator information-------------");
        System.out.println("----------------------------------------------");
        System.out.println("Name: " + name);
        System.out.println("Country: " + country);
        System.out.println("Years in power: " + yearsInPower);
    }

}
