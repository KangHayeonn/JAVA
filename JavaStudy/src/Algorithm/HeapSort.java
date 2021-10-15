package Algorithm;

import java.util.Arrays;

public class HeapSort {
	public static void main(String args[]) {
		int[] arr = {0, 5, 8, 4, 7, 10, 9, 2, 1, 6, 3}; 
		// 0�� �־��� ������ ��� ������ ���ϰ� �ϱ� ����
		/*
		int i = arr.length/2;
		while(i > 0) {
			maxHeap(arr, arr.length, i);
			i--;
		}
		System.out.println(Arrays.toString(arr));
		//����
		for(int j = arr.length-1; j>0; j--) {
			swap(arr, j, 1);  // ��Ʈ ���� ���ϴ� ��� ���� ��ȯ
			System.out.println("SWAP ��: " + Arrays.toString(arr));
			maxHeap(arr, i-1, 1); // ��Ʈ ��带 �������� �ִ� ���� ����
			System.out.println("MAXheAP �� : " +Arrays.toString(arr));
		}*/
		int node = 1; // �ʱ� ��Ʈ��� �ε��� ����
		maxHeap(arr, arr.length, 11);
		
		System.out.println(Arrays.toString(arr));
	}
	
	public static void maxHeap(int[] arr, int length, int value) {
		/*
		int compareNode =0;
		
		while((node*2) < length) {
			int min = arr[node*2]; // �����ڽ�
			int minNode = node*2; // �����ڽ� ���
			
			if((node*2+1) < length && min < arr[node*2+1]) {
				min = arr[node*2+1];
				minNode = node*2+1;
			}
			
			if(min < arr[node]) break; // �θ� �¿� �ڽĺ��� ũ�� break;
			System.out.println(node + " " + minNode);
			swap(arr, node, minNode);
			System.out.println(node + " " + minNode);
			compareNode = minNode;
		}
		
		if(node != length/2) {
			maxHeap(arr, length, compareNode);
		}*/
		int p = arr.length-1; // p=1�̸� ��Ʈ���
		while(p > 1 && arr[p/2] > arr[p]) {
			System.out.println("������?");
			int temp = arr[p/2]; // �θ� ����� ��
			arr[p/2] = value;
			arr[p] =temp;
			p /= 2;
		}
		System.out.println(p);
	}
	
	public static void swap(int[] arr, int a, int b) {
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}
}
