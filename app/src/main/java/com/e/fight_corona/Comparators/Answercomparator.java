package com.e.fight_corona.Comparators;

import com.e.fight_corona.models.Answer;
import com.e.fight_corona.models.Query;

import java.sql.Timestamp;
import java.util.Comparator;


public class Answercomparator implements Comparator<Answer>
{
    public int compare(Answer a1,Answer a2)
    {
        long x=(long)a1.getTime();
        long y=(long)a2.getTime();
        Timestamp new1=new Timestamp(x);
        Timestamp new2=new Timestamp(y);
        return(new2.compareTo(new1));
    }
}
