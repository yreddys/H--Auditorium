package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Booking;
import com.example.demo.entity.Seat;
import com.example.demo.entity.SeatRow;
import com.example.demo.service.BookingService;

@Controller
public class BookingController {

	@Autowired
	private BookingService bookingService;

	private List<SeatRow> busLayout;

	private Set<Integer> bookedSeatNumbers = new HashSet<>(); // Store booked seat numbers

	private Seat findSelectedSeat(int selectedSeatNumber) {
		for (SeatRow row : generateBusLayout()) {
			for (Seat seat : row.getSeats()) {
				if (seat.getNumber() == selectedSeatNumber) {
					return seat;
				}
			}
		}
		return null;
	}

	private List<SeatRow> generateBusLayout() {
		// Create a sample bus layout with seats
		List<SeatRow> rows = new ArrayList<>();
		int seatsPerRow = 4; // Seats per row
		int totalRows = 4; // Total number of rows

		int seatNumber = 1;
		for (int i = 1; i <= totalRows; i++) {
			List<Seat> seats = new ArrayList<>();
			for (int j = 1; j <= seatsPerRow; j++) {
				Seat seat = new Seat();
				seat.setNumber(seatNumber);
				seat.setAvailable(true);
				seats.add(seat);
				seatNumber++;
			}
			rows.add(new SeatRow(seats));
		}
		return rows;
	}

	@GetMapping("/bus")
	public String showBusLayout(Model model) {
		List<SeatRow> busLayout = generateBusLayout();
		model.addAttribute("busLayout", busLayout);
		model.addAttribute("booking", new Booking()); // Initialize an empty Booking object
		return "buslayout";
	}

	@PostMapping("/confirm")
	public String confirmBooking(@ModelAttribute("booking") Booking booking, Model model) {
		// Handle booking confirmation here
		// Example: Create a Booking object and store it in your data store
		Booking newBooking = new Booking(booking.getSeatNumber(), booking.getName(), booking.getAge(),
				booking.getMobile(), booking.getEmail(), booking.getFare());
		bookingService.createBooking(newBooking);

		model.addAttribute("booking", newBooking); // Pass the booking object to the confirmation page
		return "booking-confirmation"; // Return a view for booking confirmation
	}

	@PostMapping("/book")
	public String bookSeat(@RequestParam("selectedSeats") int selectedSeatNumber, Model model) {
		Seat selectedSeat = findSelectedSeat(selectedSeatNumber);
		Booking booking = new Booking();

		if (bookedSeatNumbers.contains(selectedSeatNumber)) {
			// Unbook the seat and remove it from the bookedSeatNumbers set
			selectedSeat.setAvailable(true);
			bookedSeatNumbers.remove(selectedSeatNumber);
			model.addAttribute("bookingMessage", "Seat " + selectedSeatNumber + " is unbooked.");
			model.addAttribute("busLayout", busLayout);
			return "buslayout"; // Return to the bus layout page
		} else if (selectedSeat != null && selectedSeat.isAvailable()) {
			selectedSeat.setAvailable(false); // Mark the seat as booked
			bookedSeatNumbers.add(selectedSeatNumber); // Store the booked seat number
			booking.setSeatNumber(selectedSeatNumber);
			model.addAttribute("booking", booking);
			return "bus-seat-booking"; // Proceed to passenger details form
		} else {
			model.addAttribute("bookingMessage", "Seat " + selectedSeatNumber + " is already booked or not available.");
			model.addAttribute("busLayout", busLayout);
			return "buslayout"; // Return to the bus layout page
		}
	}
	 @GetMapping("/allBookings")
	    public String getAllBookings(Model model) {
	        List<Booking> bookings = bookingService.findAllBookings();
	        model.addAttribute("bookings", bookings);
	        return "allBookings"; 
	    }
}