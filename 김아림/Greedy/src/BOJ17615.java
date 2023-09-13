import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class BOJ17615 {

    public static void main(String[] args)  throws IOException {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n= Integer.parseInt(br.readLine());
        String str=br.readLine();
        int result=Integer.MAX_VALUE;

        int red=0,blue=0;
        for(int i=0;i<n;i++) {
            if(str.charAt(i)=='R')
                red++;
            else
                blue++;
        }

        /*
         * 빨간공 모두 왼쪽으로 옮기는 경우
         * 기존에 제일 왼쪽에 있는 빨간공은 옮기지 않아도 되기 때문에
         * 모든 빨간공의 개수 - 왼쪽에 있는 빨간공의 개수를 하면 옮겨야 하는 빨간공의 개수가 나온다
         * 오른쪽으로 옮기는 경우는 반대로 코드 짜면 됨
         */
        int red_left=0;
        for(int i=0;i<n;i++) {
            if(str.charAt(i)=='R')
                red_left++;
            else
                break;
        }
        result=Math.min(red-red_left,result);

        // 빨간공 모두 오른쪽으로 옮기는 경우
        int red_right=0;
        for(int i=n-1;i>=0;i--) {
            if(str.charAt(i)=='R')
                red_right++;
            else
                break;
        }
        result=Math.min(red-red_right, result);

        // 파란공 모두 왼쪽으로 옮기는 경우
        int blue_left=0;
        for(int i=0;i<n;i++) {
            if(str.charAt(i)=='B')
                blue_left++;
            else
                break;
        }
        result=Math.min(blue-blue_left,result);

        // 파란공 모두 오른쪽으로 옮기는 경우
        int blue_right=0;
        for(int i=n-1;i>=0;i--) {
            if(str.charAt(i)=='B')
                blue_right++;
            else
                break;
        }
        result=Math.min(blue-blue_right, result);

        System.out.println(result);
    }
}