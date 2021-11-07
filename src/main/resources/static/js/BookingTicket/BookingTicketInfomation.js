$(document).ready(function() {

    var promotionDiscount = parseInt(($('.btn-promotion').eq(0)).attr('data-value'));
    var totalPrice = parseInt($('#total-price').attr('data-value').toString().slice(0, $('#total-price').attr('data-value').toString().length));
    $('#total-price').text(parseInt(totalPrice - promotionDiscount) + 'đ');
    // alert(totalPrice - promotionDiscount);
    sessionStorage.setItem("idPromotion", 0);



    $(".dropdown-menu a").click(function() {
        $(this).parents(".dropdown").find('.btn').html($(this).text());
        $(this).parents(".dropdown").find('.btn').val($(this).data('value'));
        var rowSelectDate = Array.prototype.slice.call($('.btn-promotion'));
        var indexDate = rowSelectDate.indexOf(this);
        var promotionDiscount = parseInt(($('.btn-promotion').eq(indexDate)).attr('data-value'));
        var totalPrice = parseInt($('#total-price').attr('data-value').toString().slice(0, $('#total-price').attr('data-value').toString().length));
        // alert(totalPrice + ' ' + parseInt(totalPrice - promotionDiscount));
        sessionStorage.setItem("idPromotion", indexDate);
        // alert(($('.btn-promotion').eq(sessionStorage.getItem("idPromotion"))).attr('id'));
        console.log(typeof promotionDiscount)
        console.log(typeof totalPrice);
        $('#total-price').text(parseInt(totalPrice - promotionDiscount) + 'đ');
    });
    $('#saveBooking').click(function() {
        var BookingSeatsDTO = JSON.parse(localStorage.getItem('BookingSeatsDTO'));
        BookingSeatsDTO['promotionId'] = ($('.btn-promotion').eq(sessionStorage.getItem("idPromotion"))).attr('id');
        var listSeats = JSON.parse(localStorage.getItem('listSeats'));
        console.log(BookingSeatsDTO);
        $.post({
            url: '/booking-ticket/SaveBooking',
            contentType: "application/json",
            data: JSON.stringify(BookingSeatsDTO),
            success: function(response) {
                var dataStatusBookingResponce = response;
                console.log(dataStatusBookingResponce);
                if (dataStatusBookingResponce.statusTransaction == "Failed") {
                    alert("Booking not completed because seat not available");
                    $.get({
                        url: "/booking-ticket/bookingSeatMovies/" + BookingSeatsDTO.movieId + '/' + BookingSeatsDTO.scheduleId + '/' + BookingSeatsDTO.date,
                        success: function(response) {
                            var newDOM = response.split("<body")[1].split(">").slice(1).join(">").split("</body>")[0];
                            $("body").html(newDOM);
                        }
                    });

                    setTimeout(function() {
                        var positionSeatNotAvailable = "Position index seat at ";
                        var listSeatNotAvailable = dataStatusBookingResponce.listSeatNotAvailable;
                        for (let indexSeat = 0; indexSeat < listSeats.length; indexSeat++) {
                            var flagStatusBooking = true;
                            for (let indexSeatNotAvailable = 0; indexSeatNotAvailable < listSeatNotAvailable.length; indexSeatNotAvailable++) {
                                if (listSeatNotAvailable[indexSeatNotAvailable] == listSeats[indexSeat]) {
                                    positionSeatNotAvailable = positionSeatNotAvailable + $('#' + listSeats[indexSeat]).text() + " ";
                                    flagStatusBooking = false;
                                }
                            }
                            if (flagStatusBooking == true) {
                                $('#' + listSeats[indexSeat]).addClass('seat-Bookingselect');
                            }
                        }
                        alert(positionSeatNotAvailable + " not available");
                    }, 200);

                } else if (dataStatusBookingResponce.statusTransaction == "Timeout") {
                    alert("Save not completed because time out booking");
                    localStorage.removeItem("BookingSeatsDTO");
                    localStorage.removeItem("listSeats");
                    sessionStorage.removeItem("IndexDayPanigation");
                    window.location.href = "/booking-ticket";

                } else {
                    alert(dataStatusBookingResponce.statusTransaction);
                    $.post({
                        url: "/booking-ticket/ResultBooking",
                        data: {
                            listBookSeat: JSON.parse(localStorage.getItem('listSeats')),
                            movieId: localStorage.getItem("movieId"),
                            scheduleId: localStorage.getItem("scheduleId"),
                            date: sessionStorage.getItem("date"),
                            promotionId: ($('.btn-promotion').eq(sessionStorage.getItem("idPromotion"))).attr('id'),
                        },
                        success: function(response) {
                            var newDOM = response.split("<body")[1].split(">").slice(1).join(">").split("</body>")[0];
                            $("body").html(newDOM);
                        }
                    });
                    localStorage.removeItem("BookingSeatsDTO");
                    localStorage.removeItem("listSeats");
                }
            }
        });
    });

    $('#back').click(function() {
        var listSeats = JSON.parse(localStorage.getItem('listSeats'));
        var BookingSeatsDTO = JSON.parse(localStorage.getItem('BookingSeatsDTO'));
        $.get({
            url: "/booking-ticket/bookingSeatMovies/" + BookingSeatsDTO.movieId + '/' + BookingSeatsDTO.scheduleId + '/' + BookingSeatsDTO.date,
            success: function(response) {
                var newDOM = response.split("<body")[1].split(">").slice(1).join(">").split("</body>")[0];
                $("body").html(newDOM);
            }

        });
        sessionStorage.removeItem("IndexDayPanigation");

    });

});