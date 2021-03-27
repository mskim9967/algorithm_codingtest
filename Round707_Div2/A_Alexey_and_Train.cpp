#include <bits/stdc++.h> 
#include <fstream>
 
using namespace std;
 
int t, n;
int a[150], b[150], tmm[150];
 
int main(void) {
    //freopen("input.txt", "r", stdin);
    ios_base::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(0);
    
    cin >> t;
   
    while(t--) {
        b[0] = 0;
        int now = 0;
        cin >> n;
        for(int i = 1; i <= n; i++)
            cin >> a[i] >> b[i];
        for(int i = 1; i <= n; i++)
            cin >> tmm[i];
 
        for(int i = 1; i <= n; i++) {
            now += a[i] - b[i - 1] + tmm[i] + (int)ceil((float)(b[i] - a[i]) / 2);
            if(i == n) now -= (int)ceil((float)(b[i] - a[i]) / 2);
            if(i != n && now < b[i]) now = b[i];
        }
        cout << now << "\n";
    }
    
    return 0;
}
 