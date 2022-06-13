import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;


public class PRO_92341 {
    static int[] fees = {180, 5000, 10, 600};
    static String[] records = {"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"};
    static HashMap<String, Integer> times;
    static HashMap<String, int[]> total;
    // 시간 계산해야하고, 시간당 금액 계산하기
    public static void main(String[] args) {
        // 임시 시간
        times = new HashMap<String, Integer>();
        // 전체 시간, 돈
        total = new HashMap<String, int[]>();

        for (int i = 0; i < records.length; i++) {
            String[] raw = records[i].split(" ");
            int time = toTime(raw[0]);
            String num = raw[1];
            if(raw[2].equals("IN")){
                if(time != toTime("23:59")) {
                    times.put(num, time);
                    // 시간, 돈
                    total.put(num, new int[]{0, 0});
                }
            }else if(raw[2].equals("OUT")){
                // 시간 계산하기
                int gap = time - times.get(num) + total.get(num)[0];
                // 기본시간 미만이면 기본 요금
                if(gap <= fees[0]){
                    total.put(num, new int[]{total.get(num)[0] + gap, fees[1]});
                }
                // 이상이면 추가요금
                else{
                    int price = total.get(num)[1] == 0 ? fees[1] : total.get(num)[1];
                    gap = (int)(((double)gap / 10) * 10);
                    price += ((gap - fees[0])/fees[2]);
                    total.put(num, new int[]{total.get(num)[0] + gap, price});
                    times.remove(num);
                }
            }
        }

        // 남은 차들은 23:59 기준으로 정렬하기z`
        while(!times.isEmpty()){
        }
    }


    private static int toTime(String s) {
        String[] temp = s.split(":");
        return Integer.parseInt(temp[0]) * 60 + Integer.parseInt(temp[1]);
    }
}
