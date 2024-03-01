import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        //Test 1
        System.out.println("Task 1 - ");
        System.out.println(evaluateExpression("5+20-8+5"));
        System.out.println(evaluateExpression("14-31-2+8+1"));
        System.out.println(evaluateExpression("0+0-0"));

        //test 2
        System.out.println("Task 2 - ");
        System.out.println(numberOfHappyStrings(List.of("abc", "b", "bac", "bbb", "aca")));
        System.out.println(numberOfHappyStrings(List.of("abbcbac", "abbc", "abcbaabc", "caabcbb")));

        //test 3
        System.out.println("Task 3 - ");
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        System.out.println("Original list:");
        printList(head);

        // Reversing the linked list
        ListNode reversedHead = reverseList(head);

        System.out.println("\nReversed list:");
        printList(reversedHead);

        //test 4
        System.out.println("Task 4 - ");
        int[] nums1 = new int[]{1, 2, 3, 3, 4, 5};
        int[] nums2 = new int[]{3, 4, 4, 5, 6, 7};
        System.out.println(Arrays.toString(findIntersection(nums1, nums2)));

        //Task5
        System.out.println("Task 5 - ");
        int[] arr1 = new int[]{6, 2, 2, 3, 4, 1};
        int[] arr2 = new int[]{10, 5, 2, 7, 1, 9};
        int[] arr3 = new int[]{1, 1, 5, 4, 5};
        System.out.println(lenOfLongSubarr(arr1, 8));
        System.out.println(lenOfLongSubarr(arr2, 15));
        System.out.println(lenOfLongSubarr(arr3, 3));

        //test 6
        System.out.println("Task 6 - ");
        int[] array1 = new int[]{5, 1, 22, 25, 6, -1, 8, 10};
        int[] array2 = new int[]{1, 6, -1, 10};
        System.out.println(isValidSequence(array1, array2));

    }
    // 1
    public static int evaluateExpression(String expression) {
        if (expression == null || expression.isEmpty()) return 0;
        expression = expression.trim().replaceAll("\\s+", "");

        int length = expression.length();
        int result = 0;
        int currentValue = 0;
        char operation = '+';

        for (int i = 0; i < length; i++) {
            char currentChar = expression.charAt(i);
            if (Character.isDigit(currentChar)) {
                currentValue = currentValue * 10 + (currentChar - '0');
            }
            if (!Character.isDigit(currentChar) || i == length - 1) {
                if (operation == '+') {
                    result += currentValue;
                } else if (operation == '-') {
                    result -= currentValue;
                }
                currentValue = 0;
                operation = currentChar;
            }
        }
        return result;
    }
    //2
    public static int numberOfHappyStrings(List<String> strings) {
        int count = 0;
        for (String str : strings) {
            boolean check = true;
            for (int i = 0; i < str.length() - 1; i++) {
                if (str.charAt(i) == str.charAt(i + 1)) {
                    check = false;
                    break;
                }
            }
            if (check) {
                count++;
            }
        }
        return count;
    }
    //3
    public static ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode current = head;

        while(current != null) {
            ListNode next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        return prev;
    }

    public static void printList(ListNode head){
        ListNode current = head;
        while(current != null){
            System.out.println( current.val + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }
    //4
    public static int[] findIntersection(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();

        for (int i = 0; i < nums1.length; i++)
            set1.add(nums1[i]);

        for (int j = 0; j < nums2.length; j++) {
            if (set1.contains(nums2[j])) {
                set2.add(nums2[j]);
            }
        }

        int[] answer = new int[set2.size()];
        int k = 0;
        for (Integer num : set2)
            answer[k++] = num;

        return answer;
    }
    //5
    static int dp[][];
    public static int lenOfLongSubarr(int[] array, int k) {
        int n=array.length;
        dp = new int[n][k+1];

        for(int i=0;i<n;i++)
            Arrays.fill(dp[i],-2);

        return fun(0,n,k,array);

    }
    public static int fun(int idx, int n, int k, int[] array){
        if (k == 0)
            return 0;

        if (idx >= n || k < 0)
            return -1;

        if (dp[idx][k] != -2)
            return dp[idx][k];

        int pick = fun(idx + 1, n, k - array[idx], array);
        int nopick = fun(idx + 1, n, k, array);

        int result = -1;

        if (pick != -1)
            result = pick + 1;
        if (nopick != -1)
            result = Math.max(result, nopick);

        return dp[idx][k] = result;

    }
    // 6
    public static boolean isValidSequence(int[] array, int[] sequence) {
        int arrayIndex = 0;
        int sequenceIndex = 0;

        while (arrayIndex < array.length && sequenceIndex < sequence.length) {
            if (array[arrayIndex] == sequence[sequenceIndex]) {
                sequenceIndex++;
            }
            arrayIndex++;
        }

        return sequenceIndex == sequence.length;
    }
}



