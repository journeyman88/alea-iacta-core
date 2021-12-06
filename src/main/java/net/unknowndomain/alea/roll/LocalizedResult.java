/*
 * Copyright 2021 Marco Bignami.
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
package net.unknowndomain.alea.roll;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * This is a result class that implemnts translation-helper methods to be extended by system specific classes.
 * 
 * @author journeyman
 */
public abstract class LocalizedResult extends GenericResult
{
    private Locale lang;
    private ResourceBundle cachedBundle;
    
    protected abstract String getBundleName();
    
    protected ResourceBundle loadBundle()
    {
        if (cachedBundle == null)
        {
            cachedBundle = ResourceBundle.getBundle(getBundleName(), lang);
        }
        return cachedBundle;
    }
    
    protected String translate(String stringId, Object ... args)
    {
        if (loadBundle().containsKey(stringId))
        {
            return String.format(lang, loadBundle().getString(stringId), args);
        }
        return stringId;
    }
    
    protected String translate(String stringId)
    {
        if (loadBundle().containsKey(stringId))
        {
            return loadBundle().getString(stringId);
        }
        return stringId;
    }

    public Locale getLang()
    {
        return lang;
    }

    public void setLang(Locale lang)
    {
        this.lang = lang;
    }
    
}
