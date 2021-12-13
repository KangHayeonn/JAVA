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
			//if(this.date == o1.date) return this.pay - o1.pay;
			//else return this.date - o1.date;  // ��¥�� �������� �ּ� ��
			return this.pay - o1.pay;
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
		
		//System.out.println(Arrays.deepToString(list));
		PriorityQueue<type> pQ = new PriorityQueue<>();
		int answer = 0; // ���� ���Ƿ�
		
		int day = list[0][1]; // ���� �ϼ�
		
		for(int i=0; i<list.length; i++) {
			if(day > 0) { // �� �ڸ��� �ִ��� Ȯ��
				pQ.add(new type(list[i][0], list[i][1]));
				day = Math.min(list[i][1] -1, day-1); // 2
				type x = pQ.peek();
				System.out.println("������ �� üũ " + i + "��° -> " + x.pay +" " + x.date +" date :: " + day);
			} else {
				type np = pQ.peek();
				while(np.date > list[i][1]){// 5 50 10 100 
					answer += np.pay;
				}
				System.out.println(i+ " ���� -> " + day +" np: "+ np);
				if(np.pay < list[i][0]) {
					pQ.poll();
					pQ.add(new type(list[i][0], list[i][1]));
					day = Math.min(list[i][1] -1, day-1);
				} else continue;
			}
			
		}
		
		while(!pQ.isEmpty()) {
			type x = pQ.poll();
			//System.out.println(x.pay +" " + x.date);
			answer += x.pay;
		}
		System.out.print(answer);
	}
}
