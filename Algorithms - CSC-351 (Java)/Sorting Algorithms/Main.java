public class Main {
	public static void main(String[] args) {
		//int[] A = { 3, 2, 5, 8, 0, 1, 7, 4, 2, 10, 3, 9 };
		//should print {0,1,2,2,3,3,4,5,7,8,9,10}
		int[] A = {5,7,3,8,2,1};
		System.out.print("Pre Counting Sort = ");
		for(int i = 0;i<A.length;i++){
			System.out.print(A[i] + " ");
		}
		System.out.println();
		Sort.counting(A);
		System.out.print("Counting Sort = ");
		for(int i = 0;i<A.length;i++){
			System.out.print(A[i] + " ");
		}

		System.out.println();
		
		int[] B = {5,7,3,8,2,1};
		System.out.print("Pre Merge-Sort = ");
		for(int i = 0;i<B.length;i++){
			System.out.print(B[i] + " ");
		}
		Sort.mergesort(B);

		System.out.println();

		System.out.print("Merge-Sort = ");
		for(int i = 0;i<A.length;i++){
			System.out.print(B[i] + " ");
		}


		
	}
}
