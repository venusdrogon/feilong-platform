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

/**
 * The Class PlaceOfBirth.
 */
@SuppressWarnings("all")
public class PlaceOfBirth{

    /** The city. */
    private String city;

    /** The country. */
    private String country;

    /**
     * The Constructor.
     *
     * @param city
     *            the city
     */
    public PlaceOfBirth(String city){
        this.city = city;
    }

    /**
     * The Constructor.
     *
     * @param city
     *            the city
     * @param country
     *            the country
     */
    public PlaceOfBirth(String city, String country){
        this(city);
        this.country = country;
    }

    /**
     * 获得 city.
     *
     * @return the city
     */
    public String getCity(){
        return city;
    }

    /**
     * 设置 city.
     *
     * @param s
     *            the city
     */
    public void setCity(String s){
        this.city = s;
    }

    /**
     * 获得 country.
     *
     * @return the country
     */
    public String getCountry(){
        return country;
    }

    /**
     * 设置 country.
     *
     * @param country
     *            the country
     */
    public void setCountry(String country){
        this.country = country;
    }
}