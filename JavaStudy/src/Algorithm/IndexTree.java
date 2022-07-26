package Algorithm;

import java.util.Arrays;

public class IndexTree {
	static int[] indexTree;
	public static void main(String args[]) {
		int[] arr = {4, 13, 11, 9, 2, 10, 5, 12, 7}; // 9개의 data 배열을 만들어보겠습니당 -> 16개의 저장소가 나와야함
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
		
		// 특정 구간의 sum을 구할 경우
		
		int sectorStart = 2, sectorEnd = 6; // 기준점 (바뀌지 않음)
		int rootStart = 0, rootEnd = tempN-1; // 케이스에 따라 변경됨
		
		int sum = getSum(sectorStart, sectorEnd, rootStart, rootEnd, 1);
		
		System.out.println(sum);
		
		// 특정 인덱스를 변경할 경우 -> ex) 4번째 인덱스를 30으로 변경
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
