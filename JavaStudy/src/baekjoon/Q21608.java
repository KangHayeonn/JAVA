// ��� �ʵ��б� (���� 21608��)

/* [ �˰��� ]
 * 
 * 1. �⺻ ���� ��� ���� ���� �̸� ����� ���� (map �� �л����� �ڸ��� ����) 
 * 2. Ư�� �л��� �ڸ� ��ġ�� �Ϸ� �ߴ��� üũ�ϴ� �迭 ����� (true/false)
 * 3. �л����� 1���� �ʿ��� �����¿츦 ���� ������� ���� �����ϴ� �л��� �ִ����� üũ (x, y, like, blank)
 * 4. 3���� �������ǿ� ���� ���� ����
 * 5. �л��� ���� ���� �ֻ��� ���ǿ� �ش��ϴ� �ڸ��� ��ġ��Ŵ
 * 6. �л� ��θ� ��ġ���Ѽ� map�� �� �� ������ 3��-5�� ������ �ݺ�
 * 7. ���� ���鼭 �� ��ǥ�� �ִ� �л� ���� �����¿쿡 �ڽ��� �����ϴ� �л��� ������� ī��Ʈ����
 * 8. 7���� �������� (0�̸� �л��� �������� 0, 1�̸� 1, 2�̸� 10, 3�̸� 100, 4�̸� 1000) ���� ���� �������� ������ ����
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
	static int[][] map; // �ڸ���ġ �⺻ ��(����)
	static int[] studentCheck; // Ư�� �л��� �ڸ� ��ġ�� �Ϸ��ߴ��� üũ
	static PriorityQueue<type> queue;
	static int[] dx = {0, 0, -1, 1}; // �� �� �� ��
	static int[] dy = {1, -1, 0, 0};
	static int N; // ������ N��N ( �л��� N*N )
	
	public static class type implements Comparable<type>{
		private int column;     // ��
		private int row;        // ��
		private int likeCount;  // ������ ���� �����ϴ� ����� �� ������ ī��Ʈ
		private int blankCount; // ������ ���� ����ִ� ĭ�� �� ĭ���� ī��Ʈ 
		
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
		Map<Integer, int[]> likeList = new HashMap<>(); // �л� - �����ϴ� ����� ��ȣ ����Ʈ
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
			likeList.put(student, students); // student : �л��� ��ȣ , students : �����ϴ� �л��� ��ȣ
		}
		
		// �ڸ� ��ġ�� �Ϸ�� �� map ���
		/*
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}*/
		
		// ������ �˻�
		for(int col=0; col<N; col++) {
			for(int row=0; row<N; row++) {
				int s = map[col][row]; // �ڸ��� ���� �л�
				int count = 0;
				
				for(int i=0; i<4; i++) { // �����¿�
					int nextCol = col + dy[i];
					int nextRow = row + dx[i];
					
					if(nextCol<0 || nextCol>=N || nextRow<0 || nextRow>=N) continue;
					
					for(Integer v : likeList.get(s)) { // �����ϴ� ��� ����Ʈ
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
				for(int i=0; i<4; i++) { // �����¿�
					int nextCol = col + dy[i];
					int nextRow = row + dx[i];
					
					if(nextCol<0 || nextCol>=N || nextRow<0 || nextRow>=N) continue;
					
					for(int j=0; j<4; j++) { // �����ϴ� ��� ����Ʈ
						if(map[nextCol][nextRow]==likeList[j]) count++;
					}
					
					if(map[nextCol][nextRow]==0) blank++;
				}
				
				queue.add(new type(col, row, count, blank));
			}
		}
		
		while(!queue.isEmpty()) {
			type x = queue.poll();
			//System.out.println("��� Ȯ�� : " + x.column + " " + x.row + " " + x.likeCount + " " +x.blankCount);
			
			if(map[x.column][x.row] == 0) {
				map[x.column][x.row] = student;
				//System.out.println("���� Ȯ�� : " + map[x.column][x.row] + " " + Arrays.toString(studentCheck));
				return;
			}
		}
	}
}
