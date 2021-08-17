package com.team21.SocialDesking;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.team21.SocialDesking.entities.Booking;
import com.team21.SocialDesking.entities.FloorLayout;
import com.team21.SocialDesking.entities.Team;
import com.team21.SocialDesking.entities.User;
import com.team21.SocialDesking.repository.BookingRepository;
import com.team21.SocialDesking.repository.FloorLayoutRepository;
import com.team21.SocialDesking.repository.UserRepository;
import com.team21.SocialDesking.services.BookingService;
import com.team21.SocialDesking.services.FloorLayoutService;
import com.team21.SocialDesking.services.UserService;
@RunWith(SpringRunner.class)
@SpringBootTest

 public class SocialDeskingApplicationTests {
	
	@Autowired
	private UserService use;
	@Autowired
	private FloorLayoutService flrdse;
	@Autowired
	private BookingService bser;

	@MockBean
	private UserRepository urepo;

	@MockBean
	private FloorLayoutRepository flrepo;
	
	@MockBean
	private BookingRepository brepo;
	
	@Test
	public void testfindUserByEmail() {
		List<Booking> lb=new ArrayList<>();
		lb.add(new Booking(1,1,2,1,"2021-08-21","2,4"));
		
		when(urepo.findByEmail(anyString())).thenReturn(new User(1,"Soumya Mukhopadhyay","sm31@gmail.com",new Team(2,"wow"),lb));
		assertEquals("Soumya Mukhopadhyay",use.findUserByEmail("sm31@gmail.com").getName());
	}
	
	
	  @Test 
	  public void testGetFloorData() {
	  when(flrepo.findByBuildingIdAndFloorId(1, 2)).thenReturn(new
	  FloorLayout(2,1,2,
	  "(1,0,1,0,1):(0,1,0,1,0):(1,0,1,0,1):(0,1,0,1,0):(1,0,1,0,1)",1));
	  assertEquals(5,flrdse.getFloorLayout(1,
	  2).getLayoutMatrix().split(":")[0].replace("[\\(\\)]","").split(",").length);
	  }
	 
	  
	  @Test
	  public void testAddNewBooking() {
		  List<Booking> lb=new ArrayList<>();
		lb.add(new Booking(1,1,2,1,"2021-08-21","2,4"));
		  when(urepo.findById(1)).thenReturn(new User(1,"Soumya Mukhopadhyay","sm31@gmail.com",new Team(2,"wow"),lb));
		  FloorLayout fl= new FloorLayout(2,1,2,
				  "(1,0,1,0,1):(0,1,0,1,0):(1,0,1,1,1):(0,1,0,1,0):(1,0,1,0,1)",1);
				  when(flrdse.getFloorLayout(1,2)).thenReturn(fl);
		  
		  Booking b=new Booking(2,1,1,2,"2021-08-21","2,3");
		  when(brepo.save(b)).thenReturn(b);
		  assertEquals(b,bser.addBooking(b));
		  
		  }
	  @Test
	  public void testGetCurrentFloorBooking() { FloorLayout fl= new
	  FloorLayout(2,1,2,
	  "(1,0,1,0,1):(0,1,0,1,0):(1,0,1,0,1):(0,1,0,1,0):(1,0,1,0,1)",1);
	  when(flrdse.getFloorLayout(1,2)).thenReturn(fl);
	  assertTrue("updated matrix",fl.getLayoutMatrix().split(":")[2].replace(
	  "[\\(\\)]","").split(",")[3]!=bser.getCurrentFloorBooking(1, 1,
	  2,Date.valueOf("2021-08-21")).getLayoutMatrix().split(":")[2].replace(
	  "[\\(\\)]","").split(",")[3]); 
	 
	  }
	  
	  @Test
	  public void testUpdateBooking() {
		  when(brepo.getById(2)).thenReturn(new Booking(2,1,1,2,"2021-08-12","2,4"));
		  List<Booking> lb=new ArrayList<>();
			lb.add(new Booking(2,1,1,2,"2021-08-12","2,4"));
			  when(urepo.findById(anyInt())).thenReturn(new User(1,"Soumya Mukhopadhyay","sm31@gmail.com",new Team(2,"wow"),lb));
		  
			  FloorLayout fl= new FloorLayout(2,1,2,
					  "(1,0,1,0,1):(0,1,0,1,0):(1,0,1,0,1):(0,1,0,1,0):(1,0,1,0,1)",1);
					  when(flrdse.getFloorLayout(1,2)).thenReturn(fl);
					  
		  Booking b=new Booking(2,1,1,2,"2021-08-21","2,3");
		  when(brepo.save(b)).thenReturn(b);
		  assertNotEquals("2,4",bser.updateBooking(b).getLocation());
	  }
	  
	  @Test
	  public void testDeleteBooking() throws Exception {
		  assertFalse("No booking as such",bser.deleteBookingById(1)==0);
	  }
	  

}
