public class Demographic {

  private String demoShortName;
  private String demoLongName;
  private int demoAccounts;
  private int demoCurrentSpending;
  private int demoPreviousSpending;
  private int demoTotalSpending;
  private String demoWatchingHistory;

  public String getDemoShortName() {
    return this.demoShortName;
  }

  public void setDemoShortName(String demoShortName) {
    this.demoShortName = demoShortName;
  }

  public String getDemoLongName() {
    return this.demoLongName;
  }

  public void setDemoLongName(String demoLongName) {
    this.demoLongName = demoLongName;
  }

  public int getDemoAccounts() {
    return this.demoAccounts;
  }

  public void setDemoAccounts(int demoAccounts) {
    this.demoAccounts = demoAccounts;
  }

  public int getDemoCurrentSpending() {
    return this.demoCurrentSpending;
  }

  public void setDemoCurrentSpending(int demoCurrentSpending) {
    this.demoCurrentSpending = demoCurrentSpending;
  }

  public void updateDemoCurrentSpending(int demoCurrentSpending) {
    this.demoCurrentSpending += demoCurrentSpending;
  }


  public int getDemoPreviousSpending() {
    return this.demoPreviousSpending;
  }

  public void setDemoPreviousSpending(int demoPreviousSpending) {
    this.demoPreviousSpending = demoPreviousSpending;
  }

  public int getDemoTotalSpending() {
    return this.demoTotalSpending;
  }

  public void setDemoTotalSpending(int demoTotalSpending) {
    this.demoTotalSpending = demoTotalSpending;
  }

  public void updateDemoSpendings() {
    this.demoTotalSpending += this.demoCurrentSpending;
    this.demoPreviousSpending = this.demoCurrentSpending;
    this.demoCurrentSpending = 0;
  }


  public String getDemoWatchingHistory() {
    return this.demoWatchingHistory;
  }

  public void setDemoWatchingHistory(String demoWatchingHistory) {
    this.demoWatchingHistory = demoWatchingHistory;
  }

  public Demographic(String demoShortName, String demoLongName, int demoAccounts) {
    this.demoShortName = demoShortName;
    this.demoLongName = demoLongName;
    this.demoAccounts = demoAccounts;
    this.demoWatchingHistory = "#";
  }


  @Override
  public String toString() {
    return 
      "demo," + getDemoShortName() + "," + getDemoLongName() + "\n" + 
      "size," + getDemoAccounts() + "\n" +
      "current_period," + getDemoCurrentSpending() + "\n" +
      "previous_period," + getDemoPreviousSpending() + "\n" +
      "total," + getDemoTotalSpending();
  }


}
