class Kmp {
  public static int[] prefixFunction(String s) {
    int[] p = new int[s.length()];
    int k = 0;
    for (int i = 1; i < s.length(); i++) {
      while (k > 0 && s.charAt(k) != s.charAt(i)) {
        k = p[k - 1];
      }
      if (s.charAt(k) == s.charAt(i)) {
        ++k;
      }
      p[i] = k;
    }
    return p;
  }

  public static void kmpMatcher(String s, String pattern) {
    int m = pattern.length();
    int[] p = prefixFunction(pattern);
    for (int i = 0, k = 0; i < s.length(); i++) {
      System.out.println(k);
      while (k > 0 && pattern.charAt(k) != s.charAt(i)) {
        k = p[k - 1];
      }
      if (pattern.charAt(k) == s.charAt(i)) {
        ++k;
      }
      if (k == m) {
        System.out.println("found "  + (i + 1 - m));
        k = p[k - 1];
      }
    }
  }
}
