package level1;

public class 없는_숫자_더하기 {
    static final int[] numbers = {1,2,3,4,6,7,8,0};
    public static void main(String[] args) {
        // 0~9의 합은 45
        int sum = 45;
        for (int n: numbers) {
            sum -= n;
        }

        System.out.println(sum);
    }
}
