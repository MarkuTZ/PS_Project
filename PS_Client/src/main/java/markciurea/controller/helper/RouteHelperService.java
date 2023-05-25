package markciurea.controller.helper;

import markciurea.model.entities.thrashLocation.ThrashLocation;
import markciurea.model.entities.user.User;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class RouteHelperService {

    public static List<Pair<Integer, Integer>> computeRoute(User employee) {
        List<ThrashLocation> thrashLocationList = employee.getThrashLocations();

        List<ThrashLocation> route = new ArrayList<>();
        Queue<ThrashLocation> queue = new LinkedList<>();

        // Start from the first thrashLocation in the list and keep going to the nearest one.
        queue.add(thrashLocationList.get(0));
        route.add(thrashLocationList.get(0));
        while (!queue.isEmpty()) {
            ThrashLocation currLocation = queue.poll();
            // Find the nearest location unvisited location.
            List<ThrashLocation> unvisitedNeighbours = thrashLocationList.stream().filter(thrashLocation -> !route.contains(thrashLocation)).toList();
            long min = Long.MAX_VALUE;
            ThrashLocation nearestNeighbour = null;
            for (ThrashLocation neighbour : unvisitedNeighbours) {
                if (manhattanDistance(currLocation, neighbour) < min) {
                    min = manhattanDistance(currLocation, neighbour);
                    nearestNeighbour = neighbour;
                }
            }
            // If we found a neighbour we add it to queue and route.
            if (nearestNeighbour != null) {
                queue.add(nearestNeighbour);
                route.add(nearestNeighbour);
            }
        }

        return route.stream()
                .map(thrashLocation ->
                        Pair.of(thrashLocation.getX().intValue(), thrashLocation.getY().intValue()))
                .toList();
    }

    private static long manhattanDistance(ThrashLocation src, ThrashLocation dst) {
        return Math.abs(src.getX() - dst.getX()) + Math.abs(src.getY() - dst.getY());
    }
}
