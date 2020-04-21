import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class RandomDatasetGenerator {
    public static void main(String[] args) {
        try{
            Scanner userInputScanner = new Scanner(System.in);
            System.out.print("Enter a positive integer value for the amount of points you would like to randomly generate: ");

            String userInput = "";
            int n = -1;
            boolean done = false;

            do {
                try {
                    userInput = userInputScanner.next();
                    n = Integer.parseInt(userInput);
                } catch (NumberFormatException e) {
                    userInput = userInput.toLowerCase();
                    if (!userInput.equals('q')) {
                        System.out.println("That is not a number! Try again or 'q' to exit.");
                    } else {
                        done = true;
                    }
                }

            } while (n < 0 && !done);

            StringBuilder xValues = new StringBuilder();
            StringBuilder yValues = new StringBuilder();
            Random random = new Random();
            random.setSeed(System.currentTimeMillis());

            for(int i = 0; i < n; i++){
                // create x values through iteration
                // create y values through randomization
                int xValue = i;
                int yValue = random.nextInt(n - (n * -1) + 1) + (n * -1);

                if(i == 0){
                    xValues.append(xValue);
                    yValues.append(yValue);
                } else {
                    xValues.append(" " + xValue);
                    yValues.append(" " + yValue);
                }

            }

            xValues.append("\n");

            String filename = "dataset" + n + ".txt";
            String path = System.getProperty("user.dir") + "\\" +  filename;
            System.out.println(path);
            File file = new File(path);
            FileWriter fileWriter = new FileWriter(file);

            System.out.println("Writing the following values: ");
            System.out.println("X: " + xValues.toString() + "\nY: " + yValues.toString());

            fileWriter.write(xValues.toString());
            fileWriter.write(yValues.toString());
            fileWriter.close();
            System.out.println("File (" + filename + ") generated.");

        }catch(IOException e){
            System.out.println(e);
        }
    }
}
