package com.shop.Juliicasta.Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Iterator;

public class ProductExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table product
	 * @mbg.generated  Wed Jul 06 20:47:06 ICT 2022
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table product
	 * @mbg.generated  Wed Jul 06 20:47:06 ICT 2022
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table product
	 * @mbg.generated  Wed Jul 06 20:47:06 ICT 2022
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table product
	 * @mbg.generated  Wed Jul 06 20:47:06 ICT 2022
	 */
	public ProductExample() {
		oredCriteria = new ArrayList<>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table product
	 * @mbg.generated  Wed Jul 06 20:47:06 ICT 2022
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table product
	 * @mbg.generated  Wed Jul 06 20:47:06 ICT 2022
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table product
	 * @mbg.generated  Wed Jul 06 20:47:06 ICT 2022
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table product
	 * @mbg.generated  Wed Jul 06 20:47:06 ICT 2022
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table product
	 * @mbg.generated  Wed Jul 06 20:47:06 ICT 2022
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table product
	 * @mbg.generated  Wed Jul 06 20:47:06 ICT 2022
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table product
	 * @mbg.generated  Wed Jul 06 20:47:06 ICT 2022
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table product
	 * @mbg.generated  Wed Jul 06 20:47:06 ICT 2022
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table product
	 * @mbg.generated  Wed Jul 06 20:47:06 ICT 2022
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table product
	 * @mbg.generated  Wed Jul 06 20:47:06 ICT 2022
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table product
	 * @mbg.generated  Wed Jul 06 20:47:06 ICT 2022
	 */
	protected abstract static class GeneratedCriteria {
		protected List<Criterion> criteria;

		protected GeneratedCriteria() {
			super();
			criteria = new ArrayList<>();
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

		protected void addCriterionForJDBCDate(String condition, Date value, String property) {
			if (value == null) {
				throw new RuntimeException("Value for " + property + " cannot be null");
			}
			addCriterion(condition, new java.sql.Date(value.getTime()), property);
		}

		protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
			if (values == null || values.size() == 0) {
				throw new RuntimeException("Value list for " + property + " cannot be null or empty");
			}
			List<java.sql.Date> dateList = new ArrayList<>();
			Iterator<Date> iter = values.iterator();
			while (iter.hasNext()) {
				dateList.add(new java.sql.Date(iter.next().getTime()));
			}
			addCriterion(condition, dateList, property);
		}

		protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
			if (value1 == null || value2 == null) {
				throw new RuntimeException("Between values for " + property + " cannot be null");
			}
			addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
		}

		public Criteria andIdIsNull() {
			addCriterion("ID is null");
			return (Criteria) this;
		}

		public Criteria andIdIsNotNull() {
			addCriterion("ID is not null");
			return (Criteria) this;
		}

		public Criteria andIdEqualTo(Integer value) {
			addCriterion("ID =", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotEqualTo(Integer value) {
			addCriterion("ID <>", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThan(Integer value) {
			addCriterion("ID >", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("ID >=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThan(Integer value) {
			addCriterion("ID <", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThanOrEqualTo(Integer value) {
			addCriterion("ID <=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdIn(List<Integer> values) {
			addCriterion("ID in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotIn(List<Integer> values) {
			addCriterion("ID not in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdBetween(Integer value1, Integer value2) {
			addCriterion("ID between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotBetween(Integer value1, Integer value2) {
			addCriterion("ID not between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andCodeIsNull() {
			addCriterion("Code is null");
			return (Criteria) this;
		}

		public Criteria andCodeIsNotNull() {
			addCriterion("Code is not null");
			return (Criteria) this;
		}

		public Criteria andCodeEqualTo(String value) {
			addCriterion("Code =", value, "code");
			return (Criteria) this;
		}

		public Criteria andCodeNotEqualTo(String value) {
			addCriterion("Code <>", value, "code");
			return (Criteria) this;
		}

		public Criteria andCodeGreaterThan(String value) {
			addCriterion("Code >", value, "code");
			return (Criteria) this;
		}

		public Criteria andCodeGreaterThanOrEqualTo(String value) {
			addCriterion("Code >=", value, "code");
			return (Criteria) this;
		}

		public Criteria andCodeLessThan(String value) {
			addCriterion("Code <", value, "code");
			return (Criteria) this;
		}

		public Criteria andCodeLessThanOrEqualTo(String value) {
			addCriterion("Code <=", value, "code");
			return (Criteria) this;
		}

		public Criteria andCodeLike(String value) {
			addCriterion("Code like", value, "code");
			return (Criteria) this;
		}

		public Criteria andCodeNotLike(String value) {
			addCriterion("Code not like", value, "code");
			return (Criteria) this;
		}

		public Criteria andCodeIn(List<String> values) {
			addCriterion("Code in", values, "code");
			return (Criteria) this;
		}

		public Criteria andCodeNotIn(List<String> values) {
			addCriterion("Code not in", values, "code");
			return (Criteria) this;
		}

		public Criteria andCodeBetween(String value1, String value2) {
			addCriterion("Code between", value1, value2, "code");
			return (Criteria) this;
		}

		public Criteria andCodeNotBetween(String value1, String value2) {
			addCriterion("Code not between", value1, value2, "code");
			return (Criteria) this;
		}

		public Criteria andNameIsNull() {
			addCriterion("Name is null");
			return (Criteria) this;
		}

		public Criteria andNameIsNotNull() {
			addCriterion("Name is not null");
			return (Criteria) this;
		}

		public Criteria andNameEqualTo(String value) {
			addCriterion("Name =", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameNotEqualTo(String value) {
			addCriterion("Name <>", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameGreaterThan(String value) {
			addCriterion("Name >", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameGreaterThanOrEqualTo(String value) {
			addCriterion("Name >=", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameLessThan(String value) {
			addCriterion("Name <", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameLessThanOrEqualTo(String value) {
			addCriterion("Name <=", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameLike(String value) {
			addCriterion("Name like", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameNotLike(String value) {
			addCriterion("Name not like", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameIn(List<String> values) {
			addCriterion("Name in", values, "name");
			return (Criteria) this;
		}

		public Criteria andNameNotIn(List<String> values) {
			addCriterion("Name not in", values, "name");
			return (Criteria) this;
		}

		public Criteria andNameBetween(String value1, String value2) {
			addCriterion("Name between", value1, value2, "name");
			return (Criteria) this;
		}

		public Criteria andNameNotBetween(String value1, String value2) {
			addCriterion("Name not between", value1, value2, "name");
			return (Criteria) this;
		}

		public Criteria andCategoryidIsNull() {
			addCriterion("CategoryID is null");
			return (Criteria) this;
		}

		public Criteria andCategoryidIsNotNull() {
			addCriterion("CategoryID is not null");
			return (Criteria) this;
		}

		public Criteria andCategoryidEqualTo(Integer value) {
			addCriterion("CategoryID =", value, "categoryid");
			return (Criteria) this;
		}

		public Criteria andCategoryidNotEqualTo(Integer value) {
			addCriterion("CategoryID <>", value, "categoryid");
			return (Criteria) this;
		}

		public Criteria andCategoryidGreaterThan(Integer value) {
			addCriterion("CategoryID >", value, "categoryid");
			return (Criteria) this;
		}

		public Criteria andCategoryidGreaterThanOrEqualTo(Integer value) {
			addCriterion("CategoryID >=", value, "categoryid");
			return (Criteria) this;
		}

		public Criteria andCategoryidLessThan(Integer value) {
			addCriterion("CategoryID <", value, "categoryid");
			return (Criteria) this;
		}

		public Criteria andCategoryidLessThanOrEqualTo(Integer value) {
			addCriterion("CategoryID <=", value, "categoryid");
			return (Criteria) this;
		}

		public Criteria andCategoryidIn(List<Integer> values) {
			addCriterion("CategoryID in", values, "categoryid");
			return (Criteria) this;
		}

		public Criteria andCategoryidNotIn(List<Integer> values) {
			addCriterion("CategoryID not in", values, "categoryid");
			return (Criteria) this;
		}

		public Criteria andCategoryidBetween(Integer value1, Integer value2) {
			addCriterion("CategoryID between", value1, value2, "categoryid");
			return (Criteria) this;
		}

		public Criteria andCategoryidNotBetween(Integer value1, Integer value2) {
			addCriterion("CategoryID not between", value1, value2, "categoryid");
			return (Criteria) this;
		}

		public Criteria andPriceIsNull() {
			addCriterion("Price is null");
			return (Criteria) this;
		}

		public Criteria andPriceIsNotNull() {
			addCriterion("Price is not null");
			return (Criteria) this;
		}

		public Criteria andPriceEqualTo(Integer value) {
			addCriterion("Price =", value, "price");
			return (Criteria) this;
		}

		public Criteria andPriceNotEqualTo(Integer value) {
			addCriterion("Price <>", value, "price");
			return (Criteria) this;
		}

		public Criteria andPriceGreaterThan(Integer value) {
			addCriterion("Price >", value, "price");
			return (Criteria) this;
		}

		public Criteria andPriceGreaterThanOrEqualTo(Integer value) {
			addCriterion("Price >=", value, "price");
			return (Criteria) this;
		}

		public Criteria andPriceLessThan(Integer value) {
			addCriterion("Price <", value, "price");
			return (Criteria) this;
		}

		public Criteria andPriceLessThanOrEqualTo(Integer value) {
			addCriterion("Price <=", value, "price");
			return (Criteria) this;
		}

		public Criteria andPriceIn(List<Integer> values) {
			addCriterion("Price in", values, "price");
			return (Criteria) this;
		}

		public Criteria andPriceNotIn(List<Integer> values) {
			addCriterion("Price not in", values, "price");
			return (Criteria) this;
		}

		public Criteria andPriceBetween(Integer value1, Integer value2) {
			addCriterion("Price between", value1, value2, "price");
			return (Criteria) this;
		}

		public Criteria andPriceNotBetween(Integer value1, Integer value2) {
			addCriterion("Price not between", value1, value2, "price");
			return (Criteria) this;
		}

		public Criteria andDetailIsNull() {
			addCriterion("Detail is null");
			return (Criteria) this;
		}

		public Criteria andDetailIsNotNull() {
			addCriterion("Detail is not null");
			return (Criteria) this;
		}

		public Criteria andDetailEqualTo(String value) {
			addCriterion("Detail =", value, "detail");
			return (Criteria) this;
		}

		public Criteria andDetailNotEqualTo(String value) {
			addCriterion("Detail <>", value, "detail");
			return (Criteria) this;
		}

		public Criteria andDetailGreaterThan(String value) {
			addCriterion("Detail >", value, "detail");
			return (Criteria) this;
		}

		public Criteria andDetailGreaterThanOrEqualTo(String value) {
			addCriterion("Detail >=", value, "detail");
			return (Criteria) this;
		}

		public Criteria andDetailLessThan(String value) {
			addCriterion("Detail <", value, "detail");
			return (Criteria) this;
		}

		public Criteria andDetailLessThanOrEqualTo(String value) {
			addCriterion("Detail <=", value, "detail");
			return (Criteria) this;
		}

		public Criteria andDetailLike(String value) {
			addCriterion("Detail like", value, "detail");
			return (Criteria) this;
		}

		public Criteria andDetailNotLike(String value) {
			addCriterion("Detail not like", value, "detail");
			return (Criteria) this;
		}

		public Criteria andDetailIn(List<String> values) {
			addCriterion("Detail in", values, "detail");
			return (Criteria) this;
		}

		public Criteria andDetailNotIn(List<String> values) {
			addCriterion("Detail not in", values, "detail");
			return (Criteria) this;
		}

		public Criteria andDetailBetween(String value1, String value2) {
			addCriterion("Detail between", value1, value2, "detail");
			return (Criteria) this;
		}

		public Criteria andDetailNotBetween(String value1, String value2) {
			addCriterion("Detail not between", value1, value2, "detail");
			return (Criteria) this;
		}

		public Criteria andDateimportIsNull() {
			addCriterion("DateImport is null");
			return (Criteria) this;
		}

		public Criteria andDateimportIsNotNull() {
			addCriterion("DateImport is not null");
			return (Criteria) this;
		}

		public Criteria andDateimportEqualTo(Date value) {
			addCriterionForJDBCDate("DateImport =", value, "dateimport");
			return (Criteria) this;
		}

		public Criteria andDateimportNotEqualTo(Date value) {
			addCriterionForJDBCDate("DateImport <>", value, "dateimport");
			return (Criteria) this;
		}

		public Criteria andDateimportGreaterThan(Date value) {
			addCriterionForJDBCDate("DateImport >", value, "dateimport");
			return (Criteria) this;
		}

		public Criteria andDateimportGreaterThanOrEqualTo(Date value) {
			addCriterionForJDBCDate("DateImport >=", value, "dateimport");
			return (Criteria) this;
		}

		public Criteria andDateimportLessThan(Date value) {
			addCriterionForJDBCDate("DateImport <", value, "dateimport");
			return (Criteria) this;
		}

		public Criteria andDateimportLessThanOrEqualTo(Date value) {
			addCriterionForJDBCDate("DateImport <=", value, "dateimport");
			return (Criteria) this;
		}

		public Criteria andDateimportIn(List<Date> values) {
			addCriterionForJDBCDate("DateImport in", values, "dateimport");
			return (Criteria) this;
		}

		public Criteria andDateimportNotIn(List<Date> values) {
			addCriterionForJDBCDate("DateImport not in", values, "dateimport");
			return (Criteria) this;
		}

		public Criteria andDateimportBetween(Date value1, Date value2) {
			addCriterionForJDBCDate("DateImport between", value1, value2, "dateimport");
			return (Criteria) this;
		}

		public Criteria andDateimportNotBetween(Date value1, Date value2) {
			addCriterionForJDBCDate("DateImport not between", value1, value2, "dateimport");
			return (Criteria) this;
		}

		public Criteria andMainimageIsNull() {
			addCriterion("MainImage is null");
			return (Criteria) this;
		}

		public Criteria andMainimageIsNotNull() {
			addCriterion("MainImage is not null");
			return (Criteria) this;
		}

		public Criteria andMainimageEqualTo(String value) {
			addCriterion("MainImage =", value, "mainimage");
			return (Criteria) this;
		}

		public Criteria andMainimageNotEqualTo(String value) {
			addCriterion("MainImage <>", value, "mainimage");
			return (Criteria) this;
		}

		public Criteria andMainimageGreaterThan(String value) {
			addCriterion("MainImage >", value, "mainimage");
			return (Criteria) this;
		}

		public Criteria andMainimageGreaterThanOrEqualTo(String value) {
			addCriterion("MainImage >=", value, "mainimage");
			return (Criteria) this;
		}

		public Criteria andMainimageLessThan(String value) {
			addCriterion("MainImage <", value, "mainimage");
			return (Criteria) this;
		}

		public Criteria andMainimageLessThanOrEqualTo(String value) {
			addCriterion("MainImage <=", value, "mainimage");
			return (Criteria) this;
		}

		public Criteria andMainimageLike(String value) {
			addCriterion("MainImage like", value, "mainimage");
			return (Criteria) this;
		}

		public Criteria andMainimageNotLike(String value) {
			addCriterion("MainImage not like", value, "mainimage");
			return (Criteria) this;
		}

		public Criteria andMainimageIn(List<String> values) {
			addCriterion("MainImage in", values, "mainimage");
			return (Criteria) this;
		}

		public Criteria andMainimageNotIn(List<String> values) {
			addCriterion("MainImage not in", values, "mainimage");
			return (Criteria) this;
		}

		public Criteria andMainimageBetween(String value1, String value2) {
			addCriterion("MainImage between", value1, value2, "mainimage");
			return (Criteria) this;
		}

		public Criteria andMainimageNotBetween(String value1, String value2) {
			addCriterion("MainImage not between", value1, value2, "mainimage");
			return (Criteria) this;
		}

		public Criteria andHavesizeIsNull() {
			addCriterion("HaveSize is null");
			return (Criteria) this;
		}

		public Criteria andHavesizeIsNotNull() {
			addCriterion("HaveSize is not null");
			return (Criteria) this;
		}

		public Criteria andHavesizeEqualTo(Boolean value) {
			addCriterion("HaveSize =", value, "havesize");
			return (Criteria) this;
		}

		public Criteria andHavesizeNotEqualTo(Boolean value) {
			addCriterion("HaveSize <>", value, "havesize");
			return (Criteria) this;
		}

		public Criteria andHavesizeGreaterThan(Boolean value) {
			addCriterion("HaveSize >", value, "havesize");
			return (Criteria) this;
		}

		public Criteria andHavesizeGreaterThanOrEqualTo(Boolean value) {
			addCriterion("HaveSize >=", value, "havesize");
			return (Criteria) this;
		}

		public Criteria andHavesizeLessThan(Boolean value) {
			addCriterion("HaveSize <", value, "havesize");
			return (Criteria) this;
		}

		public Criteria andHavesizeLessThanOrEqualTo(Boolean value) {
			addCriterion("HaveSize <=", value, "havesize");
			return (Criteria) this;
		}

		public Criteria andHavesizeIn(List<Boolean> values) {
			addCriterion("HaveSize in", values, "havesize");
			return (Criteria) this;
		}

		public Criteria andHavesizeNotIn(List<Boolean> values) {
			addCriterion("HaveSize not in", values, "havesize");
			return (Criteria) this;
		}

		public Criteria andHavesizeBetween(Boolean value1, Boolean value2) {
			addCriterion("HaveSize between", value1, value2, "havesize");
			return (Criteria) this;
		}

		public Criteria andHavesizeNotBetween(Boolean value1, Boolean value2) {
			addCriterion("HaveSize not between", value1, value2, "havesize");
			return (Criteria) this;
		}

		public Criteria andHavecolorIsNull() {
			addCriterion("HaveColor is null");
			return (Criteria) this;
		}

		public Criteria andHavecolorIsNotNull() {
			addCriterion("HaveColor is not null");
			return (Criteria) this;
		}

		public Criteria andHavecolorEqualTo(Boolean value) {
			addCriterion("HaveColor =", value, "havecolor");
			return (Criteria) this;
		}

		public Criteria andHavecolorNotEqualTo(Boolean value) {
			addCriterion("HaveColor <>", value, "havecolor");
			return (Criteria) this;
		}

		public Criteria andHavecolorGreaterThan(Boolean value) {
			addCriterion("HaveColor >", value, "havecolor");
			return (Criteria) this;
		}

		public Criteria andHavecolorGreaterThanOrEqualTo(Boolean value) {
			addCriterion("HaveColor >=", value, "havecolor");
			return (Criteria) this;
		}

		public Criteria andHavecolorLessThan(Boolean value) {
			addCriterion("HaveColor <", value, "havecolor");
			return (Criteria) this;
		}

		public Criteria andHavecolorLessThanOrEqualTo(Boolean value) {
			addCriterion("HaveColor <=", value, "havecolor");
			return (Criteria) this;
		}

		public Criteria andHavecolorIn(List<Boolean> values) {
			addCriterion("HaveColor in", values, "havecolor");
			return (Criteria) this;
		}

		public Criteria andHavecolorNotIn(List<Boolean> values) {
			addCriterion("HaveColor not in", values, "havecolor");
			return (Criteria) this;
		}

		public Criteria andHavecolorBetween(Boolean value1, Boolean value2) {
			addCriterion("HaveColor between", value1, value2, "havecolor");
			return (Criteria) this;
		}

		public Criteria andHavecolorNotBetween(Boolean value1, Boolean value2) {
			addCriterion("HaveColor not between", value1, value2, "havecolor");
			return (Criteria) this;
		}
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table product
	 * @mbg.generated  Wed Jul 06 20:47:06 ICT 2022
	 */
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

	/**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table product
     *
     * @mbg.generated do_not_delete_during_merge Wed Apr 20 22:57:00 ICT 2022
     */
    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }
}