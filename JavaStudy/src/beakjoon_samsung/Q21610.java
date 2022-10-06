// 마법사 상어와 비바라기 (백준 골드5)

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
		int d; // 방향
		int s; // 거리
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
			isVisited = new boolean[N][N]; // 구름이 생기는 칸과 구름이 사라진 칸을 구분하기 위해
			step4();
			cloud = new ArrayList<Cood>(); // 구름이 모두 사라진다.
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
		// 모든 구름이 d방향을 s칸 이동한다.
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
		// 각 구름에서 비가 내려 구름이 있는 칸의 바구니에 저장된 물의 양이 1 증가한다.
		for(Cood t: cloud) {
			map[t.r][t.c] += 1;
		}
	}
	public static void step4() {
		// 물복사버그 마법 시전
		int nr, nc;
		int count;
		for(Cood t: cloud) {   // 물이 증가한 칸
			count = 0;
			for(int i=0; i<4; i++) {
				nr = t.r + dr2[i];
				nc = t.c + dc2[i];
				
				if(nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
				if(map[nr][nc] > 0) count += 1; // 물이 있는 바구니 수만큼 카운팅
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
