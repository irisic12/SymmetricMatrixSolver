package MatrixUtils;

public class FullMatrix extends AbstractMatrix{
    public FullMatrix(int n) {
        super(n, n*n);
    }

    @Override
    protected int convertIJtoK(int i, int j) {
        return (i - 1) * n + j - 1;
    }

    @Override
    protected boolean isInArray(int i, int j) {
        return true;
    }
}
