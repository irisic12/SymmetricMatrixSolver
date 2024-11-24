package MatrixUtils;

public class LowerLMatrix extends AbstractMatrix {
    protected int l;

    public LowerLMatrix(int n, int l) {
        super(n, n * l);
        this.l = l;
    }

    @Override
    protected int convertIJtoK(int i, int j) {
        return (j - 1) * l + i - j;
    }

    @Override
    protected boolean isInArray(int i, int j) {
        return i - j < l && i >= j;
    }

    public int getL() {
        return l;
    }
}
