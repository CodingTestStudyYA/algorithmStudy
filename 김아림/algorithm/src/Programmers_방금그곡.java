import java.util.PriorityQueue;

public class Programmers_방금그곡 {

    static String m = "A#";

    static String[] musicinfos = { "12:00,12:01,HELLO,A#" };

    public static void main(String[] args) {
        PriorityQueue<Song> list = new PriorityQueue<>((o1, o2) -> o2.runtime - o1.runtime);

        String answer = "";
        String memory = replace(m);

        // 일단 문자열 쪼개기 먼저

        for (String input : musicinfos) {
            String[] oneSong = input.split(",");
            String[] startTime = oneSong[0].split(":");
            String[] endTime = oneSong[1].split(":");

            // 음악 시간 게산하기
            int start_hour = Integer.parseInt(startTime[0]);
            int end_hour = Integer.parseInt(endTime[0]);

            int start_m = Integer.parseInt(startTime[1]);
            int end_m = Integer.parseInt(endTime[1]);

            int runtime = 0;
            if (start_hour == end_hour)
                runtime = end_m - start_m;

            else if (start_hour < end_hour) {
                int hour = (end_hour - start_hour - 1) * 60;
                int minute = 60 - start_m + end_m;
                runtime = hour + minute;
            }

            // runtime만큼 반복하기? No
            // 중간부터 들을 경우를 고려해줘야......

            StringBuilder sb = new StringBuilder();
            String note = replace(oneSong[3]);

            StringBuilder stringBuilder = new StringBuilder();
            for (int j = 0; j < runtime; j++) {
                sb.append(note.charAt(j % note.length()));
            }

            Song s = new Song(oneSong[0], oneSong[1], oneSong[2], sb, runtime);

            list.add(s);
        }

        boolean flag = false;

        while (!list.isEmpty() && !flag) {

            Song s = list.poll(); // 곡 하나 빼서
            // 여기서 이제 비교해줘야함
            StringBuilder note = s.note;
            if (note.toString().contains(memory)) {
                answer = s.title;
                break;
            }

        }

        if (answer.equals(""))
            answer = "(None)";

        System.out.println(answer);

    }

    static public String replace(String str) {
        str = str.replace("A#", "a");
        str = str.replace("G#", "g");
        str = str.replace("F#", "f");
        str = str.replace("D#", "d");
        str = str.replace("C#", "c");

        return str;
    }


    static class Song {
        String title;
        String startTime;
        String endTime;
        StringBuilder note;
        int runtime;

        Song(String startTime, String endTime, String title, StringBuilder note, int runtime) {
            this.title = title;
            this.startTime = startTime;
            this.endTime = endTime;
            this.note = note;
            this.runtime = runtime;
        }

    }
}