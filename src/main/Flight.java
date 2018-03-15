package main;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.io.File;
import java.util.Calendar;

/**
 * Class representing characteristics of a flight.
 * Allows for searching a csv of flight data by airport and date.
 */
public class Flight
{
    // default file path to csv containing flight data.
    private static final String DEFAULT_FLIGHT_DATA_FILE = "flightData.csv";
    // Array of flights
    private static ArrayList<Flight> flights = new ArrayList<>();

    // flight characteristics
    private String flightCode;
    private String originAirport;
    private String destinationAirport;
    private String weekdays;
    private int departureTime;
    private int arrivalTime;
    private String airline;
    private int price;
    private String flightClass;

    /**
     * Constructor for adding flights in flight data
     * that match the given airport and date
     *
     * @param airport the airport code to search for
     * @param date the date to search for
     * @throws FileNotFoundException if path to flight data file not found
     */
    public Flight(String airport, Date date) throws FileNotFoundException {
        flights.addAll(searchFlights(airport, date));
    }

    /**
     * Constructor for an individual flight
     * Adds constructed flight to static flights list.
     *
     * @param flightCode
     * @param originAirport
     * @param destinationAirport
     * @param weekdays
     * @param departureTime
     * @param arrivalTime
     * @param airline
     * @param price
     * @param flightClass
     */
    public Flight(String flightCode, String originAirport, String destinationAirport,
                  String weekdays, int departureTime, int arrivalTime,
                  String airline, int price, String flightClass)
    {
        this.flightClass = flightClass;
        this.flightCode = flightCode;
        this.originAirport = originAirport;
        this.destinationAirport = destinationAirport;
        this.weekdays = weekdays;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.airline = airline;
        this.price = price;
        this.flightClass = flightClass;
    }

    /**
     * Search for flights using on airport and data.
     * @param requestedAirport origin or destination airport to search for
     * @param requestedDate date of flight to search for
     * @return ArrayList of Flight objects
     * @throws FileNotFoundException if path to flight data is not found
     */
    public ArrayList<Flight> searchFlights(String requestedAirport, Date requestedDate) throws FileNotFoundException {
        ArrayList<Flight> matchingFlights = new ArrayList<>();
        String requestedDayOfWeek = getDayOfWeek(requestedDate);

        Scanner scanner = new Scanner( new File(DEFAULT_FLIGHT_DATA_FILE));
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            // remove whitespace, split by comma
            String[] fields = line.replaceAll("\\s+","").split(",");

            // get flight characteristics needed for matching
            String originAirport = fields[1];
            String destinationAirport = fields[2];
            String weekdays = fields[3];

            // check if requested airport and date matches
            if ((requestedAirport.equals(originAirport) || requestedAirport.equals(destinationAirport))
                    && weekdays.contains(requestedDayOfWeek)) {

                    // get the rest of the flight characteristics
                    String flightCode = fields[0];
                    int departureTime = Integer.parseInt(fields[4]);
                    int arrivalTime = Integer.parseInt(fields[5]);
                    String airline = fields[6];
                    int price = Integer.parseInt(fields[7]);
                    String flightClass = fields[8];

                    // instantiate flight and add to ArrayList for matching flights
                    Flight flight = new Flight(
                            flightCode, originAirport, destinationAirport,
                            weekdays, departureTime, arrivalTime,
                            airline, price, flightClass
                    );
                    matchingFlights.add(flight);
                }
            }

        return matchingFlights;
    }

    /**
     * Get the day of the week of a given date
     * @param date A date to be processed.
     * @return A string of length 1, containing the day of week.
     */
    public String getDayOfWeek(Date date)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        // Calendar.DAY_OF_WEEK is zero-based
        // Minus one to make one-based like flight data
        return Integer.toString(dayOfWeek - 1);
    }


    /**
     * Override Object's toString method for flight class
     *
     * @return A string containing all relevant flight data
     */
    @java.lang.Override
    public String toString() {
        return "main.Flight{" +
                "flightCode='" + flightCode + '\'' +
                ", originAirport='" + originAirport + '\'' +
                ", destinationAirport='" + destinationAirport + '\'' +
                ", weekdays=" + weekdays +
                ", departureTime=" + departureTime +
                ", arrivalTime=" + arrivalTime +
                ", airline='" + airline + '\'' +
                ", price=" + price +
                ", flightClass='" + flightClass + '\'' +
                '}';
    }

    public static ArrayList<Flight> getFlights() { return flights; }

    public String getFlightCode() { return flightCode; }

    public String getOriginAirport() { return originAirport; }

    public String getDestinationAirport() { return destinationAirport; }

    public String getWeekdays() { return weekdays; }

    public int getDepartureTime() { return departureTime; }

    public int getArrivalTime() { return arrivalTime; }

    public String getAirline() { return airline; }

    public int getPrice() { return price; }

    public String getFlightClass() { return flightClass; }

}