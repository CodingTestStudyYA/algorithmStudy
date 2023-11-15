import javax.sound.midi.SysexMessage;
import java.util.PriorityQueue;

public class Programmers_방금그곡_ver2 {

    static String m = "C#C"	;
    // CHBCHBCHBCHB
    static String[] musicinfos = {"12:00,12:06,HELLO,C#C#CC#", "11:00,12:00,WWWW,C#C#CC#"
    };
    static String answer = "";

    public static void main(String[] args) {
        class Music {
            int play_time;
            String title;
            String note;

            Music(int play_time, String title, String note) {
                this.play_time = play_time;
                this.title = title;
                this.note = note;
            }
        }

        // C, C#, D, D#, E, F, F#, G, G#, A, A#, B


        //  일치하는 음악이 여러개일때는 재생시간이 가장 긴 음악 반환
        // 조건에 일치하는 음악이 없으면 "(None)"

        // 기억하는 음악 먼저 분배하기
        String memory = convert(m.length(), m);
        System.out.println(memory);

        // 입력값 분배부터 해서 pq에 넣어주기
        PriorityQueue<Music> pq = new PriorityQueue<Music>((o1, o2) -> {
            return o2.play_time - o1.play_time; // 내림차순 정렬 -> 같을 경우 먼저 입력된 음악
        });

        for (String info : musicinfos) {
            String[] infos = info.split(",");

            String[] start_time = infos[0].split(":");
            String[] end_time = infos[1].split(":");
            String title = infos[2];
            String notes = infos[3];

            // 재생시간 구하기
            int start_hour = Integer.parseInt(start_time[0]);
            int start_minute = Integer.parseInt(start_time[1]);
            int end_hour = Integer.parseInt(end_time[0]);
            int end_miniute = Integer.parseInt(end_time[1]);

            int play_time  = 0;
            if(start_hour == end_hour){
                play_time += end_miniute - start_minute;
            }else{ // 시간이 다를 경우
                play_time += (end_hour - start_hour -1) * 60 + (end_miniute + (60 - start_minute));
            }

            String converted_note = convert(play_time, notes);
            Music m = new Music(play_time, title, converted_note);
            pq.add(m);
        }

        // 정보를 다 입력한 뒤에 notes 보기

        while(!pq.isEmpty()){

            Music cur = pq.poll();

            // m과 비교하기
            // 기억하는 시간이 재생시간보다 짧은 경우
            // 기억시간 AB
            // 재생시간 5 -> 악보가 BABC라면

            if(memory.length() <= cur.note.length()){

                System.out.println("case 1 : " + cur.title + " " + cur.note + " " + cur.play_time);
                // 어디를 기억하는지 알 수 없으므로 하나씩 검사해본다.
                // 시작점
                for(int start = 0; start < cur.note.length(); start++){
                    boolean flag = true;
                    int idx_for_cur = start;
                    int idx_for_memory = 0;

                    while(idx_for_memory < memory.length()){
                        if(memory.charAt(idx_for_memory) != cur.note.charAt(idx_for_cur)){
                            flag = false;
                            break;
                        }
                        idx_for_memory++;
                        idx_for_cur++;
                        if(idx_for_cur == cur.note.length()){
                            idx_for_cur = 0;
                        }
                    }

                    if(flag){
                        // 답을 찾음
                        answer = cur.title;
                        System.out.println(answer);
                        System.exit(0);
                    }

                }
            }else if(memory.length() > cur.note.length()){
                System.out.println("case 2 : " + cur.title + " " + cur.note);
                // 14분 // ABCDEFG 일 경우
                // ABCDEFGABCDEFG
                // 기억시간은 ABCDEFGAB일 경우 -> 8 개
                // 기억하는 시간이 재생시간보다 긴 경우
                // 재생시간을 계속해서 반복해서 기억하는 시간과 모두 일치하는지 확인한다.
                StringBuilder sb = new StringBuilder();
                while(sb.length() < cur.play_time){
                    sb.append(cur.note);
                }
                String stretched_note = sb.toString().substring(0, cur.play_time);
                System.out.println(stretched_note);
                if(stretched_note.contains(memory)){
                    answer = cur.title;
                    System.out.println(answer);
                    System.exit(0);
                    // 이거를 그냥 break만 해버려서 20,21,26,27 틀림 ㅜ.ㅜ 바보
                }
            }
        }

        if(answer.length() == 0) {
            System.out.println("(None)");
        }else{
            System.out.println(answer);
        }

    }


    public static String convert(int playTime, String notes){

        // C# -> H
        // D# -> I
        // F# -> J
        // G# -> K
        // A# -> L
        // notes 배열 구하기

        StringBuilder sb = new StringBuilder();
        char cur = notes.charAt(0);
        sb.append(cur);
        for(int i = 1; i < notes.length(); i++){
            char next = notes.charAt(i);
            if(next == '#'){
                sb.deleteCharAt(sb.length()-1); // 바로 앞에 넣은 애를 빼야함
                switch (cur){
                    case 'C' :
                        sb.append("H");
                        break;
                    case 'D' :
                        sb.append("I");
                        break;
                    case 'F' :
                        sb.append("J");
                        break;
                    case 'G' :
                        sb.append("K");
                        break;
                    case 'A' :
                        sb.append("L");
                        break;
                    case 'E' :
                        sb.append("M");
                        break;
                }
            }else{
                sb.append(next);
                cur = next;
            }
        }

        String converted = sb.toString();
        // 이걸 고침으로써 4번등의 실패 해결
        if(playTime < converted.length()){
            converted = converted.substring(0, playTime);
        }

        return converted;
    }


}
