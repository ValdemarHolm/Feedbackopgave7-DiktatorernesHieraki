# **TESTREADME - Feedbackopgave 7: Diktatorernes hierarki**

Dette dokument beskriver den samlede teststrategi, de udførte enhedstests, polymorfitests samt alle afdækkede edge cases (grænsetilfælde) for systemet.

Testsuiten er implementeret som en selvstændig, kørbar testklasse i [`test/SystemTest.java`](test/SystemTest.java). Den kræver ingen eksterne biblioteker (som Maven/Gradle eller JUnit) og kan køres direkte på enhver standard Java-installation (testet på Java 27).

---

## **1. Hvordan køres testene?**

### **I IntelliJ IDEA:**
1. Højreklik på mappen `test` i projektvisningen (hvis den ikke allerede er markeret som *Test Sources Root*, vælg *Mark Directory as -> Test Sources Root*).
2. Åbn filen [`test/SystemTest.java`](test/SystemTest.java).
3. Klik på den grønne **Run**-knap ud for `public static void main(String[] args)`.

### **Via terminal / PowerShell:**
Kør følgende kommandoer fra projektets rodmappe:

```powershell
# 1. Kompiler kildekode og tests
javac -d out/production/Feedbackopgave -cp src src/LeaderTypes/*.java src/*.java test/*.java

# 2. Kør testsuiten
java -cp "out/production/Feedbackopgave;test;src" SystemTest
```

---

## **2. Testresultater - Hovedoversigt**

| Testkategori | Antal tests | Bestået (PASS) | Fejlet (FAIL) | Edge Cases / Obs |
| :--- | :---: | :---: | :---: | :---: |
| **1. Klassehierarki & Polymorfi** | 13 | 13 | 0 | 0 |
| **2. LeaderManager kernefunktionalitet** | 10 | 10 | 0 | 0 |
| **3. Edge Cases & Grænsebetingelser** | 5 | 5 | 0 | 4 |
| **4. Datavalidering & Værdigrænser** | 16 | 16 | 0 | 2 |
| **5. Design- & Arkitektur-observationer** | 2 | 2 | 0 | 4 |
| **TOTAL** | **46** | **46** | **0** | **10** |

---

## **3. Detaljerede testtabeller for alle 46 tests**

### **Kategori 1: Klassehierarki & Polymorfi (13 tests)**
Tester at arv, interface-implementeringer og dynamisk metodekald (polymorfi) fungerer efter hensigten.

| # | Testnavn | Testede værdier / Input | Forventet resultat | Faktisk observeret resultat | Status |
| :-: | :--- | :--- | :--- | :--- | :-: |
| **01** | `MilitaryDictator instanceof Leader` | `new MilitaryDictator("M. Test", "Country A", 5, "General")` | `true` (arver fra `Leader`) | Objekt er instans af `Leader` | ✅ PASS |
| **02** | `MilitaryDictator implements PowerActions` | Samme objekt som ovenfor | `true` (implementerer interface) | Objekt er instans af `PowerActions` | ✅ PASS |
| **03** | `PoliticalDictator instanceof Leader` | `new PoliticalDictator("P. Test", "Country B", 10, "Party X")` | `true` (arver fra `Leader`) | Objekt er instans af `Leader` | ✅ PASS |
| **04** | `PoliticalDictator implements PowerActions` | Samme objekt som ovenfor | `true` (implementerer interface) | Objekt er instans af `PowerActions` | ✅ PASS |
| **05** | `AIDictator instanceof Leader` | `new AIDictator("AI. Test", "Country C", 2, "Company Y", "AI Model Z")` | `true` (arver fra `Leader`) | Objekt er instans af `Leader` | ✅ PASS |
| **06** | `AIDictator !(instanceof PowerActions)` | Samme objekt som ovenfor | `true` (må IKKE have interfacet) | Objekt implementerer ikke `PowerActions` | ✅ PASS |
| **07** | `Military giveSpeech() polymorfi` | `Leader l = military; l.giveSpeech();` | Tale indeholder *"military speech"* | `"Our military might is the greatest..."` | ✅ PASS |
| **08** | `Political giveSpeech() polymorfi` | `Leader l = political; l.giveSpeech();` | Tale indeholder *"political speech"* | `"Our country is the strongest democracy..."` | ✅ PASS |
| **09** | `AIDictator giveSpeech() polymorfi` | `Leader l = ai; l.giveSpeech();` | Tale indeholder *"Nobody should have RAM"* | `"Nobody should have RAM at home..."` | ✅ PASS |
| **10** | `Military printDetails() subklassefelt` | `Leader l = military; l.printDetails();` | Udskriver `"Military rank: Captain General"` | `"Military rank: Captain General"` udskrives | ✅ PASS |
| **11** | `Military printDetails() superkald` | Samme kald som ovenfor | Udskriver grundlæggende info (`Name: Pinochet`) | `"Name: Pinochet"` udskrives via `super` | ✅ PASS |
| **12** | `Political printDetails() subklassefelt`| `Leader l = political; l.printDetails();` | Udskriver `"Political party: CPC"` | `"Political party: CPC"` udskrives | ✅ PASS |
| **13** | `Political printDetails() superkald` | Samme kald som ovenfor | Udskriver grundlæggende info (`Name: Mao`) | `"Name: Mao"` udskrives via `super` | ✅ PASS |

