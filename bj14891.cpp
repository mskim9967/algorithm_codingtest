/*
    Prob
    https://www.acmicpc.net/problem/14891

    Writer
    MyungSeung Kim(mskim9967@gmail.com)
*/
#include <bits/stdc++.h> 
#include <fstream>

using namespace std;

int k;
int gear[4][8];
int now[4];

int main(void) {
    freopen("input.txt", "r", stdin);
    ios_base::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(0);
    
    string line;
    for(int i = 0; i < 4; i++) {
        cin >> line;
        for(int j = 0; j < 8; j++)
            gear[i][j] = line.at(j) - '0';
    }
    cin >> k;
    int g, d;
    for(int i = 0; i < k; i++) {
        cin >> g >> d;
        g--;
        now[g] = (now[g] - d + 8) % 8;  // rotate
        int dir = d;
        for(int left = g - 1; left >= 0; left--) { // left gears
            if(gear[left + 1][(now[left + 1] + 6 + dir) % 8] == gear[left][(now[left] + 2) % 8]) break; // stop if both are same magnatic
            dir *= -1; // change direction
            now[left] = (now[left] - dir + 8) % 8; // rotate next gear
        }
        
        dir = d;
        for(int right = g + 1; right < 4; right++) { // right gears
            if(gear[right - 1][(now[right - 1] + 2 + dir) % 8] == gear[right][(now[right] + 6) % 8]) break; // stop if both are same magnatic
            dir *= -1;  // change direction
            now[right] = (now[right] - dir + 8) % 8;  // rotate next gear
        }
    }

    cout << gear[0][now[0]] + gear[1][now[1]] * 2 + gear[2][now[2]] * 4 + gear[3][now[3]] * 8 << '\n';

    return 0;
}