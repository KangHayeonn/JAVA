// 카카오 프렌즈 컬러링북 (프로그래머스 LEVEL2)

// BFS

package TestPractice;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class KakaoFriendsColoringBook {
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
		int[][] picture = {{1,1,1,0}, {1,1,1,0}, {0,0,0,1}, {0,0,0,1}, {0,0,0,1}, {0,0,0,1}};
		System.out.println(Arrays.toString(solution(6, 4, picture)));
	}
	public static int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;
        
        M = m;
        N = n;
        
        visited = new boolean[m][n];
        
        for(int i=0; i<n; i++) {
        	for(int j=0; j<m; j++) {
        		if(!visited[j][i] && picture[j][i]>0) {
        			int count = bfs(i, j, picture[j][i], picture);
        			System.out.println(count);
        			numberOfArea++;
        			if(maxSizeOfOneArea < count) maxSizeOfOneArea = count;
        		}
        	}
        }

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }
    private static int bfs(int x, int y, int num, int[][] picture) {
        Queue<type> q = new LinkedList<>();
        q.add(new type(x, y));
        int countSum =1; 
        
        while(!q.isEmpty()) {
            type t = q.poll();
            
            visited[t.y][t.x] = true;
            
            for(int i=0; i<4; i++) {
                int nx = t.x + dx[i];
                int ny = t.y + dy[i];
                System.out.println(nx + " : " + ny);
                
                if(nx >= 0 && nx < N && ny >= 0 && ny < M) {
                    if(!visited[ny][nx] && num == picture[ny][nx]) {
                    	System.out.println(" => " +nx + " : " + ny);
                        q.add(new type(nx, ny));
                        countSum++;
                        visited[ny][nx] = true; // 이 부분 중요
                    }
                }
            }
        }
        
        return countSum;
    }
}
