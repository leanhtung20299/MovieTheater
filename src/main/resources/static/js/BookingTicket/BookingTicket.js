$(document).ready(function() {

    localStorage.setItem("movieId", $('#movieId').text());
    localStorage.setItem("scheduleId", $('#scheduleId').text());

    // alert(localStorage.getItem("movieId"));
    // alert(localStorage.getItem("scheduleId"));

    $('.minus').click(function() {
        var $input = $(this).parent().find('#quantityBooking');
        var count = parseInt($input.val()) - 1;
        count = count < 1 ? 1 : count;
        $input.val(count);
        $input.change();
        return false;
    });
    $('.plus').click(function() {
        var $input = $(this).parent().find('#quantityBooking');
        $input.val(parseInt($input.val()) + 1);
        $input.change();
        return false;
    });

    $('.seat-select').click(function() {

        // alert($('#quantityBooking').val());
        var rowSelectSeat = Array.prototype.slice.call($('.seat-select'));
        var indexSelectSeat = rowSelectSeat.indexOf(this);
        if ($(this).hasClass("seat-Bookingselect") == true) {
            $('.seat-select').eq(indexSelectSeat).removeClass("seat-Bookingselect");
        } else {
            $(this).addClass("seat-Bookingselect");
            // $(".seat-Nowselecting").css("background-color: rgba(68, 153, 51, 0.774) !important")
        }

        alert('Booking seat ticket at ' + $('.seat-select').eq(indexSelectSeat).text());
        // $('.seat-select').eq(indexSelectSeat).css()


    });

    $('#paymentBooking').click(function() {
        // alert($('#quantityBooking').val());
        var seatBookingSelect = $('.seat-Bookingselect');
        var listBookSeat = [];
        var listSeats = [];

        if (seatBookingSelect.length == $('#quantityBooking').val()) {
            for (let index = 0; index < seatBookingSelect.length; index++) {
                let seatBooking = seatBookingSelect.eq(index).text();
                let seatType;
                let seatId;
                if ($(seatBookingSelect.eq(index).hasClass('seat-upgrade'))) {
                    seatType = 1;
                    seatId = $(seatBookingSelect.eq(index)).attr('id');
                }
                if ($(seatBookingSelect.eq(index).hasClass('seat-normal'))) {
                    seatType = 2;
                    seatId = $(seatBookingSelect.eq(index)).attr('id');
                }

                let seatValue = seatBooking.toString().split(' ');
                var BookSeat = {
                    seatId: seatId,
                    rowSeat: seatValue[0],
                    columeSeat: seatValue[1],
                    seatType: seatType,
                    statusBooking: 1,
                }
                console.log(BookSeat)
                listBookSeat.push(BookSeat);
                listSeats.push(seatId);
            }

            var BookingSeatsDTO = {
                listBookSeat: listBookSeat,
                movieId: localStorage.getItem("movieId"),
                scheduleId: localStorage.getItem("scheduleId"),
                date: sessionStorage.getItem("date"),
            };
            console.log(BookingSeatsDTO);
            localStorage.setItem("BookingSeatsDTO", JSON.stringify(BookingSeatsDTO));

            $.post({
                url: '/booking-ticket/bookingSeat',
                contentType: "application/json",
                data: JSON.stringify(BookingSeatsDTO),
                success: function(response) {
                    var dataStatusBookingResponce = response;
                    console.log(dataStatusBookingResponce);
                    if (dataStatusBookingResponce.statusTransaction == "Failed") {
                        alert("Select not completed because seat not available");
                        var positionSeatNotAvailable = "Position index seat at ";
                        var listSeatNotAvailable = dataStatusBookingResponce.listSeatNotAvailable;
                        for (let indexSeatNotAvailable = 0; indexSeatNotAvailable < listSeatNotAvailable.length; indexSeatNotAvailable++) {
                            for (let indexSeat = 0; indexSeat < listSeats.length; indexSeat++) {
                                if (listSeatNotAvailable[indexSeatNotAvailable] == listSeats[indexSeat]) {
                                    $('#' + listSeats[indexSeat]).removeClass('seat-Bookingselect');
                                    $('#' + listSeatNotAvailable[indexSeatNotAvailable]).addClass('seat-sode');
                                    positionSeatNotAvailable = positionSeatNotAvailable + $('#' + listSeats[indexSeat]).text() + " ";
                                } else {
                                    $('#' + listSeatNotAvailable[indexSeatNotAvailable]).addClass('seat-sode');
                                }
                            }
                        }
                        alert(positionSeatNotAvailable + " not available");
                    } else {
                        localStorage.setItem("listSeats", JSON.stringify(listSeats));

                        $.post({
                            url: "/booking-ticket/ConfirmBooking",
                            data: {
                                listBookSeat: listSeats,
                                movieId: localStorage.getItem("movieId"),
                                scheduleId: localStorage.getItem("scheduleId"),
                                date: sessionStorage.getItem("date")
                            },
                            success: function(response) {
                                var newDOM = response.split("<body")[1].split(">").slice(1).join(">").split("</body>")[0];
                                $("body").html(newDOM);
                            }
                        });
                    }
                }
            });
        } else {
            alert("Booking seat not true quantity!")
            $('#quantityBooking').val()
            if (confirm("Are you want  to booking " + seatBookingSelect.length + " ticket quantity ?")) {
                $(".input").val(seatBookingSelect.length);
            }
        }

    });

});