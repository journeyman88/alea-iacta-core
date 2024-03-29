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
package net.unknowndomain.alea.expr;

import java.util.Locale;
import java.util.Optional;
import java.util.UUID;
import net.unknowndomain.alea.command.BasicCommand;
import net.unknowndomain.alea.command.PrintableOutput;
import net.unknowndomain.alea.messages.MsgBuilder;
import net.unknowndomain.alea.messages.MsgStyle;
import net.unknowndomain.alea.messages.ReturnMsg;

/**
 *
 * @author journeyman
 */
public class ExpressionCommand extends BasicCommand
{

    @Override
    protected String getCommandRegex()
    {
        return "expr";
    }

    @Override
    public Optional<PrintableOutput> execCommand(String cmdLine, Optional<UUID> callerId)
    {
        Optional<PrintableOutput> retVal = Optional.empty();
        var prefixMatcher = PREFIX.matcher(cmdLine);
        if (prefixMatcher.matches())
        {
            if (!(cmdLine.contains(CMD_HELP) || cmdLine.contains("-h")))
            {
                var verbose = false;
                var params = prefixMatcher.group(CMD_PARAMS);
                if (cmdLine.contains("-v"))
                {
                    verbose = true;
                    params = params.replaceAll("-v", "");
                }
                var solver = new Expression(params);
                var result = solver.getResult();
                result.setVerbose(verbose);
                retVal = Optional.of(result);
            }
        }
        return retVal;
    }
    
    @Override
    public ReturnMsg printHelp(Locale lang)
    {
        var retVal = new MsgBuilder();
        retVal.append("Expression Engine (expr) Help", MsgStyle.BOLD, MsgStyle.UNDERLINE).append("\n").append("\n");
        var sb = new StringBuilder();
        
        sb.append("The expr command performs and solves a simple set of addition/subtraction operations using the following dice annotation").append("\n");
        sb.append(" * NdX: roll N dice with X faces and sum them (es: 2d12)").append("\n");
        sb.append(" * N: add an Integer number (es: +5, -3)").append("\n");
        sb.append(" * NdXkY: roll N dice with X faces, sum the Y with the highest face value (es: 10d6k8)").append("\n");
        sb.append(" * NdXlY: roll N dice with X faces, sum the Y with the lowest face value (es: 3d12l2)").append("\n");
        sb.append(" * NdX/Y: roll N dice with X faces, count the ones with a face value higher or equal than Y (es: 10d10/7)").append("\n");
        sb.append(" * NdX\\Y: roll N dice with X faces, count the ones with a face value lower or equal than Y (es: 10d10\\4)").append("\n");
        sb.append("These operation can be compounded in any way: 4d4/3  + 10d6k5 - 1d8 - 1d12\\5 +7").append("\n");
        retVal.append(sb.toString(), MsgStyle.CODE);
        return retVal.build();
    }
    
}
