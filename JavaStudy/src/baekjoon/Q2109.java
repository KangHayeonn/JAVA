// ��ȸ ���� (���� 2109��)

// �ܼ��ϰ� ������� Ž���ؼ� �ڸ��� ��ġ ������ ( ������ ����Ž���̶� �ð��� �����ɸ�)

package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Q2109 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine()); // n ���� ���п��� ���� ��û
		StringTokenizer st = new StringTokenizer("");
		int[][] list = new int[n][2];
		int Max = 0;
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken()); // ������
			int d = Integer.parseInt(st.nextToken()); // ����
			list[i][0] = p;
			list[i][1] = d;
			Max = Math.max(Max, list[i][1]);
		}
		
		Arrays.sort(list, new Comparator<int[]>() {
			@Override
			public int compare(int[] x, int[] y) {
				if(x[0]==y[0]) return y[1]-x[1];
				else return y[0]-x[0]; // �����ῡ ���� ��������
			}
		});
		
		boolean[] isVisited = new boolean[Max];
		int answer = 0;
		
		for(int i=0; i<list.length; i++) {
			int len = list[i][1]-1;
			for(int j=len; j>=0; j--) {
				if(!isVisited[j]) {
					isVisited[j] = true;
					answer += list[i][0];
					break;
				}
			}
		}
		
		System.out.print(answer);
	}
}