---

### **Kategori 2: LeaderManager kernefunktionalitet (10 tests)**
Tester at initialisering af listen, tilføjelse af ledere samt handlingsforløbet i `performActions()` fungerer korrekt.

| # | Testnavn | Testede værdier / Input | Forventet resultat | Faktisk observeret resultat | Status |
| :-: | :--- | :--- | :--- | :--- | :-: |
| **14** | `createLeaders() non-null` | `LeaderManager.createLeaders()` | Returnerer initialiseret liste | Liste er ikke-null | ✅ PASS |
| **15** | `createLeaders() størrelse` | Samme returværdi | Præcis 4 elementer i listen | `leaders.size() == 4` | ✅ PASS |
| **16** | `createLeaders() militære diktatorer` | Gennemløb af `leaders` med `instanceof` | Præcis 2 `MilitaryDictator` | 2 militære diktatorer fundet | ✅ PASS |
| **17** | `createLeaders() politiske diktatorer` | Gennemløb af `leaders` med `instanceof` | Præcis 2 `PoliticalDictator` | 2 politiske diktatorer fundet | ✅ PASS |
| **18** | `addLeader() udvider liste` | `LeaderManager.addLeader(ai, leaders)` | Listens størrelse øges fra 4 til 5 | `leaders.size() == 5` | ✅ PASS |
| **19** | `addLeader() korrekt placering` | `leaders.get(leaders.size() - 1)` | Det sidst tilføjede element er `ai` | Referencen matcher tilføjet objekt | ✅ PASS |
| **20** | `performActions() alle 5 ledere` | Liste med de 5 diktatorer | Output indeholder alle 5 navne | Videla, Tito, Pinochet, Mao, Altman til stede | ✅ PASS |
| **21** | `performActions() militære PowerActions` | Gennemløb af militære ledere | Kalder policy, repress og parade | Militære policies og parade udskrevet | ✅ PASS |
| **22** | `performActions() politiske PowerActions`| Gennemløb af politiske ledere | Kalder policy, repress og parade | Politiske policies og parade udskrevet | ✅ PASS |
| **23** | `performActions() udelader AI PowerActions`| Gennemløb af `AIDictator` | Kalder IKKE PowerActions for AI | Ingen `PowerActions`-metoder afviklet for AI | ✅ PASS |

---

### **Kategori 3: Edge Cases & Grænsebetingelser (5 tests)**
Tester hvordan systemet opfører sig ved ekstreme situationer, tomme lister, `null`-værdier og uventede kald.

| # | Testnavn | Testede værdier / Input | Forventet adfærd / Resultat | Faktisk adfærd | Status |
| :-: | :--- | :--- | :--- | :--- | :-: |
| **24** | `performActions() med tom liste` | `new ArrayList<Leader>()` (0 elementer) | Gennemføres uden fejl; intet output | Ingen fejl, intet output, håndteret pænt | ✅ PASS |
| **25** | `performActions(null)` | `null` sendt som parameter | Bør guardes eller kaste NPE | Kaster `NullPointerException` (mangler guard) | ⚠️ OBS |
| **26** | `performActions() med null i liste` | `leaders.add(null); performActions(leaders);` | Bør ignorere null eller guarde | Kaster `NullPointerException` ved `printDetails()` | ⚠️ OBS |
| **27** | `addLeader() med null som liste` | `LeaderManager.addLeader(ai, null)` | Bør guardes mod null-pointer | Kaster `NullPointerException` ved `.add()` | ⚠️ OBS |
| **28** | `addLeader() med null som leder` | `LeaderManager.addLeader(null, leaders)` | Bør afvises med advarsel | `null` indsættes ukritisk i listen | ⚠️ OBS |

---

### **Kategori 4: Dataintegritet & Værdigrænser (16 tests)**
Tester datatypernes grænser (minimum, maksimum, negative tal, specialtegn, lange strenge og null-strenge).

