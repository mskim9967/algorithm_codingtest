/*
    Prob
    https://www.acmicpc.net/problem/14890

    Writer
    MyungSeung Kim(mskim9967@gmail.com)
*/
#include <bits/stdc++.h> 
#include <fstream>

using namespace std;

int n, l, ans;
int mmap1[100][100], mmap2[100][100];

void count(int mmap[100][100]) {
    for(int y = 0; y < n; y++) {
        bool possible = true;
        int flat = 1;
        for(int x = 1; x < n; x++) {
            if(mmap[y][x] == mmap[y][x - 1])  
                flat++;
            else if(mmap[y][x] - mmap[y][x - 1] == 1 && flat >= l) // uphill
                flat = 1;
            else if(mmap[y][x] - mmap[y][x - 1] == -1 && flat >= 0) // downhill
                flat = -l + 1;
            else {
                possible = false;
                break;
            }
        }
        if(possible && flat >= 0) ans++;
    }
}

int main(void) {
    freopen("input.txt", "r", stdin);
    ios_base::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(0);
    
    cin >> n >> l;
    for(int y = 0; y < n; y++) 
        for(int x = 0; x < n; x++) {
            cin >> mmap1[y][x];   
            mmap2[x][y] = mmap1[y][x];
        }

    count(mmap1);
    count(mmap2); 
           
    cout << ans << "\n"; 
    return 0;
}