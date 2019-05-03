class SegmentTree {
  int[] st, delta;

  void build(int root, int l, int r) {
    if (l == r) {
      st[root] = arr[l];
      return;
    }
    int mid = (l + r) >> 1;
    build(root * 2, l, mid);
    build(root * 2 + 1, mid + 1, r);
    st[root] = st[root * 2] + st[root * 2 + 1];
  }

  void propagete(int root, int l, int r) {
    if (delta[root] != 0) {
      st[root] += (r - l + 1) * delta[root];
      delta[root * 2] += delta[root];
      delta[root * 2 + 1] += delta[root];
      delta[root] = 0;
    }
  }

  int query(int root, int l, int r, int f, int t) {
    if (f > r || t < l)
      return 0;
    if (f <= l && r <= t) {
      return st[root] + delta[root];
    }
    propagete(root, l, r);
    int mid = (l + r) >> 1;
    int left = query(root * 2, l, mid, f, t);
    int right = query(root * 2 + 1, mid + 1, r, f, t);
    return left + right;
  }

  void update(int root, int l, int r, int f, int t, int val) {
    if (f > r || t < l)
      return;
    if (f <= l && r <= t) {
      delta[root] += val;
      return;
    }
    propagete(root, l, r);
    int mid = (l + r) >> 1;
    update(root * 2, l, mid, f, t, val);
    update(root * 2 + 1, mid + 1, r, f, t, val);
    st[root] = st[root * 2] + st[root * 2 + 1];
  }
}
