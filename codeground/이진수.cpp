/*
        Prob
        2021 SCPC 예선 2번 이진수
*/

#include <bits/stdc++.h>

#include <fstream>

#define N 50010

using namespace std;
int tc, n, t;
string line;
bool b[N];

void solve() {
  bool a[N] = {0};

  for (int i = 1; i <= n; i++) {
    if (!b[i]) continue;

    if (i <= n - t && i > t) {
      if (!a[i + t] && !a[i - t]) {
        if (i + t + t <= n && !b[i + t + t]) {
          a[i - t] = 1;
        } else {
          a[i + t] = 1;
        }
      }
    } else if (i <= n - t) {
      a[i + t] = 1;
    } else if (i > t) {
      a[i - t] = 1;
    }
  }
  for (int i = 1; i <= n; i++) cout << a[i];
}

int main(void) {
  freopen("input.txt", "r", stdin);
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
  cout.tie(0);

  cin >> tc;
  for (int tt = 1; tt <= tc; tt++) {
    cin >> n >> t >> line;
    for (int i = 1; i <= n; i++) b[i] = line[i - 1] == '1';

    cout << "Case #" << tt << "\n";
    solve();
    cout << "\n";
  }

  return 0;
}