/*
 * Copyright 2020 journeyman.
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

import net.unknowndomain.alea.messages.MsgBuilder;

/**
 *
 * @author journeyman
 */
public abstract class PartResult
{
    private Integer result;
    private String expr;

    public abstract void formatVerbose(MsgBuilder msgBuilder);
    
    public Integer getResult()
    {
        return result;
    }

    public void setResult(Integer result)
    {
        this.result = result;
    }

    public String getExpr()
    {
        return expr;
    }

    public void setExpr(String expr)
    {
        this.expr = expr;
    }
}
