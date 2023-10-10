import java.util.ArrayList;
import java.util.List;

public class Programmers_스킬트리 {

    static String skill = "CBD";
    static String[] skill_trees = {"BACDE", "CBADF", "AECB", "BDA"};

    public static void main(String[] args) {
        int answer = 0;

        for (int i = 0; i < skill_trees.length; i++) {

            // 위상 정렬인듯 (순서)
            // 연결 그래프, 차수 저장 배열 필요함
            boolean flag = true;
            List<Integer>[] list = new ArrayList[28];
            int[] degree = new int[28]; // 알파벳 전부 넣어야지.........
            String cur_tree = skill_trees[i];


            for (int j = 0; j < list.length; j++) {
                list[j] = new ArrayList<>();
            }

            for (int j = 0; j < skill.length() - 1; j++) {
                list[skill.charAt(j) - 'A'].add(skill.charAt(j + 1) - 'A');
                degree[skill.charAt(j + 1) - 'A'] += 1;
            }


            for (int idx = 0; idx < cur_tree.length(); idx++) {
                int cur = cur_tree.charAt(idx) - 'A';

                if (degree[cur] == 0) {
                    // 가능함
                    // cur을 base로 하는 리스트의 degree 하나씩 빼주기
                    for (Integer it : list[cur]) {
                        if (degree[it] == 0)
                            continue;
                        degree[it]--;
                    }
                } else {
                    flag = false;
                    break;
                }
            }

            if (flag) {
                answer = answer == -1 ? 1 : answer + 1;
            }
        }

        System.out.println(answer);
    }

}