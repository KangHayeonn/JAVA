// 연구소 (백준 골드5)

// 1. 0 중에 3개를 조합으로 뽑아 벽을 세움
// 2. 바이러스를 퍼뜨려봄 (bfs)
// 3. 이때의 바이러스의 개수를 구함
// 4. 바이러스 개수가 최소가 될 수록 안전영역이 커지므로 최소일 때만 갱신

package beakjoon_samsung;

import java.io.*;
import java.util.*;

public class Q14502 {
	static int virus = Integer.MAX_VALUE;
	static ArrayList<Type> arr;
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
		int N = Integer.parseInt(st.nextToken()); // 세로크기
		int M = Integer.parseInt(st.nextToken()); // 가로크기
		int[][] map = new int[N][M];
		int wall = 0;
		arr = new ArrayList<>();
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==2) {
					arr.add(new Type(i, j));
				} else if(map[i][j]==1) {
					wall++;
				}
			}
		}
		
		int[][] isVisited = copy(map);
		combi(map, isVisited, 3);

		int answer = N * M - virus - (wall+3);
		System.out.println(answer);
	}
	public static void combi(int[][] map, int[][] isVisited, int depth) {
		if(depth==0) {
			bfs(isVisited);
			return;
		}
		
		for(int i=0; i<isVisited.length; i++) {
			for(int j=0; j<isVisited[0].length; j++) {
				if(isVisited[i][j] == 0) {
					isVisited[i][j] = 1;
					combi(map, isVisited, depth-1);
					isVisited[i][j] = 0;
				}
			}
		}
	}
	public static void bfs(int[][] isVisited) {
		Queue<Type> q = new LinkedList<>();
		int[][] newMap = copy(isVisited);
		int count = 0;
		
		for(Type t : arr) {
			q.offer(new Type(t.r, t.c));
			count++;
		}
		
		while(!q.isEmpty()) {
			Type t = q.poll();
			
			final int[][] moving = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
			for(int i=0; i<moving.length; i++) {
				int nr = t.r + moving[i][0];
				int nc = t.c + moving[i][1];
				
				if(nr < 0 || nc < 0 || nr >= newMap.length || nc >= newMap[0].length) continue;
				if(newMap[nr][nc] == 1 || newMap[nr][nc] == 2) continue;
				
				q.offer(new Type(nr, nc));
				newMap[nr][nc] = 2;
				count++;
			}
		}
		virus = Math.min(virus, count);
	}
	public static int[][] copy(int[][] arr) {
		int[][] copy = new int[arr.length][arr[0].length];
		
		for(int i=0; i<arr.length; i++) {
			for(int j=0; j<arr[0].length; j++) {
				copy[i][j] = arr[i][j];
			}
		}
		
		return copy;
	}
}
