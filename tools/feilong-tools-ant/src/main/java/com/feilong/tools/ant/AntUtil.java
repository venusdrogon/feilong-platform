/*
 * Copyright (C) 2008 feilong (venusdrogon@163.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.feilong.tools.ant;

import java.io.File;

import org.apache.tools.ant.DefaultLogger;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;

/**
 * ant 工具类.
 *
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-11-26 下午01:41:31
 */
public final class AntUtil{

    /**
     * 执行某个任务.
     *
     * @param antFilePath
     *            ant文件路径
     * @param targetName
     *            targetName
     * @param messageOutputLevel
     *            日志级别 *
     *            <P>
     *            Constants for the message levels are in the {@link Project Project} class. The order of the levels, from least to most
     *            verbose, is <code>MSG_ERR</code>, <code>MSG_WARN</code>, <code>MSG_INFO</code>, <code>MSG_VERBOSE</code>,
     *            <code>MSG_DEBUG</code>.
     *            <P>
     *            The default message level for DefaultLogger is Project.MSG_ERR.
     */
    public static void executeTarget(String antFilePath,String targetName,int messageOutputLevel){
        Project project = new Project();
        // 添加日志输出
        DefaultLogger consoleLogger = new DefaultLogger();
        consoleLogger.setErrorPrintStream(System.err);
        consoleLogger.setOutputPrintStream(System.out);
        // 输出信息级别
        consoleLogger.setMessageOutputLevel(messageOutputLevel);
        project.addBuildListener(consoleLogger);

        project.init();

        ProjectHelper projectHelper = ProjectHelper.getProjectHelper();
        File buildFile = new File(antFilePath);

        // see http://yxhcquedu.iteye.com/blog/861110 但是我的层次太多了
        // ProjectHelper.configureProject(project, new File("E:/DataCommon/java/Taglib/Apache Ant/config/build-config-common-nested.xml"));
        // ProjectHelper.configureProject(project, new File("E:/DataCommon/java/Taglib/Apache Ant/config/build-feilong-nested.xml"));
        projectHelper.parse(project, buildFile);
        project.executeTarget(targetName);
    }
}
