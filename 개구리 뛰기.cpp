/*
    Prob
    SCPC 1회 예선 개구리 뛰기
    Writer
    MyungSeung Kim(mskim9967@gmail.com)
*/

#include <bits/stdc++.h> 
#include <fstream>
using namespace std;

int tc, n, k;
int a[1000010];

void solve() {
    int ans = 0;
    cin >> n;
    a[0] = 0;
    for(int i = 1; i <= n; i++) cin >> a[i];
    cin >> k;

    for(int i = 0, j = 1; j <= n;) {
        if(a[i] + k >= a[j])  // 더 멀리 뛸 수 있음
            j++;
        else { // 최대한 이동함
            if(j - 1 == i) { // 움직이지 못함
                cout << "-1"<< "\n";
                return ;
            }
            ++ans;
            i = j - 1;
        }
    }
    cout << ++ans << "\n"; // 도착 시 마지막 점프 횟수 더해줌
}

int main(void) {
    freopen("input.txt", "r", stdin);
    ios_base::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(0); cout << fixed; cout.precision(7);

    cin >> tc;
	for(int tt = 1; tt <= tc; tt++) {
        cout << "Case #" << tt << "\n";
        solve();
	}
    return 0;
} 
