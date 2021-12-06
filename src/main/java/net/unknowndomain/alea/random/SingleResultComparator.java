/*
 * Copyright 2021 m.bignami.
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

import java.util.Comparator;

/**
 *
 * @author m.bignami
 * @param <T>
 */
public class SingleResultComparator<T extends Comparable> implements Comparator<SingleResult<T>>
{
    
    private final boolean desc;
    
    public SingleResultComparator()
    {
        this(false);
    }
    
    public SingleResultComparator(boolean desc)
    {
        this.desc = desc;
    }

    @Override
    public int compare(SingleResult<T> o1, SingleResult<T> o2)
    {
        return (desc?-1:1) * o1.getValue().compareTo(o2.getValue());
    }
    
}
