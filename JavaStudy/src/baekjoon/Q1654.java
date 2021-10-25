// ���� �ڸ��� (���� 1654��)

// ������������ ����
// ���� ū���� �̺�Ž�� -> mid : �ش� ��
// ���� �ش� ���� ������ ���ҵ鿡 ������ �� ���� ���� N���� ������ �ѹ��� �̺�Ž��
// ���� �ش� ���� ������ ���ҵ鿡 ������ �� ���� ���� N���� Ŭ ��� �ش� ���� +1
// ���� N���� �������� ��찡 ����� �� ���� �ش� ���� ���

package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q1654 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int K = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		Integer[] arr = new Integer[K];
		
		for(int i=0; i<K; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(arr, (a,b) -> b-a); // �������� (Comparator�� ���ϰ� ���� �� int ��� Integer ����ؾ���)
		
		int answer = cutting(arr, K, N);
		
		System.out.print(answer);
		//System.out.println(Arrays.toString(arr));
	}
	
	public static int cutting(Integer[] arr, int K, int N) {
		int left = 0;
		int right = arr[0];
		boolean check = false;
		
		if(N==K) return arr[K-1];
		
		int mid = (left + right) /2;
		
		while(left <= right) {
			int sum = 0;
			for(int i=0; i<K; i++) {
				sum += arr[i] / mid;
			}
			
			if(sum < N) {
				if(check == true) return mid - 1;
				mid = (left + mid) / 2;
				continue;
			}
			else if(sum > N) {
				mid = mid + 1;
				check = true;
				continue;
			}
			else return mid;
		}
		
		return mid; 
	}
}
