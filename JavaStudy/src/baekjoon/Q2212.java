// ���� (���� 2212��)

// 1. �־����� ���� N �� ����
// 2. �� ���� �ΰ��� �Ÿ��� ����ϰ� �ִ��� ����
// 3. �ִ� ���� ������ ���߱� K�϶� K-1
// 4. �׸��� ��� ���� ���ؼ� ���

package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q2212 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int count = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		StringTokenizer N = new StringTokenizer(br.readLine(), " ");
		int arr[] = new int[count];
		
		for(int i=0; i<count; i++) {
			arr[i] = Integer.parseInt(N.nextToken());
		}
		
		Arrays.sort(arr);
		
		int distance[] = new int[count-1];
		for(int i=0; i<count-1; i++) {
			distance[i] = arr[i+1] - arr[i];
		}
		
		Arrays.sort(distance);
		
		int sum = 0;
		
		for(int i=0; i <= distance.length-K ; i++) {
			sum += distance[i];
		}
		
		//System.out.println(Arrays.toString(distance));
		System.out.print(sum);
	}
}
