import MatrixUtils.LowerMatrix;
import MatrixUtils.Matrix;
import MatrixUtils.SymmetricMatrix;
import MatrixUtils.UpperMatrix;

import java.util.Arrays;

public class Solver {
    public static double[] solve(SymmetricMatrix a, double[] f) {
        int n = a.getN();

        Matrix b = new LowerMatrix(n);
        Matrix c = new UpperMatrix(n);
        double[] y = new double[n];
        double[] x = new double[n];

        /*
        TODO:
            1) заполнить b
            2) заполнить c
            3) заполнить y
            4) заполнить x
            5) вернуть x
         */

        // b
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                double sum = 0;
                for (int k = 1; k <= j - 1; k++) {
                    sum += b.get(i,k)*c.get(k,j);
                }
                b.set(i,j, a.get(i,j) - sum);
            }

            c.set(i,i, 1);
            for (int j = i + 1; j <= n; j++) {
                double diff = a.get(i,j);
                for (int k = 1; k <= i - 1; k++) {
                    diff -= b.get(i,k)*c.get(k,j);
                }
                c.set(i,j, diff / b.get(i,i));
            }
        }


        // y
        for (int i = 1; i <= n; ++i) {
           double diff = f[i-1];
           for(int k = 1; k <= i-1; k++) {
               diff -= b.get(i,k)*y[k-1];
           }
           y[i-1] = diff/b.get(i,i);
        }

        // x
        for(int i = n; i >= 1; --i) {
            double sum = 0;
            for(int k = i + 1; k <= n; k++) {
                sum += b.get(k,i)*x[k-1];
            }
            x[i-1] = y[i-1] - sum/b.get(i,i);
        }


        System.out.println("B full:");
        b.printFull();
        System.out.println("C full:");
        c.printFull();
        System.out.println("y: \n" + Arrays.toString(y));

        return x;
    }
}
