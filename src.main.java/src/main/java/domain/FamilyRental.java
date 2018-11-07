package src.main.java.domain;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.List;

public class FamilyRental implements IRental, IRentValidation{
    private static final int MAX_LIMIT = 5;
    private static final int MIN_LIMIT = 3;

    private List<Rental> rentals;
    
    public FamilyRental(List<Rental> rentals) throws InvalidFamilyRentalException {
        super();
        this.rentals = rentals;
        validate();
    }

    public List<Rental> getRentals() {
        return rentals;
    }
    
    public void addRental(Rental rental) {
        if(rentals.size() > MAX_LIMIT) {
            rentals.add(rental);
        }
    }

    @Override
    public BigDecimal calculatePromotion() {
        long sum = rentals.stream().mapToLong(rental->rental.getType().getAmount()).sum();
        BigDecimal discout = new BigDecimal(BigInteger.valueOf(30 * sum)).divide(new BigDecimal(100),2,RoundingMode.CEILING);
        return BigDecimal.valueOf(sum).subtract(discout);
    }

    @Override
    public boolean validate() throws InvalidFamilyRentalException {
        if (rentals==null || rentals.size() < MIN_LIMIT || rentals.size() > MAX_LIMIT) {
            throw new InvalidFamilyRentalException();            
        }
        return true;
    }
}
