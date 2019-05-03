public class TernarySearch {
  public static double ternarySearch(DoubleUnaryOperator f, double lo, double hi) {
    for (int step = 0; step < 1000; step++) {
      double m1 = lo + (hi - lo) / 3;
      double m2 = hi - (hi - lo) / 3;
      if (f.applyAsDouble(m1) < f.applyAsDouble(m2))
        lo = m1;
      else
        hi = m2;
    }
    return (lo + hi) / 2;
  }
  public static int ternarySearch(IntUnaryOperator f, int fromInclusive, int toInclusive) {
    int lo = fromInclusive;
    int hi = toInclusive;
    while (hi > lo + 2) {
      int m1 = lo + (hi - lo) / 3;
      int m2 = hi - (hi - lo) / 3;
      if (f.applyAsInt(m1) < f.applyAsInt(m2))
        lo = m1;
      else
        hi = m2;
    }
    int res = lo;
    for (int i = lo + 1; i <= hi; i++)
      if (f.applyAsInt(res) < f.applyAsInt(i))
        res = i;
    return res;
  }

  public static int ternarySearch2(IntUnaryOperator f, int fromInclusive, int toInclusive) {
    int lo = fromInclusive - 1;
    int hi = toInclusive;
    while (hi - lo > 1) {
      int mid = (lo + hi) >>> 1;
      if (f.applyAsInt(mid) < f.applyAsInt(mid + 1)) {
        lo = mid;
      } else {
        hi = mid;
      }
    }
    return hi;
  }
}
