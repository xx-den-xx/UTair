package ru.bda.utair.presenter.mappers;

import java.util.List;

import ru.bda.utair.model.dto.TownResultDTO;
import ru.bda.utair.presenter.vo.Town;
import rx.Observable;
import rx.functions.Func1;

/**
 * email: denbelobaba@gmail.com
 *
 * @author Belobaba Denis
 */

public class TownMappers implements Func1<TownResultDTO, List<Town>> {
    @Override
    public List<Town> call(TownResultDTO townResultDTO) {

        List<Town> towns = Observable.from(townResultDTO.getTowns())
                .map(townDTO -> new Town(townDTO))
                .toList()
                .toBlocking()
                .first();
        return towns;
    }
}
