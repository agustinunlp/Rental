package src.main.test.rental;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import junit.framework.TestCase;
import src.main.java.domain.Bike;
import src.main.java.domain.FamilyRental;
import src.main.java.domain.InvalidFamilyRentalException;
import src.main.java.domain.InvalidRentalBikeException;
import src.main.java.domain.Rental;
import src.main.java.domain.RentalDaily;
import src.main.java.domain.RentalHourly;
import src.main.java.domain.RentalWeekly;

public class RentalTests extends TestCase {
    
  private static final String THE_BIKE_IS_NOT_AVAILABLE = "The bike is not available";
private Long hourlyExpectedPrice = 5l;
  private Long dailyExpectedPrice = 20l;
  private Long weeklyExpectedPrice = 60l;
  private Bike bike = new Bike("Montain", "bike all terrain", true);
  private Bike unavailableBike = new Bike("Montain", "bike all terrain", false);
    
    @Test
    public void testHourRental() throws InvalidRentalBikeException {        
        Rental rental = new Rental(bike, new RentalHourly());
        assertEquals(rental.calculatePromotion(), BigDecimal.valueOf(hourlyExpectedPrice));
    }
    
    @Test
    public void testHourRentalUnavailableBike() {        
        Rental rental = null;
        try {
            rental = new Rental(unavailableBike, new RentalHourly());
        } catch (InvalidRentalBikeException e) {
            assertEquals(e.getMessage(), THE_BIKE_IS_NOT_AVAILABLE);
        }
        assertNull(rental);
    }    
    
    @Test
    public void testDayRental() throws InvalidRentalBikeException {
        Rental rental = new Rental(bike, new RentalDaily());
        assertEquals(rental.calculatePromotion(), BigDecimal.valueOf(dailyExpectedPrice));
    }
    
    @Test
    public void testDailyRentalUnavailableBike() {        
        Rental rental = null;
        try {
            rental = new Rental(unavailableBike, new RentalDaily());
        } catch (InvalidRentalBikeException e) {
            assertEquals(e.getMessage(), THE_BIKE_IS_NOT_AVAILABLE);
        }
        assertNull(rental);
    }       
    
    @Test
    public void testWeekRental() throws InvalidRentalBikeException {
        Rental rental = new Rental(bike, new RentalWeekly());
        assertEquals(rental.calculatePromotion(), BigDecimal.valueOf(weeklyExpectedPrice));
    }
    
    @Test
    public void testWeeklyRentalUnavailableBike() {        
        Rental rental = null;
        try {
            rental = new Rental(unavailableBike, new RentalWeekly());
        } catch (InvalidRentalBikeException e) {
            assertEquals(e.getMessage(), THE_BIKE_IS_NOT_AVAILABLE);
        }
        assertNull(rental);
    }    
    

    @Test
    public void testFamilyRental() throws InvalidRentalBikeException, InvalidFamilyRentalException {
        List<Rental> rentals = new ArrayList<>();
        rentals.add(new Rental(bike, new RentalWeekly()));
        rentals.add(new Rental(bike, new RentalHourly()));
        rentals.add(new Rental(bike, new RentalDaily()));
        FamilyRental rental = new FamilyRental(rentals);
        assertEquals(rental.calculatePromotion(), new BigDecimal(59.50).setScale(2));
    }
        
    @Test
    public void testFamilyRentalLessRentals() throws InvalidRentalBikeException {
        List<Rental> rentals = new ArrayList<>();
        rentals.add(new Rental(bike, new RentalWeekly()));
        rentals.add(new Rental(bike, new RentalHourly()));
        FamilyRental rental = null;
        try {
            rental = new FamilyRental(rentals);
        } catch (InvalidFamilyRentalException e) {
            assertEquals(e.getMessage(), "The rental is invalid - The family promotion includes between 3 and 5 bikes");
        }
        assertNull(rental);
    }
    
    @Test
    public void testFamilyRentalMoreRentals() throws InvalidRentalBikeException {
        List<Rental> rentals = new ArrayList<>();
        rentals.add(new Rental(bike, new RentalWeekly()));
        rentals.add(new Rental(bike, new RentalHourly()));
        rentals.add(new Rental(bike, new RentalHourly()));
        rentals.add(new Rental(bike, new RentalHourly()));
        rentals.add(new Rental(bike, new RentalHourly()));
        rentals.add(new Rental(bike, new RentalHourly()));
        FamilyRental rental = null;
        try {
            rental = new FamilyRental(rentals);
        } catch (InvalidFamilyRentalException e) {
            assertEquals(e.getMessage(), "The rental is invalid - The family promotion includes between 3 and 5 bikes");
        }
        assertNull(rental);
    }
   

}
