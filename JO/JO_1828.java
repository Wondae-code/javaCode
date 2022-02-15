package SSAFY;

import java.util.Arrays;
import java.util.Scanner;

public class JO_1828 {
    static class chem implements Comparable<chem>{
        int low, high;

        chem(int l, int h){
            this.low = l;
            this.high = h;
        }

        @Override
        public int compareTo(chem o) {
            return this.high != o.high ? this.high - o.high : this.low - o.low;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        chem[] chems = new chem[N];

        for (int i = 0; i < N; i++) {
            chems[i] = new chem(sc.nextInt(), sc.nextInt());
        }

        int cnt = getChem(chems);

        System.out.println(cnt);
    }

    private static int getChem(chem[] chems) {
        int cnt = 0;

        Arrays.sort(chems);
        cnt++;

        int maxTemp = chems[0].high;

        for (int i = 1; i < chems.length; i++) {
            if(chems[i].low > maxTemp){
                maxTemp = chems[i].high;
                cnt++;
            }
        }

        return cnt;
    }
}
