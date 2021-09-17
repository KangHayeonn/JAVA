// N개의 최소공배수 (프로그래머스 LEVEL2)

// ver1
// (1) 두 수의 최대공배수(lcm)를 구함
// (2) 두 수의 최대공배수를 구하기 위해 두 수의 최대공약수(gcd)를 구함
// (3) 최대공약수는 나머지가 0이 될때까지 재귀함수를 돌려 구해줌 
// (4) 최소공배수는 공식을 이용해서 구해줌 (두 수 곱하기 / 최대공약수)
// (5) 이러한 과정을 두개씩 묶어 계산-반복-계산-반복-... 후 , arr의 끝까지 진행

package codingtest_9th_3rd;

public class Q4 {
	static public void main(String args[]) {
		int[] arr = {2};
		Q4 s = new Q4();
		System.out.println(s.solution(arr));
	}
	public int solution(int[] arr) {
        int answer = 0;
        if(arr.length==1) return arr[0];
        answer = lcm(arr[0], arr[1]);
        if(arr.length==2) return answer;
        for(int i=2; i<arr.length; i++) {
        	answer = lcm(answer, arr[i]);
        }
        return answer;
    }
	public int lcm(int a, int b) {
		return a*b/gcd(a,b);
	}
	public int gcd(int a, int b) {
		if(a%b == 0) return b;
		return gcd(b, a%b);
	}
}
