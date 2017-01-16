package com.example.pierrickvinot.myapplication.models;

import android.text.format.DateFormat;

import com.example.pierrickvinot.myapplication.tools.InternalSearcher;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by bribri on 11/01/2017.
 */

public class ListEvents {

    private List<Event> eventlist;
    private List<String> datesWithEvent;

    public ListEvents() {
        this.eventlist = new ArrayList<Event>();
        this.datesWithEvent = new ArrayList<String>();
    }

    public List<Event> getEventlist() {
        return eventlist;
    }

    public void setEventlist(List<Event> eventlist) {
        this.eventlist = eventlist;
    }

    public List<String> getDatesWithEvent() {
        return datesWithEvent;
    }

    public void setDatesWithEvent(List<String> datesWithEvent) {
        this.datesWithEvent = datesWithEvent;
    }

    public void getSavedDatas() {

        ListEvents save = InternalSearcher.getEvents();

        if(save==null){
            return;
        }

        this.eventlist = save.getEventlist();
        this.datesWithEvent = save.getDatesWithEvent();

        refreshDates();

    }

    private void refreshDates(){
        datesWithEvent = new ArrayList<String>();
        for (int i = 0; i < eventlist.size(); i++)
        {
            Event event = eventlist.get(i);
            Calendar var = Calendar.getInstance();
            var.setTime(event.start);
            Calendar fin = Calendar.getInstance();
            fin.setTime(event.end);
            fin.add(Calendar.DATE,1);
            while(var.getTime().before(fin.getTime())){
                datesWithEvent.add(DateFormat.format("yyyy.MM.dd", var.getTime()).toString());
                var.add(Calendar.DATE,1);
            }
        }
    }

    public int size(){
        return eventlist.size();
    }

    public Event get(int i){
        return eventlist.get(i);
    }

    public boolean isDateWithEvent(Date date){
        boolean verif = datesWithEvent.contains(DateFormat.format("yyyy.MM.dd", date).toString());
        return verif;
    }

    public List<Event> getEventsFromDate(Date date){
        List<Event> result = new ArrayList<Event>();
        for (int i = 0; i < eventlist.size(); i++) {
            Event event = eventlist.get(i);
            if(event.isDateInEvent(date))
                result.add(event);
        }
        return result;
    }

    public void add(Event event)
    {
        eventlist.add(event);
        addDates(event);
    }

    public void delete(Event event)
    {
        eventlist.remove(event);
        refreshDates();
    }
    public void merge(ListEvents toAdd){
        List<Event> list = toAdd.getEventlist();
        for (int i = 0; i < list.size(); i++) {
            if(!eventlist.contains(list.get(i))){
                eventlist.add(list.get(i));
                addDates(list.get(i));
            }
        }
    }

    private void addDates(Event event){
        Calendar var = Calendar.getInstance();
        var.setTime(event.start);
        Calendar fin = Calendar.getInstance();
        fin.setTime(event.end);
        fin.add(Calendar.DATE,1);
        while(var.getTime().before(fin.getTime())){
            datesWithEvent.add(DateFormat.format("yyyy.MM.dd", var.getTime()).toString());
            var.add(Calendar.DATE,1);
        }
    }
}
