// ������ ���� ��ٶ�� (���� ���5)

package beakjoon_samsung;

import java.io.*;
import java.util.*;

public class Q21610 {
	static int N, M;
	static int[][] map;
	static ArrayList<Cood> cloud;
	static int[] dr = {0, - 1, -1, -1, 0, 1, 1, 1};
	static int[] dc = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dr2 = {-1, -1, 1, 1};
	static int[] dc2 = {-1, 1, 1, -1};
	static boolean[][] isVisited;
	public static class Type {
		int d; // ����
		int s; // �Ÿ�
		public Type(int d, int s) {
			this.d = d;
			this.s = s;
		}
	}
	public static class Cood {
		int r, c;
		public Cood(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		cloud = new ArrayList<>();
		cloud.add(new Cood(N-1, 0));
		cloud.add(new Cood(N-1, 1));
		cloud.add(new Cood(N-2, 0));
		cloud.add(new Cood(N-2, 1));
		
		Type[] command = new Type[M];
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			command[i] = new Type(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken()));
		}
		
		int idx = 0;
		while(M-- > 0) {
			step1(command[idx].d, command[idx].s);
			idx+=1;
			step2();
			isVisited = new boolean[N][N]; // ������ ����� ĭ�� ������ ����� ĭ�� �����ϱ� ����
			step4();
			cloud = new ArrayList<Cood>(); // ������ ��� �������.
			step5();
		}
		
		int sum = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				sum += map[i][j];
			}
		}
		
		System.out.println(sum);
	}
	public static void step1(int d, int s) {
		// ��� ������ d������ sĭ �̵��Ѵ�.
		ArrayList<Cood> tmpArr = new ArrayList<>();
		int r = dr[d];
		int c = dc[d];
		
		int nr, nc;
		for(Cood t : cloud) {
			if(r < 0) {
				nr = (t.r + r*s + N*50) % N;
			} else {
				nr = (t.r + r*s) % N;
			}
			
			if(c < 0) {
				nc = (t.c + c*s + N*50) % N;
			} else {
				nc = (t.c + c*s) % N;
			}
			
			tmpArr.add(new Cood(nr, nc));
		}
		
		cloud = new ArrayList<Cood>();
		for(Cood t : tmpArr) {
			cloud.add(new Cood(t.r, t.c));
		}
	}
	public static void step2() {
		// �� �������� �� ���� ������ �ִ� ĭ�� �ٱ��Ͽ� ����� ���� ���� 1 �����Ѵ�.
		for(Cood t: cloud) {
			map[t.r][t.c] += 1;
		}
	}
	public static void step4() {
		// ��������� ���� ����
		int nr, nc;
		int count;
		for(Cood t: cloud) {   // ���� ������ ĭ
			count = 0;
			for(int i=0; i<4; i++) {
				nr = t.r + dr2[i];
				nc = t.c + dc2[i];
				
				if(nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
				if(map[nr][nc] > 0) count += 1; // ���� �ִ� �ٱ��� ����ŭ ī����
			}
			map[t.r][t.c] += count;
			isVisited[t.r][t.c] = true;
		}
	}
	public static void step5() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j] >= 2 && !isVisited[i][j]) {
					map[i][j] -= 2;
					cloud.add(new Cood(i, j));
				}
			}
		}
	}
}
