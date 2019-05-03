
public class Matrix {
  public static int[][] matrixAdd(int[][] a, int[][] b) {
    int n = a.length;
    int m = a[0].length;
    int[][] res = new int[n][m];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        res[i][j] = a[i][j] + b[i][j];
      }
    }
    return res;
  }
  public static long[][] matrixMul(long[][] a, long[][] b, long mod) {
    int n = a.length;
    int m = a[0].length;
    int k = b[0].length;
    long[][] res = new long[n][k];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < k; j++) {
        for (int p = 0; p < m; p++) {
          res[i][j] = (res[i][j] + a[i][p] * b[p][j] % mod + mod) % mod;
        }
      }
    }
    return res;
  }
  public static long[][] matrixPow(long[][] a, long p, long mod) {
    if (p == 0) {
      return matrixUnitLong(a.length);
    } else if (p % 2 == 0) {
      return matrixPow(matrixMul(a, a, mod), p / 2, mod);
    } else {
      return matrixMul(a, matrixPow(a, p - 1, mod), mod);
    }
  }
  public static int[][] matrixPowSum(int[][] a, int p) {
    int n = a.length;
    if (p == 0) {
      return new int[n][n];
    }
    if (p % 2 == 0) {
      return matrixMul(matrixPowSum(a, p / 2), matrixAdd(matrixUnit(n), matrixPow(a, p / 2)));
    } else {
      return matrixAdd(a, matrixMul(matrixPowSum(a, p - 1), a));
    }
  }
  public static int[][] matrixUnit(int n) {
    int[][] res = new int[n][n];
    for (int i = 0; i < n; ++i) {
      res[i][i] = 1;
    }
    return res;
  }
}
