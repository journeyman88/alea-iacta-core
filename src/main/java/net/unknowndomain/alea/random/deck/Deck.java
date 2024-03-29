/*
 * Copyright 2021 journeyman.
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
package net.unknowndomain.alea.random.deck;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import net.unknowndomain.alea.random.Generator;
import net.unknowndomain.alea.random.SingleResult;

/**
 *
 * @author journeyman
 */
public abstract class Deck<T> implements Generator<T>
{
    public abstract String getName();
    
    public abstract List<T> getContents();

    @Override
    public Optional<SingleResult<T>> nextResult()
    {
        Optional<SingleResult<T>> retVal = Optional.empty();
        if (!getContents().isEmpty())
        {
            Collections.shuffle(getContents());
            var extract = getContents().remove(0);
            var result = new SingleResult<T>(getName(), extract);
            retVal = Optional.ofNullable(result);
        }
        return retVal;
    }
    
}
