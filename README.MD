# **Feedbackopgave 7 - Diktatorernes hieraki**

## **Gruppemedlemmer:**
Jonas, Oliver, Valdemar og Kasper

## **Projektbeskrivelse:**
Dette projekt er en opgave, hvor vi har implementeret et hieraki af diktatorer. Vi har lavet en superklasse kaldet Leader, som indeholder de felter og metoder, som alle diktatorer har til fælles. Herefter har vi lavet to subklasser kaldet MilitaryDictator og PoliticalDictator, som nedarver fra superklassen Leader. Vi har også lavet et interface kaldet PowerActions, som indeholder metoder, som nogle af diktatorerne kan implementere.

## **Hvordan køres programmet:**
Programmet starter i vores Main-klasse, hvor vi kalder metoden createLeaders(); fra klassen LeaderManager. Metoden opretter en ArrayList, som indeholder objekter af typen Leader. Herefter oprettes der fire forskellige diktatorobjekter, som består af 2 MilitaryDictator og to PoliticalDictator, der alle tilføjes til listen. Og da alle diktatortyperne nedarver fra superklassen Leader kan vi samle dem i samme ArrayList.

Vi har tilføjet en ekstra diktator til ArrayList ved hjælp af metoden addLeader();. Her har vi oprettet en AIDictator.

Så kalder vi metoden performActions(); som gennemgår alle diktatorerne i listen, ved hjælp af en for-each løkke. For hvert objekt bliver metoderne printDetails(); og giveSpeech(); kaldt. Her bruger vi polymorfi, da de forskellige diktatortyper har deres egne versioner af metoderne. Da giveSpeech(); er en abstrakt metode i superklassen Leader, vil programmet automatisk udføre den version af metoden som passer til den enkelte diktatortype og det samme gælder printDetails(); for de subklasser der har overskrevet metoden.

Programmet kontrollerer også om den enkelte diktator implmenterer PowerActions interfacet og hvis diktatoren gør det så bliver metoderne implementPolicy(); , repressOpposition(); og holdParade(); også kaldt.

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
Implementering af instanceOf, samt at få det til at virke med vores arraylist.  
Forståelse af polymorfi og hvordan man kan bruge det i praksis.

## **Tests vi har lavet:**