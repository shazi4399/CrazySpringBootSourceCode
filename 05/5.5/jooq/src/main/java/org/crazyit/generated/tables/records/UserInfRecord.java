/*
 * This file is generated by jOOQ.
 */
package org.crazyit.generated.tables.records;


import org.crazyit.generated.tables.UserInf;
import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record4;
import org.jooq.Row4;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class UserInfRecord extends UpdatableRecordImpl<UserInfRecord> implements Record4<Integer, String, String, Integer> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>springboot.user_inf.user_id</code>.
     */
    public void setUserId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>springboot.user_inf.user_id</code>.
     */
    public Integer getUserId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>springboot.user_inf.name</code>.
     */
    public void setName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>springboot.user_inf.name</code>.
     */
    public String getName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>springboot.user_inf.password</code>.
     */
    public void setPassword(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>springboot.user_inf.password</code>.
     */
    public String getPassword() {
        return (String) get(2);
    }

    /**
     * Setter for <code>springboot.user_inf.age</code>.
     */
    public void setAge(Integer value) {
        set(3, value);
    }

    /**
     * Getter for <code>springboot.user_inf.age</code>.
     */
    public Integer getAge() {
        return (Integer) get(3);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record4 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row4<Integer, String, String, Integer> fieldsRow() {
        return (Row4) super.fieldsRow();
    }

    @Override
    public Row4<Integer, String, String, Integer> valuesRow() {
        return (Row4) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return UserInf.USER_INF.USER_ID;
    }

    @Override
    public Field<String> field2() {
        return UserInf.USER_INF.NAME;
    }

    @Override
    public Field<String> field3() {
        return UserInf.USER_INF.PASSWORD;
    }

    @Override
    public Field<Integer> field4() {
        return UserInf.USER_INF.AGE;
    }

    @Override
    public Integer component1() {
        return getUserId();
    }

    @Override
    public String component2() {
        return getName();
    }

    @Override
    public String component3() {
        return getPassword();
    }

    @Override
    public Integer component4() {
        return getAge();
    }

    @Override
    public Integer value1() {
        return getUserId();
    }

    @Override
    public String value2() {
        return getName();
    }

    @Override
    public String value3() {
        return getPassword();
    }

    @Override
    public Integer value4() {
        return getAge();
    }

    @Override
    public UserInfRecord value1(Integer value) {
        setUserId(value);
        return this;
    }

    @Override
    public UserInfRecord value2(String value) {
        setName(value);
        return this;
    }

    @Override
    public UserInfRecord value3(String value) {
        setPassword(value);
        return this;
    }

    @Override
    public UserInfRecord value4(Integer value) {
        setAge(value);
        return this;
    }

    @Override
    public UserInfRecord values(Integer value1, String value2, String value3, Integer value4) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached UserInfRecord
     */
    public UserInfRecord() {
        super(UserInf.USER_INF);
    }

    /**
     * Create a detached, initialised UserInfRecord
     */
    public UserInfRecord(Integer userId, String name, String password, Integer age) {
        super(UserInf.USER_INF);

        setUserId(userId);
        setName(name);
        setPassword(password);
        setAge(age);
    }
}