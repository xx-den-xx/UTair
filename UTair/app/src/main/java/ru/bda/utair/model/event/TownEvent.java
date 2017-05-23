package ru.bda.utair.model.event;

import ru.bda.utair.presenter.vo.Town;

/**
 * email: denbelobaba@gmail.com
 *
 * @author Belobaba Denis
 */

public class TownEvent {
    public final Town town;
    public final String route;
    public TownEvent(Town town, String route) {
        this.town = town;
        this.route = route;
    }
}
