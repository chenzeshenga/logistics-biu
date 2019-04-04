package com.abc.chenzeshenga.logistics.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TemplateExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TemplateExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andUuidIsNull() {
            addCriterion("uuid is null");
            return (Criteria) this;
        }

        public Criteria andUuidIsNotNull() {
            addCriterion("uuid is not null");
            return (Criteria) this;
        }

        public Criteria andUuidEqualTo(String value) {
            addCriterion("uuid =", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidNotEqualTo(String value) {
            addCriterion("uuid <>", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidGreaterThan(String value) {
            addCriterion("uuid >", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidGreaterThanOrEqualTo(String value) {
            addCriterion("uuid >=", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidLessThan(String value) {
            addCriterion("uuid <", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidLessThanOrEqualTo(String value) {
            addCriterion("uuid <=", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidLike(String value) {
            addCriterion("uuid like", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidNotLike(String value) {
            addCriterion("uuid not like", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidIn(List<String> values) {
            addCriterion("uuid in", values, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidNotIn(List<String> values) {
            addCriterion("uuid not in", values, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidBetween(String value1, String value2) {
            addCriterion("uuid between", value1, value2, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidNotBetween(String value1, String value2) {
            addCriterion("uuid not between", value1, value2, "uuid");
            return (Criteria) this;
        }

        public Criteria andCreate_onIsNull() {
            addCriterion("create_on is null");
            return (Criteria) this;
        }

        public Criteria andCreate_onIsNotNull() {
            addCriterion("create_on is not null");
            return (Criteria) this;
        }

        public Criteria andCreate_onEqualTo(Date value) {
            addCriterion("create_on =", value, "create_on");
            return (Criteria) this;
        }

        public Criteria andCreate_onNotEqualTo(Date value) {
            addCriterion("create_on <>", value, "create_on");
            return (Criteria) this;
        }

        public Criteria andCreate_onGreaterThan(Date value) {
            addCriterion("create_on >", value, "create_on");
            return (Criteria) this;
        }

        public Criteria andCreate_onGreaterThanOrEqualTo(Date value) {
            addCriterion("create_on >=", value, "create_on");
            return (Criteria) this;
        }

        public Criteria andCreate_onLessThan(Date value) {
            addCriterion("create_on <", value, "create_on");
            return (Criteria) this;
        }

        public Criteria andCreate_onLessThanOrEqualTo(Date value) {
            addCriterion("create_on <=", value, "create_on");
            return (Criteria) this;
        }

        public Criteria andCreate_onIn(List<Date> values) {
            addCriterion("create_on in", values, "create_on");
            return (Criteria) this;
        }

        public Criteria andCreate_onNotIn(List<Date> values) {
            addCriterion("create_on not in", values, "create_on");
            return (Criteria) this;
        }

        public Criteria andCreate_onBetween(Date value1, Date value2) {
            addCriterion("create_on between", value1, value2, "create_on");
            return (Criteria) this;
        }

        public Criteria andCreate_onNotBetween(Date value1, Date value2) {
            addCriterion("create_on not between", value1, value2, "create_on");
            return (Criteria) this;
        }

        public Criteria andFile_nameIsNull() {
            addCriterion("file_name is null");
            return (Criteria) this;
        }

        public Criteria andFile_nameIsNotNull() {
            addCriterion("file_name is not null");
            return (Criteria) this;
        }

        public Criteria andFile_nameEqualTo(String value) {
            addCriterion("file_name =", value, "file_name");
            return (Criteria) this;
        }

        public Criteria andFile_nameNotEqualTo(String value) {
            addCriterion("file_name <>", value, "file_name");
            return (Criteria) this;
        }

        public Criteria andFile_nameGreaterThan(String value) {
            addCriterion("file_name >", value, "file_name");
            return (Criteria) this;
        }

        public Criteria andFile_nameGreaterThanOrEqualTo(String value) {
            addCriterion("file_name >=", value, "file_name");
            return (Criteria) this;
        }

        public Criteria andFile_nameLessThan(String value) {
            addCriterion("file_name <", value, "file_name");
            return (Criteria) this;
        }

        public Criteria andFile_nameLessThanOrEqualTo(String value) {
            addCriterion("file_name <=", value, "file_name");
            return (Criteria) this;
        }

        public Criteria andFile_nameLike(String value) {
            addCriterion("file_name like", value, "file_name");
            return (Criteria) this;
        }

        public Criteria andFile_nameNotLike(String value) {
            addCriterion("file_name not like", value, "file_name");
            return (Criteria) this;
        }

        public Criteria andFile_nameIn(List<String> values) {
            addCriterion("file_name in", values, "file_name");
            return (Criteria) this;
        }

        public Criteria andFile_nameNotIn(List<String> values) {
            addCriterion("file_name not in", values, "file_name");
            return (Criteria) this;
        }

        public Criteria andFile_nameBetween(String value1, String value2) {
            addCriterion("file_name between", value1, value2, "file_name");
            return (Criteria) this;
        }

        public Criteria andFile_nameNotBetween(String value1, String value2) {
            addCriterion("file_name not between", value1, value2, "file_name");
            return (Criteria) this;
        }

        public Criteria andUuidLikeInsensitive(String value) {
            addCriterion("upper(uuid) like", value.toUpperCase(), "uuid");
            return (Criteria) this;
        }

        public Criteria andFile_nameLikeInsensitive(String value) {
            addCriterion("upper(file_name) like", value.toUpperCase(), "file_name");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}