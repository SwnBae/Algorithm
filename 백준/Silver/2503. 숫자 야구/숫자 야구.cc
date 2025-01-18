#include<iostream>
using namespace std;

int count, ans = 0;
int n[100],s[100],b[100];
bool check;

bool numchk(int n){
    int chk[3];
    int p = n;

    for(int i=2;i>-1;i--){
        chk[i] = p % 10;
        p = p/10;
    }

    if(chk[0] == chk[1] || chk[1] == chk[2] || chk[2] == chk[0]){
        return false;
    }
    if(!chk[0]||!chk[1]||!chk[2]){
        return false;
    }

    return true;
}

bool hintchk(int idx, int num){
    int n1[3];
    int n2[3];
    int orgin = num;
    int chknum = n[idx];

    for(int i=2;i>-1;i--){
        n1[i] = orgin%10;
        orgin = orgin/10;
    }

    for(int i=2;i>-1;i--){
        n2[i] = chknum%10;
        chknum = chknum/10;
    }

    int chks = 0,chkb = 0;

    for(int i=0; i<3; i++){
        if(n1[i] == n2[i]){
            chks++;
        }
        if(n1[i] == n2[(i+1)%3] || n1[i] == n2[(i+2)%3]){
            chkb++;
        }
    }

    return chks == s[idx] && chkb == b[idx];
}

int main(){
    cin >> count;

    for(int i=0; i<count; i++){
        cin >> n[i] >> s[i] >> b[i];
    }

    for(int i=123; i<=987; i++){
        if(!numchk(i)) continue;

        check = true;

        for(int j = 0; j<count && check; j++){
            check = hintchk(j,i);
        }

        if(check){
            ans++;
        }
    }

    cout << ans;

    return 0;
}

//숫자 123~987에서
// ㄴ 받은 힌트만큼 돌림
// 123, 324, 328
// 힌트가 조건임, 조건을 [모두 만족!]하는 숫자라면 ok.
// -> 123 1 1
// -> 356 1 0
// -> 327 2 0
// -> 489 0 1


