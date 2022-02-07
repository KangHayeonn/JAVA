// �丶�� (���� 7576��)

/* [ �˰��� ]
 * 
 * 1. BFS �� �̿��� ���� �丶���� ��ġ���� ���� �۶߸�
 * 2. �����¿츦 üũ�ϸ� 0�� ��� 1�� ������ �� �� ����
 * 3. -1�� ���� ��� �н�
 * 4. ���� ��� �� Ž���� ���� ���, 0�� ���������� -1�� ��� (�丶�䰡 ��� ���� ���ϴ� ��Ȳ)
 * 5. 1�� ���� 0�� ���
 * 6. 1���� Ŭ ���� �ش� ������ -1�� �� (ó�� ���������� �� ��)
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
	static int[] dx = { 0, 0, -1, 1 }; // �� �� �� ��
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
		int M = Integer.parseInt(st.nextToken()); // ������ ���� ĭ�� ��
		int N = Integer.parseInt(st.nextToken()); // ������ ���� ĭ�� ��
		
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
				
				if(map[ny][nx] == 0) { // �� ���� �丶���� ���
					
					if(isVisited[ny][nx] == 0) {
						isVisited[ny][nx] = q.count +1;
						queue.add(new type(nx, ny, q.count+1));
					}
				}
			}
		}
	}
}
