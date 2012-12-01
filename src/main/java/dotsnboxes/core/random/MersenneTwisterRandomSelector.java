package dotsnboxes.core.random;

import java.util.Random;

import org.uncommons.maths.random.MersenneTwisterRNG;
import org.uncommons.maths.random.SecureRandomSeedGenerator;

/**
 * MersenneTwister-based implementation of RandomSelector
 * 
 * @author luis
 */
public class MersenneTwisterRandomSelector implements RandomSelector {

    private Random random;

    public MersenneTwisterRandomSelector() {
        try {
            random = new MersenneTwisterRNG(new SecureRandomSeedGenerator());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public byte selectRandomByte(byte[] allowedValues) {

        if (allowedValues.length == 1) {
            return allowedValues[0];
        }
        int r = random.nextInt(allowedValues.length);
        return allowedValues[r];
    }

    public int nextInt(int n) {

        return random.nextInt(n);
    }

    public double nextDouble() {

        return random.nextDouble();
    }
}
