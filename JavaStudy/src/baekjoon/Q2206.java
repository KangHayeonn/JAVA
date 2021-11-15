// �� �μ��� �̵��ϱ�

/* [ �˰��� ]
 * 
 * 1. ó�� ĭ���� �����ؼ� �����¿�� �̵��ϸ鼭 ���� ��ġ & distance & ���� �μ� Ƚ���� �߰�.
 * 2. ���� ������ �����ʰ� ���� �μ� Ƚ���� 0�� ��츸 �߰�.
 * 3. ���� N-1, K-1 �� �� ��� �ش� �̵��Ÿ��� ���
 * 4. ���� 1�� ��� ���̱� ������ �ش� ���� �ѹ��� �μ� �� ���� -> distance�� ���� �μ� Ƚ���� ��� �߰�
 * 5. ���� 0�� ��� ���� �ƴϱ� ������ �ش� �κ��� �׳� ��� -> distance�� �߰�
 * 6. ���� ��ΰ� ������ ������ ���� ��� continue
 * 
 */
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q2206 {
	static int[] dx = {0, 0, -1, 1}; // �����¿� 
	static int[] dy = {1, -1, 0, 0};
	
	public static class type {
		private int x;
		private int y;
		private int distance;
		private int drill;
		
		public type(int x, int y, int distance, int drill) {
			this.x = x;
			this.y = y;
			this.distance = distance;
			this.drill = drill;
		}
	}
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		//int[] check = new int[N*M];
		int[][] check = new int[N][M];
		
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = str.charAt(j)-'0';
				//check[M*(i%M) + j] = Integer.MAX_VALUE;
				check[i][j] = Integer.MAX_VALUE;
			}
		}
		System.out.println(Arrays.toString(check));
		BFS(map, check, N, M);
	}
	
	public static void BFS(int[][] map, int[][] check, int N, int M) {
		Queue<type> move = new LinkedList<>(); // �̵�
		move.add(new type(0, 0, 1, 0));
		check[0][0] = 0;
		
		while(!move.isEmpty()) {
			type temp = move.poll();
			
			if(temp.x == M-1 && temp.y == N-1) {
				System.out.print(temp.distance);
				System.out.println("������?");
				return;
			}
			
			for(int i=0; i<4; i++) {
				int now_x = temp.x + dx[i];
				int now_y = temp.y + dy[i];
				
				if(now_x < 0 || now_y < 0 || now_x > M-1 || now_y > N-1 ) continue;
				if(check[now_y][now_x] <= temp.drill) continue;
				//if(check[now_x*(now_x%M) + now_y] <= temp.drill) continue; // �̹� �ѹ��� ���� ���̱� ������ pass
				System.out.println(Arrays.toString(check));
				if(map[now_y][now_x] == 0) {
					move.add(new type(now_x, now_y, temp.distance+1, temp.drill));
					//check[now_x*(now_x%M) + now_y] = temp.drill;
					check[now_y][now_x] = temp.drill;
				}
				else {
					if(temp.drill == 0) {
						move.add(new type(now_x, now_y, temp.distance+1, temp.drill+1));
						//check[now_x*(now_x%M) + now_y] = temp.drill+1;
						check[now_y][now_x] = temp.drill+1;
					}
				}
			}
		}
		if(move.isEmpty()) {
			System.out.print(-1);
			return;
		}
	}
}
