/*
 * Copyright 2022 Marco Bignami.
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
package net.unknowndomain.alea.random.customdice;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import net.unknowndomain.alea.random.Generator;
import net.unknowndomain.alea.random.SingleResult;

/**
 *
 * @author m.bignami
 * @param <T>
 */
public abstract class CustomDice <T extends Object> implements Generator<List<T>>
{

    public abstract String getName();

    public abstract List<CustomFace<T>> getFaces();

    @Override
    public Optional<SingleResult<List<T>>> nextResult()
    {
        var result = ThreadLocalRandom.current().nextInt(getFaces().size());
        var retVal = new SingleResult<List<T>>(getName(), getFaces().get(result).getFaceValues());
        return Optional.of(retVal);
    }
    
}
