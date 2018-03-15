package test;

import main.Flight;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class FlightTest {

    @Test
    void searchFlights() throws FileNotFoundException, ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = sdf.parse("15/03/2018");
        Flight tester = new Flight("LGW", new Date());

        // test for correct input
        ArrayList<Flight> flights = tester.searchFlights("LGW", date);
        assertEquals(4, flights.size());

        // test for nonexistent data
        flights = tester.searchFlights("FAK", date);
        assertEquals(0, flights.size());


    }

    @Test
    void getDayOfWeek() throws FileNotFoundException, ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = sdf.parse("15/03/2018");

        Flight tester = new Flight("LGW", date);
        assertEquals("4", tester.getDayOfWeek(date));
    }
}