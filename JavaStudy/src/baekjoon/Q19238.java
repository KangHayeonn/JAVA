// ��ŸƮ �ý� (���� 19238��)

/* [ �˰��� ]
 * 
 * 1. �ʱ� ���� ��ĭ�� map�� �޾���
 * 2. �ý��� ��ġ�� �� �°��� ����� �� �������� ��ġ�� �޾���
 * 3. �ý��� ��ġ�� �°��� ����� ��ġ�� ���̸� ã���ְ� ���� �ּڰ��� ������� �̾��� - PriorityQueue (BFS)
 * 4. ��������� �Ҹ�� ���� ����
 * 5. 4������ ���� ���� ������������� ���������� ���� ����� ��� ���� (BFS)
 * 6. ���������� �Ҹ�� ���� ����
 * 7. ���������� �Ҹ�� ������ 2�� ����
 * 
 * ���� - 3���� 5���� ��� : �ش� ������(����� or ������)�� �� �� ���� ���� ���ᰡ ������ ���� -1�� ��½�����
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
	static type taxi; // �ý���ġ
	static passenger list[]; // �°��� ����� & ������
	static boolean check[][]; // �ش� ���� �湮�ߴ��� üũ�ϴ� �κ�
	static int[] dx = {0, 0, -1, 1}; // �� �� �� ��
	static int[] dy = {1, -1, 0, 0}; // �� �� �� ��
	
	static int[][] map;
	static int N; // N*N ũ���� ��
	static int M; // M���� �°��� �¿�� ���� ��ǥ
	static int fuel; // ����
	
	public static class type {
		int column; //��
		int row; // ��
		public type(int column, int row) {
			this.column = column;
			this.row = row;
		}
	}
	public static class passenger implements Comparable<passenger>{
		int dist; // �ִܰ��
		int startCol; // ����� ��
		int startRow; // ����� ��
		int endCol; // ������ ��
		int endRow; // ������ ��
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
					return this.startRow - p.startRow; // �� ��ȣ�� ���� �°�
				} else return this.startCol - p.startCol; // �� ��ȣ�� ���� �°�
			} else return this.dist - p.dist; // �ִܰ�ΰ� ���� ª�� �°�
		}
	}
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // N*N ũ���� ��
		M = Integer.parseInt(st.nextToken()); // M���� �°��� �¿�� ���� ��ǥ
		fuel = Integer.parseInt(st.nextToken()); // �ʱ� ������ ��
		
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==1) map[i][j] = -1; // ���� -1�� �ٲ��� 
			}
		}
		
		st = new StringTokenizer(br.readLine());
		taxi = new type(Integer.parseInt(st.nextToken())-1,Integer.parseInt(st.nextToken())-1);
		
		list = new passenger[M+1];
		ArrayList<type> person = new ArrayList<>(); // ���� ���ľ��� �°� ��
		for(int i=1; i<M+1; i++) {
			st = new StringTokenizer(br.readLine());
			int startCol = Integer.parseInt(st.nextToken());
			int startRow = Integer.parseInt(st.nextToken());
			int endCol = Integer.parseInt(st.nextToken());
			int endRow = Integer.parseInt(st.nextToken());
			list[i] = new passenger(0, startCol-1, startRow-1, endCol-1, endRow-1);
			person.add(new type(startCol-1, startRow-1));
			map[startCol-1][startRow-1]= i; // 1������ ����(�°���ȣ)
		}
		
		while(person.size() > 0) {
			
			check = new boolean[N][N];
			type x = findStart(map, N); // ����� ����
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
			map[x.column][x.row] = 0; // (����) �¿� �°��� 0���� �ʱ�ȭ���Ѽ� �����ֱ�
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
				if(fuel < 0 ) { // ���� ���ڶ���� üũ
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
				if(fuel < 0 ) { // ���� ���ڶ���� üũ
					return -1;
				}
				taxi.column = list[num].endCol; // �ý� ��ġ �ٲ��ֱ� (��)
				taxi.row = list[num].endRow;    // �ý� ��ġ �ٲ��ֱ� (��)
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
