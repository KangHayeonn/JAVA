// 마법사 상어와 파이어스톰 (백준 골드3)

package beakjoon_samsung;

import java.io.*;
import java.util.*;

public class Q20058 {
	static int N, Q;
	static int[][] map;
	static int L;
	static int[] dr = {-1, 1, 0, 0}; // 상 하 좌 우
	static int[] dc = {0, 0, -1, 1};
	static boolean[][] isVisited;
	static int iceGroup=0;
	public static class Type {
		int r, c;
		public Type(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		N = (int)Math.pow(2,  N);
		
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		
		int[] magicArr = new int[Q];
		
		for(int i=0; i<Q; i++) {
			magicArr[i]= Integer.parseInt(st.nextToken());
		}
		
		int idx = 0;
		while(Q-- > 0) {
			L = magicArr[idx];
			selectStartPart();
			breakIce();
			idx += 1;
		}
		
		int sum = 0;
		isVisited = new boolean[N][N];
		for(int r=0; r<N; r++) {
			for(int c=0; c<N; c++) {
				if(map[r][c] > 0) {
					sum += map[r][c];
					
					if(!isVisited[r][c]) {
						bfs(r, c);
					}
				}
			}
		}
		
		System.out.println(sum + "\n" + iceGroup);
	}
	public static void selectStartPart() {
		// 2^L * 2^L 부분 격자로 나눈다.
		
		int num = (int)Math.pow(2, L);
		
		for(int r=0; r<N; r+=num) {
			for(int c=0; c<N; c+=num) {
				divisionPart(r, c, num);
			}
		}
	}
	public static void divisionPart(int r, int c, int K) {
		int num = K;
		for(int i=0; i<K/2; i++) {
			mapTurn(r+i, c+i, num);
			num -= 2;
		}
	}
	public static void mapTurn(int r, int c, int K) {
		int[] Top = new int[K];
		int getIdx = c;
		for(int i=0; i<K; i++) {
			Top[i] = map[r][getIdx];
			getIdx += 1;
		}
		
		int[] Right = new int[K];
		getIdx = r;
		for(int i=0; i<K; i++) {
			Right[i] = map[getIdx][c+K-1];
			getIdx += 1; 
		}
		
		int[] Down = new int[K];
		getIdx = c+K-1;
		for(int i=0; i<K; i++) {
			Down[i] = map[r+K-1][getIdx];
			getIdx -= 1;
		}
		
		int[] Left = new int[K];
		getIdx = r+K-1;
		for(int i=0; i<K; i++) {
			Left[i] = map[getIdx][c];
			getIdx -= 1;
		}
		
		////////////////////////////////////
		
		getIdx = 0;
		for(int i=c; i<c+K; i++) {
			map[r][i] = Left[getIdx];
			getIdx += 1;
		}
		
		getIdx = 0;
		for(int i=r; i<r+K; i++) {
			map[i][c+K-1] = Top[getIdx];
			getIdx += 1;
		}
		
		getIdx = 0;
		for(int i=c+K-1; i>=c; i--) {
			map[r+K-1][i] = Right[getIdx];
			getIdx += 1;
		}
		
		getIdx = 0;
		for(int i=r+K-1; i>=r; i--) {
			map[i][c] = Down[getIdx];
			getIdx += 1;
		}
	}
	public static void breakIce() {
		int nr, nc;
		int cntIce; // 인접해있는 얼음칸의 개수
		Queue<Type> q = new LinkedList<>();
		
		for(int r=0; r<N; r++) {
			for(int c=0; c<N; c++) {
				cntIce = 0;
				
				for(int i=0; i<4; i++) {
					nr = r + dr[i];
					nc = c + dc[i];
					
					if(nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
					
					if(map[nr][nc] > 0) cntIce += 1;
				}
				
				if(cntIce < 3) {
					q.offer(new Type(r, c));
				}
			}
		}
		
		while(!q.isEmpty()) {
			Type t = q.poll();
			map[t.r][t.c] -= 1;
		}
	}
	public static void bfs(int r, int c) {
		Queue<Type> q = new LinkedList<>();
		q.offer(new Type(r, c));
		isVisited[r][c] = true;
		
		int count = 1;
		int nr, nc;
		while(!q.isEmpty()) {
			Type t = q.poll();
			
			for(int i=0; i<4; i++) {
				nr = t.r + dr[i];
				nc = t.c + dc[i];
				
				if(nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
				
				if(!isVisited[nr][nc] && map[nr][nc] > 0) {
					isVisited[nr][nc] = true;
					count += 1;
					q.offer(new Type(nr, nc));
				}
			}
		}
		iceGroup = Math.max(iceGroup, count);
	}
}
