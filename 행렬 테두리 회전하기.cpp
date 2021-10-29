/*
    Prob
    https://programmers.co.kr/learn/courses/30/lessons/77485

    Writer
    MyungSeung Kim(mskim9967@gmail.com)
*/

#include <bits/stdc++.h>
using namespace std;

int mmap[100][100];

int rotate(vector<int> &query) {
    int xmin = query[1] - 1, xmax = query[3] - 1,
        ymin = query[0] - 1, ymax = query[2] - 1,
        x = xmin, y = ymin, tmp = mmap[y][x], mmin = INT_MAX;
    
    while(x < xmax) 
        mmin = min(mmin, tmp), swap(tmp, mmap[y][x + 1]), x++;
    while(y < ymax) 
        mmin = min(mmin, tmp), swap(tmp, mmap[y + 1][x]), y++;
    while(x > xmin) 
        mmin = min(mmin, tmp), swap(tmp, mmap[y][x - 1]), x--;
    while(y > ymin) 
        mmin = min(mmin, tmp), swap(tmp, mmap[y - 1][x]), y--;
    
    return mmin;
}

vector<int> solution(int rows, int columns, vector<vector<int>> queries) {
    vector<int> ans;
    for(int y = 0; y < rows; y++)
        for(int x = 0; x < columns; x++)
            mmap[y][x] = y * columns + x + 1;
    
    for(vector<int> query : queries) 
        ans.push_back(rotate(query));
    
    return ans;
}
