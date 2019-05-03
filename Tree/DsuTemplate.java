abstract class DsuTemplate {

  final List<Integer>[] g;
  int n;
  int[] size;

  public DsuTemplate(List<Integer>[] g) {
    this.g = g;
    n = g.length;
    size = new int[n];
    findSize(1, 0);
    dfs(1, 0, true);
  }

  void dfs(int u, int p, boolean keep) {
    int mx = -1, big = -1;
    for (int v : g[u]) {
      if (v != p && size[v] > mx) {
        mx = size[v];
        big = v;
      }
    }
    for (int v : g[u]) {
      if (v != p && v != big) {
        dfs(v, u, false);
      }
    }
    if (big != -1) {
      dfs(big, u, true);
    }
    add(u, p, big);
    // do answer query for node here
    if (!keep) {
      erase(u, p);
    }
  }

  public abstract void add(int v, int p, int big);

  public abstract void erase(int v, int p);

  void findSize(int u, int p) {
    size[u] = 1;
    for (int v : g[u]) {
      if (v != p) {
        findSize(v, u);
        size[u] += size[v];
      }
    }
  }
}
