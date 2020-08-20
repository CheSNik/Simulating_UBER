import java.util.ArrayList;

public class Car {

    private int startStation;
    private int destinationStation;
    private int milesWent=0;
    private ArrayList<Passenger> passengersInCar = new ArrayList<>();
    private ArrayList<Station> stations;

    /**
     * Car constructor
     * @param startStation sets car's starting station
     * @param destinationStation sets car's destination station
     * @param stations provide information about passengers at every station
     */
    public Car(int startStation, int destinationStation, ArrayList<Station> stations) {
        this.startStation = startStation;
        this.destinationStation = destinationStation;
        this.stations = stations;
        takePassenger(startStation);
    }

    /**
     * Grab passenger from the station if there is space in the car
     * and passenger destination point along the way with car's destination
     * @param station defines from what station car will take passengers
     */
    private void takePassenger(int station){
        //Retrieve data about passengers that are on the same station with the car
        if (stations.get(station-1).getPassengers().size()!=0) {
            ArrayList<Passenger> tempList = stations.get(station - 1).getPassengers();
            Passenger tempPass = null;

            //Choose and take passengers that have destination point on the car's way to destination point
            if (startStation <= destinationStation) {
                for (int i = tempList.size() - 1; i >= 0 && passengersInCar.size() != 3; i--) {
                    tempPass = tempList.get(i);
                    if (tempPass.getDestinationStation() > startStation && tempPass.getDestinationStation() <= destinationStation) {
                        passengersInCar.add(tempPass);
                        tempList.remove(tempPass);
                    }
                }
            } else {
                for (int i = tempList.size() - 1; i >= 0 && passengersInCar.size() != 3; i--) {
                    tempPass = tempList.get(i);
                    if (tempPass.getDestinationStation() < startStation && tempPass.getDestinationStation() >= destinationStation) {
                        passengersInCar.add(tempPass);
                        tempList.remove(tempPass);

                    }
                }
            }
        }

    }

    /**
     * Moving the car toward destination point taking into account destination points of passengers
     */
    public void goToNextStopStation(){
        int nextStation=0;
        if (passengersInCar.size()!=0 ) {
            //set default next station of zero's passenger
            nextStation = passengersInCar.get(passengersInCar.size() - 1).getDestinationStation();
            //check if other passengers have closer destination point
            for (int i = passengersInCar.size() - 2; i >= 0; i--) {
                //in case when car moves forward along the route
                if (startStation<destinationStation && nextStation > passengersInCar.get(i).getDestinationStation()) {
                    nextStation = passengersInCar.get(i).getDestinationStation();
                }
                //in case when car moves back direction along the route
                else if (startStation>destinationStation && nextStation < passengersInCar.get(i).getDestinationStation())
                    nextStation = passengersInCar.get(i).getDestinationStation();
            }
            milesWent += passengersInCar.size() * (Math.abs(startStation - nextStation));
        }
        else{

        }

        if (passengersInCar.size() ==0 || nextStation == destinationStation)
        startStation = destinationStation;
        else
        startStation = nextStation;

        dropPassenger(startStation);
        takePassenger(startStation);
    }

    /**
     * Drops passenger at his destination point upon arrival
     * @param dropStation defines at what station passenger should out of the car
     */
    private void dropPassenger(int dropStation) {

        for (int i=passengersInCar.size()-1; i>=0;i--){
            if (dropStation == passengersInCar.get(i).getDestinationStation()){
                stations.get(dropStation-1).getPassengers().add(passengersInCar.get(i));
                passengersInCar.remove(i);
            }
        }
    }

    /**
     * Computes miles the car went
     * @return miles went by certain car
     */
    public int getMilesWent(){
        return milesWent;
    }

    /**
     * Starting point of car wrapper
     * @return Starting point
     */
    public int getStartStation(){
        return startStation;
    }
    /**
     * Destination point of car wrapper
     * @return Destination point
     */
    public int getDestinationStation(){
        return destinationStation;
    }
}
