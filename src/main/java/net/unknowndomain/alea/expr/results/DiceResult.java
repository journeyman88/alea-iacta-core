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
package net.unknowndomain.alea.expr.results;

import java.util.LinkedList;
import java.util.List;
import net.unknowndomain.alea.messages.MsgBuilder;
import net.unknowndomain.alea.messages.MsgStyle;
import net.unknowndomain.alea.random.SingleResult;

/**
 *
 * @author m.bignami
 */
public class DiceResult extends PartResult
{
    private List<SingleResult<Integer>> results = new LinkedList<>();
    
    @Override
    public void formatVerbose(MsgBuilder msgBuilder)
    {
        var v = !getResults().isEmpty();
        if (v)
        {
            msgBuilder.append(getExpr());
            msgBuilder.append(" => ");
            var first = true;
            msgBuilder.append("(");
            if (v)
            {
                for (var roll : getResults())
                {
                    if (first)
                    {
                        first = false;
                    }
                    else 
                    {
                        msgBuilder.append(",");
                    }
                    msgBuilder.append(roll.getValue());
                }
            }
            msgBuilder.append(")");
            msgBuilder.appendNewLine();
        }
    }

    public List<SingleResult<Integer>> getResults()
    {
        return results;
    }

    public void setResults(List<SingleResult<Integer>> results)
    {
        this.results = results;
    }
}
