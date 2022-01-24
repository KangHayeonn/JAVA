// ġŲ TOP N (���� 11582��)

/* [ �˰��� ]
 * 
 * 1. �Էµ� ġŲ���� ���� N���� /2�� �ϸ鼭 k���� ������ ������ ������ �ݺ�
 * 
 * - ����: �պ�����
 */
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q11582 {
	static int N, k;
	static int[] sorted;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] list = new int[N];
		sorted = new int[N];
		
		for(int i=0; i<N; i++) {
			list[i] = Integer.parseInt(st.nextToken());
		}
		
		k = Integer.parseInt(br.readLine());
		//System.out.println(Arrays.toString(list));
		merge_sort(list, 0, N-1);
		for(int i=0; i<list.length; i++) {
			System.out.print(list[i]+" ");
		}
		//System.out.println(Arrays.toString(list));
		
	}
	
	// Top-Down ���
	public static void merge_sort(int[] list, int left, int right) {
		if(left == right) return;
		
		int mid = (left+right)/2;
		
		// ���� ����
		merge_sort(list, left, mid);
		merge_sort(list, mid+1, right);
		
		merge(list, left, mid, right);
	}
	
	// Buttom-Up ���
	public static void mergeSort(int[] list, int left, int right) {
		/*
		 * 1 - 2 - 4 - 8 ... : 1���� ���긮��Ʈ�� ������ ������ �� �辿 �ø�
		 */
		for( int size = 1; size <= right; size += size) {
			for(int l=0; l<=right-size; l+=(2*size)) {
				int low = l;
				int mid = l+size-1;
				int high = Math.min(l+ (2*size)-1, right);
				merge(list, low, mid, high);
			}
			//System.out.println(Arrays.toString(list));
			//System.out.println("N, K : " + N +" " +k + " -> " +size);
		}
	}
	
	// ���� ����
	public static void merge(int[] list, int left, int mid, int right) {
		// right-left : ���� �������� ����Ʈ ũ��
		if(right - left > N / k) {
			return;
		}
		int l = left;
		int r = mid+1;
		int idx = left;
		
		while(l <= mid && r <= right) {
			
			if(list[l] <= list[r]) {
				sorted[idx] = list[l];
				idx++;
				l++;
			} else {
				sorted[idx] = list[r];
				idx++;
				r++;
			}
		}
		// ���� �κи���Ʈ�� ���� �� �迭�� ��� ä������ ��� (������ �κи���Ʈ�� ���� ��������)
		if(l > mid) {
			while(r <= right) {
				sorted[idx] = list[r];
				idx++;
				r++;
			}
		} 
		// ������ �κи���Ʈ�� ���� �� �迭�� ��� ä������ ��� (���� �κи���Ʈ�� ���� ��������)
		else {
			while(l <= mid) {
				sorted[idx] = list[l];
				idx++;
				l++;
			}
		}
		for(int i=left; i<=right; i++) {
			list[i] = sorted[i];
		}
	}
}
