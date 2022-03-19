import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class PRO_92334 {
    static String[] id_list = {"ryan", "con"};
    static String[] report = {"ryan con", "ryan con", "ryan con", "ryan con"};
    static int k = 3;

    public static void main(String[] args) {
        HashMap<String, foo> votes = new HashMap<>();
        // 신고 당하는 사람 : 신고자 리스트, 신고횟수
        for (String s : id_list) {
            votes.put(s, new foo());
        }

        for(String s : report){
            String[] p = s.split(" ");
            foo f = votes.get(p[1]);
            // 만약 f.src에 p[0]이 있으면 넘김
            if(!f.src.contains(p[0])){
                f.src.add(p[0]);
                f.val++;
            }
            votes.put(p[1], f);
        }
        
        // 결과를 배열에 담기
        HashMap<String, Integer> result = new HashMap<>();
        for (String s: id_list){
            result.put(s, 0);
        }
        for (foo f: votes.values()) {
            if(f.val >= k){
                for (String s : f.src) {
                    int v = result.get(s);
                    result.put(s, v+1);
                }
            }
        }

        int[] Ans = new int[id_list.length];
        int cnt = 0;
        for (String s : id_list) {
            Ans[cnt] = result.get(s);
            cnt++;
        }

        System.out.println(Arrays.toString(Ans));
    }

    private static class foo{
        ArrayList<String> src = new ArrayList<>();
        int val = 0;
    }
}
