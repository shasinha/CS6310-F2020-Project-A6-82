import java.util.Arrays;

public class StreamingWars {

  private int numDemos;
  private final int LIMIT_DEMOS = 10;
  private Demographic[] demos;

  private int numStudios;
  private final int LIMIT_STUDIOS = 10;
  private Studio[] studios;

  private int numEvents;
  private final int LIMIT_EVENTS = 20;
  private Event[] events;

  private int numStreams;
  private final int LIMIT_STREAMS = 10;
  private Stream[] streams;

  private int numOffers;
  private final int LIMIT_OFFERS = LIMIT_EVENTS * LIMIT_STREAMS;
  private Offer[] offers;

  private int watchGroupStreams[][];

  private int monthTimeStamp;
  private int yearTimeStamp;

  private int selectDemo, selectEvent, selectStudio, selectStream, selectOffer;

  public StreamingWars() {
    numDemos = 0;
    demos = new Demographic[LIMIT_DEMOS];

    numStudios = 0;
    studios = new Studio[LIMIT_STUDIOS];

    numEvents = 0;
    events = new Event[LIMIT_EVENTS];

    numStreams = 0;
    streams = new Stream[LIMIT_STREAMS];

    numOffers = 0;
    offers = new Offer[LIMIT_OFFERS];

    watchGroupStreams = new int[LIMIT_DEMOS][LIMIT_STREAMS];

    monthTimeStamp = 10;
    yearTimeStamp = 2020;
  }

  public void createDemo(String demoShortName, String demoLongName, int demoAccounts) {
    if (numDemos < LIMIT_DEMOS) { 
      demos[numDemos] = new Demographic(demoShortName, demoLongName, demoAccounts);
      this.numDemos++;
    }
  }

  public void createStudio(String studioShortName, String studioLongName) {
    if (numStudios < LIMIT_STUDIOS) {
      studios[numStudios] = new Studio(studioShortName, studioLongName);
      numStudios++;
    }
  }

  public void createEvent(String eventType, String eventFullName, int eventYear, int eventDuration, String eventStudioOwner, int eventLicenseFee) {
    if (numEvents < LIMIT_EVENTS) {
      events[numEvents] = new Event(
          eventType
        , eventFullName
        , eventYear
        , eventDuration
        , eventStudioOwner
        , eventLicenseFee
      );
      numEvents++;
    }
  }

  public void createStream(String streamShortName, String streamLongName, int streamSubscription) {
    if (numStreams < LIMIT_STREAMS) { 
      streams[numStreams] = new Stream(streamShortName, streamLongName, streamSubscription);
      numStreams++;
    }
  }

  public void offerEvent(String eventType, String streamingService, String eventName, int eventYear, int eventPrice) {
    offers[numOffers] = new Offer(eventType, streamingService, eventName, eventYear, eventPrice);
    numOffers++;

    // The streaming service must license the event from the studio
    String payStudio = "";
    int payLicenseFee = 0;

    for (selectEvent = 0; selectEvent < numEvents; selectEvent++) {
        if (events[selectEvent].getEventFullName().equals(eventName) && events[selectEvent].getEventYear() == eventYear) {
          payStudio = events[selectEvent].getEventStudioOwner();
          payLicenseFee = events[selectEvent].getEventLicenseFee();
        }
    }

    for (selectStream = 0; selectStream < numStreams; selectStream++) {
        if (streams[selectStream].getStreamShortName().equals(streamingService)) {
            streams[selectStream].updateStreamLicencing(payLicenseFee);
        }
    }

    for (selectStudio = 0; selectStudio < numStudios; selectStudio++) {
        if (studios[selectStudio].getStudioShortName().equals(payStudio)) {
          studios[selectStudio].updateStudioCurrentRevenue(payLicenseFee);
        }
    }

  }

