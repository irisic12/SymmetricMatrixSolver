package MatrixUtils;

public class SymmetricMatrix extends AbstractMatrix {
    protected int l;

    public SymmetricMatrix(double[][] initData) {
        super(initData.length, initData.length * initData[0].length);
        this.l = initData[0].length;

        for (int i = 0; i < initData.length; i++) {
            for (int j = 0; j < initData[0].length; j++) {
                Pos pos = convertKtoIJ(l * i + j);
                if(pos.j() > n) break;
                set(pos.i(), pos.j(), initData[i][j]);
            }
        }
    }

    public SymmetricMatrix(int n, int l) {
        super(n, n*l);
    }

    @Override
    protected int convertIJtoK(int i, int j) {
        if (i > j) return convertIJtoK(j, i);
        return l * (i-1) + (j-i);
    }

    protected Pos convertKtoIJ(int k) {
        int i = k / l + 1;
        int j = i + k % l;
        return new Pos(i, j);
    }

    @Override
    protected boolean isInArray(int i, int j) {
        if (i > j) return isInArray(j, i);
        return j - i < l;
    }

    public int getL(){
        return l;
    }
}
