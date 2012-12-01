package dotsnboxes.core;

import java.util.ArrayList;
import java.util.List;

/**
 * A Strategy determines which line to select next based on the current
 * configuration of the board.
 * 
 * @author luis
 */
public abstract class Strategy {

    /**
     * Given a byte array representing the current board configuration, in the
     * form [0,0,0,0,1,1,0,0,1,1,0,0] (for a 2x2 board, where 0 is an unselected
     * line and 1 is a selected line), for example, return a byte with value
     * 0-11 (for a 2x2 board) representing the next selection, where 0 is the
     * top left line, moving right and downwards.
     */
    public abstract byte evaluateNextLine(byte[] configuration);

    /**
     * Returns a byte array with allowed next line values for a given
     * configuration.
     */
    public static byte[] allowedValues(byte[] configuration) {

        byte[] allowedValues = new byte[configuration.length - cardinality(configuration)];
        short valIndx = 0;
        for (int i = 0; i < configuration.length; i++) {
            if (configuration[i] == 0) {
                allowedValues[valIndx++] = (byte) i;
            }
        }
        return allowedValues;
    }

    /**
     * Returns a byte array with allowed next line values for a given
     * configuration represented by an int.
     */
    public static byte[] allowedValues(int configuration, int numLines) {

        // TODO rewrite so that a list is not used
        List<Integer> allowed = new ArrayList<Integer>();
        for (int i = 0; i < numLines; i++) {
            int k = (configuration >> i) & 1;
            if (k == 0)
                allowed.add(i);
        }

        byte[] b = new byte[allowed.size()];
        for (int i = 0; i < allowed.size(); i++) {
            b[i] = allowed.get(i).byteValue();
        }

        return b;
    }

    /**
     * Returns the number of bits set to true.
     * 
     * from BitSet.java - JDK source
     */
    public static int cardinality(byte[] bits) {

        int card = 0;
        for (int i = bits.length - 1; i >= 0; i--) {
            long a = bits[i];

            if (a == 0)
                continue;
            if (a == -1) {
                card += 64;
                continue;
            }

            a = ((a >> 1) & 0x5555555555555555L) + (a & 0x5555555555555555L);
            a = ((a >> 2) & 0x3333333333333333L) + (a & 0x3333333333333333L);
            int b = (int) ((a >>> 32) + a);
            b = ((b >> 4) & 0x0f0f0f0f) + (b & 0x0f0f0f0f);
            b = ((b >> 8) & 0x00ff00ff) + (b & 0x00ff00ff);
            card += ((b >> 16) & 0x0000ffff) + (b & 0x0000ffff);
        }
        return card;
    }
}
