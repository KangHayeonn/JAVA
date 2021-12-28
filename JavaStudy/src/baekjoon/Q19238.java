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
import java.util.StringTokenizer;

public class Q19238 {
	public static class taxi {
		int column; //��
		int row; // ��
		public taxi(int column, int row) {
			this.column = column;
			this.row = row;
		}
	}
	public static class passenger {
		int startCol; //��
		int startRow; // ��
		int endCol; //��
		int endRow; // ��
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
		int N = Integer.parseInt(st.nextToken()); // N*N ũ���� ��
		int M = Integer.parseInt(st.nextToken()); // M���� �°��� �¿�� ���� ��ǥ
		int fuel = Integer.parseInt(st.nextToken()); // �ʱ� ������ ��
		
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
		System.out.println("���� : " + fuel);
		for(int i=0; i<M; i++) {
			System.out.println(list[i].startCol +" " + list[i].startRow + " -> " + list[i].endCol +" " + list[i].endRow);
		}
	}
}
