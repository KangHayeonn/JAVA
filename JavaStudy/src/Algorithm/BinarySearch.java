// 이진 탐색

/* [ 알고리즘 ]
 
 * 배열을 정렬
 * 정렬된 배열에서 mid를 구함 (mid = 양쪽 끝 인덱스 /2)
 * mid 인덱스와 배열에서 찾고자 하는 target 값을 비교
 * mid 값보다 target이 크면 left를 mid+1로 두고 오른쪽 구간을 비교
 * mid 값보다 target이 작으면 right를 mid-1로 두고 왼쪽 구간을 반환
 * target이 나올 때까지 해당 과정 반복
 * target이 없다면 None을 반환
*/


package Algorithm;

public class BinarySearch {
	public static void main(String args[]) {
		int[] arr = {1,4,7,9,11,17};
		System.out.println(binarySearch(arr, 4));
		System.out.println(binarySearch2(arr, 4, 0, arr.length-1));
	}
	
	// ver1: for문으로 구현
	public static int binarySearch(int arr[], int target) {
		 int left = 0;
		 int right = arr.length-1;
	
		 while(left<=right) {
			 int mid = (left+right) /2;
			 
			 if(arr[mid]==target) return mid;
			 if(target > arr[mid]) left = mid+1;
			 else right = mid-1;
		 }
		 
		 return -1;
	}
	
	// ver2: 재귀로 구현
	public static int binarySearch2(int arr[], int target, int left, int right) {
		if(left > right) return -1;
		
		int mid = (left+right) /2;
		if(arr[mid] == target) return mid;
		if(target > arr[mid]) return binarySearch2(arr, target, mid+1, right);
		else return binarySearch2(arr, target, left, mid-1);
	}
}
