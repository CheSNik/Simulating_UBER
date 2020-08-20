import java.util.ArrayList;

public class Station {
    /**
     * stores station's name
     */
    private int Name;
    /**
     * stores references to passengers
     */
    private ArrayList<Passenger> passengers;
    /**
     * stores references to cars
     */
    private ArrayList<Car> cars;

    /**
     * Station constructor
     * @param name sets name of the station
     * @param passengers sets reference to ArrayList object of passenger
     */
    public Station(int name, ArrayList<Passenger> passengers) {
        Name = name;
        this.passengers = passengers;

    }

    /**
     * Station name wrapper
     * @return name of station
     */
    public int getName() {
        return Name;
    }
    /**
     * ArrayList of cars wrapper
     * @return array of cars
     */
    public ArrayList<Car> getCars() {
        return cars;
    }
    /**
     * Sets cars to the station
     */
    public void setCars (ArrayList<Car> cars){
        this.cars = cars;
    }
    /**
     * ArrayList of passengers wrapper
     * @return array of passengers
     */
    public ArrayList<Passenger> getPassengers() {
        return passengers;
    }
}
