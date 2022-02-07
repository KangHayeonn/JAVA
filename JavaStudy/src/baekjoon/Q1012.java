// 유기농 배추 (백준 1012번)

/* [ 알고리즘 ]
 * 
 * 1. 섬의 개수(백준 4963번)와 동일한 문제
 * 2. BFS 이용
 * 
 */
package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q1012 {
	static int[][] map;
	static int dx[] = { 0, 0, -1, 1 }; // 상 하 좌 우
	static int dy[] = { 1, -1, 0, 0 };
	static int count;
	
	public static class type {
		private int x;
		private int y;
		
		public type (int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스의 개수
		StringTokenizer st = new StringTokenizer("");
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		for(int i=0; i<T; i++) {
			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken()); // 배추밭의 가로길이
			int N = Integer.parseInt(st.nextToken()); // 배추밭의 세로길이
			int K = Integer.parseInt(st.nextToken()); // 배추 심어져 있는 위치 개수
			
			map = new int[N][M];
			count = 0;
			
			for(int j=0; j<K; j++) {
				st = new StringTokenizer(br.readLine());
				int X = Integer.parseInt(st.nextToken()); // x값
				int Y = Integer.parseInt(st.nextToken()); // y값
				
				map[Y][X] = 1;
			}
			
			for(int l=0; l<N; l++) {
				for(int m=0; m<M; m++) {
					if(map[l][m]==1) {
						BFS(m, l, M, N);
					}
				}
			}
			
			bw.write(count + "\n");
		}
		
		bw.flush();
	}
	public static void BFS(int x, int y, int M, int N) {
		Queue<type> queue = new LinkedList<type>();
		queue.add(new type(x, y));
		
		while(!queue.isEmpty()) {
			type q = queue.poll();
			
			for(int i=0; i<4; i++) {
				int nx = q.x + dx[i];
				int ny = q.y + dy[i];
				
				if( nx<0 || ny<0 || nx>=M || ny>=N ) continue;
				
				if(map[ny][nx]==1) {
					map[ny][nx]=2;
					queue.add(new type(nx, ny));
				}
			}
		}
		count++;
	}
}
