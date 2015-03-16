
/**
 * Basic Vector and Matrix functions
 *
 * @author Kareem
 * @version 1.0
 */
public class MatrixVectorFunctions {

    /**
     * Scale a matrix by a certain amount
     *
     * @param a The matrix to be scaled
     * @param b The vector to scale it by
     * @return The scaled vector
     */
    public static double[][] matrixScaling(double[][] a, double b) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                a[i][j] *= b;
            }

        }
        return a;
    }
    
    /**
     * Simple vector addition
     * 
     * @param a The first vector
     * @param b The second vector
     * @return  The combined vector
     */
    public static double[] vectorAddition(double[] a, double[] b) {
        if (!(a.length == b.length)) {
            throw new IllegalArgumentException("Vectors are not the same size");
        }
        double[] c = new double[a.length];
        for (int i = 0; i < a.length; i++) {
            c[i] = a[i]+b[i];
        }
        return c;
    }
    
    /**
     * Simple vector subtraction
     * 
     * @param a The first vector
     * @param b The second vector
     * @return  The combined vector
     */
    public static double[] vectorSubtraction(double[] a, double[] b) {
        if (!(a.length == b.length)) {
            throw new IllegalArgumentException("Vectors are not the same size");
        }
        double[] c = new double[a.length];
        for (int i = 0; i < a.length; i++) {
            c[i] = a[i]-b[i];
        }
        return c;
    }

    /**
     * Transposes the matrix
     *
     * @param a The matrix to be transposed
     * @return The transposed matrix
     */
    public static double[][] matrixTranspose(double[][] a) {
        double[][] transpose = new double[a[0].length][a.length];
        for (int i = 0; i < transpose.length; i++) {
            for (int j = 0; j < transpose[i].length; j++) // A place in the "transpose" matrix gets assigned the opposit place
            // in the "pent" matrix.
            {
                transpose[i][j] = a[j][i];
            }
        }
        return transpose;
    }

    /**
     * Gets the vector length
     *
     * @param a The vector to be measured
     * @return The length of the vector
     */
    public static double vectorLength(double[] a) {
        double tmp = 0;
        for (int i = 0; i < a.length; i++) {
            tmp += a[i] * a[i];
        }

        return Math.sqrt(tmp);
    }

    /**
     * Gets the cross product of 2 vectors
     *
     * @param a The first vector
     * @param b The second vector
     * @return Returns the cross product
     */
    public static double[] crossProduct(double[] a, double[] b) {
        if (a.length != 3 || b.length != 3) {
            throw new IllegalArgumentException("Vector is not in R3");
        }
        double[] tmp = new double[a.length];
        tmp[0] = a[1] * b[2] - b[1] * a[2];
        tmp[1] = a[2] * b[0] - b[2] * a[0];
        tmp[2] = a[0] * b[1] - b[0] * b[1];
        return tmp;

    }

    /**
     * Gets the angle between a 2D vector
     *
     * @param a The vector to be measured
     * @param b The vector to be measured
     * @return The angle in radians between the 2 vector
     */
    public static double vectorAngle2D(double[] a, double[] b) {
        return Math.asin((dotProduct(a, b)) / (vectorLength(a) * vectorLength(b)));
    }

    /**
     * The angle between a 3D vector
     *
     * @param a The vector to be measured
     * @param b The vector to be measured
     * @return The angle in radians between the 2 vectors
     */
    public static double[] vectorAngle3D(double[] a, double[] b) {
        double[] normA = vectorNormalize(a);
        double[] normB = vectorNormalize(b);

        double angle = vectorAngle2D(normA, normB);
        if (Math.abs(angle) < 0.0001) {
            return new double[]{0, 0, 1};
        }
        if ((Math.abs(angle) - Math.PI) < 0.001) {
            normA = rotation(new double[]{0.5, 0, 0}, normA);
        }
        double[] axis = normA;
        axis = crossProduct(axis, normA);
        return new double[]{axis[0], axis[1], axis[2], angle};
    }

    /**
     * Rotates a vector by a certain amount
     *
     * @param x The vector to be rotated by
     * @param a The amount we want to rotate it by in radians
     * @return Returns the rotated vector
     */
    public static double[] rotation(double[] x, double[] a) {
        double[][] Rx = {
            {1, 0, 0},
            {0, Math.cos(x[0]), -Math.sin(x[0])},
            {0, Math.sin(x[0]), Math.cos(x[0])}};

        double[][] Ry = {
            {Math.cos(x[1]), 0, Math.sin(x[1])},
            {0, 1, 0},
            {-Math.sin(x[1]), 0, Math.cos(x[1])}};

        double[][] Rz = {
            {Math.cos(x[2]), -Math.sin(x[2]), 0},
            {Math.sin(x[2]), Math.cos(x[2]), 0},
            {0, 0, 1}};

        double[][] Rxy = multipliMatrix(Rx, Ry);
        double[][] Rxyz = multipliMatrix(Rxy, Rz);
        return multipliVect(Rxyz, a);

    }

    /**
     * Gets the dotProduct between 2 vector
     *
     * @param a The first vector to be measured
     * @param b The second vector to be measured
     * @return Returns the dot product
     */
    public static double dotProduct(double[] a, double[] b) {
        if (a.length != b.length) {
            throw new IllegalArgumentException("Vector are not the same size");
        }

        double tmp = 0;
        for (int i = 0; i < a.length; i++) {
            tmp += a[i] * b[i];
        }
        return tmp;
    }

    /**
     * Returns a normalized vector
     *
     * @param a The vector to be normalized
     * @return The normalized vector
     */
    public static double[] vectorNormalize(double[] a) {
        if (a.length == 0) {
            throw new IllegalArgumentException("Vector is empty");
        }

        double tmp = vectorLength(a);
        for (int i = 0; i < a.length; i++) {
            a[i] = a[i] / tmp;
        }
        return a;
    }

    /**
     * Multiply 2 matrices
     *
     * @param A The first Matrix
     * @param B The 2nd Matrix
     * @return The Matrix which has been calculated
     */
    public static double[][] multipliMatrix(double[][] A, double[][] B) {

        int aRows = A.length;
        int aColumns = A[0].length;
        int bRows = B.length;
        int bColumns = B[0].length;

        if (aColumns != bRows) {
            throw new IllegalArgumentException("A:Rows: " + aColumns + " did not match B:Columns " + bRows + ".");
        }

        double[][] C = new double[aRows][bColumns];

        for (int i = 0; i < aRows; i++) {
            for (int j = 0; j < bColumns; j++) {
                C[i][j] = 0.00000;
            }
        }

        for (int i = 0; i < aRows; i++) { // aRow
            for (int j = 0; j < bColumns; j++) { // bColumn
                for (int k = 0; k < aColumns; k++) { // aColumn
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }

        return C;
    }

    /**
     * Multiply a matrix by vector
     *
     * @param A The matrix
     * @param B The vector
     * @return Returns the calculated vector
     */
    public static double[] multipliVect(double[][] A, double[] B) {

        int aRows = A.length;
        int aColumns = A[0].length;
        int bRows = B.length;

        if (aColumns != bRows) {
            throw new IllegalArgumentException("A:Rows: " + aColumns + " did not match B:Columns " + bRows + ".");
        }

        double[] C = new double[aRows];

        for (int i = 0; i < aRows; i++) {
            C[i] = 0.00000;
        }

        for (int i = 0; i < aRows; i++) { // aRow
            for (int k = 0; k < aColumns; k++) { // aColumn
                C[i] += A[i][k] * B[k];
            }
        }

        return C;
    }
}
