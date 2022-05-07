package MapTest;

//4번 (DFS -> 다익스트라로 코드 변경하기)

import java.util.*;

public class Q4 {
 static int[][] map;
 static int N;
 static boolean[] summit;
 static boolean[] gateList;
 static int count;
 static ArrayList<Integer> answerList = new ArrayList<>();
 public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
     int[] answer = {};
     map = new int[n+1][n+1];
     N = n;

     summit = new boolean[N+1];
     for(int i=0; i<summits.length; i++) {
         summit[summits[i]] = true;
     }
     gateList = new boolean[N+1];
     for(int i=0; i<gates.length; i++) {
         gateList[gates[i]] = true;
     }

     for(int i=0; i<paths.length; i++) {
         int from = paths[i][0];
         int to = paths[i][1];
         int cost = paths[i][2];

         map[from][to] = cost;
         map[to][from] = cost;
     }

     for(int i=0; i<gates.length; i++) {
         int gate = gates[i];
         boolean[] isVisited = new boolean[N+1];
         count = 0;
         dfs_go(gate, gate, isVisited, 0); // 출발지 -> 산봉우리
     }

     for(int i=0; i<answerList.size(); i++) {
         System.out.println(answerList.get(i));
     }
     return answer;
 }
 public static void dfs_go(int gate, int from, boolean[] isVisited, int value) {
     if(gate != from && gateList[from]) {
         return;
     }
     isVisited[from] = true;
     if(summit[from]) {
         count = Math.max(count, value);
         boolean[] isVisited2 = new boolean[N+1];
         isVisited2[from] = true;
         dfs_back(gate, from, from, isVisited2, isVisited, 0);
     }

     for(int i = 1; i<=N; i++) {
         if(map[from][i] > 0 && !isVisited[i]) {
             value = Math.max(value, map[from][i]);
             dfs_go(gate, i, isVisited, value);
         }
     }
 }
 public static void dfs_back(int gate, int target_summit, int from, boolean[] isVisited2, boolean[] isVisited, int value) {
     if(gate != from && gateList[from]) return;
     if(target_summit != from && summit[from]) {
         return;
     }
     isVisited2[from] = true;
     if(from == gate) { // 정답
         count = Math.max(count, value);
         answerList.add(count);
         return;
     }

     for(int i = 1; i<=N; i++) {
         if(map[from][i] > 0 && !isVisited2[i]) {
             value = Math.max(value, map[from][i]);
             isVisited2[i] = true;
             dfs_back(gate, target_summit, i, isVisited2, isVisited, value);
             isVisited2[i] = false;
         }
     }
 }
}
