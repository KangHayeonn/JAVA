package Algorithm;

import java.util.Arrays;

public class HeapSort {
	public static void main(String args[]) {
		int[] arr = {0, 5, 8, 4, 7, 10, 9, 2, 1, 6, 3}; 
		// 0을 넣어준 이유는 노드 구현을 편하게 하기 위해
		/*
		int i = arr.length/2;
		while(i > 0) {
			maxHeap(arr, arr.length, i);
			i--;
		}
		System.out.println(Arrays.toString(arr));
		//정렬
		for(int j = arr.length-1; j>0; j--) {
			swap(arr, j, 1);  // 루트 노드와 최하단 노드 값을 교환
			System.out.println("SWAP 후: " + Arrays.toString(arr));
			maxHeap(arr, i-1, 1); // 루트 노드를 기준으로 최대 힙을 만듦
			System.out.println("MAXheAP 후 : " +Arrays.toString(arr));
		}*/
		int node = 1; // 초기 루트노드 인덱스 정보
		maxHeap(arr, arr.length, 11);
		
		System.out.println(Arrays.toString(arr));
	}
	
	public static void maxHeap(int[] arr, int length, int value) {
		/*
		int compareNode =0;
		
		while((node*2) < length) {
			int min = arr[node*2]; // 왼쪽자식
			int minNode = node*2; // 왼쪽자식 노드
			
			if((node*2+1) < length && min < arr[node*2+1]) {
				min = arr[node*2+1];
				minNode = node*2+1;
			}
			
			if(min < arr[node]) break; // 부모가 좌우 자식보다 크면 break;
			System.out.println(node + " " + minNode);
			swap(arr, node, minNode);
			System.out.println(node + " " + minNode);
			compareNode = minNode;
		}
		
		if(node != length/2) {
			maxHeap(arr, length, compareNode);
		}*/
		int p = arr.length-1; // p=1이면 루트노드
		while(p > 1 && arr[p/2] > arr[p]) {
			System.out.println("들어오나?");
			int temp = arr[p/2]; // 부모 노드의 값
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
