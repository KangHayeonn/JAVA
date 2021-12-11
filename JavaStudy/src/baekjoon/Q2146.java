// �ٸ� ����� (���� 2146��)

/* [ �˰��� ]
 * 
 * 1. ���� �� ���� ��ȣ�� �ٸ��� �������־� ���� �и� (BFS)
 * 2. ���� �и��� �� ���� ������ �ٸ� ���� ���� ������ �Ÿ��� �������ָ鼭 �ٸ� ���� ���� ��� �ּڰ��� ���� (BFS)
 * 3. 2���� ���� ť�� ��ǥ�� �Ÿ� ������ ��� ���� 
 * 
 * - BFS : ������������ �̵��ϸ鼭 BFS ������ (�������� BFS ����)
 */
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q2146 {
	static int[][] map;
	static boolean[][] isVisited;
	static int[] dx = {0, 0, -1, 1}; // �� �� �� ��
	static int[] dy = {1, -1, 0, 0};
	
	public static class type {
		int x;
		int y;
		int distance;
		public type(int x, int y, int distance) {
			this.x = x;
			this.y = y;
			this.distance = distance;
		}
	}
	
	public static void main (String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // ������ ũ��
		StringTokenizer st = new StringTokenizer("");
		map = new int[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				int x = Integer.parseInt(st.nextToken());
				map[i][j] = x;
			}
		}
		
		isVisited = new boolean[N][N];
		int count = 2;
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j] == 1) {
					count = mapMake(i, j, N, count);
				}
			}
		}
		
		int answer = 20000;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				isVisited = new boolean[N][N];
				if(map[i][j] >= 2) {
					answer = BFS(i, j, N, answer);
				}
			}
		}
		
		/*
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}*/
		
		System.out.print(answer);
	}
	
	// ���� ������ �и�
	public static int mapMake(int x, int y, int N, int count) {
		Queue<type> q = new LinkedList<>();
		q.add(new type(x, y, 0));
		isVisited[x][y] = true;
		map[x][y] = count;
		
		while(!q.isEmpty()) {
			type t = q.poll();
			
			for(int i=0; i<4; i++) {
				int nx = t.x + dx[i];
				int ny = t.y + dy[i];
				
				if( nx < 0 || ny < 0 || nx >= N || ny >= N ) continue;
				
				if(map[nx][ny] == 1 && !isVisited[nx][ny]) {
					map[nx][ny] = count;
					q.add(new type(nx, ny, 0));
				}
			}
		}
		return count+1;
	}
	
	// ������ �ּ� �Ÿ� ���ϱ�
	public static int BFS(int x, int y, int N, int answer) {
		Queue<type> q = new LinkedList<>();
		q.add(new type(x, y, 0));
		isVisited[x][y] = true;
		
		while(!q.isEmpty()) {
			type t= q.poll();
			
			for(int i=0; i<4; i++) {
				int nx = t.x + dx[i];
				int ny = t.y + dy[i];
				
				if( nx < 0 || ny < 0 || nx >= N || ny >= N ) continue;
				
				// �ٸ� ���̰ų� �ٴ��� ��� �ش� ���ǹ��� ��
				if(map[nx][ny] != map[x][y] && !isVisited[nx][ny]) {
					if(map[nx][ny] == 0) { // �ٴ��� ���
						q.add(new type(nx, ny, t.distance+1));
					} else { // �ٸ� ���� ���
						answer = Math.min(answer, t.distance);
					}
					isVisited[nx][ny] = true; // �� �κ��� ��ġ�� �߿� (���ǹ� �Ѱ����� �־��� ��� -> OutOfMemoryError: Java heap space �̷� ������)
				}
			}
		}
		
		return answer;
	}
}
