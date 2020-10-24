public class Studio {
  private String studioShortName;
  private String studioLongName;
  private int studioCurrentRevenue;
  private int studioPreviousRevenue;
  private int studioTotalRevenue;

  public String getStudioShortName() {
    return this.studioShortName;
  }

  public void setStudioShortName(String studioShortName) {
    this.studioShortName = studioShortName;
  }

  public String getStudioLongName() {
    return this.studioLongName;
  }

  public void setStudioLongName(String studioLongName) {
    this.studioLongName = studioLongName;
  }

  public int getStudioCurrentRevenue() {
    return this.studioCurrentRevenue;
  }

  public void setStudioCurrentRevenue(int studioCurrentRevenue) {
    this.studioCurrentRevenue = studioCurrentRevenue;
  }

  public void updateStudioCurrentRevenue(int studioCurrentRevenueIncr) {
    this.studioCurrentRevenue += studioCurrentRevenueIncr;
  }

  public int getStudioPreviousRevenue() {
    return this.studioPreviousRevenue;
  }

  public void setStudioPreviousRevenue(int studioPreviousRevenue) {
    this.studioPreviousRevenue = studioPreviousRevenue;
  }

  public int getStudioTotalRevenue() {
    return this.studioTotalRevenue;
  }

  public void setStudioTotalRevenue(int studioTotalRevenue) {
    this.studioTotalRevenue = studioTotalRevenue;
  }

  public void updateStudioRevenues() {
    this.studioTotalRevenue += this.studioCurrentRevenue;
    this.studioPreviousRevenue = this.studioCurrentRevenue;
    this.studioCurrentRevenue = 0;
  }

  public Studio(String studioShortName, String studioLongName) {
    this.studioShortName = studioShortName;
    this.studioLongName = studioLongName;
  }

  @Override
  public String toString() {
      return "studio," + getStudioShortName() + "," + getStudioLongName() + "\n" +
      "current_period," + getStudioCurrentRevenue() + "\n" +
      "previous_period," + getStudioPreviousRevenue() + "\n" +
      "total," + getStudioTotalRevenue();
  }

}
