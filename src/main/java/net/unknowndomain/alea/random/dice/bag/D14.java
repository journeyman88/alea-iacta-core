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
package net.unknowndomain.alea.random.dice.bag;

import net.unknowndomain.alea.random.dice.DiceN;

/**
 *
 * @author journeyman
 */
public class D14 extends DiceN
{
    /**
     * The instance of this dice.
     */
    public static final D14 INSTANCE = new D14();
    
    private D14()
    {
    }
    
    @Override
    public String getName()
    {
        return "d14";
    }

    @Override
    public int getMinResult()
    {
        return 1;
    }

    @Override
    public int getMaxResult()
    {
        return 14;
    }
    
}
