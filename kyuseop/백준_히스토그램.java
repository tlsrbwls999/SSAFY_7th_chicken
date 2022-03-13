
import java.util.*;
import java.io.*;

public class Main {

	static int N;
	static int height[], tree[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

//			st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(br.readLine());
		height = new int[N + 1];

		height[0] = Integer.MAX_VALUE;
		for (int i = 1; i <= N; i++) {
			height[i] = Integer.parseInt(br.readLine());
		}

		int h = (int) Math.ceil(Math.log(N) / Math.log(2));
		tree = new int[1 << (h + 1)]; // 최소 높이 인덱스 저장

		// 각 구간에서 가장 낮은 높이의 인덱스 저장 (min)
		init(1, N, 1);

		/**
		 * 가장 작은 높이가 있는 한 다른 높이는 넓이계산에 영향을 주지 못한다. 현 구간 가장 작은 높이로 넓이를 구하고, 그 지점을 제외한 나머지
		 * 양 영역에서 넓이를 구하며 최대값 갱신 -> 종료 : left<=right가 아니라면 종료
		 */

		calcArea(1, N);

		System.out.println(maxArea);

	} // main end

	static int maxArea = 0;

	static void calcArea(int start, int end) {
		if (start > end)
			return;
		int width = end - start + 1;
		int minHeightIdx = query(start, end, 1, 1, N);
		maxArea = Math.max(maxArea, width * height[minHeightIdx]);
		// 가장 낮은 높이가 나온 인덱스 기준으로 양분
		calcArea(start, minHeightIdx - 1);
		calcArea(minHeightIdx + 1, end);
	}

	static int init(int start, int end, int current) {
		if (start == end)
			return tree[current] = start; // 인덱스를 반환하고, 필요시 인덱스로 높이 구하기

		int mid = (start + end) / 2;

		int idx1 = init(start, mid, current * 2);
		int idx2 = init(mid + 1, end, current * 2 + 1);

		if (height[idx1] < height[idx2])
			return tree[current] = idx1;
		else
			return tree[current] = idx2;
	}

	// 구간의 최소 높이의 인덱스를 구하는 쿼리
	static int query(int start, int end, int current, int left, int right) {
		// 구간을 벗어나면 MAX_VALUE를 가진 인덱스 0을 반환
		if (left > end || right < start)
			return 0;

		if (left >= start && right <= end)
			return tree[current];

		int mid = (left + right) / 2;

		int idx1 = query(start, end, current * 2, left, mid);
		int idx2 = query(start, end, current * 2 + 1, mid + 1, right);

		if (height[idx1] < height[idx2])
			return idx1;
		else
			return idx2;
	}
}
