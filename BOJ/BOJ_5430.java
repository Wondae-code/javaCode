package SSAFY;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

// 아 collections은 뒤집기 있는데 Arrays는 왜 없슴~

public class BOJ_5430 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        OUTER : for (int tc = 0; tc < T; tc++) {
            String raw = br.readLine();
            raw = raw.replace("RR", "");
            char[] cmd = raw.toCharArray();
            // cmd 중 RR 두개 붙어 있는거 제거하기

            br.readLine();

            int[] arr = parse(br.readLine());
            int idxS = 0;
            int idxE = arr.length;
            boolean flag = false; // reversed?

            for (char c: cmd) {
                if(c == 'R'){
                    flag = !flag;
                }else if(c == 'D'){
                    if(!flag) {
                        idxS++;
                    }else{
                        idxE--;
                    }
                    if (idxS > idxE) {
                        sb.append("error\n");
                        continue OUTER;
                    }
                }
            }

            arr = Arrays.copyOfRange(arr, idxS, idxE);
            if(flag) {
                reverse(arr);
            }


            sb.append(Arrays.toString(arr)).append("\n");
        }

        System.out.print(sb.toString().replace(" ", ""));
    }

    private static void reverse(int[] arr){
        int idxL = 0;
        int idxR = arr.length-1;

        while(idxL < idxR){
            swap(arr, idxL, idxR);

            idxL++;
            idxR--;
        }
    }

    private static void swap(int[] arr, int idxL, int idxR) {
        int temp = arr[idxL];
        arr[idxL] = arr[idxR];
        arr[idxR] = temp;
    }

    private static int[] parse(String s){
        /**
         *  parse string input "[1,2,3,4]" to integer array [1,2,3,4]
         */

        // 괄호 제거
        if(!s.equals("[]")) {
            s = s.substring(1, s.length() - 1);
            int[] output = Arrays.stream(s.split(",")).mapToInt(Integer::parseInt).toArray();

            return output;
        }else{
            return new int[0];
        }
    }
}
