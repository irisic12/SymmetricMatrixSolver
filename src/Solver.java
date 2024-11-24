import MatrixUtils.*;

import java.util.Arrays;

public class Solver {
    public static double[] solve(SymmetricMatrix a, double[] f) {

        int n = a.getN();
        int l = a.getL();

        Matrix b = new LowerLMatrix(n, l);
        double[] y = new double[n];
        double[] x = new double[n];

        // b
        for (int j = 1; j <= n; j++) {
            for (int i = j; i <= j + l - 1; i++) {
                double diff = a.get(i, j);
                for (int k = 1; k <= j - 1; k++) {
                    diff -= b.get(i, k) * b.get(j, k) / b.get(k, k);
                }
                b.set(i, j, diff);
            }

        }

        // y
        for (int i = 1; i <= n; i++) {
            double diff = f[i - 1];
            for (int k = 1; k <= i - 1; k++) {
                diff -= b.get(i, k) * y[k - 1];
            }
            y[i - 1] = diff / b.get(i, i);
        }

        // x
        for (int i = n; i >= 1; --i) {
            double sum = 0;
            for (int k = i + 1; k <= n; k++) {
                sum += b.get(k, i) * x[k - 1];
            }
            x[i - 1] = y[i - 1] - sum / b.get(i, i);
        }

        System.out.println("B full:");
        b.printFull();
        System.out.println("y: \n" + Arrays.toString(y));
        return x;
    }
}
