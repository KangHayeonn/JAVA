// 어항 정리 (백준 플래티넘5)

package beakjoon_samsung;

import java.io.*;
import java.util.*;

public class Q23291 {
	static int N, K;
	static int[][] map;
	public static class Type {
		int r, c;
		public Type(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	public static class Type2 {
		int r, c;
		int val; // 추가되거나 삭제될 값
		public Type2(int r, int c, int val) {
			this.r = r;
			this.c = c;
			this.val = val;
		}
	}
 	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		st = new StringTokenizer(br.readLine());
		int max, min = 10001;
		for(int i=0; i<N; i++) {
			map[N-1][i] = Integer.parseInt(st.nextToken());
			min = Math.min(min, map[N-1][i]);
		}
		
		int turn = 1;
		while(true) {
			
			// step1 : 물고기수가 가장 적은 어항에 물고기 하나 넣기
			for(int i=0; i<N; i++) {
				if(map[N-1][i] == min) map[N-1][i] += 1;
			}
			
			// 공중부양 (시계방향 90도)
			turn1();
			
			// 물고기 수 조절
			checkFish();
			
			// 다시 일렬로 순서대로 바닥에 놓기
			straightLine();
			
			// 공중부양 (시계방향 180도)
			turn2();
			
			// 물고기 수 조절
			checkFish();
			
			// 다시 일렬로 순서대로 바닥에 놓기
			straightLine();
			
			max = 0; min = 10001;
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(map[i][j] > 0) {
						max = Math.max(map[i][j], max);
						min = Math.min(map[i][j], min);
					}
				}
			}
			
			if(Math.abs(max - min) <= K) break;
			
			turn += 1;
		}
		
		System.out.println(turn);
	}
	public static void turn1() {
		int r=1, c=1;
		int nr = N-1, nc = 0;
		int startC = 0;
		Queue<Integer> q = new LinkedList<>();
		Queue<Type> qMap = new LinkedList<>();
		
		while(true) {
			for(int i=startC; i<startC+c; i++) {
				for(int j=N-r; j<N; j++) {
					q.offer(map[j][i]);
					qMap.offer(new Type(j, i));
				}
			}
			
			startC += c;
			
			if(r == c) r += 1;
			else c += 1;
			
			nr = N - r;
			nc = nc + c; // 1
			
			if(nc >= N) break;
			
			while(!qMap.isEmpty()) {
				Type t = qMap.poll();
				map[t.r][t.c] = 0;
			}
			
			for(int i=nr; i<N-1; i++) {
				for(int j=nc; j>nc-c; j--) {
					int num = q.poll();
					map[i][j] = num;
				}
			}
		}
	}
	public static void checkFish() {
		Queue<Type2> q = new LinkedList<>();
		int[] dr = {-1, 1, 0, 0}; // 상 하 좌 우
		int[] dc = {0, 0, -1, 1};
		
		int nr, nc;
		int diff; // 두 수의 차이
		boolean[][] isVisited = new boolean[N][N];
		for(int r=0; r<N; r++) {
			for(int c=0; c<N; c++) {
				if(map[r][c] > 0 && !isVisited[r][c]) {
					isVisited[r][c] = true;
					
					for(int i=0; i<4; i++) {
						nr = r + dr[i];
						nc = c + dc[i];
						
						if(nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
						
						if(map[nr][nc] > 0 && !isVisited[nr][nc]) {
							diff = Math.abs(map[r][c] - map[nr][nc]);
							int d = diff / 5;
							
							if(d > 0) {
								if(map[r][c] > map[nr][nc]) {
									q.offer(new Type2(r, c, -d));
									q.offer(new Type2(nr, nc, d));
								} else {
									q.offer(new Type2(r, c, d));
									q.offer(new Type2(nr, nc, -d));
								}
							}
						}
					}
				}
			}
		}
		
		while(!q.isEmpty()) {
			Type2 t = q.poll();
			map[t.r][t.c] += t.val;
		}
	}
	public static void straightLine() {
		Queue<Integer> q = new LinkedList<>();
		
		for(int c=0; c<N; c++) {
			for(int r=N-1; r>=0; r--) {
				if(map[r][c] > 0) {
					q.offer(map[r][c]);
					map[r][c] = 0;
				}
			}
		}
		
		int idx = 0;
		while(!q.isEmpty()) {
			map[N-1][idx] = q.poll();
			idx += 1;
		}
	}
	public static void turn2() {
		Stack<Integer> s = new Stack<>();
		for(int i=0; i<N/2; i++) {
			s.add(map[N-1][i]);
			map[N-1][i] = 0;
		}
		
		for(int i=N/2; i<N; i++) {
			map[N-2][i] = s.pop();
		}
		
		Stack<Integer> s1 = new Stack<>();
		Stack<Integer> s2 = new Stack<>();
		
		for(int i=N/2; i<N*3/4; i++) {
			s1.add(map[N-1][i]);
			map[N-1][i] = 0;
			s2.add(map[N-2][i]);
			map[N-2][i] = 0;
		}
		
		for(int i=N*3/4; i<N; i++) {
			map[N-4][i] = s1.pop();
			map[N-3][i] = s2.pop();
		}
	}
}
