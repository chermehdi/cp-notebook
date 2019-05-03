class Hash {
  static int mul = 131;
  static final Random random = new Random();
  static final long firstMod = 1297425359;
  static final long secondMod = 1859599523;
  static final long firstInvMul = BigInteger.valueOf(mul)
      .modInverse(BigInteger.valueOf(firstMod))
      .longValue();
  static final long secondInvMul = BigInteger.valueOf(mul)
      .modInverse(BigInteger.valueOf(secondMod))
      .longValue();

  long[] firstHash, secondHash;
  long[] firstInv, secondInv;
  int n;

  public Hash(String s) {
    initialize(s);
  }

  public Hash(String s, int mul) {
    this.mul = mul;
    initialize(s);
  }

  private void initialize(String s) {
    n = s.length();
    firstHash = new long[n + 1];
    secondHash = new long[n + 1];
    firstInv = new long[n + 1];
    secondInv = new long[n + 1];
    firstInv[0] = 1;
    secondInv[0] = 1;

    long powerFirstMod = 1;
    long powerSecondMod = 1;
    for (int i = 0; i < n; i++) {
      firstHash[i + 1] = (firstHash[i] + s.charAt(i) * powerFirstMod) % firstMod;
      powerFirstMod = powerFirstMod * mul % firstMod;
      firstInv[i + 1] = firstInv[i] * firstInvMul % firstMod;
      secondHash[i + 1] = (secondHash[i] + s.charAt(i) * powerSecondMod) % secondMod;
      powerSecondMod = powerSecondMod * mul % secondMod;
      secondInv[i + 1] = secondInv[i] * secondInvMul % secondMod;
    }
  }

  public long getHash(int i, int len) {
    return (((firstHash[i + len] - firstHash[i] + firstMod) * firstInv[i] % firstMod) << 32)
        + (secondHash[i + len] - secondHash[i] + secondMod) * secondInv[i] % secondMod;
  }
}
