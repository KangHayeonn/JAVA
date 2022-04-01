// 기지국 설치 (Step1-1)

package programmersSchool;

import java.util.LinkedList;
import java.util.Queue;

// ver1 : 시간초과

/*
public class BashStation {
	public static void main(String args[]) {
		int[] stations = {4, 11};
		
		System.out.println(solution(11, stations, 1));
	}
	public static int solution(int n, int[] stations, int w) {
		int answer = 0;

        boolean[] arr = new boolean[n];
        for(int i=0; i<stations.length; i++) {
            int num = stations[i]-1;
            arr[num] = true;

            // 시간초과 발생
            for(int j=1; j<=w; j++) {
                if(num-j>=0) arr[num-j] = true;
                if(num+j<n) arr[num+j] = true;
            }
        }

        Queue<Integer> q = new LinkedList<>();
        boolean check = false; // false 일 때 (true 만나면 : true)
        int count = 0;
        
        // false의 갯수 카운트 -> true 만날 때까지
        for(int i=0; i<arr.length; i++) {
            if(!arr[i]) {
                if(check) count = 1;
                else count++;
                check = false;
            } else {
                if(count!=0) q.add(count);
                count = 0;
                check=true;
            }
        }

        // 마지막에 true가 아닌 false로 끝날 경우
        if(count!=0) q.add(count);

        while(!q.isEmpty()) {
            int qNum = q.poll();
            double cNum = 2 * w + 1; // 비교해줄 숫자 (양 사이드 + 본인)
            int a = (int) Math.ceil(qNum/cNum);

            answer += a;
        }

        return answer;	
	}
}

*/

// ver2
/*
public class BashStation {
	public static void main(String args[]) {
		int[] stations =  {5, 7, 20};
		
		System.out.println(solution(30, stations, 3));
	}
	public static int solution(int n, int[] stations, int w) {
		int answer = 0;

		int num1 =0;
		int num2 =0;
		
        Queue<Integer> q = new LinkedList<>();
        for(int i=0; i<stations.length; i++) {
            num1 = stations[i]-1; 
            if(i==0) {
            	q.add(num1-0-w);
            } else {
            	num2 = stations[i-1];
            	q.add(num1-num2-2*w);
            }
        }
        
        if(num1!=n-1 && (n-1-num1)>w) {
        	q.add((n-1)-num1-w); // num2 -> num1
        }
        
        while(!q.isEmpty()) {
            int qNum = q.poll();
            if(qNum < 0) continue;
            else {
            	double cNum = 2 * w + 1; // 비교해줄 숫자 (양 사이드 + 본인)
                int a = (int) Math.ceil(qNum/cNum);
                answer += a;
            }
        }

        return answer;	
	}
}
*/

// ver3 : 코드 리팩토링

public class BashStation {
	public static void main(String args[]) {
		int[] stations =  {5, 7, 20};
		
		System.out.println(solution(30, stations, 3));
	}
	public static int solution(int n, int[] stations, int w) {
		int answer = 0, num = 0;
		int before = w+1;

		for(int s : stations) {
		    num = s - before;   
		    answer += (num > 0) ? (int)Math.ceil((num) / (double)(2*w+1)) : 0;
		    before = s + 2*w+1;
		}

		num = n-before+w+1;
		answer += (num > 0) ? (int)Math.ceil((num) / (double)(2*w+1)) : 0;

		return answer;
	}
}