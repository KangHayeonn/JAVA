// �� (3190��)

/* [ �˰��� ]
 * - �ش� X�ʱ��� �����̰� ������ȯ �� X+1�� �� �ش� �������� �̵�
 * - ���� ���̴� ť�� ��ǥ ������ ����
 * 
 * Snake(��) ó�� ���� 1,
 * �ʸ� �������� for���� ���� (���ѷ��� -> ���� �����ų� ���� �ε����� break)
 * ����� ������ ��ǥ�� �߰��� -> queue.add()�� �� / queue.poll()||queue.remove()�� ���� ����
 * ����� ������ ���Լ��� + �߰� -> queue.add() + queue.poll()||queue.remove()
 * ���� ������ȯ�� �ִ� �ʸ� ���� ��� �ش� �ʱ��� �����̰� ���� ��ȯ�� ���� �ʿ� �ش� �������� ����
 * 
 * ���� ǥ�� (�����¿�)
 * �� ��    /   �� ��   /   �� ��  /   �� ��
 * dx:0   /  dx:1   /  dx:0   /  dx:-1
 * dy:1   /  dy:0   /  dy:-1  /  dy:0
 * idx[0] /  idx[1] /  idx[2] /  idx[3]
 * 
 * �������� ������ȯ (L)  : (�����ε��� + 3) % 4
 * ���������� ������ȯ (D) : (�����ε��� + 1) % 4
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
	
	static Queue<coordinate> snake = new LinkedList<>(); // �� ����
	
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
		int N = Integer.parseInt(br.readLine()); // ������ ũ��
		int[][] arr = new int[N][N]; // ó�� 0���� �ʱ�ȭ
		int K = Integer.parseInt(br.readLine()); // ����� ����
		StringTokenizer st;
		for(int i=0; i<K; i++) { // ��� ��ǥ ǥ��
			st = new StringTokenizer(br.readLine());
			arr[Integer.parseInt(st.nextToken())-1][Integer.parseInt(st.nextToken())-1] = 1;
		}
		int L = Integer.parseInt(br.readLine()); // ���� ���� ��ȯ Ƚ�� L
		
		ArrayList<type> arrDirection = new ArrayList<>();
		for(int i=0; i<L; i++) {
			st = new StringTokenizer(br.readLine());
			arrDirection.add(new type(Integer.parseInt(st.nextToken()), st.nextToken()));
		}
		
		int[][] direction = {{0,1},{1,0},{0,-1},{-1,0}}; // ��(��) ��(��) ��(��) ��(��)
		
		snake.add(new coordinate(1,1)); // ó�� ���� ���� (1,1) ���� ����
		int[][] init = new int[1][2];
		int init_direction = 0;
		init[0][0] = 0;
		init[0][1] = 0;
		
		int count = 0; //��
		int i = 0; // arrDirection �ε���
		
		while(true) {
			count++; // ��
			switch(init_direction) {
				case 0: init[0][0] += direction[init_direction][0];
						init[0][1] += direction[init_direction][1];
						check(N, init[0][0],init[0][1], count);
						inputQueue(arr, init[0][0], init[0][1]);
						/*
						if(arr[init[0][0]][init[0][1]]==1) {
							snake.add(new coordinate(init[0][0], init[0][1])); // �Ӹ��̵�
						} else {
							snake.add(new coordinate(init[0][0], init[0][1])); // �Ӹ��̵�
							snake.remove(); // ���� ����
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
			snake.add(new coordinate(a, b)); // �Ӹ��̵�
			arr[a][b]=0; //��������ֱ�
		} else {
			snake.add(new coordinate(a, b)); // �Ӹ��̵�
			snake.remove(); // ���� ����
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
