/*
    Prob
    2021 SCPC 1차 예선 1번 친구들
    Writer
    MyungSeung Kim(mskim9967@gmail.com)
*/

#include <bits/stdc++.h> 
#include <fstream>

using namespace std;

int tc, ans, n;
int a[100010], ds[100010];

int getSet(int a) {
	if(a == ds[a]) return a;
	return ds[a] = getSet(ds[a]);
}

void uni(int a, int b){
	if(a > b) swap(a, b);
	ds[getSet(b)] = a;
}

int main(void) {
    freopen("input.txt", "r", stdin);
    ios_base::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(0);
	
	cin >> tc;
	for(int tt = 1; tt <= tc; tt++) {
		ans = 0;
		for(int i = 1; i < 100010; i++) 
			ds[i] = i; // 집합 초기화
		
		cin >> n;
		for(int i = 1; i <= n; i++)
			cin >> a[i];
		
		for(int i = 1; i <= n; i++)
			if(i + a[i] <= n) // 친구관계라면 집합에 추가
				uni(i, i + a[i]);
		
		for(int i = 1; i <= n; i++) // 집합의 개수=극대 그룹의 개수
			if(ds[i] == i) ans++;
		
		cout << "Case #" << tt << "\n";
		cout << ans << "\n";
	}
    return 0;
}