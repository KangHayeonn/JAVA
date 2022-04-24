// 로봇 청소기 (백준 골드5)

package beakjoon_samsung;

import java.io.*;
import java.util.*;

public class Q14503 {
	static int N, M;
	static int[][] map;
	static int[] dr = {-1, 0, 1, 0}; // 북 동 남 서
	static int[] dc = {0, 1, 0, -1};
	static int answer = 0;
	public static class Type {
		int r, c, d;
		public Type(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}
	}
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 세로크기
		M = Integer.parseInt(st.nextToken()); // 가로크기
		
		st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		bfs(r, c, d);
		System.out.println(answer);
	}
	public static void bfs(int r, int c, int d) {
		Queue<Type> q = new LinkedList<>();
		q.offer(new Type(r, c, d));
		int nr=0, nc=0;
		
		while(!q.isEmpty()) {
			Type t= q.poll();
			if(map[t.r][t.c] == 0)  {
				answer++;
				map[t.r][t.c] = 2; // 청소함
			}
			d = t.d;
			
			int count = 4;
			boolean check = false;
			while(count-- > 0) {
				d = (d+3)%4;
				nr = t.r + dr[d];
				nc = t.c + dc[d];
				
				if(nr < 0 || nc < 0 || nr >= N || nc >= M) continue;
				
				if(map[nr][nc] == 0) {
					q.offer(new Type(nr, nc, d));
					check = true;
					break;
				} else continue;
			}
			
			if(count<0 && !check) {
				int back_d = (d+2)%4;
				nr = nr - dr[d]+ dr[back_d];
				nc = nc - dc[d]+ dc[back_d];
				if(nr < 0 || nc < 0 || nr >= N || nc >= M) return;
				if(map[nr][nc]==1) return;
				else q.offer(new Type(nr, nc, d));
			}
		}
	}
}
