package com.ge.tms.common.consts;

import java.math.BigDecimal;

/**
 * @author Nitin K.
 * Class containing constants used throughout the application.
 */
public class Const {

	public static final String cryptSecret = "tadalin-rest-application";

	public static final Integer DEFAULT_PAGE_SIZE = 15;

	public static final String PAGE_SIZE = "pageSize";

	public static final String PAGE_NUM = "pageNum";

	public static final String PAGE_START = "pageStart";

	//Charges Constants
	public static final BigDecimal networkCharges = BigDecimal.valueOf(15.0);

	public static final BigDecimal congestionCharges = BigDecimal.valueOf(12.0);

	public static final BigDecimal fixedCharges = BigDecimal.valueOf(10.0);
	
	public static final BigDecimal deemandCharges = BigDecimal.valueOf(13.0);

	public static final BigDecimal loadSizeCharges = BigDecimal.valueOf(16.0);

	public static final BigDecimal energyComissionCharges = BigDecimal.valueOf(5.0);

	public static final BigDecimal miscCharges = BigDecimal.valueOf(5.0);
	
	public static final BigDecimal taxCharges = BigDecimal.valueOf(80.);
	
	//Plan Constants
	public static final BigDecimal TOU = BigDecimal.valueOf(12.0);
	
	public static final BigDecimal TIRED = BigDecimal.valueOf(14.0);
	
	public static final BigDecimal EV = BigDecimal.valueOf(17.0);

}
