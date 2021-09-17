// N���� �ּҰ���� (���α׷��ӽ� LEVEL2)

// ver1
// (1) �� ���� �ִ�����(lcm)�� ����
// (2) �� ���� �ִ������� ���ϱ� ���� �� ���� �ִ�����(gcd)�� ����
// (3) �ִ������� �������� 0�� �ɶ����� ����Լ��� ���� ������ 
// (4) �ּҰ������ ������ �̿��ؼ� ������ (�� �� ���ϱ� / �ִ�����)
// (5) �̷��� ������ �ΰ��� ���� ���-�ݺ�-���-�ݺ�-... �� , arr�� ������ ����

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
