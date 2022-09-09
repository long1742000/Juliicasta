package com.shop.Juliicasta.Model;

import java.util.Date;

public class Bill {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column bill.ID
	 * @mbg.generated  Wed Jul 06 20:47:06 ICT 2022
	 */
	private Integer id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column bill.BillNo
	 * @mbg.generated  Wed Jul 06 20:47:06 ICT 2022
	 */
	private String billno;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column bill.AccountID
	 * @mbg.generated  Wed Jul 06 20:47:06 ICT 2022
	 */
	private Integer accountid;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column bill.EmployeeID
	 * @mbg.generated  Wed Jul 06 20:47:06 ICT 2022
	 */
	private Integer employeeid;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column bill.StatusID
	 * @mbg.generated  Wed Jul 06 20:47:06 ICT 2022
	 */
	private Integer statusid;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column bill.DateCreate
	 * @mbg.generated  Wed Jul 06 20:47:06 ICT 2022
	 */
	private String datecreate;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column bill.Phone
	 * @mbg.generated  Wed Jul 06 20:47:06 ICT 2022
	 */
	private String phone;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column bill.Address
	 * @mbg.generated  Wed Jul 06 20:47:06 ICT 2022
	 */
	private String address;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column bill.StatusName
	 * @mbg.generated  Wed Jul 06 20:47:06 ICT 2022
	 */
	private String statusname;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column bill.ID
	 * @return  the value of bill.ID
	 * @mbg.generated  Wed Jul 06 20:47:06 ICT 2022
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column bill.ID
	 * @param id  the value for bill.ID
	 * @mbg.generated  Wed Jul 06 20:47:06 ICT 2022
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column bill.BillNo
	 * @return  the value of bill.BillNo
	 * @mbg.generated  Wed Jul 06 20:47:06 ICT 2022
	 */
	public String getBillno() {
		return billno;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column bill.BillNo
	 * @param billno  the value for bill.BillNo
	 * @mbg.generated  Wed Jul 06 20:47:06 ICT 2022
	 */
	public void setBillno(String billno) {
		this.billno = billno;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column bill.AccountID
	 * @return  the value of bill.AccountID
	 * @mbg.generated  Wed Jul 06 20:47:06 ICT 2022
	 */
	public Integer getAccountid() {
		return accountid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column bill.AccountID
	 * @param accountid  the value for bill.AccountID
	 * @mbg.generated  Wed Jul 06 20:47:06 ICT 2022
	 */
	public void setAccountid(Integer accountid) {
		this.accountid = accountid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column bill.EmployeeID
	 * @return  the value of bill.EmployeeID
	 * @mbg.generated  Wed Jul 06 20:47:06 ICT 2022
	 */
	public Integer getEmployeeid() {
		return employeeid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column bill.EmployeeID
	 * @param employeeid  the value for bill.EmployeeID
	 * @mbg.generated  Wed Jul 06 20:47:06 ICT 2022
	 */
	public void setEmployeeid(Integer employeeid) {
		this.employeeid = employeeid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column bill.StatusID
	 * @return  the value of bill.StatusID
	 * @mbg.generated  Wed Jul 06 20:47:06 ICT 2022
	 */
	public Integer getStatusid() {
		return statusid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column bill.StatusID
	 * @param statusid  the value for bill.StatusID
	 * @mbg.generated  Wed Jul 06 20:47:06 ICT 2022
	 */
	public void setStatusid(Integer statusid) {
		this.statusid = statusid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column bill.DateCreate
	 * @return  the value of bill.DateCreate
	 * @mbg.generated  Wed Jul 06 20:47:06 ICT 2022
	 */
	public String getDatecreate() {
		return datecreate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column bill.DateCreate
	 * @param datecreate  the value for bill.DateCreate
	 * @mbg.generated  Wed Jul 06 20:47:06 ICT 2022
	 */
	public void setDatecreate(String datecreate) {
		this.datecreate = datecreate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column bill.Phone
	 * @return  the value of bill.Phone
	 * @mbg.generated  Wed Jul 06 20:47:06 ICT 2022
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column bill.Phone
	 * @param phone  the value for bill.Phone
	 * @mbg.generated  Wed Jul 06 20:47:06 ICT 2022
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column bill.Address
	 * @return  the value of bill.Address
	 * @mbg.generated  Wed Jul 06 20:47:06 ICT 2022
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column bill.Address
	 * @param address  the value for bill.Address
	 * @mbg.generated  Wed Jul 06 20:47:06 ICT 2022
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column bill.StatusName
	 * @return  the value of bill.StatusName
	 * @mbg.generated  Wed Jul 06 20:47:06 ICT 2022
	 */
	public String getStatusname() {
		return statusname;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column bill.StatusName
	 * @param statusname  the value for bill.StatusName
	 * @mbg.generated  Wed Jul 06 20:47:06 ICT 2022
	 */
	public void setStatusname(String statusname) {
		this.statusname = statusname;
	}
}