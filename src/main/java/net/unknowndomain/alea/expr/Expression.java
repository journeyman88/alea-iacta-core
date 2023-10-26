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

import net.unknowndomain.alea.expr.parts.DropPart;
import net.unknowndomain.alea.expr.results.PartResult;
import net.unknowndomain.alea.expr.parts.UpperPart;
import net.unknowndomain.alea.expr.parts.KeepPart;
import net.unknowndomain.alea.expr.parts.SimplePart;
import net.unknowndomain.alea.expr.parts.ExpPart;
import net.unknowndomain.alea.expr.parts.LowerPart;
import net.unknowndomain.alea.expr.parts.ModPart;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static java.util.regex.Pattern.compile;

/**
 *
 * @author journeyman
 */
public class Expression
{
    private static final Pattern KEEP_PATTERN = compile("(?<keep>(\\+|-?)\\d+(d|D)(\\d+|F)(k|K)\\d+)");
    private static final Pattern DROP_PATTERN = compile("(?<drop>(\\+|-?)\\d+(d|D)(\\d+|F)(l|L)\\d+)");
    private static final Pattern UPPER_PATTERN = compile("(?<upper>(\\+|-?)\\d+(d|D)(\\d+|F)\\/\\d+)");
    private static final Pattern LOWER_PATTERN = compile("(?<lower>(\\+|-?)\\d+(d|D)(\\d+|F)\\\\\\d+)");
    private static final Pattern DICE_PATTERN = compile("(?<dice>(\\+|-?)\\d+(d|D)(\\d+|F))");
    private static final Pattern MOD_PATTERN = compile("(?<mod>(\\+|-?)\\d+)");
    
    private final List<ExpPart> parts = new ArrayList<>();
    
    public Expression(String input)
    {
        var expr = input.replaceAll(" ", "");
        var modExpr = expr;
        var keepMatch = KEEP_PATTERN.matcher(modExpr);
        while(keepMatch.find())
        {
            var keepEx = keepMatch.group("keep");
            parts.add(new KeepPart(keepEx));
        }
        modExpr = keepMatch.replaceAll("");
        var dropMatch = DROP_PATTERN.matcher(modExpr);
        while(dropMatch.find())
        {
            var keepEx = dropMatch.group("drop");
            parts.add(new DropPart(keepEx));
        }
        modExpr = dropMatch.replaceAll("");
        var upprMatch = UPPER_PATTERN.matcher(modExpr);
        while(upprMatch.find())
        {
            var upprEx = upprMatch.group("upper");
            parts.add(new UpperPart(upprEx));
        }
        modExpr = upprMatch.replaceAll("");
        var lowrMatch = LOWER_PATTERN.matcher(modExpr);
        while(lowrMatch.find())
        {
            var lowrEx = lowrMatch.group("lower");
            parts.add(new LowerPart(lowrEx));
        }
        modExpr = lowrMatch.replaceAll("");
        var diceMatch = DICE_PATTERN.matcher(modExpr);
        while(diceMatch.find())
        {
            var diceEx = diceMatch.group("dice");
            parts.add(new SimplePart(diceEx));
        }
        modExpr = diceMatch.replaceAll("");
        var modMatch = MOD_PATTERN.matcher(modExpr);
        while(modMatch.find())
        {
            var modEx = modMatch.group("mod");
            parts.add(new ModPart(modEx));
        }
    }
            
    public void addPart(ExpPart part)
    {
        parts.add(part);
    }
    
    public String getExpression()
    {
        var sb = new StringBuilder();
        for (var p : parts)
        {
            sb.append(p.getExpr());
        }
        return sb.toString();
    }
    
    public List<PartResult> getResults()
    {
        List<PartResult> result = new ArrayList<>(parts.size());
        for (var p : parts)
        {
            result.add(p.getResult());
        }
        return result;
    }
    
    public ExpressionResult getResult()
    {
        return new ExpressionResult(getExpression(), getResults());
    }
    
}
