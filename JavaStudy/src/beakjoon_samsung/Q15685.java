// 드래곤 커브 (백준 골드4)

package beakjoon_samsung;

import java.io.*;
import java.util.*;

public class Q15685 {
	static int[][] map = new int[101][101]; // 0~100
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, -1, 0, 1};
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer("");
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			dragonCurve(x, y, d, g);
		}
		
		int answer = 0;
		for(int i=0; i<100; i++) {
			for(int j=0; j<100; j++) {
				if(map[i][j]==1 && map[i][j+1]==1 && map[i+1][j]==1 && map[i+1][j+1]==1) {
					answer++;
				}
			}
		}
		System.out.println(answer);
	}
	public static void dragonCurve(int x, int y, int d, int g) {
		ArrayList<Integer> arr = new ArrayList<>();
		arr.add(d);
		
		for(int i=1; i<=g; i++) {
			for(int j=arr.size()-1; j>=0; j--) {
				arr.add((arr.get(j)+1) % 4); // 반시계방향 (끝점을 기준으로 시계방향이기 때문)
			}
		}
		
		map[y][x] = 1;
		for(Integer n : arr) {
			x += dx[n];
			y += dy[n];
			map[y][x] = 1;
		}
	}
}
