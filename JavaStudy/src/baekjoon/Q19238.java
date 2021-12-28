// 스타트 택시 (백준 19238번)

/* [ 알고리즘 ]
 * 
 * 1. 초기 벽과 빈칸의 map을 받아줌
 * 2. 택시의 위치와 각 승객의 출발지 및 목적지의 위치를 받아줌
 * 3. 택시의 위치와 승객의 출발지 위치의 길이를 찾아주고 제일 최솟값인 출발지를 뽑아줌 - PriorityQueue (BFS)
 * 4. 출발지까지 소모된 연료 빼기
 * 5. 4번에서 뽑힌 현재 출발지에서부터 도착지까지 가장 가까운 경로 선정 (BFS)
 * 6. 도착지까지 소모된 연료 빼기
 * 7. 도착지까지 소모된 연료의 2배 충전
 * 
 * 주의 - 3번과 5번의 경우 : 해당 도착지(출발지 or 목적지)로 갈 수 없는 경우와 연료가 떨어질 경우는 -1을 출력시켜줌
 * 
 */
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q19238 {
	public static class taxi {
		int column; //행
		int row; // 열
		public taxi(int column, int row) {
			this.column = column;
			this.row = row;
		}
	}
	public static class passenger {
		int startCol; //행
		int startRow; // 열
		int endCol; //행
		int endRow; // 열
		public passenger(int startCol, int startRow, int endCol, int endRow) {
			this.startCol = startCol;
			this.startRow = startRow;
			this.endCol = endCol;
			this.endRow = endRow;
		}
	}
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // N*N 크기의 맵
		int M = Integer.parseInt(st.nextToken()); // M명의 승객을 태우는 것이 목표
		int fuel = Integer.parseInt(st.nextToken()); // 초기 연료의 양
		
		int[][] map = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		taxi taxi = new taxi(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
		
		passenger list[] = new passenger[M];
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			list[i] = new passenger(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				System.out.print(map[i][j] +" ");
			}
			System.out.println();
		}
		System.out.println(taxi.column +" " + taxi.row);
		System.out.println("연료 : " + fuel);
		for(int i=0; i<M; i++) {
			System.out.println(list[i].startCol +" " + list[i].startRow + " -> " + list[i].endCol +" " + list[i].endRow);
		}
	}
}
