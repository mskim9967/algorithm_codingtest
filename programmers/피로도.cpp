#include <string>
#include <vector>

using namespace std;

int solution(int k, vector<vector<int>> dungeons) {
    int ret = 0;
    
    for(int i = 0; i < dungeons.size(); i++) {
        if(dungeons[i][0] > k) continue;
        
        vector<vector<int>> temp = dungeons;
        temp.erase(temp.begin() + i);
        ret = max(ret, 1 + solution(k - dungeons[i][1], temp));
    } 
    
    return ret;
}
