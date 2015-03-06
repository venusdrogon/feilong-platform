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
package org.springframework.expression.inventor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The Class Society.
 */
@SuppressWarnings("all")
public class Society{

    /** The name. */
    private String         name;

    /** The Advisors. */
    public static String   Advisors  = "advisors";

    /** The President. */
    public static String   President = "president";

    /** The members. */
    private List<Inventor> members   = new ArrayList<Inventor>();

    /** The officers. */
    private Map            officers  = new HashMap();

    /**
     * 获得 members.
     *
     * @return the members
     */
    public List getMembers(){
        return members;
    }

    /**
     * 获得 officers.
     *
     * @return the officers
     */
    public Map getOfficers(){
        return officers;
    }

    /**
     * 获得 name.
     *
     * @return the name
     */
    public String getName(){
        return name;
    }

    /**
     * 设置 name.
     *
     * @param name
     *            the name
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Checks if is member.
     *
     * @param name
     *            the name
     * @return true, if checks if is member
     */
    public boolean isMember(String name){
        boolean found = false;
        for (Inventor inventor : members){
            if (inventor.getName().equals(name)){
                found = true;
                break;
            }
        }
        return found;
    }
}