import java.util.Scanner;

public class Main {

    public static boolean check(int num){
        int count = 0;
        for(int i=1;i<=num;i++){
            if(num%i==0){
                count++;
            }
        }
        if(count==2){
            return true;
        }
        else return false;
    }
    public static void main(String[] args) {
        int n = 0;
        int count = 0;

        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        sc.nextLine();

        int[] num = new int[n];

        for (int i = 0; i < n; i++) {
            num[i] = sc.nextInt();
        }

        for(int i=0;i<n;i++){
            if(check(num[i])){
                count++;
            }
        }

        System.out.println(count);
    }
}