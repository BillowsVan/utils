import java.math.BigInteger;
import java.util.Arrays;
import java.util.UUID;

/**
 * Created by fht on 2017-04-27 08:46.
 * 参考python shortUUID实现
 */
public class ShortUUID {

    private static char[] alphabet = new char[]{'2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    private static BigInteger alphabetLength = new BigInteger(alphabet.length + "");

    private static BigInteger zero = new BigInteger("0");

    private static String intToString(BigInteger number) {
        StringBuilder output = new StringBuilder();
        BigInteger bigInteger = zero;
        while (!number.max(bigInteger).equals(bigInteger)) {
            BigInteger[] integers = number.divideAndRemainder(alphabetLength);
            number = integers[0];
            int digit = integers[1].intValue();

            output.append(alphabet[digit]);
        }
        return output.toString();
    }

    private static BigInteger stringToInt(String str) {
        BigInteger bigInteger = zero;
        for (int i = str.length() - 1; i >= 0; i--) {
            bigInteger = bigInteger
                .multiply(alphabetLength)
                .add(new BigInteger(Arrays.binarySearch(alphabet, str.charAt(i)) + ""));
        }
        return bigInteger;
    }

    public static String encode() {
        return encode(UUID.randomUUID());
    }

    public static String encode(String name) {
        return encode(UUID.nameUUIDFromBytes(name.getBytes()));
    }

    public static String encode(UUID uuid) {
        return encodeHexUUID(uuid.toString().replaceAll("-", ""));
    }

    private static String encodeHexUUID(String hexUUID) {
        return intToString(new BigInteger(hexUUID, 16));
    }

    public static String decode(String shortUUID) {
        return new BigInteger(stringToInt(shortUUID) + "").toString(16);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i ++) {
            UUID originUUID = UUID.randomUUID();
            System.out.println("原始UUID：" + originUUID);

            String shortUUID = encode(originUUID);
            System.out.println("短UUID：" + shortUUID);

            String decodedUUID = decode(shortUUID);
            System.out.println("decodedUUID：" + decodedUUID);

            System.out.println("=============================");
        }
    }


}
