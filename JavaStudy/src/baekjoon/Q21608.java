// 상어 초등학교 (백준 21608번)

/* [ 알고리즘 ]
 * 
 * 1. 기본 맵을 사람 수에 따라 미리 만들어 놓음 (map 에 학생들의 자리를 저장) 
 * 2. 특정 학생이 자리 배치를 완료 했는지 체크하는 배열 만들기 (true/false)
 * 3. 학생별로 1번의 맵에서 상하좌우를 보며 빈공간일 경우와 좋아하는 학생이 있는지를 체크 (x, y, like, blank)
 * 4. 3번을 문제조건에 따라 정렬 해줌
 * 5. 학생에 따라 가장 최상위 조건에 해당하는 자리에 배치시킴
 * 6. 학생 모두를 배치시켜서 map이 꽉 찰 때까지 3번-5번 과정을 반복
 * 7. 맵을 돌면서 각 좌표에 있는 학생 별로 상하좌우에 자신이 좋아하는 학생이 몇명인지 카운트해줌
 * 8. 7번을 기준으로 (0이면 학생의 만족도는 0, 1이면 1, 2이면 10, 3이면 100, 4이면 1000) 값에 따라 만족도의 총합을 구함
 */
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Q21608 {
	static int[][] map; // 자리배치 기본 맵(교실)
	static int[] studentCheck; // 특정 학생이 자리 배치를 완료했는지 체크
	static PriorityQueue<type> queue;
	static int[] dx = {0, 0, -1, 1}; // 상 하 좌 우
	static int[] dy = {1, -1, 0, 0};
	static int N; // 교실은 N×N ( 학생수 N*N )
	
	public static class type implements Comparable<type>{
		private int column;     // 행
		private int row;        // 열
		private int likeCount;  // 인접한 곳에 좋아하는 사람이 몇 명인지 카운트
		private int blankCount; // 인접한 곳에 비어있는 칸이 몇 칸인지 카운트 
		
		public type(int column, int row, int likeCount, int blankCount) {
			this.column = column;
			this.row = row;
			this.likeCount = likeCount;
			this.blankCount = blankCount;
		}
		
		@Override
		public int compareTo(type t) {
			if(this.likeCount != t.likeCount) return t.likeCount - this.likeCount;
			else {
				if(this.blankCount != t.blankCount) return t.blankCount - this.blankCount;
				else {
					if(this.column != t.column) return this.column - t.column;
					else return this.row - t.row;
				}
			}
		}
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer("");
		Map<Integer, int[]> likeList = new HashMap<>(); // 학생 - 좋아하는 사람의 번호 리스트
		int[] students = new int[4];
		map = new int[N][N];
		studentCheck = new int[N*N+1];
		
		for(int i=0; i<N*N; i++) {
			st = new StringTokenizer(br.readLine());
			int student = Integer.parseInt(st.nextToken());
			students = new int[4];
			for(int j=0; j<4; j++) {
				students[j] = Integer.parseInt(st.nextToken());
			}
			find(student, students);
			likeList.put(student, students); // student : 학생의 번호 , students : 좋아하는 학생의 번호
		}
		
		// 자리 배치가 완료된 후 map 출력
		/*
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}*/
		
		// 만족도 검사
		for(int col=0; col<N; col++) {
			for(int row=0; row<N; row++) {
				int s = map[col][row]; // 자리에 앉은 학생
				int count = 0;
				
				for(int i=0; i<4; i++) { // 상하좌우
					int nextCol = col + dy[i];
					int nextRow = row + dx[i];
					
					if(nextCol<0 || nextCol>=N || nextRow<0 || nextRow>=N) continue;
					
					for(Integer v : likeList.get(s)) { // 좋아하는 사람 리스트
						if(v==map[nextCol][nextRow]) count++;
					}
				}
				studentCheck[s] = count;
			}
		}
		
		int sum = 0;
		for(int i=0; i<studentCheck.length; i++) {
			int x = studentCheck[i];
			switch(x) {
			case 0 : sum += 0; break;
			case 1 : sum += 1; break;
			case 2 : sum += 10; break;
			case 3 : sum += 100; break;
			case 4 : sum += 1000; break;
			default : break;
			}
		}
		
		System.out.println(sum);
	}
	public static void find(int student, int[] likeList) {
		queue = new PriorityQueue<>();
		int count, blank;
		
		for(int col=0; col<N; col++) {
			for(int row =0; row<N; row++) {
				count = 0;
				blank =0;
				for(int i=0; i<4; i++) { // 상하좌우
					int nextCol = col + dy[i];
					int nextRow = row + dx[i];
					
					if(nextCol<0 || nextCol>=N || nextRow<0 || nextRow>=N) continue;
					
					for(int j=0; j<4; j++) { // 좋아하는 사람 리스트
						if(map[nextCol][nextRow]==likeList[j]) count++;
					}
					
					if(map[nextCol][nextRow]==0) blank++;
				}
				
				queue.add(new type(col, row, count, blank));
			}
		}
		
		while(!queue.isEmpty()) {
			type x = queue.poll();
			//System.out.println("출력 확인 : " + x.column + " " + x.row + " " + x.likeCount + " " +x.blankCount);
			
			if(map[x.column][x.row] == 0) {
				map[x.column][x.row] = student;
				//System.out.println("저장 확인 : " + map[x.column][x.row] + " " + Arrays.toString(studentCheck));
				return;
			}
		}
	}
}
