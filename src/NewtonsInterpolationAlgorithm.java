public class NewtonsInterpolationAlgorithm {
    public static double execute(double[] xs, double[] ys, double z){
        double[] cs = new double[xs.length];

        coeff(xs, ys, cs);

        return evalNewton(xs, cs, z);
    }

    private static void coeff(double[] xs, double[] ys, double[] cs){
        int n = xs.length;

        for(int i = 0; i < n; i++){
            cs[i] = ys[i];
        }

        for(int j = 1; j < n; j++){
            for(int i = n-1 ; i > j-1; i--){
                cs[i] = (cs[i] - cs[i-1]) / (xs[i] - xs[i-j]);
//                System.out.println("cs[" + i + "] = " + cs[i]);
            }
        }

//        System.out.println("Coefficients: " + print1DArray(cs));
    }

    private static double evalNewton(double[] xs, double[] cs, double z){
        int n = cs.length;
        double result = cs[n-1];

        for(int i = n-2; i >= 0; i--){
            result = result * (z - xs[i]) + cs[i];
        }

        return result;
    }

    private static String print1DArray(double[] array){
        StringBuilder output = new StringBuilder("{");

        for(int i = 0; i < array.length; i++){
            output.append(array[i]);
            output.append(i != array.length - 1 ? "," : "}");
        }

        return output.toString();
    }
}
