import java.util.Scanner;

public class TestCaseReader {

    private int numDemos;
    private String demoShortName[];
    private String demoLongName[];
    private int demoAccounts[];
    private int demoCurrentSpending[];
    private int demoPreviousSpending[];
    private int demoTotalSpending[];
    private String demoWatchingHistory[];
    private final int LIMIT_DEMOS = 10;

    private int numStudios;
    private String studioShortName[];
    private String studioLongName[];
    private int studioCurrentRevenue[];
    private int studioPreviousRevenue[];
    private int studioTotalRevenue[];
    private final int LIMIT_STUDIOS = 10;

    private int numEvents;
    private String eventType[];
    private String eventFullName[];
    private String eventStudioOwner[];
    private int eventYear[];
    private int eventDuration[];
    private int eventLicenseFee[];
    private final int LIMIT_EVENTS = 20;

    private int numStreams;
    private String streamShortName[];
    private String streamLongName[];
    private int streamSubscription[];
    private int streamCurrentRevenue[];
    private int streamPreviousRevenue[];
    private int streamTotalRevenue[];
    private int streamLicensing[];
    private final int LIMIT_STREAMS = 10;

    private int numOffers;
    private String offerStream[];
    private String offerType[];
    private String offerEventName[];
    private int offerEventYear[];
    private int offerEventPrice[];
    private final int LIMIT_OFFERS = LIMIT_EVENTS * LIMIT_STREAMS;

    private int watchGroupStreams[][];

    private int monthTimeStamp;
    private int yearTimeStamp;

    public TestCaseReader() {
        numDemos = 0;
        demoShortName = new String[LIMIT_DEMOS];
        demoLongName = new String[LIMIT_DEMOS];
        demoAccounts = new int[LIMIT_DEMOS];
        demoCurrentSpending = new int[LIMIT_DEMOS];
        demoPreviousSpending = new int[LIMIT_DEMOS];
        demoTotalSpending = new int[LIMIT_DEMOS];
        demoWatchingHistory = new String[LIMIT_DEMOS];
        for (int index = 0; index < LIMIT_DEMOS; index++) {
            demoWatchingHistory[index] = "#";
        }

        numStudios = 0;
        studioShortName = new String[LIMIT_STUDIOS];
        studioLongName = new String[LIMIT_STUDIOS];
        studioCurrentRevenue = new int[LIMIT_STUDIOS];
        studioPreviousRevenue = new int[LIMIT_STUDIOS];
        studioTotalRevenue = new int[LIMIT_STUDIOS];

        numEvents = 0;
        eventType = new String[LIMIT_EVENTS];
        eventFullName = new String[LIMIT_EVENTS];
        eventStudioOwner = new String[LIMIT_EVENTS];
        eventYear = new int[LIMIT_EVENTS];
        eventDuration = new int[LIMIT_EVENTS];
        eventLicenseFee = new int[LIMIT_EVENTS];

        numStreams = 0;
        streamShortName = new String[LIMIT_STREAMS];
        streamLongName = new String[LIMIT_STREAMS];
        streamSubscription = new int[LIMIT_STREAMS];
        streamCurrentRevenue = new int[LIMIT_STREAMS];
        streamPreviousRevenue = new int[LIMIT_STREAMS];
        streamTotalRevenue = new int[LIMIT_STREAMS];
        streamLicensing = new int[LIMIT_STREAMS];

        numOffers = 0;
        offerStream = new String[LIMIT_OFFERS];
        offerType = new String[LIMIT_OFFERS];
        offerEventName = new String[LIMIT_OFFERS];
        offerEventYear = new int[LIMIT_OFFERS];
        offerEventPrice = new int[LIMIT_OFFERS];

        watchGroupStreams = new int[LIMIT_DEMOS][LIMIT_STREAMS];

        monthTimeStamp = 10;
        yearTimeStamp = 2020;
    }

