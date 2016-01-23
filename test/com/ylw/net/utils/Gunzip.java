package com.ylw.net.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Gunzip {
	public static void main(String[] args) throws FileNotFoundException {
		if (args.length < 1) {
			System.out.println("使用方式：java Gunzip xx.raw");
		}
		String file = args[0];
		RandomAccessFile rf = new RandomAccessFile(file, "r");
		Scanner scan = new Scanner(System.in);
		do {
System.out.println("请输入");
		} while (true);
	}
}
