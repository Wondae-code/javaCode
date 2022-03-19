package SSAFY;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class BOJ_1713 {
    static ArrayList<Person> order;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int size = sc.nextInt();
        order = new ArrayList<>();
        int peopleCnt = sc.nextInt();

        outer : for (int i = 0; i < peopleCnt; i++) {
            int num = sc.nextInt();
            // 값이 있는 경우
            int key = search(num);
            if(search(num) != -1){
                order.get(key).vote++;
            }else {
                // 값이 없는 경우
                // 빈 곳이 있는 경우
                if (order.size() < size) {
                    order.add(new Person(num, 1, i));
                    continue outer;
                }

                // 빈 곳이 없는 경우 -> 가장 vote가 적은 것 빼기
                Collections.sort(order);
                order.remove(0);
                order.add(new Person(num, 1, i));
            }
        }


        Collections.sort(order, Comparator.comparingInt(e->e.person));

        for (Person p : order) {
            System.out.print(p.person + " ");
        }

    }

    private static class Person implements Comparable<Person>{
        int person, vote, arrival;

        public Person(int person, int vote, int arrival) {
            this.person = person;
            this.vote = vote;
            this.arrival = arrival;
        }

        @Override
        public int compareTo(Person o) {
            if(this.vote == o.vote){
                return this.arrival - o.arrival;
            }
            return this.vote - o.vote;
        }

        @Override
        public String
        toString() {
            return "Person{" +
                    "person=" + person +
                    ", vote=" + vote +
                    ", arrival=" + arrival +
                    '}';
        }
    }

    private static int search(int value){
        for (int i = 0; i < order.size(); i++) {
            Person temp = order.get(i);
            if(temp.person == value){
                // 키 찾기
                return order.indexOf(temp);
            }
        }
        return -1;
    }
}
