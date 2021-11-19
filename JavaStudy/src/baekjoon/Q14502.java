// 연구소 (백준 14502번)

/* [ 알고리즘 ]
 * 
 * 1. 빈칸과 벽 바이러스의 좌표를 먼저 이차원 배열로 받음
 * 2. 0 좌표 전체에서 3개를 조합으로 뽑은 뒤 1로 변환 시킴
 * 3. 2번 후 바이러스를 퍼뜨려봄 (2 가 있는 좌표에서 x<0, y<0, x>M, y>=N 이 되면 stop)
 * 4. 그 전까지 2로 변환 시키면서 count시킴
 * 5. 바이러스의 갯수를 센 count 를 바이러스 갯수 배열에 넣어줌
 * 6. 모든 조합에 따른 바이러스 퍼뜨리는 경우가 끝나면 바이러스 갯수 배열에서 최솟값을 가져옴
 * 7. 안전 영역의 최대 크기 = N*M - (1의 개수 + 3 + 2의 개수 + 6번에서 나온 최솟값)
 * 
 */
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q14502 {
	
	static ArrayList<type> virus; // 초기 바이러스 좌표 배열
	static ArrayList<Integer> virusCountArr; // 바이러스를 퍼뜨린 후 바이러스 개수를 담을 배열
	static int[] dx = {0, 0, -1, 1}; // 상 하 좌 우
	static int[] dy = {1, -1, 0, 0};
	
	
	public static class type {
		private int x;
		private int y;
		
		public type(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		int countOne=0, countVirus=0; // countOne : 초기 1의 개수, countVirus : 초기 바이러스의 개수
		virus = new ArrayList<>(); // 초기 바이러스 좌표 배열
		
		virusCountArr = new ArrayList<>();
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==1) countOne++;
				else if(map[i][j]==2) {
					countVirus++;
					virus.add(new type(j, i)); // [**주의] 초기 바이러스들의 좌표를 미리 저장해서 이후 바이러스 퍼뜨릴 때 편리하게 설정
				}
				else continue;
			}
		}

		int[][] visited = copy(map); // map을 그대로 깊은 복사
		combi(map, visited, 3);

		// 안전영역의 최대 크기 : N*M - (1의 개수 + 3 + 2의 개수 + 6번에서 나온 최솟값)
		System.out.println(N*M - (countOne + 3 + countVirus + Collections.min(virusCountArr)));
	}
	
	public static void combi(int[][] map, int[][] visited, int L) {
		if(L==0) {
			BFS_virus(visited);
			visited = copy(map);
			return;
		}
		
		for(int i=0; i<visited.length; i++) {
			for(int j=0; j<visited[i].length; j++) {
				if(visited[i][j]==0) { 
					visited[i][j] = 1;
					combi(map, visited, L-1); // combi(map, visited, i+1, j+1, L-1); 이거에서 변경
					visited[i][j]= 0;
				}
			}
		}
		
	}
	
	public static void BFS_virus(int[][] map) { 
		Queue<type> q = new LinkedList<>();
		for(int i=0; i<virus.size(); i++) {
			q.add(new type(virus.get(i).x , virus.get(i).y));
		}

		int virusCount = 0; // 추가로 전이된 바이러스 개수
		int[][] check = copy(map); // 깊은 복사
		
		while(!q.isEmpty()) {
			type q_virus = q.poll();
			for(int i=0; i<4; i++) {
				int nx = q_virus.x + dx[i];
				int ny = q_virus.y + dy[i];

				if(nx>=0 && ny>=0 && nx<check[0].length && ny<check.length) {
					if(check[ny][nx]==0) {
						check[ny][nx] = 2;
						virusCount++;
						q.add(new type(nx, ny));
					}
				}
			}
		}
		virusCountArr.add(virusCount);
	}
	
	public static int[][] copy(int[][] arr) { // 깊은 복사 : 전부를 복사하여 이전 참조를 공유하지 않음 (원본 객체 값에 영향을 주지 않음)
		int[][] copy = new int[arr.length][arr[0].length];
		
		for(int i=0; i<arr.length; i++) {
			for(int j=0; j<arr[0].length; j++) {
				copy[i][j] = arr[i][j];
			}
		}
		
		return copy;
	}
}
