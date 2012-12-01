package dotsnboxes.core.ai;

import dotsnboxes.core.Strategy;
import dotsnboxes.core.random.RandomSelector;

/**
 * A strategy the selects the next line at random.
 * 
 * @author luis
 */
public class RandomStrategy extends Strategy {

    private RandomSelector selector;

    public RandomStrategy(RandomSelector selector) {

        this.selector = selector;
    }

    @Override
    public byte evaluateNextLine(byte[] configuration) {

        return selector.selectRandomByte(allowedValues(configuration));
    }
}
