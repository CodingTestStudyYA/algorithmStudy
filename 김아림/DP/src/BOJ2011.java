import java.util.*;
import java.io.*;

public class BOJ2011 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[] dp; //i-1 번째 까지 문자 암호 해석의 경우의 수


    public static void main(String[] args) throws IOException {
        String og_password = br.readLine();

        if(og_password.charAt(0)=='0') {
            System.out.println("0");
            return;
        }

        dp = new int[og_password.length() + 1];
        dp[0] = dp[1] = 1;

        for (int i = 2; i <= og_password.length(); i++) {
            char cur = og_password.charAt(i-1);
            char pre = og_password.charAt(i-2);

            if (cur == '0') {
                if (pre == '1' || pre == '2') dp[i] = dp[i - 2] % 1000000;
                else break;
            } else {
                if (pre == '0') dp[i] = dp[i - 1] % 1000000;
                else {
                    int temp = (pre - '0') * 10 + (cur - '0');
                    if (1 <= temp && temp <= 26) dp[i] = (dp[i - 2] + dp[i - 1]) % 1000000;
                    else dp[i] = dp[i - 1] % 1000000;
                }
            }
        }

        System.out.println(dp[og_password.length()] % 1000000);
    }
}

