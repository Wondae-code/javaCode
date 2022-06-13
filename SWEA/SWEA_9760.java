import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SWEA_9760 {
    static Card[] hands;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            hands = new Card[5];
            String[] raw = br.readLine().split(" ");
            for (int c = 0; c < 5; c++) {
                char suit = raw[c].charAt(0);
                int rawInt = raw[c].charAt(1);
                if (rawInt <= '9' && rawInt >= '2') {
                    rawInt -= '0';
                } else if (rawInt == 'A') {
                    rawInt = 1;
                } else if (rawInt == 'T') {
                    rawInt = 10;
                } else if (rawInt == 'J') {
                    rawInt = 11;
                } else if (rawInt == 'Q') {
                    rawInt = 12;
                } else if (rawInt == 'K') {
                    rawInt = 13;
                }
                hands[c] = new Card(suit, rawInt);
                // System.out.printf("%d : %d/%d : %s\n",test_case, c+1, 5, hands[c]);
                // System.out.println();
            }


        }
    }

    private static boolean StraightFlush() {

        return true;
    }

    private static boolean FourOfaKind() {
        return true;
    }

    private static boolean FullHouse() {
        return true;
    }

    private static boolean Flush() {
        return true;
    }

    private static boolean Straight() {
        return true;
    }

    private static boolean ThreeOfaKind() {
        return true;
    }

    private static boolean TwoPair() {
        return true;
    }

    private static boolean OnePair() {
        return true;
    }

    private static boolean HighCard() {
        return true;
    }

    private static class Card {
        char suit;
        int rank;

        public Card(char suit, int rank) {
            this.suit = suit;
            this.rank = rank;
        }

        @Override
        public String toString() {
            return "Card{" +
                    "suit=" + suit +
                    ", rank=" + rank +
                    '}';
        }
    }
}
