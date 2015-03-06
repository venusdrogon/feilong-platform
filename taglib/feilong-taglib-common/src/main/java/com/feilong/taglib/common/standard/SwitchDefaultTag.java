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
package com.feilong.taglib.common.standard;

import javax.servlet.jsp.tagext.Tag;

import com.feilong.taglib.base.BaseTag;

/**
 * default标签 配合switch标签使用.
 *
 * @author 金鑫 2010年3月19日 11:24:28
 */
public class SwitchDefaultTag extends BaseTag{

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /* (non-Javadoc)
     * @see javax.servlet.jsp.tagext.BodyTagSupport#doStartTag()
     */
    @Override
    public int doStartTag(){
        Tag tag = getParent();
        SwitchTag switchTag = (SwitchTag) tag;
        if (!switchTag.isExecuteTag()){
            switchTag.setExecuteTag();
            return EVAL_BODY_INCLUDE;
        }
        return SKIP_BODY;
    }
}
