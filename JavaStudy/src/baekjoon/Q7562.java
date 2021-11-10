// ����Ʈ�� �̵� (���� 7562��)

/* [ �˰��� ]
 *
 * 1. �̵��� �� �ִ� ��θ� dx, dy�� �迭�� ����
 * 2. �̵��� ������ ������ ��ġ�� �̵��� Ƚ���� ����
 * 3. �̹� �̵��� ĭ�� �ٽ� �� �� ����(�ִ� ����̹Ƿ�) & ü������ ũ�⸦ �Ѿ �� ���� 
 *
 */
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q7562 {
	
	static private int[] dx = {1, 2, 2, 1, -1, -2, -2, -1}; // �� �� �� ��
	static private int[] dy = {2, 1, -1, -2, -2, -1, 1, 2};
	static StringBuilder sb = new StringBuilder();
	
	public static class type {
		int x, y, count =0;
		public type(int x, int y, int count) {
			this.x = x;
			this.y = y;
			this.count = count;
		}
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int count = Integer.parseInt(br.readLine());
		StringTokenizer st;
		type start, end;
		boolean isVisited[][]; // �ش� ĭ�� �湮�� ���� �ִ��� üũ
		
		for(int i=0; i<count; i++) {
			int L = Integer.parseInt(br.readLine());
			isVisited = new boolean[L][L];
			
			st = new StringTokenizer(br.readLine());
			start = new type(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0);
			st = new StringTokenizer(br.readLine());
			end = new type(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0);
			
			BFS(isVisited, L, start, end);
		}
		System.out.print(sb);
	}
	
	public static void BFS(boolean[][] isVisited, int L, type start, type end) {
		Queue<type> queue = new LinkedList<type>();
		queue.add(start);
		isVisited[start.x][start.y] = true;
		
		while(!queue.isEmpty()) {
			type w = queue.poll();
			
			if(w.x == end.x && w.y == end.y) {
				sb.append(w.count).append("\n");
				break;
			}
			
			for(int dir=0; dir<8; dir++) {
				int now_x = w.x + dx[dir];
				int now_y = w.y + dy[dir];
				
				if(now_x < 0 || now_y < 0 || now_x >= L || now_y >= L) continue;
				if(!isVisited[now_x][now_y]) {
					isVisited[now_x][now_y] = true;
					queue.add(new type(now_x, now_y, w.count+1));
				}
				else continue;
			}
		}
	}
}
