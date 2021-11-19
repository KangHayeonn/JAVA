// ������ (���� 14502��)

/* [ �˰��� ]
 * 
 * 1. ��ĭ�� �� ���̷����� ��ǥ�� ���� ������ �迭�� ����
 * 2. 0 ��ǥ ��ü���� 3���� �������� ���� �� 1�� ��ȯ ��Ŵ
 * 3. 2�� �� ���̷����� �۶߷��� (2 �� �ִ� ��ǥ���� x<0, y<0, x>M, y>=N �� �Ǹ� stop)
 * 4. �� ������ 2�� ��ȯ ��Ű�鼭 count��Ŵ
 * 5. ���̷����� ������ �� count �� ���̷��� ���� �迭�� �־���
 * 6. ��� ���տ� ���� ���̷��� �۶߸��� ��찡 ������ ���̷��� ���� �迭���� �ּڰ��� ������
 * 7. ���� ������ �ִ� ũ�� = N*M - (1�� ���� + 3 + 2�� ���� + 6������ ���� �ּڰ�)
 * 
 */
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q14502 {
	
	static ArrayList<type> virus; // �ʱ� ���̷��� ��ǥ �迭
	static ArrayList<Integer> virusCountArr; // ���̷����� �۶߸� �� ���̷��� ������ ���� �迭
	static int[] dx = {0, 0, -1, 1}; // �� �� �� ��
	static int[] dy = {1, -1, 0, 0};
	
	
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
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		int countOne=0, countVirus=0; // countOne : �ʱ� 1�� ����, countVirus : �ʱ� ���̷����� ����
		virus = new ArrayList<>(); // �ʱ� ���̷��� ��ǥ �迭
		
		virusCountArr = new ArrayList<>();
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==1) countOne++;
				else if(map[i][j]==2) {
					countVirus++;
					virus.add(new type(j, i)); // [**����] �ʱ� ���̷������� ��ǥ�� �̸� �����ؼ� ���� ���̷��� �۶߸� �� ���ϰ� ����
				}
				else continue;
			}
		}

		int[][] visited = copy(map); // map�� �״�� ���� ����
		combi(map, visited, 3);

		// ���������� �ִ� ũ�� : N*M - (1�� ���� + 3 + 2�� ���� + 6������ ���� �ּڰ�)
		System.out.println(N*M - (countOne + 3 + countVirus + Collections.min(virusCountArr)));
	}
	
	public static void combi(int[][] map, int[][] visited, int L) {
		if(L==0) {
			BFS_virus(visited);
			visited = copy(map);
			return;
		}
		
		for(int i=0; i<visited.length; i++) {
			for(int j=0; j<visited[i].length; j++) {
				if(visited[i][j]==0) { 
					visited[i][j] = 1;
					combi(map, visited, L-1); // combi(map, visited, i+1, j+1, L-1); �̰ſ��� ����
					visited[i][j]= 0;
				}
			}
		}
		
	}
	
	public static void BFS_virus(int[][] map) { 
		Queue<type> q = new LinkedList<>();
		for(int i=0; i<virus.size(); i++) {
			q.add(new type(virus.get(i).x , virus.get(i).y));
		}

		int virusCount = 0; // �߰��� ���̵� ���̷��� ����
		int[][] check = copy(map); // ���� ����
		
		while(!q.isEmpty()) {
			type q_virus = q.poll();
			for(int i=0; i<4; i++) {
				int nx = q_virus.x + dx[i];
				int ny = q_virus.y + dy[i];

				if(nx>=0 && ny>=0 && nx<check[0].length && ny<check.length) {
					if(check[ny][nx]==0) {
						check[ny][nx] = 2;
						virusCount++;
						q.add(new type(nx, ny));
					}
				}
			}
		}
		virusCountArr.add(virusCount);
	}
	
	public static int[][] copy(int[][] arr) { // ���� ���� : ���θ� �����Ͽ� ���� ������ �������� ���� (���� ��ü ���� ������ ���� ����)
		int[][] copy = new int[arr.length][arr[0].length];
		
		for(int i=0; i<arr.length; i++) {
			for(int j=0; j<arr[0].length; j++) {
				copy[i][j] = arr[i][j];
			}
		}
		
		return copy;
	}
}
