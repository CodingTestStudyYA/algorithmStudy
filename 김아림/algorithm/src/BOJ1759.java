import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1759 {

    static int L, C; // 최소 한개의 모음, 두개의 자음
    // 순서 신경 안씀 => 조합
    static char[] tgt;
    static char[] src;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        src = new char[C];
        tgt = new char[L];

        for(int i = 0; i < C; i++){
            src[i] = st.nextToken().charAt(0);
        }

        Arrays.sort(src);
        comb(0,0);
        System.out.println(sb);
    }

    static void comb(int tgt_idx, int src_idx){
        if(tgt_idx == L){
            int count_mo = 0;
            int count_ja = 0;

            for(char c : tgt){
                if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u'){
                    count_mo++;
                }else {
                    count_ja++;
                }
            }

            if(count_mo >= 1 && count_ja >= 2){
                sb.append(tgt).append("\n");
            }

            return;
        }

        if(src_idx == C){
            return;
        }

        tgt[tgt_idx] = src[src_idx];
        comb(tgt_idx+1, src_idx+1);
        comb(tgt_idx, src_idx+1);
    }
}
