// ¿Œ±∏ ¿Ãµø (πÈ¡ÿ ∞ÒµÂ5)

package beakjoon_samsung;

import java.io.*;
import java.util.*;

public class Q16234 {
	static int[][] map;
	static boolean[][] isVisited;
	static int[] dr = {0, 0, 1, -1}; // µøº≠≥≤∫Î
	static int[] dc = {1, -1, 0, 0}; // µøº≠≥≤∫Î
	static int N, L, R;
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
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int answer = 0;
		while(true) {
			boolean check = false;
			isVisited = new boolean[N][N];
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(bfs(i, j)) check = true;
				}
			}
			
			if(!check) break;
			else answer++;
		}
		System.out.println(answer);
	}
	public static boolean bfs(int r, int c) {
		if(isVisited[r][c]) return false;
		Queue<Type> q = new LinkedList<>();
		ArrayList<Type> arr = new ArrayList<>();
		q.offer(new Type(r, c));
		arr.add(new Type(r, c));
		int total = 0;
		
		while(!q.isEmpty()) {
			Type t = q.poll();
			isVisited[t.r][t.c] = true;
			total += map[t.r][t.c];
			
			for(int i=0; i<4; i++) {
				int nr = t.r + dr[i];
				int nc = t.c + dc[i];
				
				if(nr < 0 || nc < 0 || nr >= N  || nc >= N) continue;
				
				if(!isVisited[nr][nc]) {
					int value = Math.abs(map[t.r][t.c] - map[nr][nc]);
					if(value >= L && value <=R) {
						arr.add(new Type(nr, nc));
						q.offer(new Type(nr, nc));
						isVisited[nr][nc] = true;
					}
				}
			}
		}
		
		if(arr.size() > 1) {
			int people = total / arr.size();
			for(Type t : arr) {
				map[t.r][t.c] = people;
			}
			return true;
		} else {
			return false;
		}
	}
}
