// �˰��� (���� 1261��)

/* [ �˰��� ]
 * 
 * - �켱���� ť�� �̿��� ���ͽ�Ʈ�� �˰��� ���
 */
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Q1261 {
	static boolean[][] isVisited;
	static int[][] map;
	static int[] dx = {0, 0, -1, 1}; // �� �� �� ��
	static int[] dy = {1, -1, 0, 0}; // �� �� �� ��
	
	public static class type implements Comparable<type> {
		private int x;
		private int y;
		private int count;
		public type(int x, int y, int count) {
			this.x = x;
			this.y = y;
			this.count = count;
		}
		@Override
		public int compareTo(type t) {
			return this.count - t.count; // count�� �������� ��������
		}
	}
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken()); // ����ũ��
		int N = Integer.parseInt(st.nextToken()); // ����ũ��
		
		map = new int[N][M];
		
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}
		
		isVisited = new boolean[N][M];
		int answer = BFS(0, 0, N, M);
		
		System.out.print(answer);
	}
	
	public static int BFS(int x, int y, int N, int M) {
		PriorityQueue<type> q = new PriorityQueue<>();
		q.add(new type(x, y, 0));
		isVisited[y][x] = true;

		while(!q.isEmpty()) {
			type t = q.poll();
			
			if(t.x == M-1 && t.y == N-1) return t.count;
			
			for(int i=0; i<4; i++) {
				int nx = t.x + dx[i];
				int ny = t.y + dy[i];
				
				if(nx<0 || ny<0 || nx>=M || ny>=N) continue;
				
				if(!isVisited[ny][nx]) {
					if(map[ny][nx]==0) { // �� ���� ���
						q.add(new type(nx, ny, t.count));
						isVisited[ny][nx] = true;
					} else {             // ���� ���
						q.add(new type(nx, ny, t.count+1));
						isVisited[ny][nx] = true;
					}
				}
			}
		}
		return 0;
	}
}
