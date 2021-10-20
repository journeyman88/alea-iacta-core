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
package net.unknowndomain.alea.random;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * This is a generic DicePool implementation.
 * 
 * @author journeyman
 * @param <T> The GenericDice to use for building the results.
 * @param <V> The class of a single result.
 */
public class GenericPool<T extends Generator<V>, V>
{
    private final T diceClass;
    private final Integer numberOfDice;
    private final Set<V> explodingValues;
    
    /**
     * Builds the GenericPool.
     * 
     * @param diceClass the instance of a Generic Dice to be used in the pool rool.
     * @param numberOfDice the number of dice that builds the pool.
     * @param explodingValues the set of values that cause dice explosion in varargs form.
     */
    public GenericPool(T diceClass, int numberOfDice, V ... explodingValues)
    {
        this(diceClass, numberOfDice, Arrays.asList(explodingValues));
    }
    
    /**
     * Builds the GenericPool.
     * 
     * @param diceClass the instance of a Generic Dice to be used in the pool rool.
     * @param numberOfDice the number of dice that builds the pool.
     * @param explodingValues the set of values that cause dice explosion as a Collection.
     */
    public GenericPool(T diceClass, int numberOfDice, Collection<V> explodingValues)
    {
        this.diceClass = diceClass;
        this.numberOfDice = numberOfDice;
        Set<V> tmpSet = new HashSet<>();
        if ((explodingValues != null) && !explodingValues.isEmpty())
        {
            tmpSet.addAll(explodingValues);
        }
        this.explodingValues = Collections.unmodifiableSet(tmpSet);
    }
    
    /**
     * Gets a list of results for this pool.
     * 
     * This method toss the dice contained in the pool to determine the results,
     * also checking if any single result matches the Set of results that cause 
     * a 'dice explosion'.
     * 
     * @return the results.
     */
    public List<SingleResult<V>> getResults()
    {
        List<SingleResult<V>> results = new LinkedList<>();
        int idx = 0;
        while(idx < numberOfDice)
        {
            idx++;
            Optional<SingleResult<V>> result = diceClass.nextResult();
            if (result.isPresent())
            {
                SingleResult<V> res = result.get();
                if (explodingValues.contains(res.getValue()))
                {
                    idx--;
                }
                results.add(res);
            }
            else
            {
                break;
            }
        }
        return results;
    }
}
