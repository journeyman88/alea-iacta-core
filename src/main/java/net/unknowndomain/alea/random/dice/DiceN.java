/*
 * Copyright 2020 Marco Bignami.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.unknowndomain.alea.random.dice;

import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import net.unknowndomain.alea.random.Generator;
import net.unknowndomain.alea.random.SingleResult;

/**
 * This is the base class to implement numeric dice with ascending numbers.
 * 
 * @author journeyman
 */
public abstract class DiceN implements Generator<Integer>
{
    /**
     * Gets the dice name.
     * 
     * @return the dX or label of the dice.
     */
    public abstract String getName();
    
    /**
     * Gets the minimum result of this dice.
     * 
     * @return the minimum result of the dice.
     */
    public abstract int getMinResult();
    
    /**
     * Gets the maximum result of this dice.
     * 
     * @return the maximum result of the dice.
     */
    public abstract int getMaxResult();
    
    @Override
    public Optional<SingleResult<Integer>> nextResult()
    {
        int result = ThreadLocalRandom.current().nextInt(getMaxResult());
        result += getMinResult();
        SingleResult<Integer> retVal = new SingleResult<>(getName(), result);
        return Optional.of(retVal);
    }
}
