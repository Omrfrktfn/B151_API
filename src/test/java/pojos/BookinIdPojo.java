package pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BookinIdPojo {

    private Integer bookingId;
    private BookingPojo booking;


    public BookinIdPojo() {
    }

    public BookinIdPojo(Integer bookingId, BookingPojo booking) {
        this.bookingId = bookingId;
        this.booking = booking;
    }

    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public BookingPojo getBooking() {
        return booking;
    }

    public void setBooking(BookingPojo booking) {
        this.booking = booking;
    }

    @Override
    public String toString() {
        return "BookinIdPojo{" +
                "bookingId=" + bookingId +
                ", booking=" + booking +
                '}';
    }
}
