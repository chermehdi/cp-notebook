class ZAlgorithm {
  public static int[] zFunction(String s) {
    int[] z = new int[s.length()];
    for (int i = 1, l = 0, r = 0; i < z.length; ++i) {
      if (i <= r)
        z[i] = Math.min(r - i + 1, z[i - l]);
      while (i + z[i] < z.length && s.charAt(z[i]) == s.charAt(i + z[i]))
        ++z[i];
      if (r < i + z[i] - 1) {
        l = i;
        r = i + z[i] - 1;
      }
    }
    return z;
  }

  static int find(String s, String pattern) {
    int[] z = zFunction(pattern + "\0" + s);
    for (int i = pattern.length() + 1; i < z.length; i++)
      if (z[i] == pattern.length())
        return i - pattern.length() - 1;
    return -1;
  }
}
