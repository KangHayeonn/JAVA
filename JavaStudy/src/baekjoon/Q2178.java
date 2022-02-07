// �̷� Ž�� (���� 2178��)

/* [ �˰��� ]
 * 
 * 1. (1,1) ������������ BFS�� ����
 * 2. �����¿츦 üũ�ϸ� 1�� �ִ� ���� ������ �̵��� �� �ִ� ĭ���� count
 * 3. (N,M)�� ��ġ�ϰ� �Ǹ� �׶��� count���� return (���� ��ġ�� ���� ��ġ ��� ����)
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
	static int dx[] = { 0, 0, -1, 1 }; // �� �� �� ��
	static int dy[] = { 1, -1, 0, 0 }; // �� �� �� ��
	
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
		int N = Integer.parseInt(st.nextToken()); // ���� ����
		int M = Integer.parseInt(st.nextToken()); // ���� ����
		
		map = new int[N][M];
		
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = str.charAt(j) - '0'; // ���� -> ����
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
