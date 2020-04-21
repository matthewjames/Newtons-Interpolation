// Write a program, in the language of your preference,
// that is used to compute Newton's interpolation. The
// program should take as input a file that contains the
// data points in the following format:
//
//        x0  x1  x2  ...  xn
//        y0  y1  y2  ...  yn
//
// that is, the first rows contains the first element of
// each data point, and the next row the second one.
// After processing the input, the program will provide
// a prompt asking of a value to be used to evaluate the
// polynomial and print the result of such evaluation,
// going back to the prompt. Entering 'q' instead of a
// real number will exit the program. Use your program
// to with the example in the exercises above to check
// your solution.


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class NewtonsInterpolation {

    public static void main(String[] args){
        Scanner fileScanner;
        String path = System.getProperty("user.dir") + "\\";
        ArrayList<Double> x = new ArrayList<>();
        ArrayList<Double> y = new ArrayList<>();

        if (args[0] != null) {
            try{
                File file = new File(path + args[0]);
                fileScanner = new Scanner(file);
                boolean firstLine = true;

                while(fileScanner.hasNextLine()){
                    String line = fileScanner.nextLine();
//                    System.out.println("Input Line: " + line);
                    Scanner lineScanner = new Scanner(line);
                    while(lineScanner.hasNext()){
                        String value = lineScanner.next();
//                        System.out.println(value);
                        if(firstLine){
                            x.add(Double.parseDouble(value));
                        } else {
                            y.add(Double.parseDouble(value));
                        }
                    }
                    firstLine = false;
                }

//                System.out.println(x.toString());
//                System.out.println(y.toString());
            }catch(IOException e){
                System.out.println(e);
            }
        }

        double[] xs = new double[x.size()];
        double[] ys = new double[y.size()];

        for(int i = 0; i < x.size(); i++){
            xs[i] = x.get(i);
            ys[i] = y.get(i);
        }

        Scanner userInputScanner = new Scanner(System.in);
        String userInput = "";
        boolean done = false;

        do {
            System.out.print("Enter a value to evaluate: ");
            userInput = userInputScanner.next();

            try{
                double z = Double.parseDouble(userInput);

                long start = System.nanoTime();
                double result = NewtonsInterpolationAlgorithm.execute(xs,ys,z);
                long end = System.nanoTime();

                System.out.println("Evaluation of " + z + " in the polynomial: " + result + ".\nRuntime: " + (end - start) + "ns");
            }catch(NumberFormatException e){
                userInput = userInput.toLowerCase();
                if(!userInput.equals('q')){
                    done = true;
                } else {
                    System.out.println("Thats not a number!");
                }
            }
        } while (!done);

    }
}
