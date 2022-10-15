// 술래잡기 (삼성 2022 상반기 기출 오전 1번)

package codetree;

import java.io.*;
import java.util.*;

public class HideAndSeek {
	static int n, m, h, k;
	static Type[][] map;
	static Map<Integer, Cood> runInfo;
	static int runIdx = 1;
	static ArrayList<Integer> tmpArr;
	static boolean[][] treeMap;
	static int[] dr = {0, 1, 0, -1}; // 우 하 좌 상
	static int[] dc = {1, 0, -1, 0};
	static Queue<Type2> q;
	static int turn = 1;
	static int score = 0;
	public static class Type {
		int cnt; // 도망자수
		ArrayList<Integer> runArr;
		public Type(int cnt, ArrayList<Integer> runArr) {
			this.cnt = cnt;
			this.runArr = runArr;
		}
	}
	public static class Cood {
		int r, c;
		int d;
		public Cood(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}
	}
	public static class Type2 {
		int num;
		int r, c;
		int d;
		public Type2(int num, int r, int c, int d) {
			this.num = num;
			this.r = r;
			this.c = c;
			this.d = d;
		}
	}
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		map = new Type[n][n];
		runInfo = new HashMap<>();
		treeMap = new boolean[n][n];
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				tmpArr = new ArrayList<>();
				map[i][j] = new Type(0, tmpArr); // map 초기화 (이후 NullPointer 에러 방지)
			}
		}
		
		int r, c, d;
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			r = Integer.parseInt(st.nextToken())-1;
			c = Integer.parseInt(st.nextToken())-1;
			d = Integer.parseInt(st.nextToken())-1;
			
			Type t = map[r][c];
			t.cnt += 1;
			t.runArr.add(runIdx);
			map[r][c] = t;
			
			runInfo.put(runIdx, new Cood(r, c, d));
			
			runIdx +=1;
		}
		
		for(int i=0; i<h; i++) {
			st = new StringTokenizer(br.readLine());
			r = Integer.parseInt(st.nextToken())-1;
			c = Integer.parseInt(st.nextToken())-1;
			treeMap[r][c] = true;
		}
		
		play();
		
		System.out.println(score);
	}
	public static void play() {
		int player_r = n/2, player_c = n/2; // 술래 (player)
		int[] pr = {-1, 0, 1, 0}; // 상 우 하 좌
		int[] pc = {0, 1, 0, -1};
		int getIdx = 0;
		boolean isTurn; // turn을 바로하는지 체크
		
		int nr = player_r, nc = player_c;
		while(true) {
			isTurn = false;
			for(int i=0; i<getIdx/2+1; i++) {
				// 도망자가 움직임
				move(nr, nc);
				
				nr = nr + pr[getIdx%4];
				nc = nc + pc[getIdx%4];
				
				if(nr == 0 && nc == 0) break;
				
				if(i == getIdx/2) {
					isTurn = true;
				} else {
					// 술래가 도망자를 잡음
					catchRunner(nr, nc, pr, pc, getIdx%4);
					if(k==0) return;
				}
			}
			if(nr == 0 && nc == 0) break;
			getIdx += 1;
			
			if(isTurn) {
				catchRunner(nr, nc, pr, pc, getIdx%4);
				if(k==0) return;
			}
		}
		
		if(nr == 0 && nc == 0) {
			catchRunner(nr, nc, pr, pc, ((getIdx-1)+2)%4);
			if(k==0) return;
		}
		
		int[] pr2 = {1, 0, -1, 0}; // 하 좌 상 우
		int[] pc2 = {0, -1, 0, 1};
		
		nr = nr - 1;
		getIdx = (n-1)*2;
		
		while(true) {
			isTurn = false;
			for(int i=0; i<getIdx/2+1; i++) {
				// 도망자가 움직임
				move(nr, nc);
				
				nr = nr + pr2[getIdx%4];
				nc = nc + pc2[getIdx%4];
				
				if(nr == 0 && nc == 0) continue;
				
				if(i == getIdx/2) {
					isTurn = true;
				} else {
					// 술래가 도망자를 잡음
					catchRunner(nr, nc, pr2, pc2, getIdx%4);
					if(k==0) return;
				}
				
				if(nr == player_r && nc == player_c) break;
			}
			if(nr == player_r && nc == player_c) break;
			getIdx -= 1;
			
			if(isTurn) {
				catchRunner(nr, nc, pr2, pc2, getIdx%4);
				if(k==0) return;
			}
		}
		
		if(nr == player_r && nc == player_c) {
			catchRunner(nr, nc, pr2, pc2, (getIdx+2)%4);
			if(k==0) return;
		}
		
		if(k > 0) play();
	}
	public static void catchRunner(int player_r, int player_c, int[] pr, int[] pc, int d) {
		int nr, nc;
		for(int i=0; i<3; i++) {
			nr = player_r + pr[d]*i;
			nc = player_c + pc[d]*i;
			
			if(nr < 0 || nc < 0 || nr >= n || nc >= n) continue;
			if(treeMap[nr][nc]) continue;

			Type t = map[nr][nc];			
			score += t.cnt * turn;
			t.cnt = 0;
			for(Integer key: t.runArr) {
				runInfo.remove(key);
			}
			t.runArr = new ArrayList<>();
			map[nr][nc] = t;
		}
		turn += 1;
		k -= 1;
	}
	public static void move(int player_r, int player_c) {
		q = new LinkedList<>();
		
		int distance = 0; // 도망자와 술래의 거리
		int nr, nc, nd;
		for(int r=0; r<n; r++) {
			for(int c=0; c<n; c++) {
				if(map[r][c].cnt == 0) continue; // 해당 칸에 도망자가 없는 경우
				
				distance = Math.abs(r-player_r) + Math.abs(c-player_c);
				
				if(distance <= 3) { // 도망자와 술래의 거리가 3보다 작을 경우
					Type t = map[r][c];
					
					for(Integer key : t.runArr) {
						Cood runner = runInfo.get(key); // 도망자
						
						for(int i=0; i<4; i+=2) {
							nd = (runner.d + i) % 4;
							nr = runner.r + dr[nd];
							nc = runner.c + dc[nd];
							
							if(nr < 0 || nc < 0 || nr >= n || nc >= n) continue;
							
							if(i==0) { // 현재 바라보고 있는 방향으로 1칸 움직임
								if(nr == player_r && nc == player_c) {
									// 움직이려는 칸에 술래가 있다면
									q.offer(new Type2(key, runner.r, runner.c, runner.d));
								} else {
									q.offer(new Type2(key, nr, nc, nd));
								}
							} else {
								if(nr == player_r && nc == player_c) {
									// 움직이려는 칸에 술래가 있다면
									q.offer(new Type2(key, runner.r, runner.c, nd));
								} else {
									q.offer(new Type2(key, nr, nc, nd));
								}
							}
							
							break;
						}
					}
				} else { // 디버깅 (3이 넘지 않는 도망자의 위치를 담아주지 않음) **
					Type t = map[r][c];
					
					for(Integer key : t.runArr) {
						Cood runner = runInfo.get(key);
						q.offer(new Type2(key, runner.r, runner.c, runner.d));
					}
				}
			}
		}
		
		map = new Type[n][n];
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				tmpArr = new ArrayList<>();
				map[i][j] = new Type(0, tmpArr); // map 초기화 (이후 NullPointer 에러 방지)
			}
		}
		
		while(!q.isEmpty()) {
			Type2 t = q.poll();
			Type originT = map[t.r][t.c];
			originT.cnt += 1;
			originT.runArr.add(t.num);
			runInfo.put(t.num, new Cood(t.r, t.c, t.d));
		}
	}
}
