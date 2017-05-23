package ru.bda.utair.view;

import java.util.List;

import ru.bda.utair.presenter.vo.Town;

/**
 * email: denbelobaba@gmail.com
 *
 * @author Belobaba Denis
 */

public interface TakeTownView extends View {
    void showTowns(List<Town> towns);
    void hideTowns();
    void startProgress();
    void stopProgress();
    void showClickTown(Town town);
}
