/*
    Prob
    https://programmers.co.kr/learn/courses/30/lessons/17680
    
    Writer
    MyungSeung Kim(mskim9967@gmail.com)
*/

#include <bits/stdc++.h> 

using namespace std;

int solution(int cacheSize, vector<string> cities) {
    if(cacheSize == 0) return 5 * cities.size();
    
    list<string> cache;
    int ans = 0;
    
    for(string city : cities) {
        transform(city.begin(), city.end(), city.begin(), ::tolower);
        auto it = find(cache.begin(), cache.end(), city);
        if(it == cache.end()) {
            ans += 5;
            if(cache.size() == cacheSize) cache.pop_back();
            cache.push_front(city);
        }
        else {
            ans += 1;
            cache.erase(it);
            cache.push_front(city);
        }
    }
    return ans;
}
