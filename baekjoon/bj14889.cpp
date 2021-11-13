/*
    Prob
    https://www.acmicpc.net/problem/14889

    Writer
    MyungSeung Kim(mskim9967@gmail.com)
*/
#include <bits/stdc++.h> 
#include <fstream>
using namespace std;

int n, statMin = INT_MAX, aStat, bStat;
vector<int> aTeam, bTeam;
int stat[20][20];

void rec(int memIdx) {
    if(memIdx == n) {
        statMin = min(statMin, abs(aStat - bStat));
        return;
    }

    if(aTeam.size() < n / 2) {
        int cp = aStat;
        aTeam.push_back(memIdx);
        for(int member : aTeam) 
            aStat += stat[memIdx][member] + stat[member][memIdx];
        rec(memIdx + 1);
        aStat = cp;
        aTeam.pop_back();
    }

    if(bTeam.size() < n / 2) {
        int cp = bStat;
        bTeam.push_back(memIdx);
        for(int member : bTeam) 
            bStat += stat[memIdx][member] + stat[member][memIdx];
        rec(memIdx + 1);
        bStat = cp;
        bTeam.pop_back();
    }
}

int main(void) {
    freopen("input.txt", "r", stdin);
    ios_base::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(0);

    cin >> n;
    for(int i = 0; i < n; i++) 
        for(int j = 0; j < n; j++)
            cin >> stat[i][j];
    
    rec(0);

    cout << statMin << "\n"; 

    return 0;
}