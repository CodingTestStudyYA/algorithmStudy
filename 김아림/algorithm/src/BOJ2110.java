
import java.util.Arrays;
import java.util.Scanner;

public class BOJ2110 {
    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int C = sc.nextInt();
        int arr[] = new int[N];

        for(int i = 0; i<N; i++){
            arr[i] = sc.nextInt();
        }

        Arrays.sort(arr);
        int start = 1;
        int end = arr[N-1] - arr[0];
        while(start <= end) {
            int mid = (start + end) / 2;

            int count = 1;
            int dist = 0;

            for(int i = 1; i < N; i++){
                dist += arr[i] - arr[i-1];
                if(dist >= mid ) {
                    count += 1;
                    dist = 0;
                }
            }

            if(count >= C)
                start = mid + 1;
            else if(count < C)
                end = mid -1;
        }

        System.out.println(end);

    }
}
