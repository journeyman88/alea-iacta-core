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
package net.unknowndomain.alea.cache;

import java.util.UUID;
import java.util.concurrent.TimeUnit;
import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.CompleteConfiguration;
import javax.cache.configuration.FactoryBuilder;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.Duration;
import javax.cache.expiry.ModifiedExpiryPolicy;
import javax.cache.spi.CachingProvider;
import net.unknowndomain.alea.roll.GenericResult;
import net.unknowndomain.alea.systems.RpgSystemCommand;

/**
 *
 * @author m.bignami
 */
public class CacheHelper {
    
    private static final String RPG_CACHE_NAME = RpgSystemCommand.class.getName() + "_" + UUID.randomUUID().toString();
    
    public static Cache<UUID, GenericResult> getRpgCache()
    {
        CachingProvider cachingProvider = Caching.getCachingProvider();
        CacheManager cacheManager = cachingProvider.getCacheManager();
        Cache<UUID, GenericResult> systemCache = cacheManager.getCache(RPG_CACHE_NAME, UUID.class, GenericResult.class);
        if (systemCache == null)
        {
            systemCache = setupCache(getDefaultConfig());
        }
        return systemCache;
    }
    
    public static Cache<UUID, GenericResult> setupCache(CompleteConfiguration<UUID, GenericResult> config)
    {
        CachingProvider cachingProvider = Caching.getCachingProvider();
        CacheManager cacheManager = cachingProvider.getCacheManager();
        return cacheManager.createCache(RPG_CACHE_NAME, config);
    }
    
    public static CompleteConfiguration<UUID, GenericResult> getDefaultConfig()
    {
        MutableConfiguration<UUID, GenericResult> config = new MutableConfiguration<>();
        config.setExpiryPolicyFactory(FactoryBuilder.factoryOf(new ModifiedExpiryPolicy(new Duration( TimeUnit.MINUTES, 2 ))));
        return config;
    }
}
