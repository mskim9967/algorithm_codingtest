/*
    Prob
    2018 SCPC 2차 예선 2번 메모지
    Writer
    MyungSeung Kim(mskim9967@gmail.com)
*/

#include <bits/stdc++.h> 
#include <fstream>

using namespace std;

int tc, k, n;
int mmap[610][610];


long long pt(int x, int y, int r) {
	if(r == 1) 
		if(x >= 0 && x < n && y >= 0 && y < n)
			return k * mmap[y][x];
		else return 0;
	
	int dx = 0, dy = r - 1;
	long long sum = 0;
	for(int i = 0; i < r - 1; i++) {
		if(x + dx >= 0 && x + dx < n && y + dy >= 0 && y + dy < n)
		sum += mmap[y + dy][x + dx];
		dy--;
		dx++;
	}
	for(int i = 0; i < r - 1; i++) {
		if(x + dx >= 0 && x + dx < n && y + dy >= 0 && y + dy < n)
		sum += mmap[y + dy][x + dx];
		dy--;
		dx--;
	}
	for(int i = 0; i < r - 1; i++) {
		if(x + dx >= 0 && x + dx < n && y + dy >= 0 && y + dy < n)
		sum += mmap[y + dy][x + dx];
		dy++;
		dx--;
	}
	for(int i = 0; i < r - 1; i++) {
		if(x + dx >= 0 && x + dx < n && y + dy >= 0 && y + dy < n)
		sum += mmap[y + dy][x + dx];
		dy++;
		dx++;
	}
	return (long long)sum * (k - r + 1) + pt(x, y, r - 1);
}

int main(void) {
    freopen("input.txt", "r", stdin);
    ios_base::sync_with_stdio(false);
	setbuf(stdout, NULL);
    cin.tie(NULL); cout.tie(0); cout << fixed; cout.precision(7);
	
	cin >> tc;
	for(int tt = 1; tt <= tc; tt++) {
		long long ans = 0;
		memset(mmap, 0, sizeof(mmap));
		
		cin >> n >> k;
		
		for(int y = 0; y < n; y++) 
			for(int x = 0; x < n; x++) 
				cin >> mmap[y][x];
		
		for(int y = -k; y < n + k; y++) 
			for(int x = -k; x < n + k; x++) {
				//cout << x << " " << y << " : " << pt(x, y, k)<<"\n";
				int next = pt(x, y, k);
				if(next > ans)ans = next;
			}
		
		cout << "Case #" << tt << "\n";
		cout << ans << "\n";	
	}
    return 0;
}