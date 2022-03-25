// 거리두기 확인하기 (프로그래머스 LEVEL2 - Kakao)

package TestPractice;

import java.util.Arrays;

public class CheckDistance {
	private static int[][] map;
	private static boolean[][] visited;
    private static int[] dx = {0, 0, -1, 1};
    private static int[] dy = {-1, 1, 0, 0};
    private static int M, N;
    
    static class type {
        int x;
        int y;
        
        public type(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
	public static void main(String args[]) {
		String[][] places = {{"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"}, {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"}, {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"}, {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"}, {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}};
		System.out.println(solution(places));
	}
	public static int[] solution(String[][] places) {
		int[] answer = {};
		M = places[0].length; // x축 길이
        N = places.length; // y축 길이
        
        for(int i=0; i<N; i++) {
        	map= new int[5][5];
        	visited = new boolean[5][5];
        	for(int j=0; j<M; j++) {
        		for(int k=0; k<5; k++) {
        			char s = places[i][j].charAt(k);
        			if(s=='P') {
        				map[j][k] = 1;
        			} else if(s=='O') {
        				map[j][k] = 0;
        			} else {
        				map[j][k] = -1;
        			}
        		}
        		System.out.println(places[i][j]);
        	}
        	System.out.println(Arrays.deepToString(map));
        }
        return answer;
        
	}
}

