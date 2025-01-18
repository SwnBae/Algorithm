import java.util.Scanner;

public class Main {
    public static boolean check(String word){
        int pre = 0;
        boolean[] check = new boolean[26];
        for(int i=0; i<word.length(); i++){
            int now = word.charAt(i);
            if(now!=pre){
               if(check[now-'a']==false){
                   check[now-'a']=true;
                   pre = now;
               }
               else{
                   return false;
               }
            }
        }
        return true;
    }
    public static void main(String[] args) {
        int size = 0;
        int count = 0;

        Scanner sc = new Scanner(System.in);

        size = sc.nextInt();
        sc.nextLine();

        String[] words = new String[size];

        for(int i = 0; i < size; i++){
           words[i] = sc.nextLine();
        }

        for(int i = 0; i < size; i++){
            if(check(words[i]) == true){
                count++;
            }
        }

        System.out.println(count);

    }
}
