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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.unknowndomain.alea.expr.results.SuccessResult;
import net.unknowndomain.alea.random.SingleResult;

/**
 *
 * @author journeyman
 */
public class UpperPart extends DicePart
{
    
    private static final Pattern PATTERN = Pattern.compile("^(.*)\\/(?<threshold>\\d+)$");
    
    private final int threshold;

    public UpperPart(String exp)
    {
        super(exp);
        var m = PATTERN.matcher(exp);
        m.find();
        threshold = Integer.parseInt(m.group("threshold"));
    }

    @Override
    public PartResult getResult()
    {
        var res = new SuccessResult();
        res.setExpr(getExpr());
        var sum = 0;
        for (var rs : dicePool.getResults())
        {
            if (rs.getValue() >= threshold)
            {
                sum ++;
                res.getValidResults().add(rs);
            }
            else
            {
                res.getDiscardedResults().add(rs);
            }
        }
        res.setResult((isPositive() ? 1 : -1) * sum);
        return res;
    }
    
}
