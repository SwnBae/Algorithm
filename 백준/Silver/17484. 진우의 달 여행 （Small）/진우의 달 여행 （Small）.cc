#include <iostream>
#include <vector>
using namespace std;

int n = 0, m = 0;
const int dx[3] = {-1, 0, 1};
vector<vector<int>> map;

// i+1 (j-1,j,j+1)
// 같은 방향 불가

int dp(int y, int x, int dir){
    int cmp = 2147483640;
    if(y == n){
        return 0;
    }
    for(int p = 0;p<3;p++){
        if(x+dx[p] < 0 || x+dx[p] >= m || p == dir) {
                continue;
        }

        cmp = min(cmp, dp(y+1,x+dx[p],p) + map[y][x]);
    }
    return cmp;
}

int main(){
    cin >> n >> m;

    int result = 2147483640;

    map = vector<vector<int>>(n, vector<int>(m, 0));

    for(int i=0;i<n;i++){
        for(int j=0;j<m;j++){
            cin >> map[i][j];
        }
    }

    for(int i=0;i<m;i++){
        result = min(result,dp(0,i,-1));
    }

    cout << result;

}