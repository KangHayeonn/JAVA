// ������ 3 (���� ���4)

package beakjoon_samsung;

import java.io.*;
import java.util.*;

public class Q17142 {
	static int N, M;
	static int[][] map;
	static ArrayList<Type> virus;
	static int zero; // �ʱ� ��ĭ ����
	static int[] dc = {1, -1, 0, 0}; // ��������
	static int[] dr = {0, 0, 1, -1};
	static ArrayList<Integer> answer = new ArrayList<>();
	public static class Type {
		int r, c;
		public Type (int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	public static class Type2 {
		int r, c;
		int count;
		public Type2 (int r, int c, int count) {
			this.r = r;
			this.c = c;
			this.count = count;
		}
	}
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		virus = new ArrayList<>();
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 2) virus.add(new Type(i, j));
				else if(map[i][j] == 0) zero++;
			}
		}
		
		// �������� ���� �̷����� M�� ��ŭ �̾���
		// �׸��� ���̷����� �۶߷���
		// �ð��� max�� �� ���� ����
		// ��ĭ�� ���̷����� ��� �۶߷ȴ��� üũ
		boolean[] isVisited = new boolean[virus.size()];
		combi(isVisited, 0, M);

		Collections.sort(answer);
		if(answer.get(0) == Integer.MAX_VALUE) System.out.println(-1);
		else {
			System.out.println(answer.get(0));
		}
	}
	public static void combi(boolean[] isVisited, int start, int depth) {
		if(depth == 0) {
			spread(isVisited);
			return;
		}
		
		for(int i=start; i<virus.size(); i++) {
			isVisited[i] = true;
			combi(isVisited, i+1, depth-1);
			isVisited[i] = false;
		}
	}
	public static void spread(boolean[] isVisited) {
		Queue<Type2> q = new LinkedList<>();
		
		for(int i=0; i<isVisited.length; i++) {
			if(isVisited[i]) { 
				Type t = virus.get(i);
				q.offer(new Type2(t.r, t.c, 0));
			}
		}
		
		boolean[][] visited = new boolean[N][N];
		int check = 0;
		int max = 0;
		while(!q.isEmpty()) {
			Type2 t = q.poll();
			visited[t.r][t.c] = true;
			max = Math.max(max, t.count);
			
			for(int i=0; i<4; i++) {
				int nr = t.r + dr[i];
				int nc = t.c + dc[i];
				
				if(nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
				if(map[nr][nc] == 1) continue;
				if(check == zero) continue; // ��ĭ Ž���� ���� �� Ȱ�� ���̷����� ��Ȱ�� ���̷����� ���� ���
				if(!visited[nr][nc]) {
					if(map[nr][nc] == 2) { // Ȱ�� ���̷����� ��Ȱ�� ���̷����� ���� ���
						q.offer(new Type2(nr, nc, t.count+1));
						visited[nr][nc] = true;
					}
					else {
						q.offer(new Type2(nr, nc, t.count+1));
						visited[nr][nc] = true;
						check++;
					}
				}
			}
		}
		
		if(check == zero) {
			answer.add(max);
		} else {
			answer.add(Integer.MAX_VALUE);
		}
	}
}
