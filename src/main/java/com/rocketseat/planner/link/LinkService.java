package com.rocketseat.planner.link;

import com.rocketseat.planner.activity.ActivityData;
import com.rocketseat.planner.trip.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class LinkService {

    @Autowired
    private LinkRepository repository;

    public LinkResponse registerLink(LinkRequestPayload payload, Trip trip) {
        Link newLink = new Link(payload.title(), payload.url(), trip);

        this.repository.save(newLink);

        return new LinkResponse(newLink.getId());
    }

    public List<ActivityData> getAllActivitiesFromId(UUID tripId) {
        return new ArrayList<>();
    }

    public List<LinkData> getAllLinksFromTripId(UUID tripId) {
        return this.repository.findByTripId(tripId).stream().map(link -> new LinkData(link.getId(), link.getTitle(), link.getUrl())).collect(Collectors.toList());
    }
}
