// ��й�ȣ (���� 2780��)
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q2780 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine()); // �׽�Ʈ���̽� ����
		
		int[] list = new int[T];
		for(int i=0; i<T; i++) {
			list[i] = Integer.parseInt(br.readLine());
		}
	}
}
