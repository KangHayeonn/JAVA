// 로봇 청소기 (백준 14503번)

/* [ 알고리즘 ]
 * 
 * 1. 세로크기 N, 가로크기 M과 로봇청소기가 있는 칸의 좌표 와 방의 맵을 입출력 받음
 * 2. 청소가능한 칸 체크 (DFS)
 * 3. 제자리에서 방향 움직이는 것 (방향 + 3) % 4 , 뒤로 움직이는 것 (방향 + 2) % 4
 * 
 */
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q14503 {
	static int count = 0; // 청소하는 칸 개수
	static int dx[] = {0, 1, 0, -1}; // 북 동 남 서
	static int dy[] = {-1, 0, 1, 0}; // 북 동 남 서
	static int N;
	static int M;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 세로크기
		M = Integer.parseInt(st.nextToken()); // 가로크기
		st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken()); // 행
		int c = Integer.parseInt(st.nextToken()); // 열
		int d = Integer.parseInt(st.nextToken()); // 바라보는 방향 (북쪽 : 0, 동쪽 : 1, 남쪽 : 2 , 서쪽 : 3)
		
		int map[][] = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		boolean isVisited[][] = new boolean[N][M];
		clean(map, isVisited, r, c, d);
		
		System.out.println(count);
	}
	public static void clean(int[][] map, boolean[][] isVisited, int r, int c, int direction) {
		if(!isVisited[r][c]) {
			isVisited[r][c] = true;
			count++;
		}
		
		boolean check = false;
		
		for(int i=0; i<4; i++) {
			direction = (direction + 3) % 4;
			int nx = c + dx[direction];
			int ny = r + dy[direction];
			
			if(nx<0 || ny<0 || nx>=M || ny>=N) continue;
			
			if(map[ny][nx]==0 && !isVisited[ny][nx]) {
				clean(map, isVisited, ny, nx, direction);
				check = true;
				break;
			}
		}
		
		if(!check) {
			int nx = c + dx[(direction + 2) % 4];
			int ny = r + dy[(direction + 2) % 4];
			
			if(map[ny][nx]==0) {
				clean(map, isVisited, ny, nx, direction); // 바라보는 방향 유지
			}
		}
	}
}
