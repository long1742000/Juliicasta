package com.shop.Juliicasta.Mapper;

import com.shop.Juliicasta.Model.Account;
import com.shop.Juliicasta.Model.AccountExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AccountMapper {
    /**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table account
	 * @mbg.generated  Wed May 18 11:00:35 ICT 2022
	 */
	long countByExample(AccountExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table account
	 * @mbg.generated  Wed May 18 11:00:35 ICT 2022
	 */
	int deleteByExample(AccountExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table account
	 * @mbg.generated  Wed May 18 11:00:35 ICT 2022
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table account
	 * @mbg.generated  Wed May 18 11:00:35 ICT 2022
	 */
	int insert(Account row);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table account
	 * @mbg.generated  Wed May 18 11:00:35 ICT 2022
	 */
	int insertSelective(Account row);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table account
	 * @mbg.generated  Wed May 18 11:00:35 ICT 2022
	 */
	List<Account> selectByExample(AccountExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table account
	 * @mbg.generated  Wed May 18 11:00:35 ICT 2022
	 */
	Account selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table account
	 * @mbg.generated  Wed May 18 11:00:35 ICT 2022
	 */
	int updateByExampleSelective(@Param("row") Account row, @Param("example") AccountExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table account
	 * @mbg.generated  Wed May 18 11:00:35 ICT 2022
	 */
	int updateByExample(@Param("row") Account row, @Param("example") AccountExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table account
	 * @mbg.generated  Wed May 18 11:00:35 ICT 2022
	 */
	int updateByPrimaryKeySelective(Account row);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table account
	 * @mbg.generated  Wed May 18 11:00:35 ICT 2022
	 */
	int updateByPrimaryKey(@Param("fullname") String fullname, @Param("phone") String phone, @Param("address") String address, @Param("id") int id);

	Account selectNewAccount();

	int countByPhone(String Phone);
    
    int countByEmail(String Email);
    
    List<Account> selectEnableAccount(int num);

    int changePassword(Map<String, Object> param);
    
    int editStatus(@Param("num") int num, @Param("id") int id);
}