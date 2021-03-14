/*
    Prob
    https://www.acmicpc.net/problem/14957

    Writer
    MyungSeung Kim(mskim9967@gmail.com)
*/
#include <bits/stdc++.h> 
#include <fstream>
using namespace std;

struct Coord {
    int x, y;
    Coord(){};
    Coord(int _x, int _y) : x(_x), y(_y) {};
}; 
int n, m; 
Coord L[50001], U[50001];

void solve() {
    int cnt = 0, ui = 0, li = 0, h = 0, sx = 0, x = 0;
    long area = 0, areaTemp = 0;
    bool start = false, isU = false;

    if(L[0].y < U[0].y) {
        while(L[li].y < U[ui].y) {
            if(ui == m && li == n) break;
            else if(ui == m) li++;
            else if(li == n) ui++;
            else if(L[li + 1].x < U[ui + 1].x) li++;
            else ui++;
        }
    }

    while(true) {
        if(isU) areaTemp += (long)h * (x - sx);

        if(U[ui].y > L[li].y) {
            isU = true;
            sx = x;
            h = U[ui].y - L[li].y;
        }
        else if(isU) {
            cnt++;
            area += areaTemp;
            areaTemp = 0;
            isU = false;
        }

        if(ui == m && li == n) break;
        else if(ui == m) {
            li++;
            x = L[li].x;
        }
        else if(li == n) {
            ui++;
            x = U[ui].x;
        }
        else if(L[li + 1].x < U[ui + 1].x) {
            li++;
            x = L[li].x;
        }
        else {
            ui++;
            x = U[ui].x;
        }
    }

    cout << cnt << " " << area << "\n";
}

int main(void) {
    freopen("input.txt", "r", stdin);
    ios_base::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(0);
    
    int temp, x, y;
    cin >> n >> m;
    cin >> temp;
    L[0] = Coord(0, temp);
    for(int i = 1; i <= n; i++) {
        cin >> x >> y;
        L[i] = Coord(x, y);
    }
    cin >> temp;
    U[0] = Coord(0, temp);
    for(int i = 1; i <= m; i++) {
        cin >> x >> y;
        U[i] = Coord(x, y);
    }

    solve();
}