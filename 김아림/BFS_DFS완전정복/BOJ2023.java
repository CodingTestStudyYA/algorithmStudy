package DFS_BFS;

import java.util.Scanner;

public class BOJ2023 {
    static int N;
    public static void main(String[] args) {
        // 소수가 될 수 있는 한자리 수 -> 2, 3, 5, 7 -> 1의 자리는 얘네 뿐임
        // 소수자 될 수 있는 두자리 수 ? => 너무 많음 ...
        // 일단 한자리 수는 정해져있으니까 그걸로 뒤에 붙이는 식으로 해야함 -> N-1번 돌리면 됨

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        making(2,1);
        making(3,1);
        making(5,1);
        making(7,1);

    }

    static void making(int n, int cnt){

        if(cnt == N && check(n)){ // N-1번 돌고 난 결과는 N일때 나오니까
            System.out.println(n);
        }

        for(int i = 0; i<=9; i++){
            if(check(10*n + i))
                making(10*n + i, cnt+1);
        }

    }

    static boolean check(int num){

         for(int i = 2; i<num; i++){
             if(num % i == 0) return false;
         }

        return true;
    }

}
