// 미로 탐색 (백준 2178번)

/* [ 알고리즘 ]
 * 
 * 1. (1,1) 시작지점부터 BFS를 돌림
 * 2. 상하좌우를 체크하며 1이 있는 곳은 퍼지고 이동할 수 있는 칸으로 count
 * 3. (N,M)에 위치하게 되면 그때의 count값을 return (시작 위치와 도착 위치 모두 포함)
 * 
 */
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q2178 {
	static int[][] map;
	static int dx[] = { 0, 0, -1, 1 }; // 상 하 좌 우
	static int dy[] = { 1, -1, 0, 0 }; // 상 하 좌 우
	
	public static class type {
		private int x;
		private int y;
		private int count;
		
		public type(int x, int y, int count) {
			this.x = x;
			this.y = y;
			this.count = count;
		}
	}
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 세로 길이
		int M = Integer.parseInt(st.nextToken()); // 가로 길이
		
		map = new int[N][M];
		
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = str.charAt(j) - '0'; // 문자 -> 숫자
			}
		}
		
		int count = BFS(0, 0, M, N);
		
		System.out.print(count);
	}
	public static int BFS(int x, int y, int M, int N) {
		Queue<type> queue = new LinkedList<type>();
		queue.add(new type(x, y, 1));
		
		while(!queue.isEmpty()) {
			type q = queue.poll();
			
			if( q.x==M-1 && q.y==N-1 ) return q.count;
			
			for(int i=0; i<4; i++) {
				int nx = q.x + dx[i];
				int ny = q.y + dy[i];
				
				if( nx<0 || ny<0 || nx>=M || ny>=N ) continue;
				
				if(map[ny][nx]==1) {
					map[ny][nx] = q.count+1;
					queue.add(new type(nx, ny, q.count+1));
				}
			}
		}
		return -1;
	}
}
