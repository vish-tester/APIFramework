package pojoRevise;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class pojoCreate {

	
	public static void main(String[] args) throws Exception  {
		
		List<RequestDates> reqDate = new ArrayList<RequestDates>();
		
		reqDate.add(new RequestDates("2024-07-01","2024-02-03"));
		reqDate.add(new RequestDates("2024-07-05","2024-07-09"));
		
		RequestDetails reqDetails = new RequestDetails("Sick Leave", 8, reqDate);
		RequestType reqType = new RequestType("Global Time off", reqDetails);
		
		ObjectMapper mapper = new ObjectMapper();
		
		String jsonResult = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(reqType);
		
		//System.out.println(jsonResult);
		
		RequestType re2 = mapper.readValue(jsonResult, RequestType.class);
		
		
		System.out.println(re2.getRequestDetails().getRequestDates().get(0).getStartDate());
		
		List<Integer> ls = new ArrayList<>(Arrays.asList(1,3,2,4,5,7));
		
		ls.set(2, 10);
		
		System.out.println(ls);
		
		
		
	}
	
	
}
