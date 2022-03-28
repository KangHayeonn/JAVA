// 예산 (Step1-3 재업로드)

// 문제유형 : BinarySearch

package programmersSchool;

import java.util.Arrays;

public class Budget2 {
	public static void main(String args[]) {
		int[] budgets = {120, 110, 140, 150};
		System.out.println(solution(budgets, 485));
	}
	public static int solution(int[] budgets, int M) {
        int answer = 0;
        
        Arrays.sort(budgets);

        int mid = 0;
        int left = budgets[0];
        int right = budgets[budgets.length-1];

        while(left <= right) {
            mid = (left + right) / 2;
            int sum = 0;

            for(int i=0; i<budgets.length; i++) {
                int budget = budgets[i];

                if(budget <= mid) {
                    sum += budget;
                } else {
                    sum += mid;
                }
            }

            if(sum <= M) {
                left = mid+1;
            } else {
                right = mid-1;
            }
        }

        answer = right;

        if(answer < budgets[0]) {
            answer = M/budgets.length;
        }

        return answer;
    }
}
