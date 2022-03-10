package 위상정렬;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class 위상정렬 {
	static int V, E;
	static List<Integer>[] list;
	static int[] degree;

	public static void main(String[] args) {
		makeTest();

		Queue<Integer> Q = new LinkedList<>();

		for (int i = 1; i <= V; i++) {
			if (degree[i] == 0)
				Q.offer(i);
		}
		while (!Q.isEmpty()) { // 사이클이 없어어야함. 사이클이 있는지 판별해야 할 경우는 for문으로 V번 반복
			int now = Q.poll();
			System.out.print(now + " -> ");

			for (int i = 0; i < list[now].size(); i++) {
				int to = list[now].get(i);

				if (--degree[to] == 0) {
					Q.offer(to);
				}
			}
		}

	}

	static void makeTest() {
		V = 6;
		E = 6;
		degree = new int[V + 1];
		list = new ArrayList[V + 1];
		for (int i = 0; i <= V; i++) {
			list[i] = new ArrayList<>();
		}
//		1 4 //1번 작업 후 4번 작업 해야 한다.
//		5 4
//		4 3
//		2 5
//		2 3
//		6 2
		list[1].add(4);
		degree[4]++;
		list[5].add(4);
		degree[4]++;
		list[4].add(3);
		degree[3]++;
		list[2].add(5);
		degree[5]++;
		list[2].add(3);
		degree[3]++;
		list[6].add(2);
		degree[2]++;
		System.out.println(Arrays.toString(degree));

	}

}
