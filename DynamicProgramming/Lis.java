class Lis {
  public class Lis2 {

    public static int[] lis(int[] a) {
      int n = a.length;
      int[] tail = new int[n];
      int[] prev = new int[n];

      int len = 0;
      for (int i = 0; i < n; i++) {
        int pos = lower_bound(a, tail, len, a[i]);
        len = Math.max(len, pos + 1);
        prev[i] = pos > 0 ? tail[pos - 1] : -1;
        tail[pos] = i;
      }

      int[] res = new int[len];
      for (int i = tail[len - 1]; i >= 0; i = prev[i]) {
        res[--len] = a[i];
      }
      return res;
    }

    static int lower_bound(int[] a, int[] tail, int len, int key) {
      int lo = -1;
      int hi = len;
      while (hi - lo > 1) {
        int mid = (lo + hi) >>> 1;
        if (a[tail[mid]] <= key) {
          lo = mid;
        } else {
          hi = mid;
        }
      }
      return hi;
    }
    public static int lisSize(int[] a) {
      NavigableSet<Integer> s = new TreeSet<>();
      for (int v : a)
        if (s.add(v)) {
          Integer higher = s.higher(v);
          if (higher != null)
            s.remove(higher);
        }
      return s.size();
    }
    static int lisSize2(int[] a) {
      int[] last = new int[a.length];
      Arrays.fill(last, Integer.MAX_VALUE);
      int len = 0;
      for (int v : a) {
        int pos = lower_bound(last, v);
        last[pos] = v;
        len = Math.max(len, pos + 1);
      }
      return len;
    }
    static int lower_bound(int[] a, int key) {
      int lo = -1;
      int hi = a.length;
      while (hi - lo > 1) {
        int mid = (lo + hi) >>> 1;
        if (a[mid] < key) {
          lo = mid;
        } else {
          hi = mid;
        }
      }
      return hi;
    }
}