    public void processInstructions(Boolean verboseMode) {
        int selectDemo, selectEvent, selectStudio, selectStream, selectOffer;
        Scanner commandLineInput = new Scanner(System.in);
        String wholeInputLine;
        String[] tokens;
        final String DELIMITER = ",";

        while (true) {
            try {
                // Determine the next command and echo it to the monitor for testing purposes
                wholeInputLine = commandLineInput.nextLine();
                tokens = wholeInputLine.split(DELIMITER);
                System.out.println("> " + wholeInputLine);

                if (tokens[0].equals("create_demo")) {
                    if (verboseMode) { System.out.println("create_demo_acknowledged"); }
                    if (numDemos >= LIMIT_DEMOS) { continue; }
                    demoShortName[numDemos] = tokens[1];
                    demoLongName[numDemos] = tokens[2];
                    demoAccounts[numDemos] = Integer.parseInt(tokens[3]);
                    numDemos++;

                } else if (tokens[0].equals("create_studio")) {
                    if (verboseMode) { System.out.println("create_studio_acknowledged"); }
                    if (numStudios >= LIMIT_STUDIOS) { continue; }
                    studioShortName[numStudios] = tokens[1];
                    studioLongName[numStudios] = tokens[2];
                    numStudios++;

                } else if (tokens[0].equals("create_event")) {
                    if (verboseMode) { System.out.println("create_event_acknowledged"); }
                    if (numEvents >= LIMIT_EVENTS) { continue; }
                    eventType[numEvents] = tokens[1];
                    eventFullName[numEvents] = tokens[2];
                    eventYear[numEvents] = Integer.parseInt(tokens[3]);
                    eventDuration[numEvents] = Integer.parseInt(tokens[4]);
                    eventStudioOwner[numEvents] = tokens[5];
                    eventLicenseFee[numEvents] = Integer.parseInt(tokens[6]);
                    numEvents++;

                } else if (tokens[0].equals("create_stream")) {
                    if (verboseMode) { System.out.println("create_stream_acknowledged"); }
                    if (numStreams >= LIMIT_STREAMS) { continue; }
                    streamShortName[numStreams] = tokens[1];
                    streamLongName[numStreams] = tokens[2];
                    streamSubscription[numStreams] = Integer.parseInt(tokens[3]);
                    numStreams++;

                } else if (tokens[0].equals("offer_movie") || tokens[0].equals("offer_ppv")) {
                    if (verboseMode) { System.out.println(tokens[0] + "_acknowledged"); }
                    if (numOffers >= LIMIT_OFFERS) { continue; }
                    offerType[numOffers] = tokens[0].substring(6);
                    offerStream[numOffers] = tokens[1];
                    offerEventName[numOffers] = tokens[2];
                    offerEventYear[numOffers] = Integer.parseInt(tokens[3]);
                    if (offerType[numOffers].equals("ppv")) {
                        offerEventPrice[numOffers] = Integer.parseInt(tokens[4]);
                    }
                    numOffers++;

                    // The streaming service must license the event from the studio
                    String payStudio = "";
                    int payLicenseFee = 0;
                    for (selectEvent = 0; selectEvent < numEvents; selectEvent++) {
                        if (eventFullName[selectEvent].equals(tokens[2]) && eventYear[selectEvent] == Integer.parseInt(tokens[3])) {
                            payStudio = eventStudioOwner[selectEvent];
                            payLicenseFee = eventLicenseFee[selectEvent];
                        }
                    }

                    for (selectStream = 0; selectStream < numStreams; selectStream++) {
                        if (streamShortName[selectStream].equals(tokens[1])) {
                            streamLicensing[selectStream] = streamLicensing[selectStream] + payLicenseFee;
                        }
                    }

                    for (selectStudio = 0; selectStudio < numStudios; selectStudio++) {
                        if (studioShortName[selectStudio].equals(payStudio)) {
                            studioCurrentRevenue[selectStudio] = studioCurrentRevenue[selectStudio] + payLicenseFee;
                        }
                    }

                } else if (tokens[0].equals("watch_event")) {
                    if (verboseMode) { System.out.println("watch_event_acknowledged"); }
                    String watchDemoGroup = tokens[1];
                    int watchPercentage = Integer.parseInt(tokens[2]);
                    String watchStream = tokens[3];
                    String watchEventName = tokens[4];
                    int watchEventYear = Integer.parseInt(tokens[5]);

                    // Identify the demo group & the number of viewers affected
                    int watchViewerCount = 0;
                    int demoIndex = -1;
                    for (selectDemo = 0; selectDemo < numDemos; selectDemo++) {
                        if (demoShortName[selectDemo].equals(watchDemoGroup)) {
                            watchViewerCount = demoAccounts[selectDemo] * watchPercentage / 100;
                            demoIndex = selectDemo;
                        }
                    }

                    // Identify the streaming service & the subscription fee
                    int watchSubscriptionFee = 0;
                    int streamIndex = -1;
                    for (selectStream = 0; selectStream < numStreams; selectStream++) {
                        if (streamShortName[selectStream].equals(watchStream)) {
                            watchSubscriptionFee = streamSubscription[selectStream];
                            streamIndex = selectStream;
                        }
                    }

                    // Identify the event selected & the Pay-Per-View price
                    // For all events: determine event type
                    String watchType = "";
                    int watchPayPerViewPrice = 0;
                    int offerIndex = -1;
                    for (selectOffer = 0; selectOffer < numOffers; selectOffer++) {
                        if (offerStream[selectOffer].equals(watchStream) && offerEventName[selectOffer].equals(watchEventName) && offerEventYear[selectOffer] == watchEventYear) {
                            watchType = offerType[selectOffer];
                            watchPayPerViewPrice = offerEventPrice[selectOffer];
                            offerIndex = selectOffer;
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
                    demoCurrentSpending[demoIndex] = demoCurrentSpending[demoIndex] + watchViewingCost;
                    streamCurrentRevenue[streamIndex] = streamCurrentRevenue[streamIndex] + watchViewingCost;

                } else if (tokens[0].equals("next_month")) {
                    if (verboseMode) { System.out.println("next_month_acknowledged"); }

                    // Update the current timestamp
                    if (monthTimeStamp == 12) { yearTimeStamp++; }
                    monthTimeStamp = (monthTimeStamp % 12) + 1;

                    // Update current, previous and total dollar amounts
                    for (selectDemo = 0; selectDemo < numDemos; selectDemo++) {
                        demoTotalSpending[selectDemo] = demoTotalSpending[selectDemo] + demoCurrentSpending[selectDemo];
                        demoPreviousSpending[selectDemo] = demoCurrentSpending[selectDemo];
                        demoCurrentSpending[selectDemo] = 0;
                    }

                    for (selectStream = 0; selectStream < numStreams; selectStream++) {
                        streamTotalRevenue[selectStream] = streamTotalRevenue[selectStream] + streamCurrentRevenue[selectStream];
                        streamPreviousRevenue[selectStream] = streamCurrentRevenue[selectStream];
                        streamCurrentRevenue[selectStream] = 0;
                    }

                    for (selectStudio = 0; selectStudio < numStudios; selectStudio++) {
                        studioTotalRevenue[selectStudio] = studioTotalRevenue[selectStudio] + studioCurrentRevenue[selectStudio];
                        studioPreviousRevenue[selectStudio] = studioCurrentRevenue[selectStudio];
                        studioCurrentRevenue[selectStudio] = 0;
                    }

                    // Remove all movie and Pay-Per-View offerings
                    numOffers = 0;
                    offerStream = new String[LIMIT_OFFERS];
                    offerType = new String[LIMIT_OFFERS];
                    offerEventName = new String[LIMIT_OFFERS];
                    offerEventYear = new int[LIMIT_OFFERS];
                    offerEventPrice = new int[LIMIT_OFFERS];

                    // Reset the subscription counts for the month
                    watchGroupStreams = new int[LIMIT_DEMOS][LIMIT_STREAMS];

                } else if (tokens[0].equals("display_demo")) {
                    if (verboseMode) { System.out.println("display_demo_acknowledged"); }
                    selectDemo = -1;
                    for (int findItem = 0; findItem < numDemos; findItem++) {
                        if (demoShortName[findItem].equals(tokens[1])) {
                            selectDemo = findItem;
                        }
                    }
                    if (selectDemo < 0) { continue; }
                    System.out.println("demo," + demoShortName[selectDemo] + "," + demoLongName[selectDemo]);
                    System.out.println("size," + demoAccounts[selectDemo]);
                    System.out.println("current_period," + demoCurrentSpending[selectDemo]);
                    System.out.println("previous_period," + demoPreviousSpending[selectDemo]);
                    System.out.println("total," + demoTotalSpending[selectDemo]);

                } else if (tokens[0].equals("display_events")) {
                    if (verboseMode) { System.out.println("display_events_acknowledged"); }
                    for (selectEvent = 0; selectEvent < numEvents; selectEvent++) {
                        System.out.println(eventType[selectEvent] + "," + eventFullName[selectEvent] + "," + eventYear[selectEvent] + "," + eventDuration[selectEvent] + "," + eventStudioOwner[selectEvent] + "," + eventLicenseFee[selectEvent]);
                    }

                } else if (tokens[0].equals("display_stream")) {
                    if (verboseMode) { System.out.println("display_stream_acknowledged"); }
                    selectStream = -1;
                    for (int findItem = 0; findItem < numStreams; findItem++) {
                        if (streamShortName[findItem].equals(tokens[1])) {
                            selectStream = findItem;
                        }
                    }
                    if (selectStream < 0) { continue; }
                    System.out.println("stream," + streamShortName[selectStream] + "," + streamLongName[selectStream]);
                    System.out.println("subscription," + streamSubscription[selectStream]);
                    System.out.println("current_period," + streamCurrentRevenue[selectStream]);
                    System.out.println("previous_period," + streamPreviousRevenue[selectStream]);
                    System.out.println("total," + streamTotalRevenue[selectStream]);
                    System.out.println("licensing," + streamLicensing[selectStream]);

                } else if (tokens[0].equals("display_studio")) {
                    if (verboseMode) { System.out.println("display_studio_acknowledged"); }
                    selectStudio = -1;
                    for (int findItem = 0; findItem < numStudios; findItem++) {
                        if (studioShortName[findItem].equals(tokens[1])) {
                            selectStudio = findItem;
                        }
                    }
                    if (selectStudio < 0) { continue; }
                    System.out.println("studio," + studioShortName[selectStudio] + "," + studioLongName[selectStudio]);
                    System.out.println("current_period," + studioCurrentRevenue[selectStudio]);
                    System.out.println("previous_period," + studioPreviousRevenue[selectStudio]);
                    System.out.println("total," + studioTotalRevenue[selectStudio]);

                } else if (tokens[0].equals("display_offers")) {
                    if (verboseMode) { System.out.println("display_offers_acknowledged"); }
                    for (selectOffer = 0; selectOffer < numOffers; selectOffer++) {
                        System.out.print(offerStream[selectOffer] + "," + offerType[selectOffer] + "," + offerEventName[selectOffer] + "," + offerEventYear[selectOffer]);
                        if (offerType[selectOffer].equals("ppv")) {
                            System.out.print("," + offerEventPrice[selectOffer]);
                        }
                        System.out.println();
                    }

                } else if (tokens[0].equals("display_time")) {
                    if (verboseMode) { System.out.println("display_time_acknowledged"); }
                    System.out.println("time," + monthTimeStamp + "," + yearTimeStamp);

                } else if (tokens[0].equals("stop")) {
                    break;
                } else {
                    if (verboseMode) { System.out.println("command_" + tokens[0] + "_NOT_acknowledged"); }
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println();
            }
        }

        if (verboseMode) { System.out.println("stop_acknowledged"); }
        commandLineInput.close();
    }

}
