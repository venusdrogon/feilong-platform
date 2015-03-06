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
package com.feilong.commons.core.awt;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;

import org.junit.Test;

import com.feilong.test.TestConstants;

/**
 * The Class WaterMarkTest.
 *
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012 1 26 18:48:11
 */
public class WaterMarkTest{

    /** The target img. */
    private String targetImg = "E:\\DataCommon\\test\\background.png";

    /** The press text. */
    private String pressText = "鑫哥爱feilong";

    /**
     * Press image.
     *
     * @throws IOException
     *             the IO exception
     */
    @Test
    public void pressImage() throws IOException{
        int x = 0;
        int y = 0;
        String fileName = "E:\\DataCommon\\test\\b.png";
        WaterMark.pressImage(targetImg, TestConstants.WATERMARK_PRESSIMG, x, y, fileName);
    }

    /**
     * Press text.
     *
     * @throws IOException
     *             the IO exception
     */
    @Test
    public void pressText() throws IOException{

        int x = 200;
        int y = 30;

        // 默认 雅黑 12 黑色
        Font font = FontUtil.YAHEI_PLAIN_12;
        Color color = ColorUtil.getColor("000000");

        String fileName = "E:\\DataCommon\\test\\b.png";
        WaterMark.pressText(targetImg, pressText, font, color, x, y, fileName);
    }
}
