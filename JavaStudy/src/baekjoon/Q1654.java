// ���� �ڸ��� (���� 1654��)

// ���� ū���� ���� ���� ���� �������� �̺�Ž�� -> mid : (���� ū�� + ���� ���� ��) / 2
// ���� �ش� ���� ������ ���ҵ鿡 ������ �� ���� ���� N���� ������ �ִ��� �ٿ���
// ���� �ش� ���� ������ ���ҵ鿡 ������ �� ���� ���� N���� Ŭ ��� �ּڰ��� �÷���
// ���� N���� �������� ��찡 ����� �� ���� �ش� ���� ���

package baekjoon;
/*
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1654 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int K = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		Integer[] arr = new Integer[K];
		
		long max = 1;
		
		for(int i=0; i<K; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			if(arr[i] > max ) max = arr[i];
		}
		
		long min = 1;
		long mid = 0;
		int count = 0;
		
		* ����� �� 
		 * 
		 * case 1 : while(min <= max)  ->  else min = mid +1   -> ��� max   (����)
		 * case 2 : while(min < max)   ->  else min = mid      -> ��� min   (����)
		 * case 3 : while(min < max)   ->  else min = mid+1    -> ��� min-1 (����)
		 *
		
		while(min <= max) {   // �� < �̰� Ʋ���� <= �̰� �´� �ɱ�? (�ش� ���� ����)
			mid = (min+max)/2;
			
			count = 0;
			
			for(int i=0; i<K; i++) {
				count += arr[i]/mid;
			}
			
			if(count < N) max = mid-1;
			else min = mid+1;
			
		}
		System.out.print(max);
		//System.out.println(Arrays.toString(arr));
		
	}
	
	 �߸��� �ڵ�
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
}*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1654 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int K = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		Integer[] arr = new Integer[K];
		
		long max = 1;
		
		for(int i=0; i<K; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			if(arr[i] > max ) max = arr[i];
		}
		
		max++; // ���������� ������ �� �����Ƿ�
		
		long min = 1;
		long mid = 0;
		int count = 0;
        long answer = 1;
		
		while(min < max) {
			mid = (min+max)/2;
			
			count = 0;
			
			for(int i=0; i<K; i++) {
				count += arr[i]/mid;
			}
			
			if(count < N) max = mid;
			else {
                min = mid+1;
                answer = mid;
            }
		}
		System.out.print(answer);
	}
}

