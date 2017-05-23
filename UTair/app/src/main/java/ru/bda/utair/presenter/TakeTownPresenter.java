package ru.bda.utair.presenter;

import java.util.List;

import ru.bda.utair.model.Model;
import ru.bda.utair.model.ModelImpl;
import ru.bda.utair.model.dto.TownDTO;
import ru.bda.utair.model.dto.TownResultDTO;
import ru.bda.utair.presenter.mappers.TownMappers;
import ru.bda.utair.presenter.vo.Town;
import ru.bda.utair.view.TakeTownView;
import ru.bda.utair.view.View;
import rx.Observer;
import rx.Subscription;
import rx.subscriptions.Subscriptions;

/**
 * email: denbelobaba@gmail.com
 *
 * @author Belobaba Denis
 */

public class TakeTownPresenter implements Presenter {

    private Model model = new ModelImpl();

    private TakeTownView view;
    private Subscription subscription = Subscriptions.empty();
    private TownMappers townMappers = new TownMappers();

    public TakeTownPresenter(TakeTownView view) {
        this.view = view;
    }

    public void getTownList(String city) {

        onStop();

        if (city.length() > 2) {
            view.hideTowns();
            view.startProgress();
            subscription = model.getTown(city)
                    .map(townMappers)
                    .subscribe(new Observer<List<Town>>() {
                        @Override
                        public void onCompleted() {
                            view.stopProgress();
                        }

                        @Override
                        public void onError(Throwable e) {
                            view.stopProgress();
                            view.showError(e.toString());
                        }

                        @Override
                        public void onNext(List<Town> towns) {
                            view.showTowns(towns);
                        }
                    });
        }
    }

    public void onClickTown(Town town) {
        view.showClickTown(town);
    }

    @Override
    public void onStop() {
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
            view.stopProgress();
        }
    }
}
