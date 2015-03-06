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
package com.feilong.tools.om.nginx.command;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * StubStatusVMCommand.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Jan 1, 2014 12:47:52 AM
 */
public final class StubStatusVMCommand implements Serializable{

    /** The Constant serialVersionUID. */
    private static final long       serialVersionUID = 288232184048495608L;

    /** 第一条记录时间, 开始抓取的时间. */
    private Date                    beginDate;

    /** 最后一条记录的时间, 结束抓取的时间. */
    private Date                    endDate;

    /** 最大值的时候. */
    private StubStatusCommand       maxActiveConnectionsStubStatusCommand;

    /** 最小值的时候. */
    private StubStatusCommand       minActiveConnectionsStubStatusCommand;

    /** The nginx stub status command list. */
    private List<StubStatusCommand> stubStatusCommandList;

    /**
     * Gets the 第一条记录时间, 开始抓取的时间.
     * 
     * @return the beginDate
     */
    public Date getBeginDate(){
        return beginDate;
    }

    /**
     * Sets the 第一条记录时间, 开始抓取的时间.
     * 
     * @param beginDate
     *            the beginDate to set
     */
    public void setBeginDate(Date beginDate){
        this.beginDate = beginDate;
    }

    /**
     * Gets the 最后一条记录的时间, 结束抓取的时间.
     * 
     * @return the endDate
     */
    public Date getEndDate(){
        return endDate;
    }

    /**
     * Sets the 最后一条记录的时间, 结束抓取的时间.
     * 
     * @param endDate
     *            the endDate to set
     */
    public void setEndDate(Date endDate){
        this.endDate = endDate;
    }

    /**
     * Gets the nginx stub status command list.
     * 
     * @return the stubStatusCommandList
     */
    public List<StubStatusCommand> getStubStatusCommandList(){
        return stubStatusCommandList;
    }

    /**
     * Sets the nginx stub status command list.
     * 
     * @param stubStatusCommandList
     *            the stubStatusCommandList to set
     */
    public void setStubStatusCommandList(List<StubStatusCommand> stubStatusCommandList){
        this.stubStatusCommandList = stubStatusCommandList;
    }

    /**
     * Gets the 最大值的时候.
     * 
     * @return the maxActiveConnectionsStubStatusCommand
     */
    public StubStatusCommand getMaxActiveConnectionsStubStatusCommand(){
        return maxActiveConnectionsStubStatusCommand;
    }

    /**
     * Sets the 最大值的时候.
     * 
     * @param maxActiveConnectionsStubStatusCommand
     *            the maxActiveConnectionsStubStatusCommand to set
     */
    public void setMaxActiveConnectionsStubStatusCommand(StubStatusCommand maxActiveConnectionsStubStatusCommand){
        this.maxActiveConnectionsStubStatusCommand = maxActiveConnectionsStubStatusCommand;
    }

    /**
     * Gets the 最小值的时候.
     * 
     * @return the minActiveConnectionsStubStatusCommand
     */
    public StubStatusCommand getMinActiveConnectionsStubStatusCommand(){
        return minActiveConnectionsStubStatusCommand;
    }

    /**
     * Sets the 最小值的时候.
     * 
     * @param minActiveConnectionsStubStatusCommand
     *            the minActiveConnectionsStubStatusCommand to set
     */
    public void setMinActiveConnectionsStubStatusCommand(StubStatusCommand minActiveConnectionsStubStatusCommand){
        this.minActiveConnectionsStubStatusCommand = minActiveConnectionsStubStatusCommand;
    }

}