| # | Testnavn | Testede værdier / Input | Forventet resultat | Faktisk observeret resultat | Status |
| :-: | :--- | :--- | :--- | :--- | :-: |
| **29** | `yearsInPower = 0 (nyslået diktator)` | `new MilitaryDictator(..., 0, ...)` | `getYearsInPower() == 0` | Værdien `0` gemmes og udskrives | ✅ PASS |
| **30** | `yearsInPower = -5 (negativ tid)` | `new MilitaryDictator(..., -5, ...)` | Bør valideres som ugyldigt | Accepteres pt. ukritisk som `-5` | ⚠️ OBS |
| **31** | `yearsInPower = Integer.MAX_VALUE` | `yearsInPower = 2.147.483.647` | Ingen overløbs- eller konverteringsfejl | Gemmes som `2147483647` uden fejl | ✅ PASS |
| **32** | `yearsInPower = Integer.MIN_VALUE` | `yearsInPower = -2.147.483.648` | Gemmes og returneres intakt | Gemmes som `-2147483648` | ✅ PASS |
| **33** | `null-strenge i printDetails()` | `name=null, country=null, rank=null` | Udskriver `"null"` uden crash | `"Name: null"`, `"Country: null"` udskrevet | ✅ PASS |
| **34** | `null-strenge i giveSpeech()` | `name=null` | Udskriver tale uden crash | `"Military dictator null is giving..."` | ✅ PASS |
| **35** | `null-strenge i implementPolicy()` | `rank=null` | Udskriver policy uden crash | `"null the null is implementing..."` | ✅ PASS |
| **36** | `null-strenge i holdParade()` | `name=null` | Udskriver parade uden crash | `"Military dictator null is holding..."` | ✅ PASS |
| **37** | `null-strenge i repressOpposition()` | `name=null` | Udskriver undertrykkelse uden crash | `"Military dictator null is repressing..."` | ✅ PASS |
| **38** | `Tomme strenge & blanktegn` | `name="", country="   ", party="\t\n"` | Udskrives uden crash | Udskrives uden fejl (ikke trimmet) | ⚠️ OBS |
| **39** | `Unicode & accenter i gettere` | `"Augusto José Ramón Pinochet Ugarte"` | Værdien forbliver intakt i hukommelsen | Karakterer bevaret 100% uændret | ✅ PASS |
| **40** | `Unicode & symboler i udskrift` | `militaryRank = "General ★ ⚔️ 🛡️"` | Udskrives uden encoding-crash | Symboler håndteres uden fejl | ✅ PASS |
| **41** | `Meget lange strenge` | Navn på 5.000 tegn (`"A" x 5000`) | Håndteres uden hukommelsesnedbrud | `getName().length() == 5000` | ✅ PASS |
| **42** | `setName() mutation` | `dictator.setName("New Name")` | Navnet opdateres til `"New Name"` | `getName()` returnerer `"New Name"` | ✅ PASS |
| **43** | `setCountry() mutation` | `dictator.setCountry("New Country")`| Landet opdateres til `"New Country"`| `getCountry()` returnerer `"New Country"` | ✅ PASS |
| **44** | `setYearsInPower() mutation` | `dictator.setYearsInPower(15)` | Værdien opdateres til `15` | `getYearsInPower()` returnerer `15` | ✅ PASS |

---

### **Kategori 5: Design- og Arkitektur-observationer (2 tests / fund)**
Afdækker inkonsistenser mellem klassernes design og den overordnede arkitektur.

| # | Observation / Test | Berørte klasser & felter | Konsekvens i systemet | Anbefalet udbedring | Status |
| :-: | :--- | :--- | :--- | :--- | :-: |
| **45** | `AIDictator printDetails() mangler` | `companyName` & `companyAI` i `AIDictator` | Felterne udskrives aldrig, da `printDetails()` ikke overskrives | Overskriv `printDetails()` i `AIDictator` med `super.printDetails()` | ⚠️ OBS |
| **46** | `Mangler indkapsling (Accessors)` | `militaryRank`, `partyName`, `companyName`, `companyAI` | Subklassernes specifikke felter mangler getters og setters | Opret getters og setters for alle specifikke subklasse-felter | ⚠️ OBS |

---

## **4. Anbefalede kodeforbedringer**

For at imødekomme de observerede edge cases anbefales følgende konkrete tilføjelser:

### 1. Tilføj null-guards i `LeaderManager.java`:
```java
public static void addLeader(Leader newLeader, ArrayList<Leader> leaders) {
    if (leaders != null && newLeader != null) {
        leaders.add(newLeader);
    }
}

public static void performActions(ArrayList<Leader> leaders) {
    if (leaders == null) return;

    for (Leader leader : leaders) {
        if (leader == null) continue;
        
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
```

### 2. Overskriv `printDetails()` i `AIDictator.java`:
```java
@Override
public void printDetails() {
    super.printDetails();
    System.out.println("Company: " + companyName);
    System.out.println("AI Model: " + companyAI);
}
```

### 3. Validering af år ved magten i `Leader.java`:
```java
public void setYearsInPower(int yearsInPower) {
    if (yearsInPower < 0) {
        System.out.println("Advarsel: År ved magten kan ikke være negativt.");
        return;
    }
    this.yearsInPower = yearsInPower;
}
```
