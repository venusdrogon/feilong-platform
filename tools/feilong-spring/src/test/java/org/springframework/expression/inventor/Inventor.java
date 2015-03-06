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

import java.util.Date;
import java.util.GregorianCalendar;

/**
 * The Class Inventor.
 */
@SuppressWarnings("all")
public class Inventor{

    /** The name. */
    private String       name;

    /** The nationality. */
    private String       nationality;

    /** The inventions. */
    private String[]     inventions;

    /** The birthdate. */
    private Date         birthdate;

    /** The place of birth. */
    private PlaceOfBirth placeOfBirth;

    /**
     * The Constructor.
     *
     * @param name
     *            the name
     * @param nationality
     *            the nationality
     */
    public Inventor(String name, String nationality){
        GregorianCalendar c = new GregorianCalendar();
        this.name = name;
        this.nationality = nationality;
        this.birthdate = c.getTime();
    }

    /**
     * The Constructor.
     *
     * @param name
     *            the name
     * @param birthdate
     *            the birthdate
     * @param nationality
     *            the nationality
     */
    public Inventor(String name, Date birthdate, String nationality){
        this.name = name;
        this.nationality = nationality;
        this.birthdate = birthdate;
    }

    /**
     * The Constructor.
     */
    public Inventor(){
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
     * 获得 nationality.
     *
     * @return the nationality
     */
    public String getNationality(){
        return nationality;
    }

    /**
     * 设置 nationality.
     *
     * @param nationality
     *            the nationality
     */
    public void setNationality(String nationality){
        this.nationality = nationality;
    }

    /**
     * 获得 birthdate.
     *
     * @return the birthdate
     */
    public Date getBirthdate(){
        return birthdate;
    }

    /**
     * 设置 birthdate.
     *
     * @param birthdate
     *            the birthdate
     */
    public void setBirthdate(Date birthdate){
        this.birthdate = birthdate;
    }

    /**
     * 获得 place of birth.
     *
     * @return the place of birth
     */
    public PlaceOfBirth getPlaceOfBirth(){
        return placeOfBirth;
    }

    /**
     * 设置 place of birth.
     *
     * @param placeOfBirth
     *            the place of birth
     */
    public void setPlaceOfBirth(PlaceOfBirth placeOfBirth){
        this.placeOfBirth = placeOfBirth;
    }

    /**
     * 设置 inventions.
     *
     * @param inventions
     *            the inventions
     */
    public void setInventions(String[] inventions){
        this.inventions = inventions;
    }

    /**
     * 获得 inventions.
     *
     * @return the inventions
     */
    public String[] getInventions(){
        return inventions;
    }
}