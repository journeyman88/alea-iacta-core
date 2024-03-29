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
package net.unknowndomain.alea.expr.parts;

import net.unknowndomain.alea.expr.results.PartResult;

/**
 *
 * @author journeyman
 */
public abstract class ExpPart
{
    private final boolean positive;
    private final String part;
    
    protected ExpPart(String exp)
    {
        positive = !exp.startsWith("-");
        var prefix = "";
        if ((positive) && !exp.startsWith("+"))
        {
            prefix += "+";
        }
        part = prefix + exp;
    }
    
    public abstract PartResult getResult();

    public boolean isPositive()
    {
        return positive;
    }

    public String getExpr()
    {
        return part;
    }
    
}
