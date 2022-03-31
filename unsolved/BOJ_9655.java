import java.util.Scanner;

public class BOJ_9655 {
    // SK -> CY 순서
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int K = sc.nextInt();

        if(K%2 == 0){
            System.out.println("CY");
        }else{
            System.out.println("SK");
        }
    }
}
