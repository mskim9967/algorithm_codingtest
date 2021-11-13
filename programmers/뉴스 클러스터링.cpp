
/*
    Prob
    https://programmers.co.kr/learn/courses/30/lessons/17677
    
    Writer
    MyungSeung Kim(mskim9967@gmail.com)
*/

#include <bits/stdc++.h> 

using namespace std;

int solution(string str1, string str2) {
    multiset<string> s1, s2;
    vector<string> inter, uni;
    regex re("[a-zA-Z]+");
    
    transform(str1.begin(), str1.end(), str1.begin(), ::tolower);
    transform(str2.begin(), str2.end(), str2.begin(), ::tolower);
    
    for(int i =  0; i < str1.size() - 1; i++)
        if(regex_match(str1.substr(i, 2), re))
            s1.insert(str1.substr(i, 2));
    for(int i =  0; i < str2.size() - 1; i++)
        if(regex_match(str2.substr(i, 2), re))
            s2.insert(str2.substr(i, 2));
    
    set_intersection(s1.begin(), s1.end(), s2.begin(), s2.end(), back_inserter(inter));
    set_union(s1.begin(), s1.end(), s2.begin(), s2.end(), back_inserter(uni));
    
    int ans = uni.size() == 0 ? 65536: ((double)inter.size() / (double)uni.size()) * 65536;
    return ans;
}
