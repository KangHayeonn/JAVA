// 마법사 상어와 파이어볼
package beakjoon_samsung;

import java.io.*;
import java.util.*;

public class Q20056 {
	static int N, M, K;
	static Type[][] map;
	static Map<Integer, TypeBall> ballMap;
	static int fireBallIdx = 1;
	public static class Type {
		int count;              // 파이어볼 개수
		ArrayList<Integer> arr; // 파이어볼 번호
		public Type(int count, ArrayList<Integer> arr) {
			this.count = count;
			this.arr = arr;
		}
	}
	public static class TypeBall {
		int r, c;
		int m, s, d; // m : 질량, s : 속력, d : 방향
		public TypeBall(int r, int c, int m, int s, int d) {
			this.r = r;
			this.c = c;
			this.m = m;
			this.s = s;
			this.d = d;
		}
	}
	public static class TypeBall2 {
		int num;
		int r, c;
		int m, s, d; // m : 질량, s : 속력, d : 방향
		public TypeBall2(int num, int r, int c, int m, int s, int d) {
			this.num = num;
			this.r = r;
			this.c = c;
			this.m = m;
			this.s = s;
			this.d = d;
		}
	}
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new Type[N][N];
		ballMap = new HashMap<>();
		
		int r, c, m, s, d;
		ArrayList<Integer> tmpArr;
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			r = Integer.parseInt(st.nextToken()) - 1;
			c = Integer.parseInt(st.nextToken()) - 1;
			m = Integer.parseInt(st.nextToken());
			s = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());

			tmpArr = new ArrayList<>();
			tmpArr.add(fireBallIdx);
			map[r][c] = new Type(1, tmpArr);
			ballMap.put(fireBallIdx, new TypeBall(r, c, m, s, d));
			fireBallIdx += 1;
		}
		for(Integer ball : ballMap.keySet()) {
			TypeBall  b = ballMap.get(ball);
		}
		
		while(K-- > 0) {
			moveAllFireBall();
			moveFinishBall();
		}
		
		int result = 0;
		for(Integer ball : ballMap.keySet()) {
			result += ballMap.get(ball).m;
		}
		
		System.out.println(result);
	}
	public static void moveAllFireBall() {
		Queue<TypeBall2> q = new LinkedList<>();
		int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
		int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};
		
		int nr, nc, moveR, moveC;
		ArrayList<Integer> tmpArr;
		for(Integer ball : ballMap.keySet()) {
			TypeBall  b = ballMap.get(ball);
			moveR = dr[b.d];
			moveC = dc[b.d];
			
			if(moveR < 0) {
				nr = (b.r + (moveR * b.s) + N*1000) % N;
			} else {
				nr = (b.r + moveR * b.s) % N; 
			}
			
			if(moveC < 0) {
				nc = (b.c + (moveC * b.s) + N*1000) % N;
			} else {
				nc = (b.c + moveC * b.s) % N;
			}
			
			Type t = map[b.r][b.c];
			t.arr.remove(Integer.valueOf(ball));
			t.count -= 1;
			map[b.r][b.c] = t;
			
			if(map[nr][nc] == null) {
				tmpArr = new ArrayList<>();
				tmpArr.add(ball);
				map[nr][nc] = new Type(1, tmpArr);
			} else {
				Type a = map[nr][nc];
				a.arr.add(ball);
				a.count += 1;
				map[nr][nc] = a;
			}
			
			q.offer(new TypeBall2(ball, nr, nc, b.m, b.s, b.d));
		}
		
		ballMap = new HashMap<>();
		while(!q.isEmpty()) {
			TypeBall2 t = q.poll();
			ballMap.put(t.num, new TypeBall(t.r, t.c, t.m, t.s, t.d));
		}
	}
	public static void moveFinishBall() {
		boolean isAll;
		int totalM, totalS;
		int checkD; // -1 초기값, 0 : 모두 홀or짝, 1: 그 외
		int cnt;

		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				Type t = map[i][j];
				
				if(t == null) continue;
				
				isAll = true;
				totalM = 0;
				totalS = 0;
				checkD = -1;
				cnt = 0;
				if(t.count >= 2) {
					cnt = t.count;
					for(Integer ball : t.arr) { // 파이어볼 번호
						TypeBall b = ballMap.get(ball);
						totalM += b.m;
						totalS += b.s;
						if(checkD == -1) {
							if(b.d % 2 == 0) checkD = 0;
							else checkD = 1;
						}
						
						ballMap.remove(ball);
						
						if(!isAll) continue;
						
						if(checkD != -1 && checkD == 0) {
							if(b.d % 2 == 0) checkD = 0;
							else {
								checkD = 1;
								isAll = false;
							}
						}
						if(checkD != -1 && checkD == 1) {
							if(b.d % 2 == 0) {
								checkD = 0;
								isAll = false;
							}
							else {
								checkD = 1;
							}
						}
					}
					
					map[i][j] = new Type(0, new ArrayList<Integer>());
					
					if(!isAll) { // 1, 3, 5, 7
						for(int idx=1; idx<=7; idx+=2) {
							ballMap.put(fireBallIdx, new TypeBall(i, j, totalM/5, totalS/cnt, idx));
							Type a = map[i][j];
							a.arr.add(fireBallIdx);
							a.count += 1;
							map[i][j] = a;
							fireBallIdx += 1;
						}
					} else { // 0, 2, 4, 6
						for(int idx=0; idx<7; idx+=2) {
							ballMap.put(fireBallIdx, new TypeBall(i, j, totalM/5, totalS/cnt, idx));
							Type a = map[i][j];
							a.arr.add(fireBallIdx);
							a.count += 1;
							map[i][j] = a;
							fireBallIdx += 1;
						}
					}
				}
			}
		}
	}
}
