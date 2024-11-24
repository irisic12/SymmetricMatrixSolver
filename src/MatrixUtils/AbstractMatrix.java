package MatrixUtils;

abstract class AbstractMatrix implements Matrix {

    protected final double[] arr;
    protected int n;

    protected AbstractMatrix(int n, int arrSize) {
        arr = new double[arrSize];
        this.n = n;
    }

    protected void checkIndexOrThrow(int i, int j) {
        if (i < 1 || j < 1 || i > n || j > n) {
            throw new IndexOutOfBoundsException(String.format(
                    "(1..%d, 1..%d) expected, but given: (%d, %d)",
                    n, n, i, j
            ));
        }
    }

    protected record Pos(int i, int j) {
    }

    abstract protected int convertIJtoK(int i, int j);

    abstract protected boolean isInArray(int i, int j);

    @Override
    public double get(int i, int j) {
        checkIndexOrThrow(i, j);
        if (!isInArray(i, j)) {
            return 0;
        }
        int k = convertIJtoK(i, j);
        return arr[k];
    }

    @Override
    public void set(int i, int j, double value) {
        checkIndexOrThrow(i, j);
        if (!isInArray(i, j)) {
            throw new IndexOutOfBoundsException(String.format(
                    "Unexpected index pos to set (%d, %d)",
                    i, j));
        }
        int k = convertIJtoK(i, j);
        arr[k] = value;
    }

    @Override
    public int getN() {
        return n;
    }

    @Override
    public void printFull() {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                System.out.printf("%-25s", get(i, j));
            }
            System.out.println();
        }
    }

    @Override
    public void printData() {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    @Override
    public double[] multiply(double[] vec) {
        if (vec.length != n) {
            throw new ArrayIndexOutOfBoundsException("Vector and matrix have to be same size");
        }
        double[] res = new double[n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                res[i] += vec[j] * get(i + 1, j + 1);
            }
        }

        return res;
    }
}
