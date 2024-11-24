import MatrixUtils.Matrix;
import MatrixUtils.SymmetricMatrix;
import MatrixUtils.LowerMatrix;
import MatrixUtils.UpperMatrix;

import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        exampleRand(4, 3);
        //example1();

    }

    static void example1() {
        double[][] data = {
                new double[]{1, 5},
                new double[]{2, 6},
                new double[]{3, 7},
                new double[]{4, 0},
        };

        double[] x = new double[]{1, 2, 3, 4};
        testInput(data, x);
    }

    static void exampleRand(int n, int l) {
        exampleRand(n, l, 100d);
    }

    static void exampleRand(int n, int l, double limit) {
        if (l > n) {
            throw new RuntimeException("Error: l>n");
        }

        Random rd = new Random();
        double[][] data = new double[n][l];
        double[] x = new double[n];
        for (int i = 0; i < n; i++) {
            x[i] = rd.nextDouble(2*limit) - limit;
            for (int j = 0; j < l; j++) {
                while (data[i][j] == 0d) {
                    data[i][j] = rd.nextDouble(2 * limit) - limit; // [-limit, limit]
                }
            }
        }
        testInput(data, x);
    }

    static void testInput(double[][] data, double[] x_real) {
        SymmetricMatrix a = new SymmetricMatrix(data);
        System.out.println("A full:");
        a.printFull();

        double[] f = a.multiply(x_real);
        double[] x_solve = Solver.solve(a, f);
        double[] x_error = calcError(x_real, x_solve);

        System.out.println();
        System.out.println("x_solve: " + Arrays.toString(x_solve));
        System.out.println("x_real:  " + Arrays.toString(x_real));
        System.out.println("x_error:  " + Arrays.toString(x_error));
    }


    static double[] calcError(double[] x_real, double[] x_solve) {
        double[] error = new double[x_real.length];
        for (int i = 0; i < x_real.length; i++) {
            error[i] = x_real[i] - x_solve[i];
        }
        return error;
    }

    void testMatrix() {
        double[][] data = {
                new double[]{1, 5},
                new double[]{2, 6},
                new double[]{2, 7},
                new double[]{4, 0},
        };

        double[] x = new double[]{1, 2, 3, 4};
        Matrix a = new SymmetricMatrix(data);
        double[] f = a.multiply(x);


        // A test
        System.out.println("A full:");
        a.printFull();
        System.out.println("A data:");
        a.printData();

        // F test
        System.out.println("f: \n" + Arrays.toString(f));

        // B test
        Matrix b = new LowerMatrix(data.length);
        for (int i = 1; i <= data.length; i++) {
            for (int j = 1; j <= i; j++) {
                b.set(i, j, i * j);
            }
        }

        System.out.println("B full:");
        b.printFull();
        System.out.println("B data:");
        b.printData();

        // C test
        Matrix c = new UpperMatrix(data.length);
        for (int i = 1; i <= data.length; i++) {
            for (int j = data.length; j >= i; j--) {
                c.set(i, j, i * j);
            }
        }

        System.out.println("C full:");
        c.printFull();
        System.out.println("C data:");
        c.printData();
    }
}