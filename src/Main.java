import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Assignment 6 P8.3 This program simulates car sharing
 * @author Sergei Chekhonin
 */
public class Main {
    /***
     * Number of repetitions of simulation
     */
    public static final int NUMBER_OF_REPETITIONS = 1000;
    /***
     * Main - enter point to the program
     */
    public static void main(String[] args) {
        //This block initialize printWriter and dateFormatter
        PrintWriter out = null;
        try {
            out = new PrintWriter("Assignment6_P8.3output.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        out.println(dtf.format(now));

        //reference of Simulation type
        Simulation simulation;

        //variable to store total revenue of all repetitions
        double totalAverageRevenue=0;

        //revenue counting loop
        for (int i=1; i<=NUMBER_OF_REPETITIONS;i++) {
            //create new simulation object
            simulation = new Simulation(out);
            totalAverageRevenue+=simulation.Run();
        }
        out.println("Total average revenue after " +NUMBER_OF_REPETITIONS+ " repetitions is "+totalAverageRevenue/NUMBER_OF_REPETITIONS);

        out.close();
        System.exit(0);

    }

}





