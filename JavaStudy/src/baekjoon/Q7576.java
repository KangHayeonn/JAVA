// 토마토 (백준 7576번)

/* [ 알고리즘 ]
 * 
 * 1. BFS 를 이용해 익은 토마토의 위치에서 부터 퍼뜨림
 * 2. 상하좌우를 체크하며 0일 경우 1로 변경해 준 뒤 진행
 * 3. -1을 만날 경우 패스
 * 4. 만약 모든 맵 탐색이 끝날 경우, 0이 남아있으면 -1을 출력 (토마토가 모두 익지 못하는 상황)
 * 5. 1일 경우는 0을 출력
 * 6. 1보다 클 경우는 해당 값에서 -1을 함 (처음 시작지점은 뺀 값)
 * 
 */
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q7576 {
	static int map[][];
	static int isVisited[][];
	static int[] dx = { 0, 0, -1, 1 }; // 상 하 좌 우
	static int[] dy = { 1, -1, 0, 0 };
	static Queue<type> queue;
	
	public static class type {
		private int x;
		private int y;
		private int count;
		
		public type (int x, int y, int count) {
			this.x = x;
			this.y = y;
			this.count = count;
		}
	}
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken()); // 상자의 가로 칸의 수
		int N = Integer.parseInt(st.nextToken()); // 상자의 세로 칸의 수
		
		map = new int[N][M];
		isVisited = new int[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		queue = new LinkedList<type>();
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j]==1) {
					queue.add(new type(j, i, 1));
					isVisited[i][j] = 1;
				} else if(map[i][j]==-1) {
					isVisited[i][j] = -1;
				}
			}
		}
		
		BFS(M, N);
		
		int max = 0;
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(isVisited[i][j]==0) {
					System.out.print(-1);
					return;
				} else if(isVisited[i][j] > max) {
					max = isVisited[i][j];
				}
			}
		}
		
		System.out.println(max-1);
	}
	public static void BFS(int M, int N) {
		
		while(!queue.isEmpty()) {
			type q = queue.poll();
			
			for(int i=0; i<4; i++) {
				int nx= q.x + dx[i];
				int ny= q.y + dy[i];
				
				if( nx<0 || ny<0 || nx>=M || ny >=N ) continue;
				
				if(map[ny][nx] == 0) { // 안 익은 토마토일 경우
					
					if(isVisited[ny][nx] == 0) {
						isVisited[ny][nx] = q.count +1;
						queue.add(new type(nx, ny, q.count+1));
					}
				}
			}
		}
	}
}
