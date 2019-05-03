public class Lca {
  int[] depth;
  int[] dfs_order;
  int cnt;
  int[] first;
  int[] minPos;
  int n;
  void dfs(List<Integer>[] tree, int u, int d) {
    depth[u] = d;
    dfs_order[cnt++] = u;
    for (int v : tree[u])
      if (depth[v] == -1) {
        dfs(tree, v, d + 1);
        dfs_order[cnt++] = u;
      }
  }
  void buildTree(int node, int left, int right) {
    if (left == right) {
      minPos[node] = dfs_order[left];
      return;
    }
    int mid = (left + right) >> 1;
    buildTree(2 * node + 1, left, mid);
    buildTree(2 * node + 2, mid + 1, right);
    minPos[node] = depth[minPos[(node << 1) + 1]] < depth[minPos[(node << 1) + 2]] ?
        minPos[(node << 1) + 1] : minPos[(node << 1) + 2];
  }
  public Lca(List<Integer>[] tree, int root) {
    int nodes = tree.length;
    depth = new int[nodes];
    Arrays.fill(depth, -1);

    n = 2 * nodes - 1;
    dfs_order = new int[n];
    cnt = 0;
    dfs(tree, root, 0);

    minPos = new int[4 * n];
    buildTree(0, 0, n - 1);

    first = new int[nodes];
    Arrays.fill(first, -1);
    for (int i = 0; i < dfs_order.length; i++)
      if (first[dfs_order[i]] == -1)
        first[dfs_order[i]] = i;
  }

  public int lca(int a, int b) {
    return minPos(Math.min(first[a], first[b]), Math.max(first[a], first[b]), 0, 0, n - 1);
  }

  int minPos(int a, int b, int node, int left, int right) {
    if (a == left && right == b)
      return minPos[node];
    int mid = (left + right) >> 1;
    if (a <= mid && b > mid) {
      int p1 = minPos(a, Math.min(b, mid), 2 * node + 1, left, mid);
      int p2 = minPos(Math.max(a, mid + 1), b, 2 * node + 2, mid + 1, right);
      return depth[p1] < depth[p2] ? p1 : p2;
    } else if (a <= mid) {
      return minPos(a, Math.min(b, mid), 2 * node + 1, left, mid);
    } else if (b > mid) {
      return minPos(Math.max(a, mid + 1), b, 2 * node + 2, mid + 1, right);
    } else {
      throw new RuntimeException();
    }
  }
}
