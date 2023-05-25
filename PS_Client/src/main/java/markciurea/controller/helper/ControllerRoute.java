package markciurea.controller.helper;


import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public interface ControllerRoute {

    List<Pair<Integer, Integer>> getRouteCheckpoints();

    String getEmailForRoute();

}
