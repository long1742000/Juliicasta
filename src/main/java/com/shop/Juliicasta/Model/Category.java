package com.shop.Juliicasta.Model;

public class Category {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column category.ID
	 * @mbg.generated  Wed Jul 06 20:47:06 ICT 2022
	 */
	private Integer id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column category.Name
	 * @mbg.generated  Wed Jul 06 20:47:06 ICT 2022
	 */
	private String name;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column category.ID
	 * @return  the value of category.ID
	 * @mbg.generated  Wed Jul 06 20:47:06 ICT 2022
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column category.ID
	 * @param id  the value for category.ID
	 * @mbg.generated  Wed Jul 06 20:47:06 ICT 2022
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column category.Name
	 * @return  the value of category.Name
	 * @mbg.generated  Wed Jul 06 20:47:06 ICT 2022
	 */
	public String getName() {
		return name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column category.Name
	 * @param name  the value for category.Name
	 * @mbg.generated  Wed Jul 06 20:47:06 ICT 2022
	 */
	public void setName(String name) {
		this.name = name;
	}
}