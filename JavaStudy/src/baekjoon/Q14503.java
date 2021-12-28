// �κ� û�ұ� (���� 14503��)

/* [ �˰��� ]
 * 
 * 1. ����ũ�� N, ����ũ�� M�� �κ�û�ұⰡ �ִ� ĭ�� ��ǥ �� ���� ���� ����� ����
 * 2. û�Ұ����� ĭ üũ (DFS)
 * 3. ���ڸ����� ���� �����̴� �� (���� + 3) % 4 , �ڷ� �����̴� �� (���� + 2) % 4
 * 
 */
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q14503 {
	static int count = 0; // û���ϴ� ĭ ����
	static int dx[] = {0, 1, 0, -1}; // �� �� �� ��
	static int dy[] = {-1, 0, 1, 0}; // �� �� �� ��
	static int N;
	static int M;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // ����ũ��
		M = Integer.parseInt(st.nextToken()); // ����ũ��
		st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken()); // ��
		int c = Integer.parseInt(st.nextToken()); // ��
		int d = Integer.parseInt(st.nextToken()); // �ٶ󺸴� ���� (���� : 0, ���� : 1, ���� : 2 , ���� : 3)
		
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
				clean(map, isVisited, ny, nx, direction); // �ٶ󺸴� ���� ����
			}
		}
	}
}
