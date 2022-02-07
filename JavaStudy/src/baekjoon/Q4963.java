// ���� ���� (���� 4963��)

/* [ �˰��� ]
 * 
 * 1. BFS �̿� - �����¿�,�밢�� ��ġ�� ���� ������ �̵� ����
 * 2. �ٴٸ� ������ return
 * 3. �̷��� �� ��� ���� �ִ��� count
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

public class Q4963 {
	static int[][] map;
	static int islandCount;
	static boolean[][] isVisited;
	static int dx[] = { 0, 0, -1, 1, -1, 1, -1, 1 }; // �� �� �� �� ���� ��� ���� �Ͽ�
	static int dy[] = { 1, -1, 0, 0, 1, 1, -1, -1 };
	
	public static class type {
		private int x;
		private int y;
		
		public type(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer("");
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		while(true) {
			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken()); // ���α���
			int N = Integer.parseInt(st.nextToken()); // ���α���
			
			if(M==0 && N==0) break;
			
			map = new int[N][M];
			islandCount = 0;
			isVisited = new boolean[N][M];
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(map[i][j] == 1) {
						BFS(j, i, M, N);
					}
				}
			}

			bw.write(islandCount+"\n");
		}
		
		bw.flush();
	}
	public static void BFS(int x, int y, int M, int N) {
		Queue<type> queue = new LinkedList<type>();
		queue.add(new type(x, y));
		isVisited[y][x] = true;
		
		while(!queue.isEmpty()) {
			type q = queue.poll();
			
			for(int i=0; i<8; i++) {
				int nx = q.x + dx[i];
				int ny = q.y + dy[i];
				
				if( nx<0 || ny<0 || nx>=M || ny>=N ) continue;
				
				if(map[ny][nx]==1 && !isVisited[ny][nx]) {
					map[ny][nx]=2;
					isVisited[ny][nx] = true;
					queue.add(new type(nx, ny));
				}
			}
		}
		islandCount++;
	}
}
