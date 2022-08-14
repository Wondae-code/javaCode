import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_1874 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int cur = 1;
        StringBuilder sb = new StringBuilder();
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        for (int i = 0; i < N; i++) {
            // 각 숫자 비교
            while(stack.peek() < arr[i]){
                stack.push(cur);
                sb.append('+').append('\n');
                cur++;
            }
            if(stack.pop() == arr[i]) {
                sb.append('-').append('\n');
            }else{
                System.out.println("NO");
                return;
            }
        }
        System.out.println(sb);
    }
}
