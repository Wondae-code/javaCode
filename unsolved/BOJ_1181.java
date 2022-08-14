import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class BOJ_1181 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int k = Integer.parseInt(br.readLine());
        String[] input =  new String[k];

        for (int i = 0; i < k; i++) {
            input[i] = br.readLine();
        }

        // 중복 제거
        Object[] test = Arrays.stream(input).distinct().toArray();

        String[] words = new String[test.length];

        for (int i = 0; i < test.length; i++) {
            words[i] = (String) test[i];
        }

        // 정렬
        Arrays.sort(words, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if(o1.length() == o2.length()){
                    return o1.compareTo(o2);
                }
                return o1.length() - o2.length();
            }
        });

        // 출력
        StringBuilder sb = new StringBuilder();
        for (String s : words) {
            sb.append(s).append('\n');
        }

        System.out.println(sb.toString());
    }
}
