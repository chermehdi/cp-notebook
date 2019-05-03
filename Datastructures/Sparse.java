class Sparse {
  int[] log;
  int[][] sparse;
  int[] arr;
  int k = 22;

  public Sparse(int[] arr, int n) {
    log = new int[n + 1];
    sparse = new int[n + 1][k];
    this.arr = arr;
    build();
  }

  void build() {
    for (int i = 2; i <= n; ++i)
      log[i] = log[i >> 1] + 1;

    for (int i = 0; i < N; i++)
      sparse[i][0] = arr[i];

    for (int j = 1; j <= K; j++)
      for (int i = 0; i + (1 << j) <= N; i++)
        sparse[i][j] = Math.min(sparse[i][j - 1], sparse[i + (1 << (j - 1))][j - 1]);
  }

  int query(int l, int r) {
    int j = log[r - l + 1];
    return Math.min(sparse[l][j], sparse[r - (1 << j) + 1][j]);
  }
}
