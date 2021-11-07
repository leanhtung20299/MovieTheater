$(document).ready(function() {
    sessionStorage.setItem("date", $("#date").text());
    console.log($("#date").text());
    console.log(sessionStorage.getItem("IndexDayPanigation"));
    if (sessionStorage.getItem("IndexDayPanigation") == null) {
        setTimeout(function() {
            $($('.btnShowDate').eq(0)).addClass("showDatePanigation");
        }, 100);
    }

    $('.btnShowDate').click(function() {

        var rowSelectDate = Array.prototype.slice.call($('.btnShowDate'));
        var indexDate = rowSelectDate.indexOf(this);
        sessionStorage.setItem("IndexDayPanigation", indexDate);
        // alert(indexDate);

        // sessionStorage.setItem("indexDay", indexDate);

        // get Day and Date of month

        var showdateofmonth = $('.showdateofmonth').eq(indexDate).text();
        var showdateofweek = $('.showdateofweek').eq(indexDate).text();
        // alert(showdateofweek + '/' + showdateofmonth);

        // alert(showdateofmonth + " " + showdateofweek);
        var timeWeekDisplay = $('#timeWeekDisplay').text();
        // alert("Update!");
        localStorage.setItem("timeWeekDisplay", timeWeekDisplay);
        $('.btnShowDate').removeClass("showDatePanigation");
        $.get({
            url: "/booking-ticket/ShowtimeViews/getShowdatebyDay",
            data: {
                day: showdateofweek,
                dateofmonth: showdateofmonth,
                timeweek: timeWeekDisplay,
            },
            success: function(response) {
                var newDOM = response.split("<body")[1].split(">").slice(1).join(">").split("</body>")[0];
                $("body").html(newDOM);
            }
        });

        setTimeout(function() {
            $($('.btnShowDate').eq(indexDate)).addClass("showDatePanigation");
        }, 200);



    });

    $('.timeWeekPanigation').click(function() {
        sessionStorage.removeItem("IndexDayPanigation");
        var rowSelectTimeWeek = Array.prototype.slice.call($('.timeWeekPanigation'));
        var indexTimeWeek = rowSelectTimeWeek.indexOf(this);

        // alert(indexTimeWeek);

        // sessionStorage.setItem("indexDay", indexDate);

        // get Day and Date of month
        var showTimeweek = $('.timeWeek').eq(indexTimeWeek).text();

        // var timeWeekDisplay = $('#timeWeekDisplay').text();

        // alert(showTimeweek);
        $.get({
            url: "/booking-ticket/ShowtimeViews/getShowbyWeek",
            data: {
                timeWeek: showTimeweek
            },
            success: function(response) {
                var newDOM = response.split("<body")[1].split(">").slice(1).join(">").split("</body>")[0];
                $("body").html(newDOM);
            }
        });




    });

});