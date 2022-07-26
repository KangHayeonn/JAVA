package Algorithm;

import java.util.Arrays;

public class IndexTree {
	static int[] indexTree;
	public static void main(String args[]) {
		int[] arr = {4, 13, 11, 9, 2, 10, 5, 12, 7}; // 9���� data �迭�� �����ڽ��ϴ� -> 16���� ����Ұ� ���;���
		int tempN = getTempN(arr.length);
		indexTree = new int[tempN*2];
		
		for(int i=0; i<arr.length; i++) {
			indexTree[tempN + i] = arr[i];
			
			// init
			int tempIdx = tempN + i;
			while(tempIdx > 1) {
				tempIdx /= 2;
				indexTree[tempIdx] += arr[i];
			}
		}
		System.out.println(Arrays.toString(indexTree));
		
		// Ư�� ������ sum�� ���� ���
		
		int sectorStart = 2, sectorEnd = 6; // ������ (�ٲ��� ����)
		int rootStart = 0, rootEnd = tempN-1; // ���̽��� ���� �����
		
		int sum = getSum(sectorStart, sectorEnd, rootStart, rootEnd, 1);
		
		System.out.println(sum);
		
		// Ư�� �ε����� ������ ��� -> ex) 4��° �ε����� 30���� ����
		getDiff(4, 30, tempN, arr);
		
		System.out.println(Arrays.toString(indexTree));
	}
	private static int getTempN(int length) {
		int idx = 1;
		while(idx < length) {
			idx *= 2;
		}
		return idx;
	}
	private static int getSum(int sectorStart, int sectorEnd, int rootStart, int rootEnd, int currIdx) {
		/**
		 * case 1)
		 *                rootStart ---------- rootEnd 1-2
		 *     sectorStart ----------------------------------- sectorEnd 0-2
		 *     
		 * case 2)
		 *                                   rootStart ---------- rootEnd 1-2
		 *     sectorStart ----- sectorEnd 0-1                               sectorStart ----- sectorEnd 3-4
		 *     
		 * case 3)
		 * 
		 *     rootStart ---------------------------------------- rootEnd 0-3
		 *                 sectorStart ------------- sectorEnd 1-2                           
		 *                                   |
		 *                      (rootStart + rootEnd) / 2
		 */
		
		if(sectorStart <= rootStart && sectorEnd >= rootEnd) return indexTree[currIdx];
		else if(sectorEnd < rootStart || sectorStart > rootEnd) return 0;
		else {
			int mid = (rootStart + rootEnd) / 2;
			int leftSum = getSum(sectorStart, sectorEnd, rootStart, mid, currIdx * 2);
			int rightSum = getSum(sectorStart, sectorEnd, mid+1, rootEnd, currIdx * 2 + 1);
			return leftSum + rightSum;
		}
	}
	private static void getDiff(int idx, int value, int tempN, int[] arr) {
		int diff = (value - arr[idx]);
		int tempIdx = tempN + idx;
		arr[4] = 30;
		
		while(tempIdx > 0) {
			indexTree[tempIdx] += diff;
			tempIdx /= 2;
		}
	}
}
