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
package net.unknowndomain.alea.systems;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import net.unknowndomain.alea.command.BasicCommand;
import net.unknowndomain.alea.messages.MsgBuilder;
import net.unknowndomain.alea.messages.MsgStyle;
import net.unknowndomain.alea.messages.ReturnMsg;
import org.apache.commons.lang3.StringUtils;

/**
 * This command is used to list all available pluggable RPGs.
 * 
 * @author journeyman
 */
public class ListSystemsCommand extends BasicCommand
{
    
    @Override
    protected String getCommandRegex()
    {
        return "system";
    }

    @Override
    public ReturnMsg execCommand(String cmdLine, Optional<Long> callerId)
    {
        MsgBuilder output = new MsgBuilder();
        List<RpgSystemDescriptor> desc = new ArrayList<>();
        int nameL = 0;
        for (RpgSystemCommand cmd : RpgSystemCommand.LOADER)
        {
            if (nameL < cmd.getCommandDesc().getSystem().length())
            {
                nameL = cmd.getCommandDesc().getSystem().length();
            }
            desc.add(cmd.getCommandDesc());
        }
        desc.sort((RpgSystemDescriptor o1, RpgSystemDescriptor o2) ->
        {
            return o1.getSystem().compareToIgnoreCase(o2.getSystem());
        });
        StringBuilder sb = new StringBuilder("Available systems:\n");
        for (RpgSystemDescriptor d : desc)
        {
            sb.append(" + ").append(StringUtils.rightPad(d.getSystem(), nameL))
                    .append(" [ ").append(d.getShortcut()).
                    append(" | ").append(d.getCommand()).append(" ]\n");
        }
        output.append(sb.toString(), MsgStyle.CODE);
        return output.build();
    }
    
}
