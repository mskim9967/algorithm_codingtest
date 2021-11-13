#include <bits/stdc++.h> 
#include <fstream>
 
using namespace std;
 
int n;
int a[200010], b[5000010], c[5000010];

int main(void) {
    freopen("input.txt", "r", stdin);
    ios_base::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(0);
    
    cin >> n;
    for(int i = 1; i <= n; i++) cin >> a[i];

    // answer always exist when all array has filled (pigeonhole principle)
    // so time complexity is not n^2,
    // O(min(n^2, 5000000))
    for(int i = 1; i <= n; i++) {
        for(int j = i + 1; j <= n; j++) {
            int &x = b[a[i] + a[j]], &y = c[a[i] + a[j]];
            if(x == 0)
                x = i, y = j;
            else 
                if(x != i && y != i && x != j && y != j) {
                    cout << "YES\n"<< x << " " << y << " " << i << " " << j;
                    return 0;
                }
        }
    }
    cout << "NO";
    
    return 0;
}