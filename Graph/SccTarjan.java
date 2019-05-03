public class SCCTarjan {
  List<Integer>[] graph;
  boolean[] visited;
  Stack<Integer> stack;
  int time;
  int[] lowlink;
  List<List<Integer>> components;

  public List<List<Integer>> scc(List<Integer>[] graph) {
    int n = graph.length;
    this.graph = graph;
    visited = new boolean[n];
    stack = new Stack<>();
    time = 0;
    lowlink = new int[n];
    components = new ArrayList<>();

    for (int u = 0; u < n; u++)
      if (!visited[u])
        dfs(u);

    return components;
  }

  void dfs(int u) {
    lowlink[u] = time++;
    visited[u] = true;
    stack.add(u);
    boolean isComponentRoot = true;

    for (int v : graph[u]) {
      if (!visited[v])
        dfs(v);
      if (lowlink[u] > lowlink[v]) {
        lowlink[u] = lowlink[v];
        isComponentRoot = false;
      }
    }

    if (isComponentRoot) {
      List<Integer> component = new ArrayList<>();
      while (true) {
        int x = stack.pop();
        component.add(x);
        lowlink[x] = Integer.MAX_VALUE;
        if (x == u)
          break;
      }
      components.add(component);
    }
  }
}
