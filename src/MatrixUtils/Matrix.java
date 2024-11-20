package MatrixUtils;

public interface Matrix {
    double get(int i, int j);
    void set(int i, int j, double value);
    int getN();
    void printFull();
    void printData();
    double[] multiply(double[] vec);
}