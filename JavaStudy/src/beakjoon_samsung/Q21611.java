// 마법사 상어와 블리자드 (백준 골드1)
package beakjoon_samsung;

import java.io.*;
import java.util.*;

public class Q21611 {
	static int N, M;
	static int[][] map;
	static int[] dr_magic = {-1, 1, 0, 0}; // 상 하 좌 우
	static int[] dc_magic = {0, 0, -1, 1};
	static Type[] magicArr;
	static int shark_r, shark_c;
	static Map<Integer, Cood> numToMap;
	static Queue<Integer> q; // 공백부터 구슬의 번호를 저장
	static int ballOne=0, ballTwo=0, ballThree=0; // 폭발한 1번~3번 구슬의 개수
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
				if(map[i][j] == 0) map[i][j] = -2;
			}
		}
		
		magicArr = new Type[M];
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			magicArr[i] = new Type(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken()));
		}
		
		shark_r = N/2;
		shark_c = N/2;
		
		map[shark_r][shark_c] = 0; // 상어 위치만 : 0 , 구슬이 없는 것은 : -2
		
		numToMap = new HashMap<>();
		mapRotation();
		
		int d, s, nr, nc;
		for(int i=0; i<M; i++) {
			d = magicArr[i].d;
			s = magicArr[i].s;
			
			for(int j=1; j<=s; j++) {
				nr = shark_r + dr_magic[d]*j;
				nc = shark_c + dc_magic[d]*j;

				map[nr][nc] = -2; // 구슬을 파괴함
			}
			pullMap();
			
			while(true) {
				int result = bumb();
				if(result == 0) break;
				else {
					pullMap();
				}
			}
			
			changeMap();
		}
		System.out.println(1*ballOne + 2*ballTwo + 3*ballThree);
	}
	public static void mapRotation() {
		int[] dr = {0, 1, 0, -1}; // 좌 하 우 상
		int[] dc = {-1, 0, 1, 0};
		
		int goIdx = 0;
		int nr = shark_r, nc = shark_c;
		int idx = 1;
		while(true) {
			for(int i=0; i<goIdx/2+1; i++) {
				nr = nr + dr[goIdx%4];
				nc = nc + dc[goIdx%4];
				
				numToMap.put(idx, new Cood(nr, nc));
				idx += 1;
				if(nr == 0 && nc == 0) break;
			}
			if(nr == 0 && nc == 0) break;
			goIdx += 1;
		}
		
		
		// -------------- 반대 -------------- //
		/* by min_princess
		while(goIdx>0) {
			for(int i=goIdx/2+1; i>0; i--) {
				nr = nr - dr[goIdx%4];
				nc = nc - dc[goIdx%4];
				
				System.out.println(nr + " : " + nc);
				if(nr==0&&nc==N-1) break;
			}
			goIdx -= 1;
		}*/
		
		
		/* by yeon_prince
		int[] dr_reverse = {0, -1, 1, 0}; // 우 상 하 좌
		int[] dc_reverse = {1, 0, 0, -1};
		
		goIdx = (N-1)*2;
		System.out.println(goIdx);
		nr = 0; nc = -1;
		while(true) {
			for(int i=0; i<goIdx/2+1; i++) {
				System.out.println(goIdx%4);
				nr = nr + dr_reverse[goIdx%4];
				nc = nc + dc_reverse[goIdx%4];
				
				System.out.println(nr + " : " + nc);
				if(nr == shark_r && nc == shark_c) break;
			}
			if(nr == shark_r && nc == shark_c) break;
			goIdx -= 1;
		}*/
				
	}
	public static int checkMapRotation() {
		int[] dr = {0, 1, 0, -1}; // 좌 하 우 상
		int[] dc = {-1, 0, 1, 0};
		
		int goIdx = 0;
		int nr = shark_r, nc = shark_c;
		int idx = 1;
		int answer = 0;
		boolean blankChk = false;
		while(true) {
			for(int i=0; i<goIdx/2+1; i++) {
				nr = nr + dr[goIdx%4];
				nc = nc + dc[goIdx%4];
				
				if(map[nr][nc] == -2 && !blankChk) {
					answer = idx;
					blankChk = true;
				}
				
				if(blankChk) q.offer(map[nr][nc]);
				idx += 1;
				
				if(nr == 0 && nc == 0) break;
			}
			if(nr == 0 && nc == 0) break;
			goIdx += 1;
		}
		return answer;
	}
	public static int bumb() {
		Queue<Integer> tmpQ = new LinkedList<>();
		
		int[] dr = {0, 1, 0, -1}; // 좌 하 우 상
		int[] dc = {-1, 0, 1, 0};
		
		int goIdx = 0;
		int nr = shark_r, nc = shark_c;
		int idx = 1;
		int ballNumber = map[shark_r][shark_c]; // 구슬 번호
		int bumbCnt = 0; // 구슬이 부서진 개수
		while(true) {
			for(int i=0; i<goIdx/2+1; i++) {
				nr = nr + dr[goIdx%4];
				nc = nc + dc[goIdx%4];
				
				if(map[nr][nc] != ballNumber) {
					ballNumber = map[nr][nc];
					
					if(tmpQ.size() >= 4) {
						while(!tmpQ.isEmpty()) {
							Cood t = numToMap.get(tmpQ.poll());
							countBumbBall(map[t.r][t.c]);
							map[t.r][t.c] = -2; // 구슬을 폭발시킴
							bumbCnt += 1;
						}
					} else tmpQ = new LinkedList<>();	
				}
				
				tmpQ.offer(idx);
				idx += 1;
				
				if(nr == 0 && nc == 0) break;
			}
			if(nr == 0 && nc == 0) break;
			goIdx += 1;
		}
		
		return bumbCnt;
	}
	public static void pullMap() {
		// map을 당기다 (빈 칸을 채운다.)
		q = new LinkedList<>();
		int num = checkMapRotation();
		
		if(num != 0) {
			while(!q.isEmpty()) {
				Cood m = numToMap.get(num);
				int numQ = q.poll();
				
				if(numQ == -2) continue;
				else {
					map[m.r][m.c] = numQ;
					num += 1;
				}
			}
			
			for(int k=num; k<N*N; k++) {
				Cood m = numToMap.get(k);
				map[m.r][m.c] = -2;
			}
		}
	}
	public static void changeMap() {
		// 구슬이 변화하는 단계 (가장 마지막 단계)
		Queue<Integer> tmpQ = new LinkedList<>(); // 구슬의 개수와 번호를 순서대로 저장
		int[] dr = {0, 1, 0, -1}; // 좌 하 우 상
		int[] dc = {-1, 0, 1, 0};
		
		int goIdx = 0;
		int nr = shark_r, nc = shark_c;
		int ballNumber = map[shark_r][shark_c];
		int cnt = 0;
		while(true) {
			for(int i=0; i<goIdx/2+1; i++) {
				nr = nr + dr[goIdx%4];
				nc = nc + dc[goIdx%4];
				
				if(map[nr][nc] != ballNumber) {
					if(ballNumber != 0) {
						tmpQ.offer(cnt);
						tmpQ.offer(ballNumber);
					}
					ballNumber = map[nr][nc];
					cnt = 0;
				} 
				
				cnt += 1;
				if(nr == 0 && nc == 0) break;
			}
			if(nr == 0 && nc == 0) break;
			goIdx += 1;
		}
		
		int idx = 1;
		while(!tmpQ.isEmpty()) {
			if(idx >= N*N) break;
			Cood t = numToMap.get(idx);
			map[t.r][t.c] = tmpQ.poll();
			idx += 1;
		}
		
		for(int k=idx; k<N*N; k++) {
			Cood m = numToMap.get(k);
			map[m.r][m.c] = -2;
		}
	}
	public static void countBumbBall(int n) {
		if(n == 1) ballOne += 1;
		else if(n == 2) ballTwo += 1;
		else if(n == 3) ballThree += 1;
	}
}
