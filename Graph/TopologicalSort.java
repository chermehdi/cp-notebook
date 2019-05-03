class TopologicalSort {
  static void dfs(List<Integer>[] graph, boolean[] used, List<Integer> order, int u) {
    used[u] = true;
    for (int v : graph[u])
      if (!used[v])
        dfs(graph, used, order, v);
    order.add(u);
  }
  public static List<Integer> topologicalSort(List<Integer>[] graph) {
    int n = graph.length;
    boolean[] used = new boolean[n];
    List<Integer> order = new ArrayList<>();
    for (int i = 0; i < n; i++)
      if (!used[i])
        dfs(graph, used, order, i);
    Collections.reverse(order);
    return order;
  }

}
