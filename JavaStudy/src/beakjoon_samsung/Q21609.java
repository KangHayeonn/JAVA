// 상어 중학교 (백준 골드2)
package beakjoon_samsung;

import java.io.*;
import java.util.*;

public class Q21609 {
	static int[][] map;
	static boolean[][] isVisited;
	static int N, M;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static ArrayList<Cood> arr;
	static PriorityQueue<Type> pq;
	public static class Type {
		int r, c; // 기준 블록 좌표
		int cnt, rainbowCnt; // 블록의 수, 무지개 블록의 수
		ArrayList<Cood> arr;
		public Type(int r, int c, int cnt, int rainbowCnt, ArrayList<Cood> arr) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
			this.rainbowCnt = rainbowCnt;
			this.arr = arr;
		}
	}
	public static class Cood {
		int r;
		int c;
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
		
		int totalCnt = 0, cnt;
		while(true) {
			cnt = autoPlay();
			
			if(cnt == 0) break;
			else {
				System.out.println(Arrays.deepToString(map));
				totalCnt += cnt;
				autoPlayStep3();
				autoPlayStep4();
				autoPlayStep3();
			}
		}
		
		System.out.print(totalCnt);
	}
	public static int autoPlay() {
		int num;
		pq = new PriorityQueue<>((a, b) -> {
			if(a.cnt != b.cnt) return b.cnt - a.cnt;
			else {
				if(a.rainbowCnt != b.rainbowCnt) return b.rainbowCnt - a.rainbowCnt;
				else {
					if(a.r != b.r) return b.r - a.r;
					else return b.c - a.c;
				}
			}
		});
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				num = map[i][j];
				if(num >= 1 && num <= M) {
					isVisited = new boolean[N][N];
					autoPlayStep1(i, j, num);
					System.out.println("-------------------");
				}
			}
		}
		
		while(!pq.isEmpty()) {
			Type t = pq.poll();
			
			for(Cood m : t.arr) {
				map[m.r][m.c] = -2;
			}
			// System.out.println(t.r + " " + t.c + " , "+ t.cnt + " , "+ t.rainbowCnt);
			return t.cnt;
		}
		return 0;
	}
	public static void autoPlayStep1(int r, int c, int K) {
		System.out.println("좌표 : "+ r + " , "+ c);
		Queue<Cood> q = new LinkedList<>();
		ArrayList<Cood> tmpArr = new ArrayList<>();
		tmpArr.add(new Cood(r, c));
		isVisited[r][c] = true;
		q.offer(new Cood(r, c));
		
		while(!q.isEmpty()) {
			Cood t = q.poll();
			
			for(int i=0; i<4; i++) {
				int nr = t.r + dr[i];
				int nc = t.c + dc[i];
				
				if(nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
				
				if(!isVisited[nr][nc] && (map[nr][nc] == 0 || map[nr][nc] == K)) {
					isVisited[nr][nc] = true;
					tmpArr.add(new Cood(nr, nc));
					q.offer(new Cood(nr, nc));
				}
			}
		}
		
		// System.out.println("test------------------");
		int originR = r, originC = c;
		int rainbowCnt = 0;
		for(Cood t : tmpArr) {
			// System.out.print(t.r + " : " + t.c + ", ");
			if(map[t.r][t.c] == 0) rainbowCnt += 1;
			
			if(originR > t.r) {
				originR = t.r;
				originC = t.c;
			} else if(originR == t.r && originC > t.c) {
				originR = t.r;
				originC = t.c;
			} else continue;
		}
		
		pq.offer(new Type(originR, originC, tmpArr.size(), rainbowCnt, tmpArr));
		// System.out.println();
		// System.out.println("기준R : " + originR + ", 기준C: "+  originC + ", 무지개갯수 : " + rainbowCnt);
		// System.out.println("\n\n");

	}
	public static void autoPlayStep3() {
		// 중력을 적용
		for(int c=0; c<N; c++) {     // 열
			int cntDown; // 떨어지는 칸수
			for(int r=N-1; r>=0; r--) { // 행
			}
		}
	}
	public static void autoPlayStep4() {
		// 90도 반시계방향으로 회전
	}
}
