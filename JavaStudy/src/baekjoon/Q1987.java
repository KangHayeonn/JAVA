// ���ĺ� (���� 1987��)

/* [ �˰��� ]
 * 
 * 1. �ش� ĭ�� �ִ� ���ĺ��� �̵� Ƚ���� �����Ѵ�.
 * 2. �����¿�� �̵� �����ϴ�.
 * 3. �̹� �ִ� ���ĺ��� ������ ĭ�� �� �� ����.
 * 4. DFS �˰����� �̿��Ѵ�.
 * 
 */

// ver 1 : �޸� �ʰ�
// BFS �̿�
/*
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q1987 {
	public static class type {
		String Alpha;
		int count;
		int x;
		int y;
		public type(String Alpha, int count, int x, int y) {
			this.Alpha = Alpha;
			this.count = count;
			this.x = x;
			this.y = y;
		}
	}
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int R = Integer.parseInt(st.nextToken()); // �� ��
		int C = Integer.parseInt(st.nextToken()); // �� ��
		
		Character[][] map = new Character[R][C];
		
		for(int i=0; i<R; i++) {
			String str = br.readLine();
			for(int j=0; j<C; j++) {
				map[i][j] = str.charAt(j);
			}
		}

		BFS(map);
	}
	
	public static void BFS(Character[][] map) {
		int[] dx = {0, 0, -1, 1};
		int[] dy = {1, -1, 0, 0};

		Queue<type> q = new LinkedList<>();
		q.add(new type(Character.toString(map[0][0]), 1, 0, 0));
		
		int answer=0;
		
		while(!q.isEmpty()) {
			type now = q.poll();
			answer = now.count;
			
			for(int i=0; i<4; i++) {
				int nextX = now.x + dx[i];
				int nextY = now.y + dy[i];
				
				if(nextX>=0 && nextY>=0 && nextX<map[0].length && nextY<map.length) {
					if(now.Alpha.indexOf(map[nextY][nextX]) >= 0) continue;
					else {
						//System.out.println(map[nextY][nextX]);
						q.add(new type(now.Alpha+map[nextY][nextX], now.count+1, nextX, nextY));
					}
				
				}
			}
		}
		System.out.println(answer);
	}
}*/


// ver 2 :����
// DFS �̿�

package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1987 {
	static int answer = 0;
	
	public static class type {
		int count;
		int x;
		int y;
		public type(int count, int x, int y) {
			this.count = count;
			this.x = x;
			this.y = y;
		}
	}
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int R = Integer.parseInt(st.nextToken()); // �� ��
		int C = Integer.parseInt(st.nextToken()); // �� ��
		
		int[][] map = new int[R][C];
		
		for(int i=0; i<R; i++) {
			String str = br.readLine();
			for(int j=0; j<C; j++) {
				map[i][j] = str.charAt(j)-'A'; // ���ĺ��� int������ ����
				//System.out.println(map[i][j]);
			}
		}
		
		boolean[] isVisited = new boolean[26];
		DFS(map, isVisited, 0, 0, 1);
		System.out.print(answer);
	}
	
	public static void DFS(int[][] map, boolean[] isVisited, int y, int x, int count) {
		int[] dx = {0, 0, -1, 1};
		int[] dy = {1, -1, 0, 0};
		
		isVisited[map[y][x]] = true;
		
		for(int i=0; i<4; i++) {
			int nextX = x + dx[i];
			int nextY = y + dy[i];
			
			if(nextX>=0 && nextY>=0 && nextX<map[0].length && nextY<map.length) {
				if(!isVisited[map[nextY][nextX]]) DFS(map, isVisited, nextY, nextX, count+1);
				else answer = Math.max(answer, count);
			}
		}
		
		isVisited[map[y][x]] = false; // �� �κ� ������ Ʋ��
	}
}
