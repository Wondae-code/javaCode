import java.util.Scanner;

public class BOJ_2941 {
    static String[] croatian = {"c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z="};
    public static void main(String[] args) {
        // 처음 든 생각 : 인덱스로 처음부터 돌면서
        // 두번째 든 생각 : 크로아티안 알파벳을 따로 배열에 넣고 찾가.
        Scanner sc = new Scanner(System.in);

        String raw = sc.next();

        for (String word: croatian) {
            if(raw.contains(word)){
                raw = raw.replace(word, "0"); // 길이만 측정할 것이라 "0"으로 대체함.
            }
        }
        System.out.println(raw.length());
    }
}
