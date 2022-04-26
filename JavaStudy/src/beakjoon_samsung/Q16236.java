// �Ʊ� ��� (���� ���3)

package beakjoon_samsung;

import java.io.*;
import java.util.*;

public class Q16236 {
	static int N;
	static int[][] map;
	static boolean[][] isVisited;
	static int[] shark = new int[4]; // �Ʊ����� ��ǥ + ũ��(�ʱ� 2) + ���� ����
	static int[] dr = {0, 0, 1, -1}; // ��������
	static int[] dc = {1, -1, 0, 0};
	static PriorityQueue<Type> pq; // ���� �� �ִ� ����⸦ ��ǥ�� ũ��, ������ �Ÿ��� ��� ť
	static int time = 0;
	public static class Type implements Comparable<Type> {
		int r, c;
		int size;
		int d;
		public Type(int r, int c, int size, int d) {
			this.r = r;
			this.c = c;
			this.size = size;
			this.d = d;
		}
		@Override
		public int compareTo(Type t) {
			if(this.d == t.d) {
				if(this.r == t.r) {
					return this.c - t.c;
				}
				return this.r - t.r;
			} else {
				return this.d - t.d;
			}
		}
	}
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer("");
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 9) {
					shark[0] = i;
					shark[1] = j;
					shark[2] = 2;
				}
			}
		}

		while(true) {
			pq = new PriorityQueue<>();
			isVisited = new boolean[N][N];
			bfs();
			if(!eat()) break;
			// ������ �����¿츦 ���� 
			// ����⸦ ������ (1~6) ���� �Ǵ� -> ������� ��ǥ, ����� ũ��, ������ �Ÿ� PriorityQueue�� �߰�
			// if(eat()) // ����;
			//else {
			//	return;
			// }
			// ���̻� ���� ���űⰡ ������ return
			// ���� �� �ִ� ����Ⱑ 1���� �� ����⸦ ��������
			// ���� �� �ִ� ����Ⱑ 1�̻� �̸� ���� �Ǵ�
			// �Ʊ���� �ڽ��� ũ��� ���� ���� ����⸦ ���� ������ ũ�Ⱑ 1 ����
			// �ð��� ���������� �̵� �ð�(�Ÿ�)�� �߰���
		}
		System.out.println(time);
	}
	public static void bfs() {
		Queue<Type> q = new LinkedList<>();
		q.offer(new Type(shark[0], shark[1], 0, 0));
		
		while(!q.isEmpty()) {
			Type t = q.poll();
			isVisited[t.r][t.c] = true;
			
			for(int i=0; i<4; i++) {
				int nr = t.r + dr[i];
				int nc = t.c + dc[i];
				
				if(nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
				if(isVisited[nr][nc]) continue;
				else {
					if(map[nr][nc] == 0) {
						q.offer(new Type(nr, nc, 0, t.d+1));
						isVisited[nr][nc] = true;
					} else if(map[nr][nc] == shark[2]) {
						q.offer(new Type(nr, nc, 0, t.d+1));
						isVisited[nr][nc] = true;
					} else if(map[nr][nc] < shark[2]) {
						pq.offer(new Type(nr, nc, map[nr][nc], t.d+1));
						isVisited[nr][nc] = true;
					}
				}
			}
		}
	} 
	public static boolean eat() {
		if(pq.size() == 0) return false;
		else {
			Type t = pq.poll();
			map[shark[0]][shark[1]] = 0;
			map[t.r][t.c] = 0;
			shark[0] = t.r;
			shark[1] = t.c;
			shark[3] += 1;
			time += t.d;
			if(shark[2] == shark[3]) {
				shark[2] += 1;
				shark[3] = 0;
				// ũ�Ⱑ Ŀ���� �Դ� Ƚ���� 0����
			}
		}
		return true;
	}
}
