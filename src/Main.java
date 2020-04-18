public class Main {

    public static void main(String[] args) {
        // write your code here
    }

    private static void coeff(float[] xs, float[] ys, float[] cs){
        int n = cs.length;

        for(int i = 0; i < n; i++){
            cs[i] = ys[i];
        }

        for(int j = 1; j < n; j++){
            for(int i = n; i > j; i--){
                cs[i] = (cs[i] - cs[i-1]) / (xs[i] - xs[i-j]);
            }
        }
    }

    private static float evalNewton(float[] xs, float[] cs, float z){
        int n = cs.length;
        float result = cs[n];

        for(int i = n-1; i > 0; i--){
            result = result * (z - xs[i]) + cs[i];
        }

        return result;
    }
}
