package com.wychuan.learn.interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.mysql.fabric.xmlrpc.base.Array;

/**
 * 全排列
 * 设一组数p = {r1, r2, r3, ... ,rn}, 全排列为perm(p)，pn = p - {rn}。
   因此perm(p) = r1perm(p1), r2perm(p2), r3perm(p3), ... , rnperm(pn)。当n = 1时perm(p} = r1。
 * @author mfhj-dz-001-504
 *
 */
public class Permutate {
	public static int total = 0;

	/**
	 * 交换数组中下标为i和j的值
	 * 
	 * @param list
	 * @param i
	 * @param j
	 */
	public static void swap(String[] list, int i, int j) {
		if (list == null || i > list.length - 1 || j > list.length - 1) {
			return;
		}
		String temp = list[i];
		list[i] = list[j];
		list[j] = temp;
	}

	/**
	 * 递归全排列
	 * @param list
	 * @param str
	 * @param len 数组长度
	 */
	public static void arrange(String[] list, int str, int len) {
		System.out.println(String.format("str:%d len:%d", str, len));
		if (str == len - 1) {
			for (int i = 0; i < len; i++) {
				System.out.print(list[i] + " ");
			}
			System.out.println();
			total++;
		} else {
			for (int i = str; i < len; i++) {
//				System.out.println(String.format("str:%d len:%d i:%d", str, len, i));
				swap(list, str, i);
				arrange(list, str + 1, len);
				swap(list, str, i);
			}
		}
	}
	
	public static String arrange2(String[] list){
		if(list==null || list.length==0){
			return "";
		}
		if(list.length == 1){
			return list[0];
		}
		for(int i=0;i<list.length;i++){
			String pre = list[i];
			String[] newArray = newArray(list, i);
			
			System.out.println(pre+arrange2(newArray));
		}
		return "";
	}
	private static String[] newArray(String[] list, int index){
		List<String> result = Arrays.asList(list);
		System.out.println(result.size()+" "+index+1);
		result.remove(index+1);
		return (String[])result.toArray();
	}

	public static void main(String[] args) {
		String[] list = { "a", "b", "c" };
//		arrange(list, 0, list.length);
		System.out.println(total);
		
		arrange2(list);
	}
}
