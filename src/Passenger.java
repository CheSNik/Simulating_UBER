public class Passenger {

    /**
     * stores passengers destination station
     */
    private int destinationStation;

    /**
     * Passenger object constructor
     * @param destinationStation sets passengers destination station
     */
    public Passenger(int destinationStation) {

        this.destinationStation = destinationStation;
    }

    /**
     * Destination point of passenger wrapper
     * @return Destination point
     */
    public int getDestinationStation() {
        return destinationStation;
    }
}
