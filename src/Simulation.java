import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

public class Simulation{

    /**
     * collection of stations
     */
    private ArrayList<Station> stations= new ArrayList<>();
    /**
     * output parameter
     */
    private double averageRevenue =0;
    /**
     * Printwriter reference
     */
    private PrintWriter out;
    /**
     * initial constant number of stations
     */
    private final int NUMBEROFSTATIONS = 30;
    /**
     * Range of random number of passengers/cars
     */
    private final int MAX_NUMBER_OF_CARS_AND_PASS_AT_STATION = 100;
    /**
     * initial constant price per mile
     */
    private final double PRICE_PER_MILE = 1.2;
    /**
     * output data for information
     */
    private int totalPassengersPerSimulation;
    /**
     * output data for information
     */
    private int totalCarsPerSimulation;
    /**
     * output data for information
     */
    private static int counter=0;

    /**
     * Constructor of Simulation object
     * @param out reference of PrintWriter
     */
    public Simulation(PrintWriter out) {
        this.out = out;
    }

       /***
     * Method runs the simulation. Creates passengers, cars, stations and asks cars to move toward destination
     * @return average value of revenue collected by all cars
     */
    public double Run(){
        counter++;
        //create stations and set passengers and cars
        createStations();

        //start moving cars up to final destination point
        moveCars();

        //calculates total miles went by all cars
        for (Station station : stations)
        {
            for (Car car : station.getCars()) {
                averageRevenue+=PRICE_PER_MILE*car.getMilesWent()/station.getCars().size();
            }
        }
        out.println("Average revenue is "+ averageRevenue);
        return averageRevenue;
    }

    //Create stations with passengers. No cars created.
    private void createStations(){
//        //Test code
//        ArrayList<Passenger> passengersOnStation1 = new ArrayList<>();
//        passengersOnStation1.add(new Passenger(1));
//        passengersOnStation1.add(new Passenger(1));
//        passengersOnStation1.add(new Passenger(1));
//        //Create station and add it to a stations list
//        stations.add(new Station(1, passengersOnStation1));
//
//
//        ArrayList<Passenger> passengersOnStation2 = new ArrayList<>();
//        passengersOnStation2.add(new Passenger(2));
//        passengersOnStation2.add(new Passenger(2));
//        passengersOnStation2.add(new Passenger(2));
//        //passengersOnStation2.add(new Passenger(4));
//        //Create station and add it to a stations list
//        stations.add(new Station(2, passengersOnStation2));
//
//        ArrayList<Passenger> passengersOnStation3 = new ArrayList<>();
//        passengersOnStation3.add(new Passenger(3));
//        passengersOnStation3.add(new Passenger(2));
//        passengersOnStation3.add(new Passenger(1));
//        //passengersOnStation3.add(new Passenger(4));
//        //Create station and add it to a stations list
//        stations.add(new Station(3, passengersOnStation3));
//
//        ArrayList<Passenger> passengersOnStation4 = new ArrayList<>();
//        passengersOnStation4.add(new Passenger(3));
//        passengersOnStation4.add(new Passenger(2));
//        passengersOnStation4.add(new Passenger(1));
//        //Create station and add it to a stations list
//        stations.add(new Station(4, passengersOnStation4));
        Random rnd = new Random();
        //create 30 stations
        for (int i = 1; i<=NUMBEROFSTATIONS; i++) {
            int randNumPass = rnd.nextInt(100)+1;

            //create passengers for the i station
            ArrayList<Passenger> passengersOnStation = new ArrayList<>();
            for (int c = 1; c<= randNumPass;c++) {
                int randDestPass = rnd.nextInt(NUMBEROFSTATIONS) + 1;
                //collect all the passengers to an array
                passengersOnStation.add(new Passenger(randDestPass));
            }
            //Create station and add it to a stations list
            totalPassengersPerSimulation+=passengersOnStation.size();
            stations.add(new Station(i, passengersOnStation));
        }

//        for (Station station : stations)
//        {
//            //Test code
//            //create 1 car at each station
//            ArrayList<Car> carsOnStation = new ArrayList<>();
//            carsOnStation.add(new Car(station.getName(), 1, stations));
//
//            //set cars to certain station
//            station.setCars(carsOnStation);
//        }
        //Create cars for each station, call takePassenger from ctor
        //creates cars for each station, separated of written above because
        // of need to provide information of passengers for cars
        for (Station station : stations)
        {
            int randNumCars = rnd.nextInt(MAX_NUMBER_OF_CARS_AND_PASS_AT_STATION)+1;
            //create array for cars at each station
            ArrayList<Car> carsOnStation = new ArrayList<>();
            for (int c = 1; c<= randNumCars;c++)
            {
                int StartCar = station.getName();
                int randDestCar = rnd.nextInt(NUMBEROFSTATIONS)+1;
                //Creation of cars and set passengers from certain station to the car
                carsOnStation.add(new Car(StartCar, randDestCar, stations));
            }
            //set cars to certain station
            totalCarsPerSimulation+=carsOnStation.size();
            station.setCars(carsOnStation);
        }
        out.println("Simulation #"+counter);
        out.println("Was created " + totalPassengersPerSimulation+ " passengers");
        out.println("Was created " + totalCarsPerSimulation+ " cars");
    }

    /***
     * For each car calls method to move towards the destination.
     */
    private void moveCars(){
        //for each car at every station call method to move its way to destination
        for (Station station : stations)
        {
            for (Car car : station.getCars()) {
                while (car.getStartStation() != car.getDestinationStation())
                    car.goToNextStopStation();

            }
        }
    }
}
