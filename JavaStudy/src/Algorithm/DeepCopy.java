package Algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DeepCopy {
	public static class Type implements Cloneable {
		int r;
		int c;
		
		public Type(int r, int c) {
			this.r = r;
			this.c = c;
		}
		
		public void setC(int c) {
			this.c = c;
		}
		
		@Override
		public String toString() {
			return "[" + r + " : " + c + "]";
		}
		
		@Override
		protected Type clone() throws CloneNotSupportedException {
			return (Type) super.clone();
		}
	}
	public static void main(String args[]) throws CloneNotSupportedException {
		System.out.println("\n arrayList ���� ���� \n");
		ArrayList<Integer> list = new ArrayList<>();
		list.add(0);
		list.add(1);
		list.add(2);
		
		ArrayList<Integer> tmpList = new ArrayList<>();
		// 1�� ���
		/*
		for(Integer i : list) {
			tmpList.add(i);
		}*/
		// 2�� ���
		tmpList.addAll(list);
		
		list.add(3);
		System.out.println(list.toString());
		System.out.println(tmpList.toString());
		
		ArrayList<Type> sampleList = new ArrayList<>();
		sampleList.add(new Type(1, 2));
		sampleList.add(new Type(100, 200));
		
		ArrayList<Type> copyList = new ArrayList<>();
		for(Type t : sampleList) {
			copyList.add(t.clone()); // deep copy ����
		}
		
		ArrayList<Type> copyList2 = new ArrayList<>();
		for(Type t : sampleList) {
			copyList2.add(t);
		}
		
		System.out.println(sampleList);
		System.out.println(copyList);
		System.out.println(copyList2);
		
		Type t = sampleList.get(1);
		t.setC(1000);
		
		System.out.println(sampleList);
		System.out.println(copyList);
		System.out.println(copyList2);
		
		System.out.println("----------------------------");
		System.out.println("\n map ���� ���� \n");
		
		HashMap<String, Integer> originMap = new HashMap<>();
		originMap.put("���", 1000);
		originMap.put("����", 1000);
		originMap.put("����", 1000);
		
		// �����ڸ� �̿��ؼ� ���� (deep copy ����)
		HashMap<String, Integer> copyMap = new HashMap<>(originMap);
		
		// clone() �̿��ؼ� ���� (deep copy ����)
		HashMap<String, Integer> copyMap2 = (HashMap<String, Integer>)originMap.clone();
		
		// putAll() �޼ҵ带 �̿��ؼ� ���� (deep copy ����)
		HashMap<String, Integer> copyMap3 = new HashMap<>();
		copyMap3.putAll(originMap);
		
		System.out.println(originMap);
		System.out.println(copyMap);
		System.out.println(copyMap2);
		System.out.println(copyMap3);
		
		originMap.put("����", 5000);
		
		System.out.println(">>> ������ <<<");
		
		System.out.println(originMap);
		System.out.println(copyMap);
		System.out.println(copyMap2);
		System.out.println(copyMap3);
		
		System.out.println("\n class Type map ���� \n");
		
		HashMap<Integer, Type> map = new HashMap<>();
		HashMap<Integer, Type> testMap = new HashMap<>();
		
		map.put(1, new Type(0, 0));
		map.put(2, new Type(1, 1));
		map.put(3, new Type(2, 2));
		
		// depp copy �Ұ��� (���� ����)
		testMap = map;
		
		// deep copy ����
		HashMap<Integer, Type> entryMap = new HashMap<>();
		for(Map.Entry<Integer, Type> entry : map.entrySet()) {
			entryMap.put(entry.getKey(), entry.getValue());
		}
		
		// deep copy ����
		HashMap<Integer, Type> classMap = new HashMap<>();
		for(Integer key : map.keySet()) {
			classMap.put(key, map.get(key).clone());
		}
		
		
		System.out.println(map);
		System.out.println(testMap);
		System.out.println(entryMap);
		System.out.println(classMap);
		
		map.put(0, new Type(5000, 5000));
		
		System.out.println(">>> ������ <<<");
		
		System.out.println(map);
		System.out.println(testMap);
		System.out.println(entryMap);
		System.out.println(classMap);
	}
}
