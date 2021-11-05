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
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;
import net.unknowndomain.alea.command.BasicCommand;
import net.unknowndomain.alea.command.PrintableOutput;
import net.unknowndomain.alea.command.SimpleOutput;
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
    public Optional<PrintableOutput> execCommand(String cmdLine, Optional<UUID> callerId)
    {
        MsgBuilder output = new MsgBuilder();
        List<RpgSystemDescriptor> desc = new ArrayList<>();
        int nameL = 0;
        int linkL = 0;
        for (RpgSystemCommand cmd : RpgSystemCommand.LOADER)
        {
            if (nameL < cmd.getCommandDesc().getSystem().length())
            {
                nameL = cmd.getCommandDesc().getSystem().length();
            }
            if (linkL < cmd.getCommandDesc().getShortcut().length())
            {
                linkL = cmd.getCommandDesc().getShortcut().length();
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
                    .append(" [ ").append(StringUtils.rightPad(d.getShortcut(), linkL)).
                    append(" | ").append(d.getCommand()).append(" ]\n");
        }
        output.append(sb.toString(), MsgStyle.CODE);
        return Optional.of(new SimpleOutput(output.build()));
    }

    @Override
    public ReturnMsg printHelp(Locale lang)
    {
        MsgBuilder msgBuilder = new MsgBuilder();
        return msgBuilder.build();
    }
    
}
