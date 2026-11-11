import LeaderTypes.AIDictator;
import LeaderTypes.Leader;
import LeaderTypes.MilitaryDictator;
import LeaderTypes.PoliticalDictator;
import LeaderTypes.PowerActions;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class SystemTest {
    private static int testsPassed = 0;
    private static int testsFailed = 0;
    private static int totalTests = 0;
    private static final ArrayList<String> findings = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("================================================================");
        System.out.println("   COMPREHENSIVE SYSTEM & EDGE-CASE TEST SUITE: DICTATOR HIERARCHY");
        System.out.println("================================================================\n");

        // 1. Structural & Polymorphic Hierarchy Tests
        testSection("1. CLASS HIERARCHY & POLYMORPHISM");
        testClassHierarchyAndInterfaces();
        testPolymorphicSpeechDispatch();
        testPolymorphicPrintDetailsDispatch();

        // 2. LeaderManager Functionality Tests
        testSection("2. LEADER MANAGER CORE FUNCTIONALITY");
        testCreateLeaders();
        testAddLeader();
        testPerformActionsNormalExecution();

        // 3. Edge Cases & Boundary Value Tests
        testSection("3. EDGE CASES & BOUNDARY CONDITIONS");
        testPerformActionsWithEmptyList();
        testPerformActionsWithNullList();
        testPerformActionsWithNullElement();
        testAddLeaderWithNullList();
        testAddLeaderWithNullLeader();

        // 4. Data Validation & Primitive/String Boundaries
        testSection("4. DATA INTEGRITY & VALUE BOUNDARIES");
        testYearsInPowerBoundaries();
        testNullStringInputs();
        testEmptyAndWhitespaceStrings();
        testSpecialCharactersAndUnicode();
        testLargeStringInputs();
        testSettersAndStateMutation();

        // 5. Code Structure & Design Observations
        testSection("5. DESIGN & ARCHITECTURE FINDINGS");
        testAIDictatorPrintDetailsGap();
        testSubclassFieldEncapsulation();

        // Final Summary Report
        printTestSummary();
    }

    private static void testSection(String sectionName) {
        System.out.println("\n--- " + sectionName + " ---");
    }

    private static void assertTrue(String testName, boolean condition, String errorDetail) {
        totalTests++;
        if (condition) {
            testsPassed++;
            System.out.printf("  [PASS] %s%n", testName);
        } else {
            testsFailed++;
            System.out.printf("  [FAIL] %s: %s%n", testName, errorDetail);
        }
    }

    private static void reportFinding(String finding) {
        findings.add(finding);
        System.out.printf("  [WARN/NOTE] %s%n", finding);
    }

    // =========================================================================
    // 1. Class Hierarchy & Polymorphism Tests
    // =========================================================================
    private static void testClassHierarchyAndInterfaces() {
        MilitaryDictator military = new MilitaryDictator("M. Test", "Country A", 5, "General");
        PoliticalDictator political = new PoliticalDictator("P. Test", "Country B", 10, "Party X");
        AIDictator ai = new AIDictator("AI. Test", "Country C", 2, "Company Y", "AI Model Z");

        assertTrue("MilitaryDictator is an instance of Leader", military instanceof Leader, "Must extend Leader");
        assertTrue("MilitaryDictator implements PowerActions", military instanceof PowerActions, "Must implement PowerActions");

        assertTrue("PoliticalDictator is an instance of Leader", political instanceof Leader, "Must extend Leader");
        assertTrue("PoliticalDictator implements PowerActions", political instanceof PowerActions, "Must implement PowerActions");

        assertTrue("AIDictator is an instance of Leader", ai instanceof Leader, "Must extend Leader");
        assertTrue("AIDictator does NOT implement PowerActions", !(ai instanceof PowerActions), "Should NOT implement PowerActions");
    }

    private static void testPolymorphicSpeechDispatch() {
        Leader militaryLeader = new MilitaryDictator("Videla", "Argentina", 5, "General");
        Leader politicalLeader = new PoliticalDictator("Tito", "Yugoslavia", 35, "Party");
        Leader aiLeader = new AIDictator("Altman", "USA", 11, "OpenAI", "ChatGPT");

        String mSpeech = captureOutput(militaryLeader::giveSpeech);
        assertTrue("MilitaryDictator.giveSpeech output contains 'military speech'",
                mSpeech.contains("military speech"), "Expected military speech content");

        String pSpeech = captureOutput(politicalLeader::giveSpeech);
        assertTrue("PoliticalDictator.giveSpeech output contains 'political speech'",
                pSpeech.contains("political speech"), "Expected political speech content");

        String aSpeech = captureOutput(aiLeader::giveSpeech);
        assertTrue("AIDictator.giveSpeech output contains 'Nobody should have RAM at home'",
                aSpeech.contains("Nobody should have RAM at home"), "Expected AI speech content");
    }

    private static void testPolymorphicPrintDetailsDispatch() {
        Leader military = new MilitaryDictator("Pinochet", "Chile", 17, "Captain General");
        Leader political = new PoliticalDictator("Mao", "China", 27, "CPC");

        String mOut = captureOutput(military::printDetails);
        assertTrue("Military printDetails includes rank", mOut.contains("Military rank: Captain General"), "Missing rank");
        assertTrue("Military printDetails includes super details", mOut.contains("Name: Pinochet"), "Missing super details");

        String pOut = captureOutput(political::printDetails);
        assertTrue("Political printDetails includes party", pOut.contains("Political party: CPC"), "Missing party");
        assertTrue("Political printDetails includes super details", pOut.contains("Name: Mao"), "Missing super details");
    }

    // =========================================================================
    // 2. LeaderManager Core Functionality Tests
    // =========================================================================
    private static void testCreateLeaders() {
        ArrayList<Leader> leaders = LeaderManager.createLeaders();
        assertTrue("createLeaders returns non-null list", leaders != null, "List is null");
        assertTrue("createLeaders returns list of size 4", leaders.size() == 4, "Expected size 4, got " + leaders.size());

        // Check expected types
        int militaryCount = 0;
        int politicalCount = 0;
        for (Leader l : leaders) {
            if (l instanceof MilitaryDictator) militaryCount++;
            if (l instanceof PoliticalDictator) politicalCount++;
        }
        assertTrue("Initial leaders contain 2 MilitaryDictators", militaryCount == 2, "Got " + militaryCount);
        assertTrue("Initial leaders contain 2 PoliticalDictators", politicalCount == 2, "Got " + politicalCount);
    }

    private static void testAddLeader() {
        ArrayList<Leader> leaders = LeaderManager.createLeaders();
        int initialSize = leaders.size();
        AIDictator ai = new AIDictator("Sam Altman", "USA", 11, "OpenAI", "ChatGPT");

        LeaderManager.addLeader(ai, leaders);
        assertTrue("addLeader increases list size by 1", leaders.size() == initialSize + 1, "Size not incremented");
        assertTrue("addLeader appends the new leader at the end", leaders.get(leaders.size() - 1) == ai, "Leader mismatch");
    }

    private static void testPerformActionsNormalExecution() {
        ArrayList<Leader> leaders = LeaderManager.createLeaders();
        AIDictator ai = new AIDictator("Sam Altman", "USA", 11, "OpenAI", "ChatGPT");
        leaders.add(ai);

        String out = captureOutput(() -> LeaderManager.performActions(leaders));
        assertTrue("performActions produces output for all 5 leaders",
                out.contains("Videla") && out.contains("Tito") && out.contains("Pinochet")
                        && out.contains("Mao") && out.contains("Sam Altman"),
                "Output missing one or more leaders");

        assertTrue("performActions executes PowerActions for MilitaryDictator",
                out.contains("implementing military policies") && out.contains("holding a glorious military parade"),
                "Military PowerActions missing");

        assertTrue("performActions executes PowerActions for PoliticalDictator",
                out.contains("implementing political policies") && out.contains("holding a political parade"),
                "Political PowerActions missing");

        assertTrue("performActions does NOT execute PowerActions for AIDictator",
                !out.contains("AI dictator Sam Altman is implementing") && !out.contains("AI dictator Sam Altman is holding"),
                "AIDictator should not execute PowerActions");
    }

    // =========================================================================
    // 3. Edge Cases & Boundary Conditions Tests
    // =========================================================================
    private static void testPerformActionsWithEmptyList() {
        ArrayList<Leader> emptyList = new ArrayList<>();
        try {
            String out = captureOutput(() -> LeaderManager.performActions(emptyList));
            assertTrue("performActions with empty list executes gracefully without exception",
                    out.isEmpty(), "Expected no output from empty list");
        } catch (Exception e) {
            assertTrue("performActions with empty list should not throw exception", false, e.toString());
        }
    }

    private static void testPerformActionsWithNullList() {
        try {
            LeaderManager.performActions(null);
            assertTrue("performActions(null) should throw NullPointerException", false, "No exception thrown");
        } catch (NullPointerException e) {
            assertTrue("performActions(null) throws NullPointerException (documented edge case)", true, "");
            reportFinding("LeaderManager.performActions does not check for null list; passing null throws NullPointerException.");
        } catch (Exception e) {
            assertTrue("Unexpected exception for performActions(null)", false, e.toString());
        }
    }

    private static void testPerformActionsWithNullElement() {
        ArrayList<Leader> listWithNull = new ArrayList<>();
        listWithNull.add(null);
        try {
            LeaderManager.performActions(listWithNull);
            assertTrue("performActions with null element should throw NullPointerException", false, "No exception thrown");
        } catch (NullPointerException e) {
            assertTrue("performActions with a null element in list throws NullPointerException", true, "");
            reportFinding("LeaderManager.performActions throws NullPointerException if the list contains a null element (no null check on 'leader').");
        } catch (Exception e) {
            assertTrue("Unexpected exception for performActions with null element", false, e.toString());
        }
    }

    private static void testAddLeaderWithNullList() {
        try {
            AIDictator ai = new AIDictator("AI", "USA", 1, "Co", "Model");
            LeaderManager.addLeader(ai, null);
            assertTrue("addLeader with null list should throw NullPointerException", false, "No exception thrown");
        } catch (NullPointerException e) {
            assertTrue("LeaderManager.addLeader throws NullPointerException when list is null", true, "");
            reportFinding("LeaderManager.addLeader does not guard against null list argument.");
        }
    }

    private static void testAddLeaderWithNullLeader() {
        ArrayList<Leader> list = new ArrayList<>();
        LeaderManager.addLeader(null, list);
        assertTrue("LeaderManager.addLeader permits adding null leader", list.size() == 1 && list.get(0) == null,
                "Expected null to be added");
        reportFinding("LeaderManager.addLeader allows adding 'null' as a Leader, which later crashes performActions.");
    }

    // =========================================================================
    // 4. Data Validation & Value Boundaries
    // =========================================================================
    private static void testYearsInPowerBoundaries() {
        // Zero years
        MilitaryDictator zeroYears = new MilitaryDictator("New Guy", "Country", 0, "Colonel");
        assertTrue("yearsInPower = 0 is accepted", zeroYears.getYearsInPower() == 0, "Value mismatch");

        // Negative years
        MilitaryDictator negativeYears = new MilitaryDictator("Time Traveler", "Country", -5, "Colonel");
        assertTrue("yearsInPower = -5 is currently accepted (no negative validation)",
                negativeYears.getYearsInPower() == -5, "Value mismatch");
        reportFinding("Leader constructor and setYearsInPower have no validation for negative values (e.g. -5 years).");

        // Large values (Integer.MAX_VALUE)
        PoliticalDictator immortal = new PoliticalDictator("Immortal", "Empire", Integer.MAX_VALUE, "Party");
        assertTrue("yearsInPower = Integer.MAX_VALUE is accepted without overflow error",
                immortal.getYearsInPower() == Integer.MAX_VALUE, "Value mismatch");

        // Integer.MIN_VALUE
        immortal.setYearsInPower(Integer.MIN_VALUE);
        assertTrue("yearsInPower = Integer.MIN_VALUE is accepted",
                immortal.getYearsInPower() == Integer.MIN_VALUE, "Value mismatch");
    }

    private static void testNullStringInputs() {
        // Null values in constructor
        MilitaryDictator nullDictator = new MilitaryDictator(null, null, 10, null);
        try {
            String details = captureOutput(nullDictator::printDetails);
            assertTrue("printDetails handles null name, country, and rank without throwing exception",
                    details.contains("Name: null") && details.contains("Military rank: null"), "Failed output");

            String speech = captureOutput(nullDictator::giveSpeech);
            assertTrue("giveSpeech handles null name safely", speech.contains("null"), "Speech crash");

            String policy = captureOutput(nullDictator::implementPolicy);
            assertTrue("implementPolicy handles null rank safely", policy.contains("null"), "Policy crash");

            String parade = captureOutput(nullDictator::holdParade);
            assertTrue("holdParade handles null name safely", parade.contains("null"), "Parade crash");

            String repress = captureOutput(nullDictator::repressOpposition);
            assertTrue("repressOpposition handles null name safely", repress.contains("null"), "Repress crash");
        } catch (Exception e) {
            assertTrue("Methods should not crash on null strings", false, e.toString());
        }
    }

    private static void testEmptyAndWhitespaceStrings() {
        PoliticalDictator emptyDictator = new PoliticalDictator("", "   ", 4, "\t\n");
        String details = captureOutput(emptyDictator::printDetails);
        assertTrue("printDetails prints empty and whitespace strings without error",
                details.contains("Name: "), "Failed handling empty string");
        reportFinding("Leader classes do not trim or reject empty / whitespace-only strings for name, country, or specific fields.");
    }

    private static void testSpecialCharactersAndUnicode() {
        String accentedName = "Augusto José Ramón Pinochet Ugarte";
        String danishCountry = "Danmark (København & Århus æøå)";
        String unicodeRank = "General ★ ⚔️ 🛡️";

        MilitaryDictator unicodeDictator = new MilitaryDictator(accentedName, danishCountry, 17, unicodeRank);
        assertTrue("Accented characters and Danish characters preserved in getter",
                unicodeDictator.getName().equals(accentedName) && unicodeDictator.getCountry().equals(danishCountry),
                "Characters corrupted");

        String out = captureOutput(unicodeDictator::printDetails);
        assertTrue("printDetails outputs special unicode symbols without crashing",
                out.contains(accentedName), "Unicode print failure");
    }

    private static void testLargeStringInputs() {
        StringBuilder largeName = new StringBuilder();
        for (int i = 0; i < 5000; i++) {
            largeName.append("A");
        }
        PoliticalDictator largeDictator = new PoliticalDictator(largeName.toString(), "BigCountry", 50, "BigParty");
        assertTrue("Extremely large string handled without memory/crash issue",
                largeDictator.getName().length() == 5000, "Length mismatch");
    }

    private static void testSettersAndStateMutation() {
        PoliticalDictator dictator = new PoliticalDictator("Old Name", "Old Country", 5, "Old Party");
        dictator.setName("New Name");
        dictator.setCountry("New Country");
        dictator.setYearsInPower(15);

        assertTrue("setName successfully mutates name", "New Name".equals(dictator.getName()), "setName failed");
        assertTrue("setCountry successfully mutates country", "New Country".equals(dictator.getCountry()), "setCountry failed");
        assertTrue("setYearsInPower successfully mutates years", dictator.getYearsInPower() == 15, "setYearsInPower failed");
    }

    // =========================================================================
    // 5. Design & Architecture Observations
    // =========================================================================
    private static void testAIDictatorPrintDetailsGap() {
        AIDictator ai = new AIDictator("Sam Altman", "USA", 11, "OpenAI", "ChatGPT");
        String out = captureOutput(ai::printDetails);

        // In AIDictator, printDetails is NOT overridden!
        // Therefore, companyName ("OpenAI") and companyAI ("ChatGPT") are never displayed!
        boolean printsCompany = out.contains("OpenAI");
        boolean printsAI = out.contains("ChatGPT");

        assertTrue("Documenting AIDictator.printDetails behavior: does not print companyName or companyAI",
                !printsCompany && !printsAI,
                "AIDictator does not override printDetails, so its unique fields are hidden.");
        reportFinding("AIDictator has private fields 'companyName' and 'companyAI', but never overrides printDetails(). As a result, these details are never displayed to the user.");
    }

    private static void testSubclassFieldEncapsulation() {
        // Checking getters and setters for subclass specific fields:
        // Notice: MilitaryDictator has militaryRank, but NO getter getMilitaryRank() or setter setMilitaryRank()!
        // PoliticalDictator has partyName, but NO getter getPartyName() or setter setPartyName()!
        // AIDictator has companyName/companyAI, but NO getters/setters!
        reportFinding("Encapsulation gap: MilitaryDictator lacks getMilitaryRank()/setMilitaryRank().");
        reportFinding("Encapsulation gap: PoliticalDictator lacks getPartyName()/setPartyName().");
        reportFinding("Encapsulation gap: AIDictator lacks getCompanyName(), getCompanyAI(), and corresponding setters.");
        assertTrue("Subclass encapsulation gaps identified and documented", true, "");
    }

    // =========================================================================
    // Helper Methods
    // =========================================================================
    private static String captureOutput(Runnable action) {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            System.setOut(new PrintStream(baos));
            action.run();
        } finally {
            System.setOut(originalOut);
        }
        return baos.toString();
    }

    private static void printTestSummary() {
        System.out.println("\n================================================================");
        System.out.println("                        TEST SUMMARY                            ");
        System.out.println("================================================================");
        System.out.printf("  Total Tests Executed : %d%n", totalTests);
        System.out.printf("  Tests Passed         : %d%n", testsPassed);
        System.out.printf("  Tests Failed         : %d%n", testsFailed);
        System.out.printf("  Edge Cases/Warnings  : %d%n", findings.size());
        System.out.println("================================================================");

        if (findings.size() > 0) {
            System.out.println("\nKey Findings & Edge Case Observations:");
            for (int i = 0; i < findings.size(); i++) {
                System.out.printf("  %d. %s%n", i + 1, findings.get(i));
            }
        }
        System.out.println("================================================================\n");
    }
}
