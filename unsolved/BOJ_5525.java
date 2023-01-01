import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class BOJ_5525 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int lenS = Integer.parseInt(br.readLine());
        char[] S = br.readLine().toCharArray();

        boolean check = false;
        int pivot = 0;
        ArrayList<char[]> parts = new ArrayList<>();

        for (int i = 0; i < lenS; i++) {
            if(!check && S[i] == 'I'){
                pivot = i;
                check = true;
            }
            if(check){
                if(i < lenS-2 && S[i+1] == 'O' && S[i+2] == 'I'){
                    i++;
                }else{
                    parts.add(Arrays.copyOfRange(S, pivot, i+1));
                    check = false;
                }
            }
        }

        int sum = 0;
        for (char[] part : parts) {
            if (part.length >= ((N * 2) + 1)) {
                sum += (part.length/2) - N + 1;
            }
        }

        System.out.println(sum);
    }
}
