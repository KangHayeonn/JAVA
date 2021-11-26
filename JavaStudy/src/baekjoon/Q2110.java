// ������ ��ġ (���� 2110��)

/* [ �˰��� ]
 * 
 * 1. ���̸� �������� �̺�Ž�� (mid = (�ִ���� + �ּұ���) / 2)
 * 2. �� ���̿� �ش��ϴ� ���� count ���� �� 
 * 3. ���� �̾ƾ��� ���� ���� ũ�� ������ �����ְ� (left = mid+1)
 * 4. �̾ƾ��� ���� ���� ������ ������ �ٿ��� (right = mid-1)
 * 5. ���̻� Ž���� ���̰� ������ ������ ���̸� return
 * 
 */
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q2110 {
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // ���� ����
		int C = Integer.parseInt(st.nextToken()); // �������� ����
		int[] arr = new int[N];
		
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(arr);
		
		int left = 1; // �ּ� ����
		int right = arr[arr.length-1] - arr[0];
		
		BinarySearch(arr, left, right, C);
	}
	
	public static void BinarySearch(int[] arr, int left, int right, int C) {
		int answer = 0;
		//int count = 0; // ����� ������ ������ �ȵǼ� ����
		
		while(left<=right) {
			int mid = (left+right)/2; // ���� ����
			int start = arr[0];
			int count = 1; // ó�� �� ī��Ʈ
			
			for(int i=0; i<arr.length; i++) {
				int dis = arr[i] - start;
				if(dis >= mid) { // ���� ������ ���� ���� �������� �� ���� 
					start = arr[i];
					count++;
				}
			}
			
			if(count<C) {
				right = mid-1;
			} else {
				answer = Math.max(answer, mid);
				left = mid+1;
			}
		}
		
		System.out.print(answer);
		return;
	}
}
