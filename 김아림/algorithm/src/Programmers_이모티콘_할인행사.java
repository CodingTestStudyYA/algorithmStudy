import java.io.IOException;
import java.util.Arrays;

public class Programmers_이모티콘_할인행사 {

    static int[][] users = {
            {40, 2900}, {23, 10000}, {11, 5200}, {5, 5900}, {40, 3100},
            {27, 9200}, {32, 6900}};
    static int[] emoticons = {1300, 1500, 1600, 4900};
    static int[] tgt, price;
    static int[] src = {10, 20, 30, 40};
    static int Cnt; // 사람 수
    static int inputMoney;  // 수입
    static int[] answer = new int[2];

    public static void main(String[] args) throws IOException {
        // 할인율이 10,20,30,40 -> 이모티콘 최대 개수 7개니까.. 조합을 해도 나쁘지 않음
        int length = emoticons.length;
        tgt = new int[length];
        price = new int[length];

        perm(0, length, users, emoticons);

    }

    static void perm(int idx, int length, int[][] users, int[] emoticons) {

        if (idx == length) {

            for (int i = 0; i < length; i++) {
                price[i] = emoticons[i] - (tgt[i] * emoticons[i] / 100);
            }

//            System.out.println(Arrays.toString(tgt));
//            System.out.println(Arrays.toString(price));

            // 여기서 걍 순차적으로 돌아야됨..... 중복순열 맞는듯...?
            selling(length, users, emoticons);
            return;
        }

        for (int i = 0; i < src.length; i++) {
            tgt[idx] = src[i];
            perm(idx + 1, length, users, emoticons);
        }
    }

    // 하나의 할인 조합에 대해서
    static void selling(int length, int[][] users, int[] emoticons) {

        // 각 이모티콘별 할인율을 적용한 금액 tgt에 들어있음
        int packageCnt = 0;  // 패키지 구매 선택 고객
        int selling = 0; // 판매액


        // 한명의 사람 -> 이모티콘을 돌면서 구매여부 결정 및 세팅
        for (int i = 0; i < users.length; i++) {
//            System.out.println(i + 1 + "번째 사용자 ~");
            int[] user = users[i];
            int sum = 0;

            for (int j = 0; j < length; j++) {
                // 자신의 기준에 맞을 경우
                // 할인율이 자기생각보다 이상
                if (tgt[j] >= user[0]) {
//                    System.out.println(j + "번째 상품 구매");
                    sum += price[j]; // 구매 완료
                }
                //
                if (sum >= user[1]) {
//                    System.out.println("안사고 서비스 가입함");
                    // 이렇게 되면 구매 안하고 서비스 가입해버림
                    packageCnt++; // 서비스 가입
                    sum = 0; // 구매 취소
                    break; // 다른건 안봐도 ㄱㅊ
                }
            }
            selling += sum; // 구매한 값 만 큼 총 매출액에 더해줌
        }

        if (Cnt < packageCnt) { // 서비스 가입자수가 제일 많은걸로
//            System.out.println(Cnt + " : " + inputMoney);
            Cnt = packageCnt;
            inputMoney = selling;
        } else if (Cnt == packageCnt && selling > inputMoney) {
//            System.out.println(Cnt + " : " + inputMoney);
            inputMoney = selling;
        }

        answer[0] = Cnt;
        answer[1] = inputMoney;
    }

}
