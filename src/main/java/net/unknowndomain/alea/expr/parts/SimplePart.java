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

import net.unknowndomain.alea.expr.results.DiceResult;
import net.unknowndomain.alea.expr.results.PartResult;
import net.unknowndomain.alea.random.SingleResult;

/**
 *
 * @author journeyman
 */
public class SimplePart extends DicePart
{
    public SimplePart(String exp)
    {
        super(exp);
    }

    @Override
    public PartResult getResult()
    {
        var res = new DiceResult();
        res.setExpr(getExpr());
        var sum = 0;
        for (var rs : dicePool.getResults())
        {
            sum += rs.getValue();
            res.getResults().add(rs);
        }
        res.setResult((isPositive() ? 1 : -1) * sum);
        return res;
    }
    
}
