package com.team21.SocialDesking.services;

import com.team21.SocialDesking.entities.Booking;
import com.team21.SocialDesking.entities.FloorLayout;
import com.team21.SocialDesking.repository.BookingRepository;
import com.team21.SocialDesking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


@Service
public class BookingServiceImpl implements BookingService{

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    FloorLayoutService floorLayoutService;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Booking addBooking(Booking booking) {
        if(userRepository.findById(booking.getUserId()) != null) {
            String tempDate = booking.getDate();
            Date date = convertStringToDate(tempDate);
            booking.setbDate(date);
            FloorLayout floorLayout = floorLayoutService.getFloorLayout(booking.getBuildingId(), booking.getFloorId());
            if (floorLayout.getAvailable() == 1) {
                String layout = floorLayout.getLayoutMatrix();
                List<List<Integer>> matrix = convertStringToMatrix(layout);
                matrix = updateMatrixWithModifiedLayout(matrix, booking.getBuildingId(),
                        booking.getFloorId(), date); //marks as unavailable
                matrix = updateMatrixWithModifiedLayoutForTeammates(matrix, booking.getUserId(),
                        booking.getBuildingId(), booking.getFloorId(), date);  //marks for bookings done by teammates
                String[] coordinates = booking.getLocation().split(",");
                if (matrix.get(Integer.parseInt(coordinates[0])).get(Integer.parseInt(coordinates[1])) != 1) {
                    return new Booking();
                } else {
                    return bookingRepository.save(booking);
                }
            } else {
                return new Booking();
            }
        }else{
            return new Booking();
        }
    }

    @Override
    public FloorLayout getCurrentFloorBooking(int userId, int buildingId, int floorId, Date date) {
        FloorLayout floorLayout = floorLayoutService.getFloorLayout(buildingId,floorId);
        String layout = floorLayout.getLayoutMatrix();
        List<List<Integer>> matrix = convertStringToMatrix(layout);

        
       matrix=updateMatrixWithModifiedLayout(matrix,buildingId,floorId,date); //marks as unavailable
       matrix=updateMatrixWithModifiedLayoutForTeammates(matrix,userId,buildingId,floorId,date);  //marks for bookings done by teammates
       String modifiedLayout = convertMatrixToString(matrix);
        floorLayout.setLayoutMatrix(modifiedLayout.substring(0,modifiedLayout.length()-1));
        return floorLayout;
    }

    @Override
    public List<Booking> getBookingsByDate(Date date) {
        return bookingRepository.findBookingByBDate(date);
    }

    @Override
    public Booking updateBooking(Booking new_booking)
    {
        Booking prev_booking = bookingRepository.getById(new_booking.getBookingID());
        String tempDate = new_booking.getDate();
        Date date = convertStringToDate(tempDate);
        if((userRepository.findById(new_booking.getUserId()) != null) && (prev_booking.getUserId()==new_booking.getUserId())) {
            FloorLayout floorLayout = floorLayoutService.getFloorLayout(new_booking.getBuildingId(), new_booking.getFloorId());
            if (floorLayout.getAvailable() == 1) {
                String layout = floorLayout.getLayoutMatrix();
                List<List<Integer>> matrix = convertStringToMatrix(layout);
                matrix = updateMatrixWithModifiedLayout(matrix, new_booking.getBuildingId(),
                        new_booking.getFloorId(), date); //marks as unavailable
                matrix = updateMatrixWithModifiedLayoutForTeammates(matrix, new_booking.getUserId(),
                        new_booking.getBuildingId(), new_booking.getFloorId(), date);  //marks for bookings done by teammates
                String[] coordinates = new_booking.getLocation().split(",");
                if (matrix.get(Integer.parseInt(coordinates[0])).get(Integer.parseInt(coordinates[1])) != 1) {
                    return new Booking();
                } else {
                    prev_booking.setbDate(date);
                    prev_booking.setBuildingId(new_booking.getBuildingId());
                    prev_booking.setLocation(new_booking.getLocation());
                    prev_booking.setFloorId(new_booking.getFloorId());
                    return bookingRepository.save(prev_booking);
                }
            } else {
                return new Booking();
            }
        }else{
            return new Booking();
        }
    }

    @Override
    public int deleteBookingById(int bookingid)
    {
        try {
            bookingRepository.deleteById(bookingid);
            return 1;
        }
        catch(Exception e)
        {
            return 0;
        }
    }

    //Helper functions
    
    private List<List<Integer>> updateMatrixWithModifiedLayout(List<List<Integer>> matrix,int buildingId,int floorId,Date date){
    	 List<Booking> bList=bookingRepository.getLocationsByUserSel(buildingId,floorId,date);
         for(Booking btemp:bList) {
         	String []locPair=btemp.getLocation().split(",");
         	matrix.get(Integer.parseInt(locPair[0])).set(Integer.parseInt(locPair[1]),0);
         }
		return matrix;
    }

	
	  private List<List<Integer>> updateMatrixWithModifiedLayoutForTeammates(List<List<Integer>>matrix,int userId,int bid,int fid,Date dt){ int
	  teamId=bookingRepository.getTeamId(userId); 
	  List<Booking> blist=bookingRepository.getBookingsForTeammates(userId, teamId,bid,fid,dt);
	  for(Booking btemp:blist){
	      String []locPair=btemp.getLocation().split(",");
	      matrix.get(Integer.parseInt(locPair[0])).set(Integer.parseInt(locPair[1]),2);
	  }
	  return matrix;
	  }

    private String convertMatrixToString(List<List<Integer>> matrix) {
        String modifiedLayout = "";
        for(List<Integer> rows_test : matrix)
        {
            if(!rows_test.isEmpty())
            {
                modifiedLayout += '(';
                modifiedLayout += rows_test.get(0);
                modifiedLayout += ',';
                for(int i=1; i < rows_test.size(); i++)
                {
                    modifiedLayout += rows_test.get(i);
                    if(i < rows_test.size()-1)
                        modifiedLayout += ',';
                }
            }
            modifiedLayout += "):";
        }
        return modifiedLayout;
    }

    private List<List<Integer>> convertStringToMatrix(String layout) {
        List<List<Integer>> matrix = new ArrayList<List<Integer>>();
        layout = layout.replaceAll("\\(","");
        layout = layout.replaceAll("\\)","");
        String[] rows = layout.split(":");
        for(String line : rows)
        {
            String[] linePieces = line.split(",");
            List<Integer> matrixPieces = new ArrayList<Integer>(linePieces.length);
            for(String piece : linePieces)
            {
                matrixPieces.add(Integer.valueOf(piece.trim()));
            }
            matrix.add(matrixPieces);
        }
        return matrix;
    }

    private Date convertStringToDate(String tempDate){
        return Date.valueOf(tempDate);
    }
}
