public class Event {
  private String eventType;
  private String eventFullName;
  private int eventYear;
  private int eventDuration;
  private String eventStudioOwner;
  private int eventLicenseFee;


  public String getEventType() {
    return this.eventType;
  }

  public void setEventType(String eventType) {
    this.eventType = eventType;
  }

  public String getEventFullName() {
    return this.eventFullName;
  }

  public void setEventFullName(String eventFullName) {
    this.eventFullName = eventFullName;
  }

  public String getEventStudioOwner() {
    return this.eventStudioOwner;
  }

  public void setEventStudioOwner(String eventStudioOwner) {
    this.eventStudioOwner = eventStudioOwner;
  }

  public int getEventYear() {
    return this.eventYear;
  }

  public void setEventYear(int eventYear) {
    this.eventYear = eventYear;
  }

  public int getEventDuration() {
    return this.eventDuration;
  }

  public void setEventDuration(int eventDuration) {
    this.eventDuration = eventDuration;
  }

  public int getEventLicenseFee() {
    return this.eventLicenseFee;
  }

  public void setEventLicenseFee(int eventLicenseFee) {
    this.eventLicenseFee = eventLicenseFee;
  }


  @Override
  public String toString() {
    return String.join(","
      , getEventType()
      , getEventFullName()
      , Integer.toString(getEventYear())
      , Integer.toString(getEventDuration())
      , getEventStudioOwner()
      , Integer.toString(getEventLicenseFee())
    );
  }


  public Event(String eventType, String eventFullName, int eventYear, int eventDuration, String eventStudioOwner, int eventLicenseFee) {
    this.eventType = eventType;
    this.eventFullName = eventFullName;
    this.eventYear = eventYear;
    this.eventDuration = eventDuration;
    this.eventStudioOwner = eventStudioOwner;
    this.eventLicenseFee = eventLicenseFee;
  }



}
