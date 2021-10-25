// �� ���� �� (���� 3273��)

// ������ �ε��� ���� left, right �� �ΰ� 
// ���� �������� �ش� ���� left--, right++�ؼ� ����� ���� ī��Ʈ����
// �˰��� : ��������

package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q3273 {
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int target = Integer.parseInt(br.readLine());
		
		Arrays.sort(arr);
		//System.out.println(Arrays.toString(arr));
		int left = 0;
		int right = arr.length-1;
		int count = 0; // �����ϴ� ���� ��
		
		while(left < right) {
			int sum = arr[left] + arr[right];
			//System.out.println(arr[left]+" : " + arr[right] + " -> " + sum);
			if(sum == target) count++;
			
			if(sum >= target) right--;
			else left++;
		}
		
		System.out.print(count);
	}
}
