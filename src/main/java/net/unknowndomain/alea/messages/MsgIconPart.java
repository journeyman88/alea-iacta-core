/*
 * Copyright 2022 Marco Bignami.
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
package net.unknowndomain.alea.messages;

import net.unknowndomain.alea.icon.AleaIcon;

/**
 * An MsgPart created to wrap a file.
 * 
 * @author journeyman
 */
public class MsgIconPart implements MsgPart
{
    private final AleaIcon icon;
    
    /**
     * Builds the part using a namespace and the id.
     * 
     * @param namespace namespace
     * @param iconId iconId
     */
    protected MsgIconPart(String namespace, String iconId)
    {
        this.icon = new AleaIcon(namespace, iconId);
    }
            
    /**
     * Builds the part using the Icon.
     * 
     * @param icon icon
     */
    protected MsgIconPart(AleaIcon icon)
    {
        this.icon = icon;
    }

    public AleaIcon getIcon()
    {
        return icon;
    }

}
