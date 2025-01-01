package Algorithm;

import java.util.Arrays;

public class BinarySearch {
	static int[] A;

	private static int binarySearch(int target) {
		int left = 0;
		int right = A.length - 1;
		while (left <= right) {
			int mid = (left + right) / 2;
			if (A[mid] < target) left = mid + 1;
			else if (A[mid] > target) right = mid - 1;
			else return mid;
		}
		return -1;
	}

	private static int lowerBound(int target) {
		int lo = 0; int hi = A.length - 1;
		int ans = A.length;
		while(lo <= hi) {
			int mid = (lo + hi) / 2;
			if(target <= A[mid]) {
				ans = mid;
				hi = mid - 1;
			}
			else {
				lo = mid + 1;
			}
		}
		return ans;
	}

	private static int upperBound(int target) {
		int lo = 0; int hi = A.length - 1;
		int ans = A.length;
		while(lo <= hi) {
			int mid = (lo + hi) / 2;
			if(target < A[mid]) {
				ans = mid;
				hi = mid - 1;
			}
			else {
				lo = mid + 1;
			}
		}
		return ans;
	}

	public static void main(String[] args) {
		A = new int[] {1, 2, 2, 3, 3, 3, 4, 5, 6};

		System.out.println(Arrays.binarySearch(A, 3));
		System.out.println(binarySearch(3));
		System.out.println(lowerBound(3));
		System.out.println(upperBound(3));
	}
}
