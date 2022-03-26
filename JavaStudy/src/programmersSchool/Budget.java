// ¿¹»ê (ÇÁ·Î±×·¡¸Ó½º ½ºÄð Ä¿¹Â·¯´× 3±â)

package programmersSchool;

import java.util.*;

public class Budget {
	public static void main(String args[]) {
		int[] d = {1, 2, 3, 3, 5};
		System.out.println(solution(d, 9));
	}

    public static int solution(int[] d, int budget) {
        int answer = 0;
        int sum = 0;

        Arrays.sort(d);
        for(int i=0; i<d.length; i++) {
            if(sum+d[i] <= budget) {
                sum += d[i];
                answer++;
            }
        }

        return answer;
    }
}
