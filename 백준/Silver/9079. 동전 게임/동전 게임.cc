#include <iostream>
#include <bitset>
#include <queue>
#include<cstring>
using namespace std;

int count = 0;
bool visited[512];
int maze[3][3];
//비트 + bfs

void rev(int y, int x){
    if(maze[y][x] == 1){
        maze[y][x] = 0;
    }
    else{
        maze[y][x] = 1;
    }
}

void hor(int y){
    for(int p=0;p<3;p++){
        rev(y,p);
    }
}

void ver(int x){
    for(int p=0;p<3;p++){
        rev(p,x);
    }
}

void diag(int num){
    if(num ==0){
        for(int p=0;p<3;p++){
            rev(p,p);
        }
    }
    if(num == 1){
        rev(2,0);
        rev(1,1);
        rev(0,2);
    }
}

bool check() {
    int first = maze[0][0];
    for(int i = 0; i < 3; i++) {
        for(int j = 0; j < 3; j++) {
            if(maze[i][j] != first) {
                return false;
            }
        }
    }
    return true;
}


int maze_int(){
    string s ="";

    for(int j=0;j<3;j++){
        for(int p=0;p<3;p++){
            s += to_string(maze[j][p]);
        }
    }

    bitset<9> b(s);
    return b.to_ulong();
}

void int_to_maze(int number) {
	for (int i = 2; i >=0; i--) {
		for (int j = 2; j >=0; j--) {
			maze[i][j] = number % 2;
			number /= 2;
		}
	}
}

int bfs(){
    queue<pair<int,int>> q;
    
    int first = maze_int();
    q.push({first,0});
    visited[first] = true;

    while(!q.empty()){
        int now = q.front().first;
        int cnt = q.front().second;
        q.pop();

        int_to_maze(now);

        if(check()){
            return cnt;
        }

        for(int i=0;i<3;i++){
            hor(i);
            int next = maze_int();
            if(!visited[next]){
                q.push({next,cnt+1});
                visited[next] = true;
            }
            hor(i);
        }

        for(int i=0;i<3;i++){
            ver(i);
            int next = maze_int();
            if(!visited[next]){
                q.push({next,cnt+1});
                visited[next] = true;
            }
            ver(i);
        }

        for(int i=0;i<2;i++){
            diag(i);
            int next = maze_int();
            if(!visited[next]){
                q.push({next,cnt+1});
                visited[next] = true;
            }
            diag(i);
        }
    }
    return -1;
}

//bfs 이용


int main(){
    string s = "";
    cin >> count;

    for(int i=0; i<count;i++){
        memset(visited, false, sizeof(visited));
        for(int j=0;j<3;j++){
            for(int k=0;k<3;k++){
                char x;
                cin >> x;
                if(x == 'H'){
                    maze[j][k] = 1;
                }
                else{
                    maze[j][k] = 0;
                }
            }
        }
        s+= to_string(bfs()) + "\n";
        //cout << bfs() << "\n";
    }

    cout<< s;

    return 0;
}