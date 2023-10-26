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
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.unknowndomain.alea.expr.results.SuccessResult;
import net.unknowndomain.alea.random.SingleResult;

/**
 *
 * @author journeyman
 */
public class KeepPart extends DicePart
{
    
    private static final Pattern PATTERN = Pattern.compile("^(.*)(k|K)(?<max>\\d+)$");
    private final int maxDice;

    public KeepPart(String exp)
    {
        super(exp);
        var m = PATTERN.matcher(exp);
        m.find();
        maxDice = Integer.parseInt(m.group("max"));
    }

    @Override
    public PartResult getResult()
    {
        var sum = 0;
        var res = new SuccessResult();
        res.setExpr(getExpr());
        var results = dicePool.getResults();
        results.sort((SingleResult<Integer> o1, SingleResult<Integer> o2) ->
        {
            return -1 * o1.getValue().compareTo(o2.getValue());
        });
        var limit = (maxDice > results.size()) ? results.size() : maxDice;
        for (var idx = 0; idx < results.size(); idx++)
        {
            var tmp = results.get(idx);
            if (idx < limit)
            {
                sum += tmp.getValue();
                res.getValidResults().add(tmp);
            }
            else
            {
                res.getDiscardedResults().add(tmp);
            }
        }
        res.setResult((isPositive() ? 1 : -1) * sum);
        return res;
    }
    
}
