import java.util.Scanner;

public class TestCaseOOReader {

  private StreamingWars sw;

  public TestCaseOOReader() {

    sw = new StreamingWars();
  }

  public void processInstructions(Boolean verboseMode) {
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
          if (verboseMode) {
            System.out.println("create_demo_acknowledged");
          }
          sw.createDemo(tokens[1], tokens[2], Integer.parseInt(tokens[3]));

        } else if (tokens[0].equals("create_studio")) {
          if (verboseMode) {
            System.out.println("create_studio_acknowledged");
          }
          sw.createStudio(tokens[1], tokens[2]);

        } else if (tokens[0].equals("create_event")) {
          if (verboseMode) {
            System.out.println("create_event_acknowledged");
          }
          sw.createEvent(tokens[1], tokens[2], Integer.parseInt(tokens[3]), Integer.parseInt(tokens[4]),
              tokens[5], Integer.parseInt(tokens[6]));

        } else if (tokens[0].equals("create_stream")) {
          if (verboseMode) {
            System.out.println("create_stream_acknowledged");
          }
          sw.createStream(tokens[1], tokens[2], Integer.parseInt(tokens[3]));

        } else if (tokens[0].equals("offer_movie") || tokens[0].equals("offer_ppv")) {
          if (verboseMode) {
            System.out.println(tokens[0] + "_acknowledged");
          }
          int eventPrice;
          if (tokens.length > 4) {
            eventPrice = Integer.parseInt(tokens[4]);
          } else {
            eventPrice = 0;
          }
          sw.offerEvent(tokens[0].substring(6), tokens[1], tokens[2], Integer.parseInt(tokens[3]),
              eventPrice);

        } else if (tokens[0].equals("watch_event")) {
          if (verboseMode) {
            System.out.println("watch_event_acknowledged");
          }
          String watchDemoGroup = tokens[1];
          int watchPercentage = Integer.parseInt(tokens[2]);
          String watchStream = tokens[3];
          String watchEventName = tokens[4];
          int watchEventYear = Integer.parseInt(tokens[5]);
          sw.watchEvent(watchDemoGroup, watchPercentage, watchStream, watchEventName, watchEventYear);

        } else if (tokens[0].equals("next_month")) {
          if (verboseMode) {
            System.out.println("next_month_acknowledged");
          }
          sw.incrMonth();

        } else if (tokens[0].equals("display_demo")) {
          if (verboseMode) {
            System.out.println("display_demo_acknowledged");
          }
          System.out.println(sw.getDemo(tokens[1]));

        } else if (tokens[0].equals("display_events")) {
          if (verboseMode) {
            System.out.println("display_events_acknowledged");
          }
          Event[] myEvents = sw.getEvents();
          for (int selectEvent = 0; selectEvent < myEvents.length; selectEvent++) {
            System.out.println(myEvents[selectEvent]);
          }

        } else if (tokens[0].equals("display_stream")) {
          if (verboseMode) {
            System.out.println("display_stream_acknowledged");
          }
          System.out.println(sw.getStream(tokens[1]));

        } else if (tokens[0].equals("display_studio")) {
          if (verboseMode) {
            System.out.println("display_studio_acknowledged");
          }
          System.out.println(sw.getStudio(tokens[1]));

        } else if (tokens[0].equals("display_offers")) {
          if (verboseMode) {
            System.out.println("display_offers_acknowledged");
          }
          Offer[] myOffers = sw.getOffers();
          for (int selectOffer = 0; selectOffer < myOffers.length; selectOffer++) {
            System.out.println(myOffers[selectOffer]);
          }

        } else if (tokens[0].equals("display_time")) {
          if (verboseMode) {
            System.out.println("display_time_acknowledged");
          }
          System.out.println(sw.getTime());

        } else if (tokens[0].equals("stop")) {
          break;
        } else {
          if (verboseMode) {
            System.out.println("command_" + tokens[0] + "_NOT_acknowledged");
          }
        }
      } catch (Exception e) {
        e.printStackTrace();
        System.out.println();
      }
    }

    if (verboseMode) {
      System.out.println("stop_acknowledged");
    }
    commandLineInput.close();
  }

}
