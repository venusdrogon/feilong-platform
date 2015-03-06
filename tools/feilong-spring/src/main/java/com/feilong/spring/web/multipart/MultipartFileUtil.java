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
package com.feilong.spring.web.multipart;

import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.feilong.commons.core.io.FileUtil;

/**
 * The Class MultipartFileUtil.
 *
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.9 2014年12月14日 下午7:51:19
 * @since 1.0.9
 */
public final class MultipartFileUtil{

    /** The Constant log. */
    private static final Logger log = LoggerFactory.getLogger(MultipartFileUtil.class);

    /**
     * 获得 multipart file info map for log map.
     *
     * @param importFile
     *            the import file
     * @return the multipart file info map for log map
     * @since 1.0.9
     */
    public static Map<String, Object> getMultipartFileInfoMapForLogMap(MultipartFile importFile){
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("importFile.getContentType()", importFile.getContentType());
        map.put("importFile.getName()", importFile.getName());
        map.put("importFile.getOriginalFilename()", importFile.getOriginalFilename());
        map.put("importFile.getSize()", FileUtil.formatSize(importFile.getSize()));
        map.put("importFile.isEmpty()", importFile.isEmpty());
        return map;
    }
}
