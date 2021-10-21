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
package net.unknowndomain.alea.expr;

import java.util.List;
import net.unknowndomain.alea.command.PrintableOutput;
import net.unknowndomain.alea.expr.results.PartResult;
import net.unknowndomain.alea.messages.MsgBuilder;
import net.unknowndomain.alea.messages.MsgStyle;
import net.unknowndomain.alea.messages.ReturnMsg;

/**
 *
 * @author journeyman
 */
public class ExpressionResult implements PrintableOutput
{
    private final String exprText;
    private final List<PartResult> results;
    private boolean verbose;
    
    public ExpressionResult(String exprText, List<PartResult> results)
    {
        this.exprText = exprText;
        this.results = results;
    }

    @Override
    public ReturnMsg buildMessage()
    {
        MsgBuilder retVal = new MsgBuilder();
        int total = 0;
        for (PartResult res : results)
        {
            total += res.getResult();
        }
        retVal.append(exprText).append(" = ").append(total, MsgStyle.BOLD);
        if (verbose)
        {
            retVal.appendNewLine();
            for (PartResult res : results)
            {
                res.formatVerbose(retVal);
            }

        }
        return retVal.build();
    }

    public String getExprText()
    {
        return exprText;
    }

    public List<PartResult> getResults()
    {
        return results;
    }

    public boolean isVerbose()
    {
        return verbose;
    }

    public void setVerbose(boolean verbose)
    {
        this.verbose = verbose;
    }
    
}
