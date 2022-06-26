/*
        Prob
        2021 SCPC 1차 예선 3번 No Cycle
*/

#include <bits/stdc++.h>

#include <fstream>

#define N 510

using namespace std;
int tc, n, k, m;
vector<int> edges[N];
bool checked[N], finished[N];

bool hasCycle(int now) {
  if (checked[now]) return !finished[now];

  checked[now] = true;

  for (int next : edges[now])
    if (hasCycle(next)) return true;

  finished[now] = true;
  return false;
}

int main(void) {
  freopen("input.txt", "r", stdin);
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
  cout.tie(0);

  cin >> tc;
  for (int tt = 1; tt <= tc; tt++) {
    for (int i = 1; i <= n; i++) edges[i].clear();

    cin >> n >> m >> k;
    int a, b;
    for (int i = 0; i < m; i++) {
      cin >> a >> b;
      edges[a].push_back(b);
    }

    cout << "Case #" << tt << "\n";

    for (int i = 0; i < k; i++) {
      memset(checked, 0, sizeof(checked));
      memset(finished, 0, sizeof(finished));
      cin >> a >> b;
      edges[a].push_back(b);
      if (hasCycle(a)) {
        edges[a].pop_back();
        edges[b].push_back(a);
        cout << 1;
      } else
        cout << 0;
    }
    cout << "\n";
  }
  return 0;
}