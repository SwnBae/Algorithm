#include <iostream>
#include <string>
#define endl "\n"
#define MAX 64
using namespace std;

int N;
int MAP[MAX][MAX];

void Input(){
    cin >> N;
    
    for(int i = 0; i < N; i++){
        string S; cin >> S;
        for(int j = 0; j<S.length(); j++){
            MAP[i][j] = S[j] - '0';
        }
    }
}

void DFS(int y, int x, int Size){
    int dx,dy;
    bool pass = true;
    int index = MAP[y][x];

    for(dy=0; dy<Size; dy++){
        for(int dx=0; dx<Size; dx++){
            if(MAP[y+dy][x+dx] != index){
                pass = false;
                break;
            }
        }
        if(pass == false) break;
    }

    if(pass){
        cout << index;
    }
    else{
        int fixSize = Size/2;

        cout << "(";
        DFS(y, x,fixSize);
        DFS(y, x + fixSize, fixSize);
        DFS(y+ fixSize, x, fixSize);
        DFS(y+ fixSize, x + fixSize, fixSize);
        cout << ")";
    }
}

int main(void){
    Input();
    DFS(0,0,N);

    return 0;
}

