package ru.bda.utair.model.event;

import java.util.Calendar;

import ru.bda.utair.presenter.vo.Town;

/**
 * email: denbelobaba@gmail.com
 *
 * @author Belobaba Denis
 */

public class DataTownsEvent {
    public final Town townFrom;
    public final Town townTo;
    public final Calendar dateForward;
    public final Calendar dateReturn;

    public DataTownsEvent (Town townFrom, Town townTo, Calendar dateForward, Calendar dateReturn) {
        this.townFrom = townFrom;
        this.townTo = townTo;
        this.dateForward = dateForward;
        this.dateReturn = dateReturn;

    }
}
