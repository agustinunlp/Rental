package src.main.java.domain;

import java.math.BigDecimal;

public class Rental implements IRental, IRentValidation{
    private Bike bike;
    private RentalType type;  
    
    public Rental(Bike bike, RentalType type) throws InvalidRentalBikeException {
        super();       
        this.bike = bike;
        this.type = type;
        validate();
    }
    public Bike getBike() {
        return bike;
    }
    public void setBike(Bike bike) {
        this.bike = bike;
    }
    public RentalType getType() {
        return type;
    }
    public void setType(RentalType type) {
        this.type = type;
    }
    @Override
    public BigDecimal calculatePromotion() {        
        return BigDecimal.valueOf(this.type.getAmount());
    }
    
    @Override
    public boolean validate() throws InvalidRentalBikeException {
        if(!bike.isAvailable()) {
            throw new InvalidRentalBikeException();
        }
        return true;
    }

}
