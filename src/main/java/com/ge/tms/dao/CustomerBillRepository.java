package com.ge.tms.dao;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ge.tms.entity.CustomerAccount;
import com.ge.tms.entity.CustomerBill;

/**
 * @author Nitin K.
 * This interface contains methods to get data from CustomerBill table in database.
 */
public interface CustomerBillRepository extends CrudRepository<CustomerBill, Integer> {

	/**
	 * Connects to database and returns the customer bill for a given bill id.
	 * @author Nitin K.
	 * @param customerBillId Integer
	 * @return CustomerBill
	 */
	public CustomerBill findByCustomerBillId(Integer customerBillId);

	/**
	 * Connects to database and returns all the customer bills for a given customer account
	 * whose billsentdate is between the given start & end dates.
	 * @author Nitin K.
	 * @param customerAccount CustomerAccount
	 * @param sqlStartDate Date
	 * @param sqlEndDate Date
	 * @return list of CustomerBill
	 */
	@Query("FROM CustomerBill WHERE customerAccount =:customerAccount AND billSentDate BETWEEN :sqlStartDate and :sqlEndDate ORDER BY billSentDate DESC")
	public List<CustomerBill> findCustomerBillByCustomerAccountAndCustomDates(
			@Param("customerAccount") CustomerAccount customerAccount, @Param("sqlStartDate") Date sqlStartDate,
			@Param("sqlEndDate") Date sqlEndDate);

	/**
	 * Connects to database and returns all the customer bills for a given customer account
	 * ordered by BillCycleEndDate newest to oldest.
	 * @author Nitin K.
	 * @param customerAccount CustomerAccount
	 * @return list of CustomerBill
	 */
	List<CustomerBill> findByCustomerAccountOrderByBillCycleEndDateDesc(CustomerAccount customerAccount);

	/**
	 * Connects to database and returns all the customer bills for a given customer account
	 * ordered by BillGenerationDate newest to oldest.
	 * @author Nitin K.
	 * @param customerAccount CustomerAccount
	 * @return list of CustomerBill
	 */
	List<CustomerBill> findByCustomerAccountOrderByBillGenerationDateDesc(CustomerAccount customerAccount);

