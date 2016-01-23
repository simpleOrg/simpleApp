package com.ylw.net.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import org.junit.Test;

public class GzipTest {
	@Test
	public void testReadGzipfile() throws Exception {
		System.out.println("hello");
		String fp = "C:\\Users\\up366pc1.hxtt-pc1\\Desktop\\submit.raw";
		RandomAccessFile rf = new RandomAccessFile(fp, "r");
		byte[] b = new byte[(int) rf.length()];
		rf.readFully(b);
		String str = new String(b);
		String s = "Content-Encoding: gzip";
		System.out.println("index = " + (str.indexOf(s) + s.length()));
		int i = 0;
		rf.seek(682);
		byte[] ob = new byte[270];
		rf.read(ob);
//		byte[] bbb=ZipStringUtils.gzip("abcdefg");
		String res = new String(ZipStringUtils.gunzip(ob));
		System.out.println(res);
		// while (rf.read(ob) != -1) {
		// System.out.print((char) ob[0]);
		// i++;
		// }
	}
}
