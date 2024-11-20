package MatrixUtils;

import static MatrixUtils.Math.sum1toN;
import static java.lang.Math.abs;

public class LowerMatrix extends AbstractMatrix{

    public LowerMatrix(int n) {
        super(n, sum1toN(n));
    }

    @Override
    protected int convertIJtoK(int i, int j) {
        return sum1toN(i - 1) + (j - 1);
    }

    @Override
    protected boolean isInArray(int i, int j) {
        return i >= j;
    }
}
