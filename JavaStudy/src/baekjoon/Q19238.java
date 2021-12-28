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
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q19238 {
	static type taxi; // 택시위치
	static passenger list[]; // 승객의 출발지 & 목적지
	static boolean check[][]; // 해당 맵을 방문했는지 체크하는 부분
	static int[] dx = {0, 0, -1, 1}; // 상 하 좌 우
	static int[] dy = {1, -1, 0, 0}; // 상 하 좌 우
	
	static int[][] map;
	static int N; // N*N 크기의 맵
	static int M; // M명의 승객을 태우는 것이 목표
	static int fuel; // 연료
	
	public static class type {
		int column; //행
		int row; // 열
		public type(int column, int row) {
			this.column = column;
			this.row = row;
		}
	}
	public static class passenger implements Comparable<passenger>{
		int dist; // 최단경로
		int startCol; // 출발지 행
		int startRow; // 출발지 열
		int endCol; // 목적지 행
		int endRow; // 목적지 열
		public passenger(int dist, int startCol, int startRow, int endCol, int endRow) {
			this.dist = dist;
			this.startCol = startCol;
			this.startRow = startRow;
			this.endCol = endCol;
			this.endRow = endRow;
		}
		@Override
		public int compareTo(passenger p) {
			if(this.dist == p.dist) {
				if(this.startCol == p.startCol) {
					return this.startRow - p.startRow; // 열 번호가 작은 승객
				} else return this.startCol - p.startCol; // 행 번호가 작은 승객
			} else return this.dist - p.dist; // 최단경로가 가장 짧은 승객
		}
	}
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // N*N 크기의 맵
		M = Integer.parseInt(st.nextToken()); // M명의 승객을 태우는 것이 목표
		fuel = Integer.parseInt(st.nextToken()); // 초기 연료의 양
		
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==1) map[i][j] = -1; // 벽을 -1로 바꿔줌 
			}
		}
		
		st = new StringTokenizer(br.readLine());
		taxi = new type(Integer.parseInt(st.nextToken())-1,Integer.parseInt(st.nextToken())-1);
		
		list = new passenger[M+1];
		ArrayList<type> person = new ArrayList<>(); // 내가 거쳐야할 승객 수
		for(int i=1; i<M+1; i++) {
			st = new StringTokenizer(br.readLine());
			int startCol = Integer.parseInt(st.nextToken());
			int startRow = Integer.parseInt(st.nextToken());
			int endCol = Integer.parseInt(st.nextToken());
			int endRow = Integer.parseInt(st.nextToken());
			list[i] = new passenger(0, startCol-1, startRow-1, endCol-1, endRow-1);
			person.add(new type(startCol-1, startRow-1));
			map[startCol-1][startRow-1]= i; // 1번부터 시작(승객번호)
		}
		
		while(person.size() > 0) {
			
			check = new boolean[N][N];
			type x = findStart(map, N); // 출발지 선택
			if(x.column==-1 && x.row==-1) {
				System.out.println(-1);
				return;
			}
			
			for(int i=0; i<person.size(); i++) {
				if(person.get(i).column==x.column && person.get(i).row==x.row) {
					person.remove(i);
				}
			}
			
			check = new boolean[N][N];
			int a = arriveEnd(x, map[x.column][x.row], map, N);
			map[x.column][x.row] = 0; // (주의) 태운 승객은 0으로 초기화시켜서 없애주기
			if(a==-1) {
				System.out.println(-1);
				return;
			}
			
			fuel+= a*2;
		}
		System.out.println(fuel);
		
	}
	public static type findStart(int[][] map, int N) {
		PriorityQueue<passenger> queue = new PriorityQueue<>();
		queue.add(new passenger(0, taxi.column, taxi.row, 0, 0));
		check[taxi.column][taxi.row]=true;
		
		while(!queue.isEmpty()) {
			passenger p = queue.poll();
			
			if(map[p.startCol][p.startRow] >= 1) {
				type x = new type(p.startCol, p.startRow);
				fuel -= p.dist;
				if(fuel < 0 ) { // 연료 모자랄경우 체크
					return new type(-1, -1);
				}
				return x;
			}
			for(int i=0; i<4; i++) {
				int nx = p.startCol + dx[i];
				int ny = p.startRow + dy[i];
				if(nx < 0 || nx >= N || ny <0 || ny >= N) {
					continue;
				}
				if(map[nx][ny] != -1 && !check[nx][ny]) {
					queue.add(new passenger(p.dist+1, nx, ny, 0, 0));
					check[nx][ny] =true;
				}
			}
		}
		return new type(-1, -1);
	}
	public static int arriveEnd(type x, int num, int[][] map, int N) {
		Queue<passenger> queue = new LinkedList<>();
		queue.add(new passenger(0, x.column, x.row, 0, 0));
		check[x.column][x.row]=true;
		
		while(!queue.isEmpty()) {
			passenger p = queue.poll();
			if(p.startCol == list[num].endCol && p.startRow == list[num].endRow) {
				fuel -= p.dist;
				if(fuel < 0 ) { // 연료 모자랄경우 체크
					return -1;
				}
				taxi.column = list[num].endCol; // 택시 위치 바꿔주기 (행)
				taxi.row = list[num].endRow;    // 택시 위치 바꿔주기 (열)
				return p.dist;
			}
			
			for(int i=0; i<4; i++) {
				int nx = p.startCol + dx[i];
				int ny = p.startRow + dy[i];
				if(nx < 0 || nx >= N || ny <0 || ny >= N) {
					continue;
				}
				if(map[nx][ny] != -1 && !check[nx][ny]) {
					queue.add(new passenger(p.dist+1, nx, ny, 0, 0));
					check[nx][ny] =true;
				}
			}
		}
		
		return -1;
	}
}
