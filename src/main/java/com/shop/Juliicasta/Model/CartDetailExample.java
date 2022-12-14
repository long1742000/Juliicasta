package com.shop.Juliicasta.Model;

import java.util.ArrayList;
import java.util.List;

public class CartDetailExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table cartdetail
	 * @mbg.generated  Wed Jul 06 20:47:06 ICT 2022
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table cartdetail
	 * @mbg.generated  Wed Jul 06 20:47:06 ICT 2022
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table cartdetail
	 * @mbg.generated  Wed Jul 06 20:47:06 ICT 2022
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table cartdetail
	 * @mbg.generated  Wed Jul 06 20:47:06 ICT 2022
	 */
	public CartDetailExample() {
		oredCriteria = new ArrayList<>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table cartdetail
	 * @mbg.generated  Wed Jul 06 20:47:06 ICT 2022
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table cartdetail
	 * @mbg.generated  Wed Jul 06 20:47:06 ICT 2022
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table cartdetail
	 * @mbg.generated  Wed Jul 06 20:47:06 ICT 2022
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table cartdetail
	 * @mbg.generated  Wed Jul 06 20:47:06 ICT 2022
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table cartdetail
	 * @mbg.generated  Wed Jul 06 20:47:06 ICT 2022
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table cartdetail
	 * @mbg.generated  Wed Jul 06 20:47:06 ICT 2022
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table cartdetail
	 * @mbg.generated  Wed Jul 06 20:47:06 ICT 2022
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table cartdetail
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
	 * This method was generated by MyBatis Generator. This method corresponds to the database table cartdetail
	 * @mbg.generated  Wed Jul 06 20:47:06 ICT 2022
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table cartdetail
	 * @mbg.generated  Wed Jul 06 20:47:06 ICT 2022
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table cartdetail
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

		public Criteria andCartidIsNull() {
			addCriterion("CartID is null");
			return (Criteria) this;
		}

		public Criteria andCartidIsNotNull() {
			addCriterion("CartID is not null");
			return (Criteria) this;
		}

		public Criteria andCartidEqualTo(Integer value) {
			addCriterion("CartID =", value, "cartid");
			return (Criteria) this;
		}

		public Criteria andCartidNotEqualTo(Integer value) {
			addCriterion("CartID <>", value, "cartid");
			return (Criteria) this;
		}

		public Criteria andCartidGreaterThan(Integer value) {
			addCriterion("CartID >", value, "cartid");
			return (Criteria) this;
		}

		public Criteria andCartidGreaterThanOrEqualTo(Integer value) {
			addCriterion("CartID >=", value, "cartid");
			return (Criteria) this;
		}

		public Criteria andCartidLessThan(Integer value) {
			addCriterion("CartID <", value, "cartid");
			return (Criteria) this;
		}

		public Criteria andCartidLessThanOrEqualTo(Integer value) {
			addCriterion("CartID <=", value, "cartid");
			return (Criteria) this;
		}

		public Criteria andCartidIn(List<Integer> values) {
			addCriterion("CartID in", values, "cartid");
			return (Criteria) this;
		}

		public Criteria andCartidNotIn(List<Integer> values) {
			addCriterion("CartID not in", values, "cartid");
			return (Criteria) this;
		}

		public Criteria andCartidBetween(Integer value1, Integer value2) {
			addCriterion("CartID between", value1, value2, "cartid");
			return (Criteria) this;
		}

		public Criteria andCartidNotBetween(Integer value1, Integer value2) {
			addCriterion("CartID not between", value1, value2, "cartid");
			return (Criteria) this;
		}

		public Criteria andProductdetailidIsNull() {
			addCriterion("ProductDetailID is null");
			return (Criteria) this;
		}

		public Criteria andProductdetailidIsNotNull() {
			addCriterion("ProductDetailID is not null");
			return (Criteria) this;
		}

		public Criteria andProductdetailidEqualTo(Integer value) {
			addCriterion("ProductDetailID =", value, "productdetailid");
			return (Criteria) this;
		}

		public Criteria andProductdetailidNotEqualTo(Integer value) {
			addCriterion("ProductDetailID <>", value, "productdetailid");
			return (Criteria) this;
		}

		public Criteria andProductdetailidGreaterThan(Integer value) {
			addCriterion("ProductDetailID >", value, "productdetailid");
			return (Criteria) this;
		}

		public Criteria andProductdetailidGreaterThanOrEqualTo(Integer value) {
			addCriterion("ProductDetailID >=", value, "productdetailid");
			return (Criteria) this;
		}

		public Criteria andProductdetailidLessThan(Integer value) {
			addCriterion("ProductDetailID <", value, "productdetailid");
			return (Criteria) this;
		}

		public Criteria andProductdetailidLessThanOrEqualTo(Integer value) {
			addCriterion("ProductDetailID <=", value, "productdetailid");
			return (Criteria) this;
		}

		public Criteria andProductdetailidIn(List<Integer> values) {
			addCriterion("ProductDetailID in", values, "productdetailid");
			return (Criteria) this;
		}

		public Criteria andProductdetailidNotIn(List<Integer> values) {
			addCriterion("ProductDetailID not in", values, "productdetailid");
			return (Criteria) this;
		}

		public Criteria andProductdetailidBetween(Integer value1, Integer value2) {
			addCriterion("ProductDetailID between", value1, value2, "productdetailid");
			return (Criteria) this;
		}

		public Criteria andProductdetailidNotBetween(Integer value1, Integer value2) {
			addCriterion("ProductDetailID not between", value1, value2, "productdetailid");
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

		public Criteria andProductnameIsNull() {
			addCriterion("ProductName is null");
			return (Criteria) this;
		}

		public Criteria andProductnameIsNotNull() {
			addCriterion("ProductName is not null");
			return (Criteria) this;
		}

		public Criteria andProductnameEqualTo(String value) {
			addCriterion("ProductName =", value, "productname");
			return (Criteria) this;
		}

		public Criteria andProductnameNotEqualTo(String value) {
			addCriterion("ProductName <>", value, "productname");
			return (Criteria) this;
		}

		public Criteria andProductnameGreaterThan(String value) {
			addCriterion("ProductName >", value, "productname");
			return (Criteria) this;
		}

		public Criteria andProductnameGreaterThanOrEqualTo(String value) {
			addCriterion("ProductName >=", value, "productname");
			return (Criteria) this;
		}

		public Criteria andProductnameLessThan(String value) {
			addCriterion("ProductName <", value, "productname");
			return (Criteria) this;
		}

		public Criteria andProductnameLessThanOrEqualTo(String value) {
			addCriterion("ProductName <=", value, "productname");
			return (Criteria) this;
		}

		public Criteria andProductnameLike(String value) {
			addCriterion("ProductName like", value, "productname");
			return (Criteria) this;
		}

		public Criteria andProductnameNotLike(String value) {
			addCriterion("ProductName not like", value, "productname");
			return (Criteria) this;
		}

		public Criteria andProductnameIn(List<String> values) {
			addCriterion("ProductName in", values, "productname");
			return (Criteria) this;
		}

		public Criteria andProductnameNotIn(List<String> values) {
			addCriterion("ProductName not in", values, "productname");
			return (Criteria) this;
		}

		public Criteria andProductnameBetween(String value1, String value2) {
			addCriterion("ProductName between", value1, value2, "productname");
			return (Criteria) this;
		}

		public Criteria andProductnameNotBetween(String value1, String value2) {
			addCriterion("ProductName not between", value1, value2, "productname");
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

		public Criteria andImagenameIsNull() {
			addCriterion("ImageName is null");
			return (Criteria) this;
		}

		public Criteria andImagenameIsNotNull() {
			addCriterion("ImageName is not null");
			return (Criteria) this;
		}

		public Criteria andImagenameEqualTo(String value) {
			addCriterion("ImageName =", value, "imagename");
			return (Criteria) this;
		}

		public Criteria andImagenameNotEqualTo(String value) {
			addCriterion("ImageName <>", value, "imagename");
			return (Criteria) this;
		}

		public Criteria andImagenameGreaterThan(String value) {
			addCriterion("ImageName >", value, "imagename");
			return (Criteria) this;
		}

		public Criteria andImagenameGreaterThanOrEqualTo(String value) {
			addCriterion("ImageName >=", value, "imagename");
			return (Criteria) this;
		}

		public Criteria andImagenameLessThan(String value) {
			addCriterion("ImageName <", value, "imagename");
			return (Criteria) this;
		}

		public Criteria andImagenameLessThanOrEqualTo(String value) {
			addCriterion("ImageName <=", value, "imagename");
			return (Criteria) this;
		}

		public Criteria andImagenameLike(String value) {
			addCriterion("ImageName like", value, "imagename");
			return (Criteria) this;
		}

		public Criteria andImagenameNotLike(String value) {
			addCriterion("ImageName not like", value, "imagename");
			return (Criteria) this;
		}

		public Criteria andImagenameIn(List<String> values) {
			addCriterion("ImageName in", values, "imagename");
			return (Criteria) this;
		}

		public Criteria andImagenameNotIn(List<String> values) {
			addCriterion("ImageName not in", values, "imagename");
			return (Criteria) this;
		}

		public Criteria andImagenameBetween(String value1, String value2) {
			addCriterion("ImageName between", value1, value2, "imagename");
			return (Criteria) this;
		}

		public Criteria andImagenameNotBetween(String value1, String value2) {
			addCriterion("ImageName not between", value1, value2, "imagename");
			return (Criteria) this;
		}
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table cartdetail
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
     * This class corresponds to the database table cartdetail
     *
     * @mbg.generated do_not_delete_during_merge Wed Apr 20 22:57:00 ICT 2022
     */
    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }
}