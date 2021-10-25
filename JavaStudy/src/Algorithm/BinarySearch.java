// ���� Ž��

/* [ �˰��� ]
 
 * �迭�� ����
 * ���ĵ� �迭���� mid�� ���� (mid = ���� �� �ε��� /2)
 * mid �ε����� �迭���� ã���� �ϴ� target ���� ��
 * mid ������ target�� ũ�� left�� mid+1�� �ΰ� ������ ������ ��
 * mid ������ target�� ������ right�� mid-1�� �ΰ� ���� ������ ��ȯ
 * target�� ���� ������ �ش� ���� �ݺ�
 * target�� ���ٸ� None�� ��ȯ
*/


package Algorithm;

public class BinarySearch {
	public static void main(String args[]) {
		int[] arr = {1,4,7,9,11,17};
		System.out.println(binarySearch(arr, 4));
		System.out.println(binarySearch2(arr, 4, 0, arr.length-1));
	}
	
	// ver1: for������ ����
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
	
	// ver2: ��ͷ� ����
	public static int binarySearch2(int arr[], int target, int left, int right) {
		if(left > right) return -1;
		
		int mid = (left+right) /2;
		if(arr[mid] == target) return mid;
		if(target > arr[mid]) return binarySearch2(arr, target, mid+1, right);
		else return binarySearch2(arr, target, left, mid-1);
	}
}
