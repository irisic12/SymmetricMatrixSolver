package MatrixUtils;

import static MatrixUtils.Math.sum1toN;

public class UpperMatrix extends AbstractMatrix{

    public UpperMatrix(int n) {
        super(n, sum1toN(n));
    }

    @Override
    protected int convertIJtoK(int i, int j) {
        return sum1toN(j - 1) + (i - 1);
    }

    @Override
    protected boolean isInArray(int i, int j) {
        return j >= i;
    }
}
