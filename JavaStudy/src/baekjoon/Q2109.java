// ��ȸ ���� (���� 2109��)

// �ּ����� �̿��� ��¥�� ū�ſ��� ���� ������ �ּҰ��� üũ���ָ鼭 Ȯ��
// ���� �ִ� �ϼ��� 2�� ��� ��ũ��� 2
// �̶� �ּҰ����� ������ ���� Ŭ��� �ּҰ��� �������ְ� �ش� ���� �߰�
// �������� ���ȿ� �ִ� ��� ���� ������

package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Q2109 {
	public static class type implements Comparable<type>{
		int pay;
		int date;
		public type(int pay, int date) {
			this.pay = pay;
			this.date = date;
		}
		@Override
		public int compareTo(type o1) {
			return o1.pay - this.pay;
		}
	}
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine()); // n ���� ���п��� ���� ��û
		StringTokenizer st = new StringTokenizer("");
		int[][] list = new int[n][2];
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken()); // ������
			int d = Integer.parseInt(st.nextToken()); // ����
			list[i][0] = p;
			list[i][1] = d;
		}
		
		Arrays.sort(list, new Comparator<int[]>() {
			@Override
			public int compare(int[] x, int[] y) {
				if(x[1]==y[1]) return y[0]-x[0];
				else return y[1]-x[1]; // ���ѿ� ���� ��������
			}
		});
		
		PriorityQueue<type> pQ = new PriorityQueue<>();
		int answer = 0; // ���� ���Ƿ�
		
		int size = list[0][1]; // �ִ� �ϼ�
		
		for(int i= size; i>0; i--) {
			System.out.println(i);
			for(int j=0; j<list.length && list[j][1] == i; j++) {
				System.out.println("tetst " + j);
				pQ.add(new type(list[j][0], list[j][1]));
				System.out.println("������?" + list[j][0]);
			}
		}
		while(!pQ.isEmpty()) {
			type p = pQ.remove();
			
			System.out.println(p.pay + " " +p.date);
		}
		//System.out.println(answer);
	}
}
