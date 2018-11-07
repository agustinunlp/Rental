package src.main.java.domain;

public class InvalidFamilyRentalException extends Exception {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Override
    public String getMessage() {
        return "The rental is invalid - The family promotion includes between 3 and 5 bikes";
    }

}
