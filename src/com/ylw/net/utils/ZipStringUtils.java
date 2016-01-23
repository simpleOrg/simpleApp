package com.ylw.net.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.util.IOUtils;

public class ZipStringUtils {
	static Logger log = LoggerFactory.getLogger(ZipStringUtils.class);

	/**
	 * 使用gzip进行压缩
	 */
	public static byte[] gzip(String primStr) {
		if (primStr == null || primStr.length() == 0) {
			return null;
		}

		ByteArrayOutputStream out = new ByteArrayOutputStream();

		GZIPOutputStream gzip = null;
		try {
			gzip = new GZIPOutputStream(out);
			gzip.write(primStr.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			IOUtils.close(gzip);
		}
		return out.toByteArray();
	}

	/**
	 * <p>
	 * Description:使用gzip进行解压缩
	 * </p>
	 *
	 * @param compressed
	 * @return
	 * @throws Exception
	 */
	public static byte[] gunzip(byte[] compressed) throws Exception {
		if (compressed == null) {
			return null;
		}

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ByteArrayInputStream in = null;
		GZIPInputStream ginzip = null;
		byte[] decompressed = null;
		try {
			in = new ByteArrayInputStream(compressed);
			ginzip = new GZIPInputStream(in);

			byte[] buffer = new byte[1024];
			int offset = -1;
			while ((offset = ginzip.read(buffer)) != -1) {
				out.write(buffer, 0, offset);
			}
			decompressed = out.toByteArray();
		} catch (IOException e) {
			log.error(e.getMessage());
		} finally {
			IOUtils.close(ginzip);
			IOUtils.close(in);
			IOUtils.close(out);
		}

		return decompressed;
	}

	/**
	 * 使用zip进行压缩
	 *
	 * @param str
	 *            压缩前的文本
	 * @return 返回压缩后的文本
	 */
	public static String zip(String str) {
		if (str == null)
			return null;
		byte[] compressed;
		ByteArrayOutputStream out = null;
		ZipOutputStream zout = null;
		String compressedStr = null;
		try {
			out = new ByteArrayOutputStream();
			zout = new ZipOutputStream(out);
			zout.putNextEntry(new ZipEntry("0"));
			zout.write(str.getBytes());
			zout.closeEntry();
			compressed = out.toByteArray();
			compressedStr = Hex.bytesToHexString(compressed);
		} catch (IOException e) {
			compressed = null;
		} finally {
			IOUtils.close(zout);
			IOUtils.close(out);
		}
		return compressedStr;
	}

	/**
	 * 使用zip进行解压缩
	 *
	 * @param compressedStr
	 *            压缩后的文本
	 * @return 解压后的字符串
	 * @throws Exception
	 */
	public static String unzip(String compressedStr) throws Exception {
		if (compressedStr == null) {
			return null;
		}

		ByteArrayOutputStream out = null;
		ByteArrayInputStream in = null;
		ZipInputStream zin = null;
		String decompressed = null;
		try {
			byte[] compressed = Hex.hexStringToBytes(compressedStr);
			out = new ByteArrayOutputStream();
			in = new ByteArrayInputStream(compressed);
			zin = new ZipInputStream(in);
			zin.getNextEntry();
			byte[] buffer = new byte[1024];
			int offset = -1;
			while ((offset = zin.read(buffer)) != -1) {
				out.write(buffer, 0, offset);
			}
			decompressed = out.toString();
		} catch (IOException e) {
			decompressed = null;
		} finally {
			IOUtils.close(zin);
			IOUtils.close(in);
			IOUtils.close(out);
		}
		return decompressed;
	}

}
