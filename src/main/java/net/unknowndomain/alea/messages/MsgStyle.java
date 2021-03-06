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
package net.unknowndomain.alea.messages;

/**
 *
 * @author journeyman
 */
public enum MsgStyle
{
    /**
     * Renders the text as Bold.
     */
    BOLD,
    /**
     * Renders the text as Italic.
     */
    ITALIC,
    /**
     * Renders the text as Underline.
     */
    UNDERLINE,
    /**
     * Renders the text as Strikethrough.
     */
    STRIKETHRU,
    /**
     * Renders the text as a Code block.
     */
    CODE,
    /**
     * Marks the text as a Spoiler.
     */
    SPOILER
}
