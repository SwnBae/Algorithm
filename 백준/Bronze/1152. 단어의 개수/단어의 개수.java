import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String s = "";
        Scanner sc = new Scanner(System.in);
        s = sc.nextLine().trim();

        String[] arr = s.split(" ");

        if(s.isBlank()){
            System.out.println(0);
        }
        else {
            System.out.println(arr.length);
        }
    }
}