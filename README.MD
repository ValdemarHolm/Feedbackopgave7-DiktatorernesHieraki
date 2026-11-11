# Feedbackopgave 7 – Diktatorernes Hierarki

Et objektorienteret Java-projekt, der modellerer et magthierarki gennem nedarvning, abstrakte klasser, interfaces og polymorfi.

## 📋 Projektbeskrivelse

Formålet med projektet er at demonstrere central objektorienteret programmering (OOP) i Java. Programmet opbygger et hierarki af forskellige ledertyper (militære, politiske og AI-baserede diktatorer), hvor fælles egenskaber og adfærd deles via en abstrakt superklasse og et interface, mens specifikke handlinger og detaljer implementeres individuelt for hver ledertype.

## 🏗️ Projektstruktur

Kildekoden er organiseret i `src/`-mappen med pakken `LeaderTypes` til domænemodellen:

```text
Feedbackopgave7-DiktatorernesHieraki/
├── src/
│   ├── LeaderTypes/
│   │   ├── Leader.java              # Abstrakt superklasse for alle ledere
│   │   ├── PowerActions.java        # Interface for magthandlinger
│   │   ├── MilitaryDictator.java    # Subklasse for militære diktatorer
│   │   ├── PoliticalDictator.java   # Subklasse for politiske diktatorer
│   │   └── AIDictator.java          # Subklasse for AI-diktatorer
│   ├── LeaderManager.java           # Håndterer oprettelse og afvikling af ledere
│   └── Main.java                    # Programmets indgangspunkt (main-metode)
├── .gitignore                       # Ignorerede filer til Git
└── README.md                        # Projektdokumentation
```

## ⚙️ Centrale koncepter & klasser

* **`Leader` (Abstrakt superklasse):** Definerer de grundlæggende egenskaber for enhver leder (`name`, `country`, `yearsInPower`), metoden `printDetails()` samt den abstrakte metode `giveSpeech()`.
* **`PowerActions` (Interface):** Fastlægger de fælles magthandlinger (`implementPolicy()`, `repressOpposition()`, `holdParade()`), som ledertyperne skal implementere.
* **Subklasser (`MilitaryDictator`, `PoliticalDictator`, `AIDictator`):** Nedarver fra `Leader` og implementerer `PowerActions` med hver deres specialiserede felter (f.eks. militær rang eller politisk parti) og unikke adfærd.
* **Polymorfi (`LeaderManager`):** Samler de forskellige ledertyper i en fælles `ArrayList<Leader>` og afvikler deres taler, detaljer og handlinger ensartet gennem programmet.

## 🚀 Sådan køres programmet

1. **Klon projektet:**
   ```bash
   git clone https://github.com
   ```
2. **Åbn i IDE:** Åbn projektmappen i **IntelliJ IDEA** (eller en anden Java-kompatibel IDE).
3. **Kør koden:** Åbn `src/Main.java` og kør `main`-metoden.

## 👥 Bidragydere

* Jonas, Oliver og Kasper