  public void watchEvent(String watchDemoGroup, int watchPercentage, String watchStream, String watchEventName, int watchEventYear) {
    // Identify the demo group & the number of viewers affected
    int watchViewerCount = 0;
    int demoIndex = -1;
    for (selectDemo = 0; selectDemo < numDemos; selectDemo++) {
      if (demos[selectDemo].getDemoShortName().equals(watchDemoGroup)) {
        watchViewerCount = demos[selectDemo].getDemoAccounts() * watchPercentage / 100;
        demoIndex = selectDemo;
      }
    }

    // Identify the streaming service & the subscription fee
    int watchSubscriptionFee = 0;
    int streamIndex = -1;
    for (selectStream = 0; selectStream < numStreams; selectStream++) {
      if (streams[selectStream].getStreamShortName().equals(watchStream)) {
        watchSubscriptionFee = streams[selectStream].getStreamSubscription();
        streamIndex = selectStream;
      }
    }

    // Identify the event selected & the Pay-Per-View price
    // For all events: determine event type
    String watchType = "";
    int watchPayPerViewPrice = 0;
    for (selectOffer = 0; selectOffer < numOffers; selectOffer++) {
      if (
        offers[selectOffer].getOfferStream().equals(watchStream) 
        && offers[selectOffer].getOfferEventName().equals(watchEventName) 
        && offers[selectOffer].getOfferEventYear() == watchEventYear) {
          watchType = offers[selectOffer].getOfferType();
          watchPayPerViewPrice = offers[selectOffer].getOfferEventPrice();
      }
    }

    int watchViewingCost = 0;
    if (watchType.equals("movie")) {
      // For movies: identify the increased percentage of new customers and subscription fee
      if (watchViewerCount > watchGroupStreams[demoIndex][streamIndex]) {
        watchViewingCost = (watchViewerCount - watchGroupStreams[demoIndex][streamIndex]) * watchSubscriptionFee;
        watchGroupStreams[demoIndex][streamIndex] = watchViewerCount;
      }
    } else if (watchType.equals("ppv")) {
      // For Pay-Per-Views: identify the demo viewers affected and event price
      watchViewingCost = watchViewerCount * watchPayPerViewPrice;
    }

    // Adjust funds for watching events
    demos[demoIndex].updateDemoCurrentSpending(watchViewingCost);
    streams[streamIndex].updateStreamCurrentRevenue(watchViewingCost);
  }

  public void incrMonth() {
    // Update the current timestamp
    if (monthTimeStamp == 12) { yearTimeStamp++; }
    monthTimeStamp = (monthTimeStamp % 12) + 1;

    // Update current, previous and total dollar amounts
    for (selectDemo = 0; selectDemo < numDemos; selectDemo++) {
        demos[selectDemo].updateDemoSpendings();
    }

    for (selectStream = 0; selectStream < numStreams; selectStream++) {
        streams[selectStream].updateStreamRevenues();
    }

    for (selectStudio = 0; selectStudio < numStudios; selectStudio++) {
        studios[selectStudio].updateStudioRevenues();
    }

    // Remove all movie and Pay-Per-View offerings
    numOffers = 0;
    offers = new Offer[LIMIT_OFFERS];

    // Reset the subscription counts for the month
    watchGroupStreams = new int[LIMIT_DEMOS][LIMIT_STREAMS];
    
  }

  public Demographic getDemo(String demoShortName) {
    selectDemo = -1;
    for (int findItem = 0; findItem < numDemos; findItem++) {
        if (demos[findItem].getDemoShortName().equals(demoShortName)) {
            selectDemo = findItem;
        }
    }
    return(demos[selectDemo]);
  }

  public Event[] getEvents() {
    return(Arrays.copyOfRange(events, 0, numEvents));
  }

  public Stream getStream(String streamShortName) {
    selectStream = -1;
    for (int findItem = 0; findItem < numStreams; findItem++) {
        if (streams[findItem].getStreamShortName().equals(streamShortName)) {
          selectStream = findItem;
        }
    }
    return(streams[selectStream]);
  }

  public Studio getStudio(String studioShortName) {
    selectStudio = -1;
    for (int findItem = 0; findItem < numStudios; findItem++) {
        if (studios[findItem].getStudioShortName().equals(studioShortName)) {
            selectStudio = findItem;
        }
    }
    return(studios[selectStudio]);

  }

  public Offer[] getOffers() {
    return(Arrays.copyOfRange(offers, 0, numOffers));
  }

  public String getTime() {
    return("time," + monthTimeStamp + "," + yearTimeStamp);
  }

}
