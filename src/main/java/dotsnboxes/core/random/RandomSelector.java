package dotsnboxes.core.random;

/**
 * Can select a random byte from an array of bytes.
 * 
 * @author luis
 */
public interface RandomSelector {

    public byte selectRandomByte(byte[] allowedValues);

    public int nextInt(int n);

    public double nextDouble();
}
