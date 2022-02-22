import java.util.Scanner;

public class IM_TEST {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int test_case = 1; test_case <= T; test_case++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            // a*b*h가 최대가 되어야한다.
            // f(h) = a*b*h = h*(a-2h)*(b-2h) = 4*h^3 - 2*(a+b)*h^2 + a*b*h
            // 위의 식을 미분하고 h 값을 구한다.
            // f'(h) = 12*h^2 - 4(a+b)*h a*b
            // h =  ((a+b) - sqrt(a^2+b^2 - ab))/6
            double h = ((a+b) - Math.sqrt(Math.pow(a,2)+ Math.pow(b,2) - a*b)) / 6;


            System.out.printf("#%d %f\n", test_case, 4*(Math.pow(h,3)) - 2 * (a + b) * Math.pow(h,2) + a * b * h );
        }
    }
}
