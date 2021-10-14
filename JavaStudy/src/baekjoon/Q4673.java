// ���� �ѹ� (���� 4673��)

// �����ڰ� �ִ� ���ڸ� ���� ���ϰ� (���� �ѹ��� �ƴ� ��)
// 10000 ���� �ش� ���� ������ ���� ��� -> ���� �ѹ�
package baekjoon;

import java.util.Arrays;

public class Q4673 {
	public static void main(String args[]) {
		int arr[] = new int[10001];
		Arrays.fill(arr,  0);
		
		for(int i=1; i<10; i++) {
			int x = i+ i / 1;
			arr[x] = 1;
		}
		
		for(int i=10; i<100; i++) {
			int x = i + i/10 + i%10;
			arr[x] = 1;
		}
		
		for(int i=100; i<1000; i++) {
			int x = i + i/100 + (i%100)/10 + (i%100)%10;
			arr[x] = 1;
		}
		
		for(int i=1000; i<10000; i++) {
			int x = i + i/1000 + (i%1000)/100 + ((i%1000)%100)/10 + ((i%1000)%100)%10;
			if(x <= 10000) arr[x] = 1;
		}
		
		for(int idx=1; idx<arr.length; idx++) {
			if(arr[idx]==0) System.out.println(idx);
		}
		
	}
}
