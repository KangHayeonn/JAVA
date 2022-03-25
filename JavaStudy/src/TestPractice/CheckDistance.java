// 거리두기 확인하기 (프로그래머스 LEVEL2 - Kakao)

package TestPractice;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class CheckDistance {
	public static void main(String args[]) {
		String[][] places = {{"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"}, {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"}, {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"}, {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"}, {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}};
		System.out.println(Arrays.toString(solution(places)));
	}
	public static int[] solution(String[][] places) {
		int[] answer = new int[places.length];
        
        for(int i=0; i<places.length; i++) {
        	String[] p = places[i];
            boolean check = true;
        	
        	for(int j=0; j<5; j++) {
        		for(int k=0; k<5; k++) {
        			char s = p[j].charAt(k);
        			if(s=='P') {
        				if(!bfs(p, j, k)) check = false;
        			}
        		}
        	}
        	
        	answer[i] = check ? 1: 0;
        }
        return answer;
	}
	public static boolean bfs(String[] p, int r, int c) { // row: 가로, column: 세로
		int[] dx = {0, 0, -1, 1};
	    int[] dy = {-1, 1, 0, 0};
		
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {r, c});
		
		while(!q.isEmpty()) {
			int[] pos = q.poll();
			
			for(int i=0; i<4; i++) {
				int nr = pos[0] + dx[i]; // 가로
				int nc = pos[1] + dy[i];    // 세로
                
                // 처음 출발점 P는 제외 (중요!)
				if(nr == r && nc == c) continue;
				
				if(nr<0 || nr>=5 || nc<0 || nc>=5) continue;
				
				// 맨헤튼거리 계산
				int d = Math.abs(nr-r) + Math.abs(nc-c);
				
				if(d<=2 && p[nr].charAt(nc) == 'P') return false;
				else if(d<2 && p[nr].charAt(nc) == 'O') q.add(new int[] {nr, nc});
			}
		}
		return true;
	}
}

