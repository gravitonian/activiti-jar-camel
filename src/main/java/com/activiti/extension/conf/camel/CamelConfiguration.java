/**
 * Copyright (C) 2016 Alfresco Software Limited.
 * <p/>
 * This file is part of the Alfresco SDK Samples project.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.activiti.extension.conf.camel;

import com.activiti.extension.bean.SomeService;
import com.activiti.extension.camel.route.CamelTaskRouteBuilder;
import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.SimpleRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure Spring with a new Camel spring context.
 *
 * @author martin.bergljung@alfresco.com
 */
@Configuration
public class CamelConfiguration {

    @Bean(name = "camelContext")
    public CamelContext camel() throws Exception{
        SimpleRegistry registry = new SimpleRegistry();
        registry.put("someService", new SomeService());

        CamelContext camelContext = new DefaultCamelContext(registry);
        camelContext.addRoutes(new CamelTaskRouteBuilder());
        camelContext.start();
        return camelContext;
    }
}
