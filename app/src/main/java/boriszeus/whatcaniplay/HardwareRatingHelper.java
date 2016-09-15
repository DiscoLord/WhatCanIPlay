package boriszeus.whatcaniplay;


public class HardwareRatingHelper {


    private String name;
    private int rating;


    public HardwareRatingHelper(String name,int rating) {
        this.name = name;
        this.rating=rating;
    }

    public int getRating() {
        return rating;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
