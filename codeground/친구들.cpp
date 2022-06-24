/*
        Prob
        2021 SCPC 예선 1번 친구들
*/

#include <bits/stdc++.h>

#include <fstream>
#define N 100010

using namespace std;
int tc, n;
int arr[N], ds[N];

void uni(int a, int b) {
  if (a > b) swap(a, b);
  ds[a] = b;
}

int find(int a) {
  if (ds[a] == a) return a;
  return ds[a] = find(ds[a]);
}

int solve() {
  int ans = 0;
  for (int i = 1; i <= n; i++) ds[i] = i;

  for (int i = 1; i <= n; i++) {
    if (arr[i] + i > n) continue;
    uni(find(i), find(arr[i] + i));
  }

  for (int i = 1; i <= n; i++)
    if (ds[i] == i) ans++;
  return ans;
}

int main(void) {
  freopen("input.txt", "r", stdin);
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
  cout.tie(0);

  cin >> tc;
  for (int tt = 1; tt <= tc; tt++) {
    cin >> n;
    for (int i = 1; i <= n; i++) cin >> arr[i];

    cout << "Case #" << tt << "\n";
    cout << solve() << "\n";
  }

  return 0;
}