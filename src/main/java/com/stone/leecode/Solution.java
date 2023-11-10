package com.stone.leecode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution {


    //求两数之和
    public static int[] twoSum(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        boolean flag = false;
        while (true) {
            int sum = nums[left] + nums[right];
            if (left >= right) {
                break;
            } else if (sum == target) {
                flag = true;
                break;
            } else if (sum > target) {
                --right;
            } else if (sum < target) {
                ++left;
            }
        }
        return flag == true ? new int[] {left, right} : new int[]{};
//        return new int[] {left, right};
    }

    //时间复杂度为O(n)
    public static int[] twoSum1(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }


    public static ListNode addTwoNumbers(ListNode l1, ListNode l2){
        ListNode dummyHead = new ListNode(0);
        ListNode p=l1, q=l2, curr=dummyHead;
        int carry = 0;
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = x + y + carry;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            if (p != null) {
                p = p.next;
            }
            if (q != null) {
                q = q.next;
            }
        }
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }

        return dummyHead.next;
    }


    //取出字符创中不重复的子字符串的最长长度
    //滑动窗口 方法
    public static int lengthOfLongestSubstring(String s) {
        int size = s.length();
        int result=0, i=0, j=0;
        Set<Character> set = new HashSet<>();
        while (i < size && j < size) {
            //如果set中不包含，则将s.charAt(j)加入到set中，更新返回的长度
            if (!set.contains(s.charAt(j))) {
                set.add(s.charAt(j++));
                result = Math.max(result, j - i);
            } else {
                set.remove(s.charAt(i++));
            }
        }

        return result;
    }

    //优化滑动窗口 方法
    public static int lengthOfLongestSubstring2(String s) {
        int size=s.length(), result=0, start = 0, end = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0, j = 0; j < size; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }

            int temp = Math.max(result, j - i + 1);
            //start、end为求最长的字符串
            if (result <= temp) {
                start = i;
                end = j;
                result = temp;
            }
            map.put(s.charAt(j), j + 1);
            System.out.println(" i:" + i + " j:" + (j+1) + " result:"+ result);
        }
        //求最长不重复的子字符串
//        System.out.println("s: " + start + " end:" + end + " 最长的字符串为："+ s.substring(start, end + 1));
        return result;
    }



    //求最长回文
    public static String longestPalindrome(String s) {
        if (s.isEmpty()) {
            return "";
        }
        char[] cs = s.toCharArray();
        int result=0, start = 0, end =0;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < cs.length; i++) {
            if (map.containsKey(cs[i])) {
                int temp = Math.max(result, i - map.get(cs[i]) + 1);
                if (temp > result && help(cs, map.get(cs[i]), i)) {
                    start = map.get(cs[i]);
                    end = i;
                    result = temp;
                }
//                System.out.println(result);
            } else {
                map.put(cs[i], i);
            }
        }

//        System.out.println("start: " + start + " end:" + end);
        return s.substring(start, end + 1);
    }

    private static boolean help(char[] s, int start, int end) {
        System.out.println("start: " + start + " end:" + end);
        int low = start, high = end;
        boolean flag = true;
        for (;low < high; low++, high--) {
            if (s[low] != s[high]) {
                flag = false;
                break;
            }
        }

        return flag;
    }

    public static String longestPalindrome2(String s) {
        if (s == null || s.length() < 1) return "";
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private static int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }


    public String longestPalindrome3(String s) {
        if(s.length() < 1){
            return "";
        }
        char[] cs = s.toCharArray();
        int[] range = new int[]{0,1};
        for(int i = 0; i < cs.length; i++){
            i = help(cs,range,i);
        }
        return s.substring(range[0],range[1]);


    }

    private int help(char[] cs,int[] range,int i){
        int lo = i, hi = i;
        //找到i后第一个不同处
        while(hi < cs.length - 1 && cs[hi] == cs[hi + 1]) {
            hi++;
        }
        //开始比较
        int ret = hi;
        while(lo > 0 && hi < cs.length - 1 && cs[lo - 1] == cs[hi + 1]){
            lo--;
            hi++;
        }
        //使range数组保存最大范围
        if((hi - lo + 1) > (range[1] - range[0])){
            range[0] = lo;
            range[1] = hi + 1;
        }
        return ret;

    }


    //反转链表
    //如 1>>2>>3>>4>>5>>null,反转为: 5>>4>>3>>2>>1>>null
    public static ListNode reverseList(ListNode head) {
        if(head == null ||head.next == null){
            System.out.println(head.val);
            return head;
        }
        ListNode ans = reverseList(head.next);
//        System.out.println(ans.val);
        head.next.next = head;
        head.next = null;
        return ans;
    }

    //判断链表中是否含有环
    //使用set来判断,时间复杂度为O(n),空间复杂度为O(n)
    private static boolean cycleList(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        ListNode p = head;
        while (p != null || p.next != null) {
            System.out.println(p.val);
            if (set.contains(p)) {
                return true;
            }
            set.add(p);
            p = p.next;
        }
        return false;
    }

    //快慢指针
    //空间复杂度为O(1)
    private static boolean cycleList2(ListNode head) {
        ListNode fast, slow;
        fast = slow = head;
        while(fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            System.out.println("快指针：" + fast.val + "  慢指针：" + slow.val);
            if (fast == slow) {
                return true;
            }
        }

        return false;
    }


    public static void main(String[] args) {
//        int[] nums = new int[] {2, 7, 11, 15};
//        int[] result = twoSum1(nums, 14);
//        for (int i : result) {
//            System.out.println(i);
//        }

//        System.out.println(longestPalindrome("abcda"));
//        System.out.println(longestPalindrome("aadada"));

        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(3);
        ListNode d = new ListNode(4);
        ListNode e = new ListNode(5);
//        ListNode f = new ListNode(6);
//        ListNode g = new ListNode(7);

        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;
//        e.next = f;
//        f.next = g;

        e.next = b;

        if (cycleList2(a)) {
            System.out.println("此链表含有环。。。。");
        } else {
            System.out.println("链表中没有环");
        }

//        ListNode r = reverseList(a);
//        System.out.println(r);
    }


}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
    }
}
