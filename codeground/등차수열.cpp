/*
    Prob
    SCPC 1회 예선 
    등차수열

    Writer
    MyungSeung Kim(mskim9967@gmail.com)
*/

#include <bits/stdc++.h> 
#include <fstream>
using namespace std;

int tc, n;

long getGcd(long a, long b) {
  if(a < b) swap(a, b);
  if(b == 0) return a;
  return getGcd(b, a % b);
}

int solve() {
  long ai, aj, minGcd;
  int ans = 0;

  cin >> n;
  for(int i = 0; i < n; i++) {
    cin >> aj;

    if(i == 0) ;
    else if(i == 1) minGcd = aj - ai;
    else {
      if(minGcd == 0 && aj - ai != 0) return 0; 
      if(minGcd != 0 && aj - ai == 0) return 0; 
      // 인접한 원소의 차(aj - ai)들의 최대공약수(minGcd)
      minGcd = min(minGcd, getGcd(minGcd, aj - ai));
    }
    
    ai = aj;
  }

  // 최대공약수의 약수의 개수(ans)
  for(int i = 1; i <= minGcd / 2; i++) 
    if(minGcd % i == 0) ans++;

  return ++ans;
}

int main(void) {
  freopen("input.txt", "r", stdin);
  ios_base::sync_with_stdio(false);
  cin.tie(NULL); cout.tie(0); cout << fixed; cout.precision(1);

  cin >> tc;
	for(int tt = 1; tt <= tc; tt++) 
    cout << "Case #" << tt << '\n' << solve() << '\n';
  return 0;
} 
