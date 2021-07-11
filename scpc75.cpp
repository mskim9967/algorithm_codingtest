/*
    Prob
    2018 SCPC 예선 2번 회문인 수의 합
    Writer
    MyungSeung Kim(mskim9967@gmail.com)
*/

#include <bits/stdc++.h> 
#include <fstream>

using namespace std;

int tc, ans;
int n;
vector<int> p;

void solve(int n) {
	for(int a : p) 
			for(int b : p)
				for(int c : p)
					if(a + b + c == n) {
						cout << ((a?1:0) + (b?1:0) + (c?1:0)) << " ";
						if(c) cout << c << " ";
						if(b) cout << b << " ";
						if(a) cout << a;
						return ;
					}
	cout << 0;
}

int main(void) {
    freopen("input.txt", "r", stdin);
    ios_base::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(0); cout << fixed; cout.precision(7);
    
	for(int i = 0; i <= 10000; i++) {
		if(i < 10) 
			p.push_back(i);
		else if(i < 100) 
			if(i % 10 == i / 10) p.push_back(i);
		else if(i < 1000)
			if(i % 10 == i / 100) p.push_back(i);
		else 
			if(i % 10 == i / 1000 && (i % 100) / 10 == (i / 100) % 10) p.push_back(i);
	}
	
	cin >> tc;
	for(int tt = 1; tt <= tc; tt++) {
		cin >> n;
		
		cout << "Case #" << tt << "\n";
		solve(n);
		cout << "\n";
	}
    return 0;
}