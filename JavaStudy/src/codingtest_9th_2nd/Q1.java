// ü���� (���α׷��ӽ� LEVEL1)

//ver 2 : �׽�Ʈ���̽� 12, 13 18 ���� (�������� ��� ����)
package codingtest_9th_2nd;
public class Q1 {
	static public void main(String[] args) {
		Q1 s = new Q1();
		int lost[] = {1,4,5};
		int reserve[] = {1,4,6};
		System.out.println(s.solution(5, lost, reserve));
	}
	
	public int solution(int n, int[] lost, int[] reserve) {
		int answer = 0;
		int size = reserve.length-1;
		
		answer = n - lost.length; // ���Ҿ���� �л���
		
		for(int i=0; i<lost.length; i++) {
			int key = lost[i];
			for(int j=0; j<reserve.length; j++) {
				if(reserve[j]==(key-1)||reserve[j]==(key)||reserve[j]==(key+1)) {
					answer++;
					reserve = delete(j, reserve, size--);
					break;
				}
			}
		}
        return answer;
	}
	
	// ���� �Լ� ��ü������ ����
	public int[] delete(int idx, int[] arr, int size) {
		int[] newArr = new int[arr.length-1];
		for(int i=0; i<idx; i++) {
			newArr[i] = arr[i];
		}
		for(int i=idx; i<size; i++) {
			newArr[i] = arr[i+1];
		}
		return newArr;
	}
}
