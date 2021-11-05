// 뱀 (3190번)

/* [ 알고리즘 ]
 * - 해당 X초까지 움직이고 방향전환 후 X+1초 때 해당 방향으로 이동
 * - 뱀의 길이는 큐에 좌표 값으로 저장
 * 
 * Snake(뱀) 처음 길이 1,
 * 초를 기준으로 for문을 돌림 (무한루프 -> 벽을 만나거나 몸에 부딪히면 break)
 * 사과를 있으면 좌표값 추가만 -> queue.add()만 함 / queue.poll()||queue.remove()는 하지 않음
 * 사과가 없으면 선입선출 + 추가 -> queue.add() + queue.poll()||queue.remove()
 * 만약 방향전환이 있는 초를 만날 경우 해당 초까지 움직이고 방향 전환후 다음 초에 해당 방향으로 진행
 * 
 * 방향 표시 (상하좌우)
 * 우 →    /   하 ↓   /   좌 ←  /   상 ↑
 * dx:0   /  dx:1   /  dx:0   /  dx:-1
 * dy:1   /  dy:0   /  dy:-1  /  dy:0
 * idx[0] /  idx[1] /  idx[2] /  idx[3]
 * 
 * 왼쪽으로 방향전환 (L)  : (현재인덱스 + 3) % 4
 * 오른쪽으로 방향전환 (D) : (현재인덱스 + 1) % 4
 */
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q3190 {
	
	static Queue<coordinate> snake = new LinkedList<>(); // 뱀 길이
	
	public static class type {
		private int time;
		private String direction;
		
		public type (int time, String direction) {
			this.time = time;
			this.direction = direction;
		}
		/*
		public String toString() {
			return time + " : " + direction;
			//return time + "";
		}*/
	}
	
	public static class coordinate {
		private int x;
		private int y;
		
		public coordinate(int x, int y) {
			this.x = x;
			this.y = y;
		}
		/*
		public String toString() {
			return x + " : " + y;
		}*/
	}
	
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 보드의 크기
		int[][] arr = new int[N][N]; // 처음 0으로 초기화
		int K = Integer.parseInt(br.readLine()); // 사과의 개수
		StringTokenizer st;
		for(int i=0; i<K; i++) { // 사과 좌표 표시
			st = new StringTokenizer(br.readLine());
			arr[Integer.parseInt(st.nextToken())-1][Integer.parseInt(st.nextToken())-1] = 1;
		}
		int L = Integer.parseInt(br.readLine()); // 뱀의 방향 변환 횟수 L
		
		ArrayList<type> arrDirection = new ArrayList<>();
		for(int i=0; i<L; i++) {
			st = new StringTokenizer(br.readLine());
			arrDirection.add(new type(Integer.parseInt(st.nextToken()), st.nextToken()));
		}
		
		int[][] direction = {{0,1},{1,0},{0,-1},{-1,0}}; // →(우) ↓(하) ←(좌) ↑(상)
		
		snake.add(new coordinate(1,1)); // 처음 뱀의 길이 (1,1) 부터 시작
		int[][] init = new int[1][2];
		int init_direction = 0;
		init[0][0] = 0;
		init[0][1] = 0;
		
		int count = 0; //초
		int i = 0; // arrDirection 인덱스
		
		while(true) {
			count++; // 초
			switch(init_direction) {
				case 0: init[0][0] += direction[init_direction][0];
						init[0][1] += direction[init_direction][1];
						check(N, init[0][0],init[0][1], count);
						inputQueue(arr, init[0][0], init[0][1]);
						/*
						if(arr[init[0][0]][init[0][1]]==1) {
							snake.add(new coordinate(init[0][0], init[0][1])); // 머리이동
						} else {
							snake.add(new coordinate(init[0][0], init[0][1])); // 머리이동
							snake.remove(); // 꼬리 삭제
						}*/
						break;
				case 1: init[0][0] += direction[init_direction][0];
						init[0][1] += direction[init_direction][1];
						check(N, init[0][0],init[0][1], count);
						inputQueue(arr, init[0][0], init[0][1]);
						break;
				case 2: init[0][0] += direction[init_direction][0];
						init[0][1] += direction[init_direction][1];
						check(N, init[0][0],init[0][1], count);
						inputQueue(arr, init[0][0], init[0][1]);
						break;
				case 3: init[0][0] += direction[init_direction][0];
						init[0][1] += direction[init_direction][1];
						check(N, init[0][0],init[0][1], count);
						inputQueue(arr, init[0][0], init[0][1]);
						break;
				default: break;
			}
			if(i < arrDirection.size()) {
				if(count == arrDirection.get(i).time) {
					if(arrDirection.get(i).direction.equals("L")) {
						init_direction = (init_direction+3) % 4;
					} else {
						init_direction = (init_direction+1) % 4;
					}
					i++;
				}
			}
		}
		
	}
	
	public static void inputQueue(int[][] arr, int a, int b) {
		if(arr[a][b]==1) {
			snake.add(new coordinate(a, b)); // 머리이동
			arr[a][b]=0; //사과없애주기
		} else {
			snake.add(new coordinate(a, b)); // 머리이동
			snake.remove(); // 꼬리 삭제
		}
	}
	
	public static void check(int N, int i, int j, int count) {
		if(i<0 || j<0 || i>=N || j>=N) {
			System.out.print(count);
			System.exit(0);
		}

		for(coordinate idx: snake) {
			if(idx.x == i && idx.y == j) {
				System.out.print(count);
				System.exit(0);
			}
		}
	}
}
