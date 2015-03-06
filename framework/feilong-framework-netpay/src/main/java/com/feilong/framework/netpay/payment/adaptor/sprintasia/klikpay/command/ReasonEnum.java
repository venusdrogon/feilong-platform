/**
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
package com.feilong.framework.netpay.payment.adaptor.sprintasia.klikpay.command;

/**
 * klikpay 失败的原因
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Apr 7, 2014 9:00:13 PM
 */
public enum ReasonEnum{

    /** The SUCCESS. */
    SUCCESS("Success","Sukses"),

    /** The CANCELED. */
    //	Order has been canceled by admin*
    CANCELED("Your transaction has been canceled","Transaksi anda sudah dibatalkan"),

    /** The PAID. */
    //Order has been paid before
    PAID("Your transaction has been paid","Transaksi anda sudah dibayar"),

    /**
     * The transaction has expired or exceeded Time limit given..
     */
    //Order time expired*
    EXPIRED("Your transaction already expired","Transaksi anda telah kedaluarsa"),

    /** The CANNOTBEPROCESSED,Technical issues . */
    //Any other technical problem
    CANNOTBEPROCESSED("Your transaction cannot be processed","Transaksi anda tidak dapat diproses");

    //	Order item currently out of stock*
    //
    //	Transaksi anda tidak dapat diproses
    //
    //	Your transaction cannot be processed

    // ***********************************************************************

    /** The english. */
    private String english;

    /** The indonesian. */
    private String indonesian;

    /**
     * Instantiates a new reason enum.
     * 
     * @param english
     *            the english
     * @param indonesian
     *            the indonesian
     */
    private ReasonEnum(String english, String indonesian){
        this.english = english;
        this.indonesian = indonesian;
    }

    /**
     * Gets the indonesian.
     * 
     * @return the indonesian
     */
    public String getIndonesian(){
        return indonesian;
    }

    /**
     * Sets the indonesian.
     * 
     * @param indonesian
     *            the indonesian to set
     */
    public void setIndonesian(String indonesian){
        this.indonesian = indonesian;
    }

    /**
     * Gets the english.
     * 
     * @return the english
     */
    public String getEnglish(){
        return english;
    }

    /**
     * Sets the english.
     * 
     * @param english
     *            the english to set
     */
    public void setEnglish(String english){
        this.english = english;
    }
}
