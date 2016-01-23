package com.ylw.net.utils;

public class Hex {

    /**将数组变为16进制
     * @param bytes
     * @return
     */
    public static String bytesToHexString(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        for (byte b : bytes) {
            int val = b & 0xff;
            if (val < 0x10) {
                sb.append("0");
            }
            sb.append(Integer.toHexString(val));
        }
        return sb.toString();
    }

    /**把index进制（即第二个参数）的ch转变为16进制
     * @param ch
     * @param index 为了抛异常用
     * @return
     * @throws Exception
     */
    private static int toDigit(final char ch, final int index) throws Exception {
        final int digit = Character.digit(ch, 16);//把16进制（即第二个参数）的ch转变为16进制
        if (digit == -1) {
            throw new Exception("Illegal hexadecimal character " + ch + " at index " + index);
        }
        return digit;
    }

    /**将16进制变为数组
     * @param str
     * @return
     * @throws Exception
     */
    public static byte[] hexStringToBytes(String str) throws Exception {
        final char data[] = str.toCharArray();
        final int len = data.length;
        if ((len & 0x01) != 0) {//奇数位会抛异常
            throw new Exception("Odd number of characters.");
        }
        final byte[] out = new byte[len >> 1];
        for (int i = 0, j = 0; j < len; i++) {
            int f = toDigit(data[j], j) << 4;//7（00000111）左移4位（乘以16）得112（01110000）
            j++;
            f = f | toDigit(data[j], j);//第二个f（11111111）与112或运算之后为127（11111111）
            j++;
            out[i] = (byte) (f & 0xFF);
        }
        return out;
    }

}
