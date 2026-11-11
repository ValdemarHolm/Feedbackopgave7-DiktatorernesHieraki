# **Feedbackopgave 7 - Diktatorernes hieraki**

## **Gruppemedlemmer:**
Jonas, Oliver, Valdemar og Kasper

## **Projektbeskrivelse:**
Vi har øvet os på at lave abstrakte klasser 

## **Hvordan køres programmet:**


## **Klasser vi har implementeret samt deres felter og metoder:**

### **Leader** superklassen:
#### Felter inde i **Leader** superklassen:
* `private String name;`
* `private String country;`
* `private int yearsInPower;`

#### Metoder inde i **Leader** superklassen:
* `public Leader(String name, String country, int yearsInPower);` (Konstruktør)
* `public String getName();`
* `public String getCountry();`
* `public int getYearsInPower();`
* `public void setName(String name);`
* `public void setCountry(String country);`
* `public void setYearsInPower(int yearsInPower);`
* `public abstract void giveSpeech();`
* `public void printDetails();`

### **PowerActions** interface:
#### Metoder inde i **PowerActions** interface:
* `public void implementPolicy();`
* `public void repressOpposition();`
* `public void holdParade();`

### **MilitaryDictator** subklassen:
#### Felter inde i **MilitaryDictator** subklassen:
* `private String name;` (Arvet fra Leader)
* `private String country;` (Arvet fra Leader)
* `private int yearsInPower;` (Arvet fra Leader)
* `private String militaryRank;`

#### Metoder inde i **MilitaryDictator** subklassen:
* `public MilitaryDictator(String name, String country, int yearsInPower, String militaryRank);` (Konstruktør)
* `@Override public void giveSpeech();` (Override af metode fra Leader)
* `@Override public void implementPolicy();` (Override af metode fra PowerActions)
* `@Override public void repressOpposition();` (Override af metode fra PowerActions)
* `@Override public void holdParade();` (Override af metode fra PowerActions)
* `@Override public void printDetails();` (Override af metode fra Leader)

### **PoliticalDictator** subklassen:
#### Felter inde i **PoliticalDictator** subklassen:
* `private String name;` (Arvet fra Leader)
* `private String country;` (Arvet fra Leader)
* `private int yearsInPower;` (Arvet fra Leader)
* `private String partyName;`

#### Metoder inde i **PoliticalDictator** subklassen:
* `public PoliticalDictator(String name, String country, int yearsInPower, String partyName);` (Konstruktør)
* `@Override public void giveSpeech();` (Override af metode fra Leader)
* `@Override public void implementPolicy();` (Override af metode fra PowerActions)
* `@Override public void repressOpposition();` (Override af metode fra PowerActions)
* `@Override public void holdParade();` (Override af metode fra PowerActions)
* `@Override public void printDetails();` (Override af metode fra Leader)

### **LeaderManager** klassen:

#### Metoder inde i **LeaderManager** klassen:
* `public static ArrayList<Leader> createLeaders();`
* `public static void addLeader(Leader newLeader, ArrayList<Leader> leaders);`
* `public static void performActions(ArrayList<Leader> leaders);`



## **Udfordringer ved projektet:**


## **Tests vi har lavet:**