	/**
	 * Connects to database and returns the number of total billed customers in a given date range.
	 * @author Nitin K.
	 * @param startDate Date
	 * @param endDate Date
	 * @return No of total customers billed
	 */
	@Query("select count(*) FROM CustomerBill WHERE billSentDate BETWEEN  :startDate and :endDate")
	public Integer findTotalCustomerBilled(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

	/**
	 * Connects to database and returns the number of total billed customers who had paid their bill in a given date range.
	 * @author Nitin K.
	 * @param startDate Date
	 * @param endDate Date
	 * @return No of total customers whose bill is paid
	 */
	@Query("select count(*) FROM CustomerBill WHERE billSentDate BETWEEN  :startDate and :endDate and paymentStatus='paid'")
	public Integer findTotalCustomerPaid(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
	
	/**
	 * Connects to database and returns a list of customer bill which are not paid in the given date range.
	 * @author Zameer
	 * @param startDate Date
	 * @param endDate Date
	 * @return list of CustomerBill
	 */
	@Query("FROM CustomerBill WHERE billSentDate BETWEEN  :startDate and :endDate and paymentStatus='' or paymentStatus='unpaid'")
	public List<CustomerBill> findTotalCustomerListPaid(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
		
	/**
	 * Connects to database and returns the total bill amount of bills sent in a given date range.
	 * @author Nitin K.
	 * @param startDate Date
	 * @param endDate Date
	 * @return Total bill amount
	 */
	@Query("select sum(billAmount) FROM CustomerBill WHERE billSentDate BETWEEN  :startDate and :endDate")
	public BigDecimal findTotalBillAmount(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

	/**
	 * Connects to database and returns the total bill amount of bills sent in a given date range and had been paid.
	 * @author Nitin K.
	 * @param startDate Date
	 * @param endDate Date
	 * @return Total bill amount paid
	 */
	@Query("select sum(billAmount) FROM CustomerBill WHERE billSentDate BETWEEN  :startDate and :endDate and paymentStatus='paid'")
	public BigDecimal findTotalBillReceived(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

	/**
	 * Connects to database and returns the total bill amount of bills sent in a given date range
	 * and had been paid before or on the due Date.
	 * @author Nitin K.
	 * @param startDate Date
	 * @param endDate Date
	 * @return Total bill amount paid upto due date.
	 */
	@Query("select sum(billAmount) FROM CustomerBill WHERE date(paymentDate) <=billDueDate  and paymentStatus='paid' and  billSentDate BETWEEN :startDate and :endDate")
	public BigDecimal findTotalBillReceivedBeforeDueDate(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
	
	/**
	 * Connects to database and returns the total bill amount of bills sent in a given date range
	 * and had been paid after the due Date.
	 * @author Nitin K.
	 * @param startDate Date
	 * @param endDate Date
	 * @return Total bill amount paid after due date.
	 */
	@Query("select sum(billAmount) FROM CustomerBill WHERE date(paymentDate) > billDueDate  and paymentStatus='paid' and  billSentDate BETWEEN :startDate and :endDate")
	public BigDecimal findTotalBillReceivedafterDueDate(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

	/**
	 * Connects to database and returns the total bill amount of bills on monthly basis for given date range.
	 * @author Nitin K.
	 * @param startDate Date
	 * @param endDate Date
	 * @return list of Object[]
	 */
	@Query("select sum(billAmount),date(billSentDate) FROM CustomerBill where date(billSentDate) BETWEEN  :startDate and :endDate group by month(billSentDate)")
	public List<Object[]> findRevenueGraphTotalBilledYear(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

	/**
	 * Connects to database and returns the total paid bill amount on monthly basis for given date range.
	 * @author Nitin K.
	 * @param startDate Date
	 * @param endDate Date
	 * @return list of Object[]
	 */
	@Query("select sum(billAmount),date(billSentDate) FROM CustomerBill where date(billSentDate) BETWEEN  :startDate and :endDate and paymentStatus='paid' group by month(billSentDate)")
	public List<Object[]> findRevenueGraphTotalCollectedYear(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

	/**
	 * Connects to database and returns the total bill amount of bills on daily basis for given date range.
	 * @author Nitin K.
	 * @param startDate Date
	 * @param endDate Date
	 * @return list of Object[]
	 */
	@Query("select sum(billAmount),date(billSentDate) FROM CustomerBill where date(billSentDate) BETWEEN  :startDate and :endDate group by date(billSentDate)")
	public List<Object[]> findRevenueGraphTotalBilledMonth(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

	/**
	 * Connects to database and returns the total paid bill amount on daily basis for given date range.
	 * @author Nitin K.
	 * @param startDate Date
	 * @param endDate Date
	 * @return list of Object[]
	 */
	@Query("select sum(billAmount),date(billSentDate) FROM CustomerBill where date(billSentDate) BETWEEN  :startDate and :endDate and paymentStatus='paid' group by date(billSentDate)")
	public List<Object[]> findRevenueGraphTotalCollectedMonth(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

	/**
	 * Connects to database and returns the total amount of interest on all bills for given date range.
	 * @author Nitin K.
	 * @param startDate Date
	 * @param endDate Date
	 * @return total amount of interest
	 */
	@Query("select sum(feeInterest) FROM CustomerBill where date(billSentDate) BETWEEN  :startDate and :endDate")
	public BigDecimal findTotalPercentageOnPenalties(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

	/**
	 * Connects to database and returns the bill and customer details of unpaid bills
	 * where no of days over due date lies within the given range.
	 * @author Nitin K.
	 * @param currentdate Date
	 * @param lessThanRange Long
	 * @param greaterThanRange Long
	 * @return list of Object[]
	 */
	@Query("select  c.billAmount , c.noOfReminderSent , c.nextReminderDate, c.finalReminderDate ,  (TO_DAYS(:currentdate)-TO_DAYS(c.billDueDate) ), c.customerAccount.customerName , c.customerAccount.accountId, c.billDueDate    from CustomerBill c where  c.paymentStatus!='paid' and (TO_DAYS(:currentdate)-TO_DAYS(c.billDueDate) ) > :greaterThanRange  and (TO_DAYS( :currentdate)-TO_DAYS(c.billDueDate) ) < :lessThanRange")
	public List<Object[]> findArRagingData(@Param("currentdate") Date currentdate, @Param("lessThanRange") Long lessThanRange,
			@Param("greaterThanRange") Long greaterThanRange);
	
	/**
	 * Connects to database and returns the bill and customer details of unpaid bills
	 * where the no of days over the due date is greater than the given upper range.
	 * @author Nitin K.
	 * @param currentdate Date
	 * @param greaterThanRange Long
	 * @return list of Object[]
	 */
	@Query("select  c.billAmount , c.noOfReminderSent , c.nextReminderDate, c.finalReminderDate ,  (TO_DAYS(:currentdate)-TO_DAYS(c.billDueDate) ), c.customerAccount.customerName , c.customerAccount.accountId, c.billDueDate    from CustomerBill c where  c.paymentStatus!='paid' and (TO_DAYS(:currentdate)-TO_DAYS(c.billDueDate) ) > :greaterThanRange")
	public List<Object[]> findArRagingDataWithoutLessThanValue(@Param("currentdate") Date currentdate,
			@Param("greaterThanRange") Long greaterThanRange);

	/*@Query("select c.billAmount, c.billDueDate, c.noOfReminderSent, c.nextReminderDate, c.finalReminderDate, "
			+ "(TO_DAYS('2017-08-07')-TO_DAYS(c.billDueDate)), c.customerAccount.customerName from CustomerBill c where "
			+ "c.paymentDate BETWEEN '2017-05-30' and '2017-06-30' and c.paymentStatus!='paid' and "
			+ "(TO_DAYS('2017-08-07')-TO_DAYS(c.billDueDate))>2 and (TO_DAYS('2017-08-07')-TO_DAYS(c.billDueDate))<100")
	public List<Object[]> findArRagingData();*/

	/**
	 * Connects to database and returns the number of unpaid bills and total unpaid bill amount
	 * where the no of days over the due date is between 0 and 30(inclusive) for a given year.
	 * @author Nitin K.
	 * @param currentdate Date
	 * @param year Integer
	 * @return list of Object[]
	 */
	@Query("select  count(*),sum(billAmount) from CustomerBill c where  c.paymentStatus!='paid' and (TO_DAYS(:currentdate)-TO_DAYS(c.billDueDate) ) > 0 and (TO_DAYS(:currentdate)-TO_DAYS(c.billDueDate) ) <=30 and year(c.billDueDate)=:year")
	public List<Object[]> findArAgingGraphData30Days(@Param("currentdate") Date currentdate, @Param("year") Integer year);

	/**
	 * Connects to database and returns the number of unpaid bills and total unpaid bill amount
	 * where the no of days over the due date is between 30 and 60(inclusive) for a given year.
	 * @author Nitin K.
	 * @param currentdate Date
	 * @param year Integer
	 * @return list of Object[]
	 */
	@Query("select  count(*),sum(billAmount) from CustomerBill c where  c.paymentStatus!='paid' and (TO_DAYS(:currentdate)-TO_DAYS(c.billDueDate) ) > 30 and (TO_DAYS(:currentdate)-TO_DAYS(c.billDueDate) ) <=60 and year(c.billDueDate)=:year")
	public List<Object[]> findArAgingGraphData60Days(@Param("currentdate") Date currentdate, @Param("year") Integer year);

	/**
	 * Connects to database and returns the number of unpaid bills and total unpaid bill amount
	 * where the no of days over the due date is between 60 and 90(inclusive) for a given year.
	 * @author Nitin K.
	 * @param currentdate Date
	 * @param year Integer
	 * @return list of Object[]
	 */
	@Query("select  count(*),sum(billAmount) from CustomerBill c where  c.paymentStatus!='paid' and (TO_DAYS(:currentdate)-TO_DAYS(c.billDueDate) ) > 60 and (TO_DAYS(:currentdate)-TO_DAYS(c.billDueDate) ) <=90 and year(c.billDueDate)=:year")
	public List<Object[]> findArAgingGraphData90Days(@Param("currentdate") Date currentdate, @Param("year") Integer year);

	/**
	 * Connects to database and returns the number of unpaid bills and total unpaid bill amount
	 * where the no of days over the due date is between 90 and 120(inclusive) for a given year.
	 * @author Nitin K.
	 * @param currentdate Date
	 * @param year Integer
	 * @return list of Object[]
	 */
	@Query("select  count(*),sum(billAmount) from CustomerBill c where  c.paymentStatus!='paid' and (TO_DAYS(:currentdate)-TO_DAYS(c.billDueDate) ) > 90 and (TO_DAYS(:currentdate)-TO_DAYS(c.billDueDate) ) <=120 and year(c.billDueDate)=:year")
	public List<Object[]> findArAgingGraphData120Days(@Param("currentdate") Date currentdate, @Param("year") Integer year);

	/**
	 * Connects to database and returns the number of unpaid bills and total unpaid bill amount
	 * where the no of days over the due date is greater than 120 for a given year.
	 * @author Nitin K.
	 * @param currentdate Date
	 * @param year Integer
	 * @return list of Object[]
	 */
	@Query("select  count(*),sum(billAmount) from CustomerBill c where  c.paymentStatus!='paid' and (TO_DAYS(:currentdate)-TO_DAYS(c.billDueDate) ) > 120  and year(c.billDueDate)=:year")
	public List<Object[]> findArAgingGraphDataMoreThan120Days(@Param("currentdate") Date currentdate, @Param("year") Integer year);
	
	/**
	 * Connects to database and returns all the customer bills for a given customer account.
	 * @author Nitin K.
	 * @param customerAccount CustomerAccount
	 * @return list of CustomerBill
	 */
	@Query("FROM CustomerBill WHERE customerAccount =:customerAccount")
	public List<CustomerBill> findCustomerBillByCustomerAccountId(@Param("customerAccount") CustomerAccount customerAccount);
}