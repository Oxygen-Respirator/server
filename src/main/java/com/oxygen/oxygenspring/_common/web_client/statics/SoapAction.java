package com.oxygen.oxygenspring._common.web_client.statics;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "soap-action")
@Getter
@Setter
public class SoapAction {

    private Reservation reservation;
    private Name name;
    private Availability availability;

    @Getter
    @Setter
    public static class Reservation {
        private String createBooking;
        private String cancelBooking;
        private String guestRequests;
        private String fixedCharges;
    }

    @Getter
    @Setter
    public static class Name {
        private String fetchProfile;
    }

    @Getter
    @Setter
    public static class Availability {
        private String availability;
        private String fetchCalendar;
        private String fetchExpectedCharges;
    }
}
