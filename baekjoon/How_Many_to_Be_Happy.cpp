  
/*
    Prob
    https://www.acmicpc.net/problem/14954
    Writer
    MyungSeung Kim(mskim9967@gmail.com)
*/
#include <bits/stdc++.h> 
#include <fstream>

using namespace std;

int n;

int main(void) {
    freopen("input.txt", "r", stdin);
    ios_base::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(0);

    cin >> n;
    
    int num = n; 
    while(true) { 
        string str = to_string(num);
        num = 0;
        for(int i = 0; i < str.length(); i++) 
            num += (str.at(i) - '0') * (str.at(i) - '0');
        if(num == 1) {
            cout << "HAPPY" << "\n";
            return 0;
        }
        if(num == n || num == 4) {
            cout << "UNHAPPY" << "\n";
            return 0;
        }
    }
    return -1;
}