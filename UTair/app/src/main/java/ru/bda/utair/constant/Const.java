package ru.bda.utair.constant;

/**
 * email: denbelobaba@gmail.com
 *
 * @author Belobaba Denis
 */

public interface Const {

    String API_TOWN = "https://api.meetup.com/2/";
    String API_KEY_TOWN = "3a2c819137b456b772e447d33258";

    String API_WEATHER = "http://api.openweathermap.org/data/2.5/";
    String API_KEY_WEATHER = "aa1f8aa50ffb1863a789594d7f122002";

    String PASS_MODE_ADULT = "adult";
    String PASS_MODE_CHILD = "child";
    String PASS_MODE_INFANT = "infant";

    String INTENT_TAKE_TOWN = "intent_take_town";
    String FLIGHT_TOWN_UP = "flight_town_up";
    String FLIGHT_TOWN_DOWN = "flight_town_down";

    String INTENT_TOWN_FROM = "intent_town_from";
    String INTENT_TOWN_TO = "intent_town_to";
    String INTENT_DATE_FORWARD = "intent_date_forward";
    String INTENT_DATE_RETURN = "intent_date_return";

    String WEATHER_ICON_URL = "http://openweathermap.org/img/w/";
    String CELSIUS = "\u2103";
}
