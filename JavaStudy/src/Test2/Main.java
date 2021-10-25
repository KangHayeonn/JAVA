package Test2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
	private static void solution(int numOfRegion, int numOfAttackableFrequency, int[][] frequencies) {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
	
		for(int i=0; i<numOfRegion; i++) {
			for(int j=0; j<frequencies[i].length; j++) {
				int value = map.containsKey(frequencies[i][j]) ? map.get(frequencies[i][j]) : 0;
				map.put(frequencies[i][j], value + 1);
			}
		}
		
		ArrayList<Integer> list = new ArrayList<>(map.keySet());
		Collections.sort(list, (key1, key2) -> map.get(key2).compareTo(map.get(key1)));
		
		int count = 0;
		int sum = 0;
		for(Integer key : list) {
			count++;
			sum += map.get(key);
			if(count==numOfAttackableFrequency) {
				System.out.print(sum);
				break;
			}
		}

	}
	
	private static class InputData {
		int numOfRegion;
		int numOfAttackableFrequency;
		int[][] frequencies;
	}
	
	private static InputData processStdin() {
		InputData inputData = new InputData();
		
		try(Scanner scanner = new Scanner(System.in)){
			String[] temp = scanner.nextLine().split(" ");
			inputData.numOfRegion = Integer.parseInt(temp[0]);
			inputData.numOfAttackableFrequency = Integer.parseInt(temp[1]);
			inputData.frequencies = new int[inputData.numOfRegion][];
			
			for(int i=0; i<inputData.numOfRegion; i++) {
				temp = scanner.nextLine().split(" ");
				int numOfFrequency = Integer.valueOf(temp[0]);
				inputData.frequencies[i] = new int[numOfFrequency];
				
				for(int j=0; j<numOfFrequency; j++) {
					inputData.frequencies[i][j] = Integer.parseInt(temp[j+1]);
				}
			}
		} catch(Exception e) {
			throw e;
		}
		
		return inputData;
	}
	
	public static void main(String[] args) throws Exception {
		InputData inputData = processStdin();
		
		solution(inputData.numOfRegion, inputData.numOfAttackableFrequency, inputData.frequencies);
	}
}
