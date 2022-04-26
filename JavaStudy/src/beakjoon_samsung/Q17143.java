// 낚시왕 (백준 골드2)

package beakjoon_samsung;

import java.io.*;
import java.util.*;

public class Q17143 {
	static Type2[][] map;
	static int R, C, M;
	static PriorityQueue<Type> shark;
	static int[] changeD = {1, 0, 3, 2};
	static int answer = 0;
	public static class Type implements Comparable<Type> {
		int r, c;
		int s; // 속력
		int d; // 이동 방향
		int z; // 크기
		public Type(int r, int c, int s, int d, int z) {
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
		}
		@Override
		public int compareTo(Type t) {
			if(this.r == t.r && this.c == t.c) return t.z - this.z;
			else if(this.r == t.r) return this.c - t.c;
			else return this.r - t.r;
		}
	}
	public static class Type2 {
		int s; // 속력
		int d; // 이동 방향
		int z; // 크기
		public Type2(int s, int d, int z) {
			this.s = s;
			this.d = d;
			this.z = z;
		}
	}
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new Type2[R][C];
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken())-1;
			int z = Integer.parseInt(st.nextToken());
			
			if(d <= 1) s %= (R - 1) * 2; // 불필요한 연산을 줄여주기 위해 속도 연산을 최소화로 줄여줌 (시간초과 방지)
			else s %= (C - 1) * 2;
			
			map[r][c] = new Type2(s, d, z);
		}
		
		int depth = -1;
		while(depth < C-1) { // C와 같아지면 탈출 (C초 후)
			// 낚시왕이 오른쪽으로 한칸이동
			depth += 1;
			shark = new PriorityQueue<>();
			// 낚시왕이 있는 열에 있는 상어 중 땅과 가장 가까운 상어를 잡음 (잡은 상어는 격자판에서 사라짐)
			catchShark(depth);
			// 상어 이동 (경계넘으면 방향 반대로)
			moveShark();
			// 한칸에 상어가 두마리 이상일 경우, 가장 큰 상어가 나머지 상어를 모두 잡아 먹음
			map = new Type2[R][C];
			removeShark();
		}
		System.out.println(answer);
	}

	public static void catchShark(int depth) {
		for(int i=0; i<R; i++) {
			if(map[i][depth] != null) {
				answer += map[i][depth].z;
				map[i][depth] = null;
				break;
			}
		}
	}
	public static void moveShark() {
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(map[i][j] != null) {
					Type t = move(i, j, map[i][j].s, map[i][j].d, map[i][j].z, map[i][j].s);
					shark.offer(t);
				}
			}
		}
	}
	public static Type move(int r, int c, int s, int d, int z, int depth) {
		if(r < 0 || c < 0 || r >= R || c >= C) {
			d = changeD[d];
			if(r < 0) {
				return move(r+1, c, s, d, z, depth+1);
			}
			else if(c < 0) {
				return move(r, c+1, s, d, z, depth+1);
			}
			else if(r >= R) {
				return move(r-1, c, s, d, z, depth+1);
			}
			else if(c >= C) {
				return move(r, c-1, s, d, z, depth+1);
			}
		}
		
		if(depth==0) {
			Type t = new Type(r, c, s, d, z);
			return t;
		}
		
		if(d==0) {
			return move(r-1, c, s, d, z, depth-1);
		}
		else if(d==1) {
			return move(r+1, c, s, d, z, depth-1);
		}
		else if(d==2) {
			return move(r, c+1, s, d, z, depth-1);
		}
		else {
			return move(r, c-1, s, d, z, depth-1);
		}
	}
	public static void removeShark() {
		boolean[][] isVisited = new boolean[R][C];
		
		while(!shark.isEmpty()) {
			Type t = shark.poll();
			
			if(isVisited[t.r][t.c]) continue;
			
			map[t.r][t.c] = new Type2(t.s, t.d, t.z);
			isVisited[t.r][t.c] = true;
		}
	}
}
