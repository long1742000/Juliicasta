package com.shop.Juliicasta.Model;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table productdetail
	 * @mbg.generated  Wed Jul 06 20:47:06 ICT 2022
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table productdetail
	 * @mbg.generated  Wed Jul 06 20:47:06 ICT 2022
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table productdetail
	 * @mbg.generated  Wed Jul 06 20:47:06 ICT 2022
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table productdetail
	 * @mbg.generated  Wed Jul 06 20:47:06 ICT 2022
	 */
	public ProductDetailExample() {
		oredCriteria = new ArrayList<>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table productdetail
	 * @mbg.generated  Wed Jul 06 20:47:06 ICT 2022
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table productdetail
	 * @mbg.generated  Wed Jul 06 20:47:06 ICT 2022
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table productdetail
	 * @mbg.generated  Wed Jul 06 20:47:06 ICT 2022
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table productdetail
	 * @mbg.generated  Wed Jul 06 20:47:06 ICT 2022
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table productdetail
	 * @mbg.generated  Wed Jul 06 20:47:06 ICT 2022
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table productdetail
	 * @mbg.generated  Wed Jul 06 20:47:06 ICT 2022
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table productdetail
	 * @mbg.generated  Wed Jul 06 20:47:06 ICT 2022
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table productdetail
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
	 * This method was generated by MyBatis Generator. This method corresponds to the database table productdetail
	 * @mbg.generated  Wed Jul 06 20:47:06 ICT 2022
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table productdetail
	 * @mbg.generated  Wed Jul 06 20:47:06 ICT 2022
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table productdetail
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

		public Criteria andProductidIsNull() {
			addCriterion("ProductID is null");
			return (Criteria) this;
		}

		public Criteria andProductidIsNotNull() {
			addCriterion("ProductID is not null");
			return (Criteria) this;
		}

		public Criteria andProductidEqualTo(Integer value) {
			addCriterion("ProductID =", value, "productid");
			return (Criteria) this;
		}

		public Criteria andProductidNotEqualTo(Integer value) {
			addCriterion("ProductID <>", value, "productid");
			return (Criteria) this;
		}

		public Criteria andProductidGreaterThan(Integer value) {
			addCriterion("ProductID >", value, "productid");
			return (Criteria) this;
		}

		public Criteria andProductidGreaterThanOrEqualTo(Integer value) {
			addCriterion("ProductID >=", value, "productid");
			return (Criteria) this;
		}

		public Criteria andProductidLessThan(Integer value) {
			addCriterion("ProductID <", value, "productid");
			return (Criteria) this;
		}

		public Criteria andProductidLessThanOrEqualTo(Integer value) {
			addCriterion("ProductID <=", value, "productid");
			return (Criteria) this;
		}

		public Criteria andProductidIn(List<Integer> values) {
			addCriterion("ProductID in", values, "productid");
			return (Criteria) this;
		}

		public Criteria andProductidNotIn(List<Integer> values) {
			addCriterion("ProductID not in", values, "productid");
			return (Criteria) this;
		}

		public Criteria andProductidBetween(Integer value1, Integer value2) {
			addCriterion("ProductID between", value1, value2, "productid");
			return (Criteria) this;
		}

		public Criteria andProductidNotBetween(Integer value1, Integer value2) {
			addCriterion("ProductID not between", value1, value2, "productid");
			return (Criteria) this;
		}

		public Criteria andSizeidIsNull() {
			addCriterion("SizeID is null");
			return (Criteria) this;
		}

		public Criteria andSizeidIsNotNull() {
			addCriterion("SizeID is not null");
			return (Criteria) this;
		}

		public Criteria andSizeidEqualTo(Integer value) {
			addCriterion("SizeID =", value, "sizeid");
			return (Criteria) this;
		}

		public Criteria andSizeidNotEqualTo(Integer value) {
			addCriterion("SizeID <>", value, "sizeid");
			return (Criteria) this;
		}

		public Criteria andSizeidGreaterThan(Integer value) {
			addCriterion("SizeID >", value, "sizeid");
			return (Criteria) this;
		}

		public Criteria andSizeidGreaterThanOrEqualTo(Integer value) {
			addCriterion("SizeID >=", value, "sizeid");
			return (Criteria) this;
		}

		public Criteria andSizeidLessThan(Integer value) {
			addCriterion("SizeID <", value, "sizeid");
			return (Criteria) this;
		}

		public Criteria andSizeidLessThanOrEqualTo(Integer value) {
			addCriterion("SizeID <=", value, "sizeid");
			return (Criteria) this;
		}

		public Criteria andSizeidIn(List<Integer> values) {
			addCriterion("SizeID in", values, "sizeid");
			return (Criteria) this;
		}

		public Criteria andSizeidNotIn(List<Integer> values) {
			addCriterion("SizeID not in", values, "sizeid");
			return (Criteria) this;
		}

		public Criteria andSizeidBetween(Integer value1, Integer value2) {
			addCriterion("SizeID between", value1, value2, "sizeid");
			return (Criteria) this;
		}

		public Criteria andSizeidNotBetween(Integer value1, Integer value2) {
			addCriterion("SizeID not between", value1, value2, "sizeid");
			return (Criteria) this;
		}

		public Criteria andColoridIsNull() {
			addCriterion("ColorID is null");
			return (Criteria) this;
		}

		public Criteria andColoridIsNotNull() {
			addCriterion("ColorID is not null");
			return (Criteria) this;
		}

		public Criteria andColoridEqualTo(Integer value) {
			addCriterion("ColorID =", value, "colorid");
			return (Criteria) this;
		}

		public Criteria andColoridNotEqualTo(Integer value) {
			addCriterion("ColorID <>", value, "colorid");
			return (Criteria) this;
		}

		public Criteria andColoridGreaterThan(Integer value) {
			addCriterion("ColorID >", value, "colorid");
			return (Criteria) this;
		}

		public Criteria andColoridGreaterThanOrEqualTo(Integer value) {
			addCriterion("ColorID >=", value, "colorid");
			return (Criteria) this;
		}

		public Criteria andColoridLessThan(Integer value) {
			addCriterion("ColorID <", value, "colorid");
			return (Criteria) this;
		}

		public Criteria andColoridLessThanOrEqualTo(Integer value) {
			addCriterion("ColorID <=", value, "colorid");
			return (Criteria) this;
		}

		public Criteria andColoridIn(List<Integer> values) {
			addCriterion("ColorID in", values, "colorid");
			return (Criteria) this;
		}

		public Criteria andColoridNotIn(List<Integer> values) {
			addCriterion("ColorID not in", values, "colorid");
			return (Criteria) this;
		}

		public Criteria andColoridBetween(Integer value1, Integer value2) {
			addCriterion("ColorID between", value1, value2, "colorid");
			return (Criteria) this;
		}

		public Criteria andColoridNotBetween(Integer value1, Integer value2) {
			addCriterion("ColorID not between", value1, value2, "colorid");
			return (Criteria) this;
		}

		public Criteria andQuantityIsNull() {
			addCriterion("Quantity is null");
			return (Criteria) this;
		}

		public Criteria andQuantityIsNotNull() {
			addCriterion("Quantity is not null");
			return (Criteria) this;
		}

		public Criteria andQuantityEqualTo(Integer value) {
			addCriterion("Quantity =", value, "quantity");
			return (Criteria) this;
		}

		public Criteria andQuantityNotEqualTo(Integer value) {
			addCriterion("Quantity <>", value, "quantity");
			return (Criteria) this;
		}

		public Criteria andQuantityGreaterThan(Integer value) {
			addCriterion("Quantity >", value, "quantity");
			return (Criteria) this;
		}

		public Criteria andQuantityGreaterThanOrEqualTo(Integer value) {
			addCriterion("Quantity >=", value, "quantity");
			return (Criteria) this;
		}

		public Criteria andQuantityLessThan(Integer value) {
			addCriterion("Quantity <", value, "quantity");
			return (Criteria) this;
		}

		public Criteria andQuantityLessThanOrEqualTo(Integer value) {
			addCriterion("Quantity <=", value, "quantity");
			return (Criteria) this;
		}

		public Criteria andQuantityIn(List<Integer> values) {
			addCriterion("Quantity in", values, "quantity");
			return (Criteria) this;
		}

		public Criteria andQuantityNotIn(List<Integer> values) {
			addCriterion("Quantity not in", values, "quantity");
			return (Criteria) this;
		}

		public Criteria andQuantityBetween(Integer value1, Integer value2) {
			addCriterion("Quantity between", value1, value2, "quantity");
			return (Criteria) this;
		}

		public Criteria andQuantityNotBetween(Integer value1, Integer value2) {
			addCriterion("Quantity not between", value1, value2, "quantity");
			return (Criteria) this;
		}

		public Criteria andSizenameIsNull() {
			addCriterion("SizeName is null");
			return (Criteria) this;
		}

		public Criteria andSizenameIsNotNull() {
			addCriterion("SizeName is not null");
			return (Criteria) this;
		}

		public Criteria andSizenameEqualTo(String value) {
			addCriterion("SizeName =", value, "sizename");
			return (Criteria) this;
		}

		public Criteria andSizenameNotEqualTo(String value) {
			addCriterion("SizeName <>", value, "sizename");
			return (Criteria) this;
		}

		public Criteria andSizenameGreaterThan(String value) {
			addCriterion("SizeName >", value, "sizename");
			return (Criteria) this;
		}

		public Criteria andSizenameGreaterThanOrEqualTo(String value) {
			addCriterion("SizeName >=", value, "sizename");
			return (Criteria) this;
		}

		public Criteria andSizenameLessThan(String value) {
			addCriterion("SizeName <", value, "sizename");
			return (Criteria) this;
		}

		public Criteria andSizenameLessThanOrEqualTo(String value) {
			addCriterion("SizeName <=", value, "sizename");
			return (Criteria) this;
		}

		public Criteria andSizenameLike(String value) {
			addCriterion("SizeName like", value, "sizename");
			return (Criteria) this;
		}

		public Criteria andSizenameNotLike(String value) {
			addCriterion("SizeName not like", value, "sizename");
			return (Criteria) this;
		}

		public Criteria andSizenameIn(List<String> values) {
			addCriterion("SizeName in", values, "sizename");
			return (Criteria) this;
		}

		public Criteria andSizenameNotIn(List<String> values) {
			addCriterion("SizeName not in", values, "sizename");
			return (Criteria) this;
		}

		public Criteria andSizenameBetween(String value1, String value2) {
			addCriterion("SizeName between", value1, value2, "sizename");
			return (Criteria) this;
		}

		public Criteria andSizenameNotBetween(String value1, String value2) {
			addCriterion("SizeName not between", value1, value2, "sizename");
			return (Criteria) this;
		}

		public Criteria andColornameIsNull() {
			addCriterion("ColorName is null");
			return (Criteria) this;
		}

		public Criteria andColornameIsNotNull() {
			addCriterion("ColorName is not null");
			return (Criteria) this;
		}

		public Criteria andColornameEqualTo(String value) {
			addCriterion("ColorName =", value, "colorname");
			return (Criteria) this;
		}

		public Criteria andColornameNotEqualTo(String value) {
			addCriterion("ColorName <>", value, "colorname");
			return (Criteria) this;
		}

		public Criteria andColornameGreaterThan(String value) {
			addCriterion("ColorName >", value, "colorname");
			return (Criteria) this;
		}

		public Criteria andColornameGreaterThanOrEqualTo(String value) {
			addCriterion("ColorName >=", value, "colorname");
			return (Criteria) this;
		}

		public Criteria andColornameLessThan(String value) {
			addCriterion("ColorName <", value, "colorname");
			return (Criteria) this;
		}

		public Criteria andColornameLessThanOrEqualTo(String value) {
			addCriterion("ColorName <=", value, "colorname");
			return (Criteria) this;
		}

		public Criteria andColornameLike(String value) {
			addCriterion("ColorName like", value, "colorname");
			return (Criteria) this;
		}

		public Criteria andColornameNotLike(String value) {
			addCriterion("ColorName not like", value, "colorname");
			return (Criteria) this;
		}

		public Criteria andColornameIn(List<String> values) {
			addCriterion("ColorName in", values, "colorname");
			return (Criteria) this;
		}

		public Criteria andColornameNotIn(List<String> values) {
			addCriterion("ColorName not in", values, "colorname");
			return (Criteria) this;
		}

		public Criteria andColornameBetween(String value1, String value2) {
			addCriterion("ColorName between", value1, value2, "colorname");
			return (Criteria) this;
		}

		public Criteria andColornameNotBetween(String value1, String value2) {
			addCriterion("ColorName not between", value1, value2, "colorname");
			return (Criteria) this;
		}
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table productdetail
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
     * This class corresponds to the database table productdetail
     *
     * @mbg.generated do_not_delete_during_merge Wed Apr 20 22:57:00 ICT 2022
     */
    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }
}