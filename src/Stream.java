public class Stream {
  private String streamShortName;
  private String streamLongName;
  private int streamSubscription;
  private int streamCurrentRevenue;
  private int streamPreviousRevenue;
  private int streamTotalRevenue;
  private int streamLicensing;


  public String getStreamShortName() {
    return this.streamShortName;
  }

  public void setStreamShortName(String streamShortName) {
    this.streamShortName = streamShortName;
  }

  public String getStreamLongName() {
    return this.streamLongName;
  }

  public void setStreamLongName(String streamLongName) {
    this.streamLongName = streamLongName;
  }

  public int getStreamSubscription() {
    return this.streamSubscription;
  }

  public void setStreamSubscription(int streamSubscription) {
    this.streamSubscription = streamSubscription;
  }

  public int getStreamCurrentRevenue() {
    return this.streamCurrentRevenue;
  }

  public void setStreamCurrentRevenue(int streamCurrentRevenue) {
    this.streamCurrentRevenue = streamCurrentRevenue;
  }

  public void updateStreamCurrentRevenue(int streamCurrentRevenueIncr) {
    this.streamCurrentRevenue += streamCurrentRevenueIncr;
  }


  public int getStreamPreviousRevenue() {
    return this.streamPreviousRevenue;
  }

  public void setStreamPreviousRevenue(int streamPreviousRevenue) {
    this.streamPreviousRevenue = streamPreviousRevenue;
  }

  public int getStreamTotalRevenue() {
    return this.streamTotalRevenue;
  }

  public void setStreamTotalRevenue(int streamTotalRevenue) {
    this.streamTotalRevenue = streamTotalRevenue;
  }

  public int getStreamLicensing() {
    return this.streamLicensing;
  }

  public void setStreamLicensing(int streamLicensing) {
    this.streamLicensing = streamLicensing;
  }

  public void updateStreamLicencing(int streamLicencingIncr) {
    this.streamLicensing += streamLicencingIncr;
  }

  public void updateStreamRevenues() {
    this.streamTotalRevenue = this.streamTotalRevenue + this.streamCurrentRevenue;
    this.streamPreviousRevenue = this.streamCurrentRevenue;
    this.streamCurrentRevenue = 0;
  }

  public Stream(String streamShortName, String streamLongName, int streamSubscription) {
    this.streamShortName = streamShortName;
    this.streamLongName = streamLongName;
    this.streamSubscription = streamSubscription;
  }


  @Override
  public String toString() {
    return 
      "stream," + getStreamShortName() + "," + getStreamLongName() + "\n" + 
      "subscription," + getStreamSubscription() + "\n" +
      "current_period," + getStreamCurrentRevenue() + "\n" +
      "previous_period," + getStreamPreviousRevenue() + "\n" +
      "total," + getStreamTotalRevenue() + "\n" +
      "licensing," + getStreamLicensing()
      ;

  }


}
