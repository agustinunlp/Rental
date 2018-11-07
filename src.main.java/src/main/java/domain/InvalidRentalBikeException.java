package src.main.java.domain;

public class InvalidRentalBikeException extends Exception {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Override
    public String getMessage() {
        return "The bike is not available";
    }

}
