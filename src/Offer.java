public class Offer {
  private String offerType;
  private String offerStream;
  private String offerEventName;
  private int offerEventYear;
  private int offerEventPrice;


  public Offer(String offerType, String offerStream, String offerEventName, int offerEventYear, int offerEventPrice) {
    this.offerType = offerType;
    this.offerStream = offerStream;
    this.offerEventName = offerEventName;
    this.offerEventYear = offerEventYear;
    
    if (offerType.equals("ppv")) {
      this.offerEventPrice = offerEventPrice;
    }
  }

  public String getOfferStream() {
    return this.offerStream;
  }

  public void setOfferStream(String offerStream) {
    this.offerStream = offerStream;
  }

  public String getOfferType() {
    return this.offerType;
  }

  public void setOfferType(String offerType) {
    this.offerType = offerType;
  }

  public String getOfferEventName() {
    return this.offerEventName;
  }

  public void setOfferEventName(String offerEventName) {
    this.offerEventName = offerEventName;
  }

  public int getOfferEventYear() {
    return this.offerEventYear;
  }

  public void setOfferEventYear(int offerEventYear) {
    this.offerEventYear = offerEventYear;
  }

  public int getOfferEventPrice() {
    return this.offerEventPrice;
  }

  public void setOfferEventPrice(int offerEventPrice) {
    this.offerEventPrice = offerEventPrice;
  }


  @Override
  public String toString() {
    String strReturn = String.join(",", getOfferStream() + "," + getOfferType() + "," + getOfferEventName() + "," + String.valueOf(getOfferEventYear()));
    if (getOfferType().equals("ppv")) {
        strReturn = String.join(",", strReturn, String.valueOf(getOfferEventPrice()));
    }
    return strReturn;
  }

  
}
