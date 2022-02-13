package SSAFY;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_2493 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 값 넣기
        int num = Integer.parseInt(br.readLine());
        int[] data = new int[num];
        String[] raw = br.readLine().split(" ");
        for (int i = 0; i < num; i++) {
            data[i] = Integer.parseInt(raw[i]);
        }

        // 스택을 통해 계산하기
        Stack<int[]> s = new Stack<>();
        int[] result = new int[num];
        for (int i = num - 1; i >= 0; i--) {
            if(s.empty()){
                s.push(new int[]{i, data[i]});
            }else{
                while(!s.empty() && data[i] > s.peek()[1]){
                    result[s.peek()[0]] = i+1;
                    s.pop();
                }
                s.push(new int[]{i, data[i]});
            }
        }

//        // 값 출력
//        for (int i : result) {
//            System.out.print(i + " ");
//        }
//        System.out.println();
        // StringBuilder 사용 -> 속도에서 이득
        StringBuilder sb = new StringBuilder();
        for (int i : result) {
            sb.append(i);
            sb.append(' ');
        }
        System.out.println(sb.toString());
    }
}
