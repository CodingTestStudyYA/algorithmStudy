
class Programmers_단어변환  {

    static boolean[] visit;
    static int answer = Integer.MAX_VALUE;
    public int solution(String begin, String target, String[] words) {

        visit = new boolean[words.length];
        dfs(begin, target, words, 0); // 마지막 인자 수정해주기
        if(answer == Integer.MAX_VALUE) answer = 0;
        return answer;
    }

    // now -> 지금 들고온 글자 , cnt는 횟수
    static void dfs(String now, String target, String[] words, int cnt){
        // 이미 사용했다면?
        // 돌고 나서 사용한 글자의 visit을 다시 false로 바꿔줘야함
        // 최소 과정인데...


        if(cnt > answer) return;  // 어차피 넘으면 안됨요
        if(now.equals(target)){
            // 찾은 경우에
            answer = Math.min(answer, cnt);
            return;
        }

        // now에서 하나 더 바꿔서 갈 수 있는 단어를 넣어서 재귀
        for(int idx = 0; idx < words.length; idx++ ){
            // 바꿀 대상 찾기
            if(visit[idx]) continue; // 이미 선택 되었던건 ㄴㄴ

            // 아직 방문하지 않은거면 글자중에서 하나만 다른지 체크해야함
            int num = 0;
            for(int i = 0; i < now.length(); i++){
                if(now.charAt(i) != words[idx].charAt(i)){
                    num++;
                }
                if(num > 1) continue; // 두개 이상 차이나면 ㄴㄴ
            }

            // 하나만 차이 나는 경우
            if(num == 1){
                visit[idx] = true;
                dfs(words[idx], target, words, cnt+1);
                visit[idx] = false;
            }

        }


    }
}