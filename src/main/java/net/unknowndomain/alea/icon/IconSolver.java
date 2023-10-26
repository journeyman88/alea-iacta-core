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
package net.unknowndomain.alea.icon;

import java.util.Optional;
import java.util.ServiceLoader;

/**
 * This is both the service entry-point for the pluggable RPGs icons and the basic icon solver.
 * This class w
 * @author journeyman
 */
public abstract class IconSolver
{
    /**
     * This is the service loader used to access the pluggable-RPGs specific icon solver.
     */
    public static final ServiceLoader<IconSolver> LOADER = ServiceLoader.load(IconSolver.class);
    
    public static Optional<AleaImage> loadIcon(AleaIcon icon)
    {
        for(var solver : LOADER)
        {
            if (solver.checkNamespace(icon.getNamespace()))
            {
                return Optional.ofNullable(solver.loadImage(icon));
            }
        }
        return Optional.empty();
    }
    
    public abstract boolean checkNamespace(String namespace);
    
    protected abstract AleaImage loadImage(AleaIcon icon);
    
}
