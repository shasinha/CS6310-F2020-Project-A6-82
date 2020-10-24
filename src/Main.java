public class Main {
    public static void main(String[] args) {
        TestCaseOOReader caseScanner = new TestCaseOOReader();
        Boolean showState = Boolean.FALSE;
      
        if (args.length >= 2 && (args[1].equals("-v") || args[1].equals("-verbose"))) {
          showState = Boolean.TRUE; 
        }
        caseScanner.processInstructions(showState);
    }
}