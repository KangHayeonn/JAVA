// ����� ���� (���� 1012��)

/* [ �˰��� ]
 * 
 * 1. ���� ����(���� 4963��)�� ������ ����
 * 2. BFS �̿�
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
	static int dx[] = { 0, 0, -1, 1 }; // �� �� �� ��
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
		int T = Integer.parseInt(br.readLine()); // �׽�Ʈ ���̽��� ����
		StringTokenizer st = new StringTokenizer("");
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		for(int i=0; i<T; i++) {
			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken()); // ���߹��� ���α���
			int N = Integer.parseInt(st.nextToken()); // ���߹��� ���α���
			int K = Integer.parseInt(st.nextToken()); // ���� �ɾ��� �ִ� ��ġ ����
			
			map = new int[N][M];
			count = 0;
			
			for(int j=0; j<K; j++) {
				st = new StringTokenizer(br.readLine());
				int X = Integer.parseInt(st.nextToken()); // x��
				int Y = Integer.parseInt(st.nextToken()); // y��
				
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
