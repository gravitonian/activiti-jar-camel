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

package com.activiti.extension.camel.route;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;

/**
 * Camel route config that sets up an Activiti Service task as
 * a Camel 'from' endpoint. When the route is invoked via the
 * service task it will log a message, then copy a file from
 * one directory to another.
 *
 * @author martin.bergljung@alfresco.com
 */
public class CamelTaskRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {
//        from("direct:start")
  //              .setBody().simple("resource:classpath:data/page.xml")
    //            .unmarshal(jaxb)
      //          .log(LoggingLevel.DEBUG, "Testing workflow creation from a Camel route")
        //        .to("activiti:camelTaskTestProcess");

        // Starting an Activiti process
        from("direct:start")
        .log(LoggingLevel.INFO, "Testing starting workflow from Camel route")
        .to("activiti:testCamelTask");


        // Activiti endpoint
        // camel endpoint:processId:camelServiceTaskId?
        String fromActivitiEndPoint = "activiti:testCamelTask:sendMsgToCamel?copyCamelBodyToBody=true";

        // File endpoints
        String dirSource = "/home/martin/temp/";
        String dirTarget = dirSource + "target/";
        String fileName = "some.txt";
        String logMsg = "Testing Apache Camel route invocation from an Activiti Camel service task.";
        String logMsg2 = "Testing Apache Camel route invocation from a file.";
        String fromFileEndpoint = String.format("file://%s?fileName=%s&noop=true", dirSource, fileName);
        String toFileEndpoint = String.format("file://%s?fileName=%s", dirTarget, fileName);
        String toBeanEndpoint = "bean:someService?method=process";

        from(fromActivitiEndPoint).log(LoggingLevel.INFO, logMsg).to(toBeanEndpoint);
        from(fromFileEndpoint).log(LoggingLevel.INFO, logMsg2).to(toFileEndpoint);
    }
}
