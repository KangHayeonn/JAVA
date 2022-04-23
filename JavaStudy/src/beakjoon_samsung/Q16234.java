// 인구 이동 (백준 골드5)

package beakjoon_samsung;

import java.io.*;
import java.util.*;

public class Q16234 {
	static int[][] map;
	static int[] dr = {0, 0, 1, -1}; // 동 서 남 북
	static int[] dc = {1, -1, 0, 0}; // 동 서 남 북
	static int answer = 0;
	static boolean[][] isVisited;
	public static class Type {
		int r, c;
		public Type(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		while(true) {
			boolean check = false;
			isVisited = new boolean[map.length][map.length];
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(!isVisited[i][j]) {
						check = bfs(false, i, j, L, R);
					}
					System.out.println(Arrays.deepToString(map));
					System.out.println(Arrays.deepToString(isVisited));
				}
			}
			
			if(check) answer++;
			else break;
		}

		System.out.println("정답 : " + answer);
		System.out.println(Arrays.deepToString(map));
	}
	public static boolean bfs(boolean check, int r, int c, int L, int R) {
		System.out.println("r : " + r + " c: " + c);
		Queue<Type> q = new LinkedList<>();
		ArrayList<Type> arr = new ArrayList<>();
		q.offer(new Type(r, c));
		int total = 0;
		int count = 0;
		arr.add(new Type(r, c));
		
		while(!q.isEmpty()) {
			Type t = q.poll();
			total += map[t.r][t.c];
			count++;
			System.out.println(t.r + " : " + t.c );
			isVisited[t.r][t.c] = true;
			
			for(int i=0; i<4; i++) {
				int nr = t.r + dr[i];
				int nc = t.c + dc[i];
				
				if(nr < 0 || nc < 0 || nr >= map.length || nc >= map.length) continue;
				
				int value = Math.abs(map[t.r][t.c] - map[nr][nc]);
				System.out.println(nr + " : " + nc + " -> " +value);
				if(value >= L && value <= R && !isVisited[nr][nc]) {
					q.offer(new Type(nr, nc));
					isVisited[nr][nc] = true;
					check = true;
					System.out.println("////// " + nr + " : " + nc + " -> " +value);
					arr.add(new Type(nr, nc));
				}
			}
		}
		
		if(!check) return false;
		else {
			total = total/count;
			for(Type t : arr) {
				map[t.r][t.c] = total;
			}
			return true;
		}
	}
}
