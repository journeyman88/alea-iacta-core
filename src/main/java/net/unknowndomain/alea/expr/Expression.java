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

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author journeyman
 */
public class Expression
{
    private static final Pattern KEEP_PATTERN = Pattern.compile("(?<keep>(\\+|-?)\\d+(d|D)(\\d+|F)(k|K)\\d+)");
    private static final Pattern DROP_PATTERN = Pattern.compile("(?<drop>(\\+|-?)\\d+(d|D)(\\d+|F)(l|L)\\d+)");
    private static final Pattern UPPER_PATTERN = Pattern.compile("(?<upper>(\\+|-?)\\d+(d|D)(\\d+|F)\\/\\d+)");
    private static final Pattern LOWER_PATTERN = Pattern.compile("(?<lower>(\\+|-?)\\d+(d|D)(\\d+|F)\\\\\\d+)");
    private static final Pattern DICE_PATTERN = Pattern.compile("(?<dice>(\\+|-?)\\d+(d|D)(\\d+|F))");
    private static final Pattern MOD_PATTERN = Pattern.compile("(?<mod>(\\+|-?)\\d+)");
    
    private final List<ExpPart> parts = new ArrayList<>();
    
    public Expression(String input)
    {
        String expr = input.replaceAll(" ", "");
        String modExpr = expr;
        Matcher keepMatch = KEEP_PATTERN.matcher(modExpr);
        while(keepMatch.find())
        {
            String keepEx = keepMatch.group("keep");
            parts.add(new KeepPart(keepEx));
        }
        modExpr = keepMatch.replaceAll("");
        Matcher dropMatch = DROP_PATTERN.matcher(modExpr);
        while(dropMatch.find())
        {
            String keepEx = dropMatch.group("drop");
            parts.add(new DropPart(keepEx));
        }
        modExpr = dropMatch.replaceAll("");
        Matcher upprMatch = UPPER_PATTERN.matcher(modExpr);
        while(upprMatch.find())
        {
            String upprEx = upprMatch.group("upper");
            parts.add(new UpperPart(upprEx));
        }
        modExpr = upprMatch.replaceAll("");
        Matcher lowrMatch = LOWER_PATTERN.matcher(modExpr);
        while(lowrMatch.find())
        {
            String lowrEx = lowrMatch.group("lower");
            parts.add(new LowerPart(lowrEx));
        }
        modExpr = lowrMatch.replaceAll("");
        Matcher diceMatch = DICE_PATTERN.matcher(modExpr);
        while(diceMatch.find())
        {
            String diceEx = diceMatch.group("dice");
            parts.add(new SimplePart(diceEx));
        }
        modExpr = diceMatch.replaceAll("");
        Matcher modMatch = MOD_PATTERN.matcher(modExpr);
        while(modMatch.find())
        {
            String modEx = modMatch.group("mod");
            parts.add(new ModPart(modEx));
        }
    }
            
    public void addPart(ExpPart part)
    {
        parts.add(part);
    }
    
    public String getExpression()
    {
        StringBuilder sb = new StringBuilder();
        for (ExpPart p : parts)
        {
            sb.append(p.getExpr());
        }
        return sb.toString();
    }
    
    public List<ExpResult> getResults()
    {
        List<ExpResult> result = new ArrayList<>(parts.size());
        for (ExpPart p : parts)
        {
            result.add(p.getResult());
        }
        return result;
    }
    
}
