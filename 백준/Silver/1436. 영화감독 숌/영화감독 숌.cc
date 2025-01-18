#include <iostream>
using namespace std;

int count = 0, number = 666;
int input;

bool check(int num){
    string n = to_string(num);

    for(int i=0;i<n.length()-2;i++){
        if(n[i] == '6' && n[i+1] == '6' && n[i+2] == '6'){

            return true;
        }
    }
    return false;
}

int main(){
    cin >> input;

    while (1){
        if(check(number)){
            count ++;
        }

        if(input == count){
            break;
        }

        number++;
    }
    
    cout << number;

    return 0;
}

// length - 2가 음수일 수 있다. 체크 후 시작값 확인