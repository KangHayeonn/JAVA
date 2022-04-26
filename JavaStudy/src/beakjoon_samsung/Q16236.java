// 아기 상어 (백준 골드3)

package beakjoon_samsung;

import java.io.*;
import java.util.*;

public class Q16236 {
	static int N;
	static int[][] map;
	static boolean[][] isVisited;
	static int[] shark = new int[4]; // 아기상어의 좌표 + 크기(초기 2) + 먹은 갯수
	static int[] dr = {0, 0, 1, -1}; // 동서남북
	static int[] dc = {1, -1, 0, 0};
	static PriorityQueue<Type> pq; // 먹을 수 있는 물고기를 좌표와 크기, 상어와의 거리를 담는 큐
	static int time = 0;
	public static class Type implements Comparable<Type> {
		int r, c;
		int size;
		int d;
		public Type(int r, int c, int size, int d) {
			this.r = r;
			this.c = c;
			this.size = size;
			this.d = d;
		}
		@Override
		public int compareTo(Type t) {
			if(this.d == t.d) {
				if(this.r == t.r) {
					return this.c - t.c;
				}
				return this.r - t.r;
			} else {
				return this.d - t.d;
			}
		}
	}
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer("");
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 9) {
					shark[0] = i;
					shark[1] = j;
					shark[2] = 2;
				}
			}
		}

		while(true) {
			pq = new PriorityQueue<>();
			isVisited = new boolean[N][N];
			bfs();
			if(!eat()) break;
			// 인접한 상하좌우를 살핌 
			// 물고기를 만나면 (1~6) 로직 판단 -> 물고기의 좌표, 물고기 크기, 상어와의 거리 PriorityQueue에 추가
			// if(eat()) // 먹음;
			//else {
			//	return;
			// }
			// 더이상 먹을 물거기가 없으면 return
			// 먹을 수 있는 물고기가 1개면 그 물고기를 먹으러감
			// 먹을 수 있는 물고기가 1이상 이면 로직 판단
			// 아기상어는 자신의 크기와 같은 순의 물고기를 먹을 때마다 크기가 1 증가
			// 시간은 먹으러가는 이동 시간(거리)을 추가함
		}
		System.out.println(time);
	}
	public static void bfs() {
		Queue<Type> q = new LinkedList<>();
		q.offer(new Type(shark[0], shark[1], 0, 0));
		
		while(!q.isEmpty()) {
			Type t = q.poll();
			isVisited[t.r][t.c] = true;
			
			for(int i=0; i<4; i++) {
				int nr = t.r + dr[i];
				int nc = t.c + dc[i];
				
				if(nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
				if(isVisited[nr][nc]) continue;
				else {
					if(map[nr][nc] == 0) {
						q.offer(new Type(nr, nc, 0, t.d+1));
						isVisited[nr][nc] = true;
					} else if(map[nr][nc] == shark[2]) {
						q.offer(new Type(nr, nc, 0, t.d+1));
						isVisited[nr][nc] = true;
					} else if(map[nr][nc] < shark[2]) {
						pq.offer(new Type(nr, nc, map[nr][nc], t.d+1));
						isVisited[nr][nc] = true;
					}
				}
			}
		}
	} 
	public static boolean eat() {
		if(pq.size() == 0) return false;
		else {
			Type t = pq.poll();
			map[shark[0]][shark[1]] = 0;
			map[t.r][t.c] = 0;
			shark[0] = t.r;
			shark[1] = t.c;
			shark[3] += 1;
			time += t.d;
			if(shark[2] == shark[3]) {
				shark[2] += 1;
				shark[3] = 0;
				// 크기가 커지고 먹는 횟수는 0으로
			}
		}
		return true;
	}
}
