import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class BOJ_14888 {
    static int[] arr, tempCnt;
    static char[] operators;
    static boolean[] verify;
    static ArrayList<Integer> results;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 값을 넣고 숫자 배열 채우기
        int size = Integer.parseInt(br.readLine());
        arr = new int[size];
        String[] raw = br.readLine().split(" ");
        for (int i = 0; i < size; i++) {
            arr[i] = Integer.parseInt(raw[i]);
        }
        // 연산자 배열 채우기
        tempCnt = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        operators = new char[Arrays.stream(tempCnt).sum()];
        int cnt = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < tempCnt[i]; j++) {
                switch (i){
                    case 0:
                        operators[cnt] = '+';
                        break;
                    case 1:
                        operators[cnt] = '-';
                        break;
                    case 2:
                        operators[cnt] = '*';
                        break;
                    case 3:
                        operators[cnt] = '/';
                        break;
                }
                cnt++;
            }
        }

        // 조합 돌리기
        results = new ArrayList<>();
        char[] position = new char[size-1];
        verify = new boolean[size-1];
        Arrays.fill(position, '0');
        combination(position, 0);


        // 결과 출력
        System.out.println(Collections.max(results));
        System.out.println(Collections.min(results));
    }

    private static void combination(char[] temp, int idx){

        if(idx == temp.length){
            //System.out.println(Arrays.toString(temp));
            results.add(calculate(temp));
            return;
        }

        for (int i = 0; i < operators.length; i++) {
            if(!verify[i]){
                verify[i] = true;
                temp[idx] = operators[i];
                combination(temp, idx+1);
                verify[i] = false;
            }
        }
    }

    private static int calculate(char[] op){
        int result = arr[0];
        int cnt = 1;
        for (char c: op) {
            switch (c){
                case '+':
                    result += arr[cnt];
                    break;
                case '-':
                    result -= arr[cnt];
                    break;
                case '*':
                    result *= arr[cnt];
                    break;
                case '/':
                    result /= arr[cnt];
                    break;
            }
            cnt++;
        }

        //System.out.println(result);

        return result;
    }
}
