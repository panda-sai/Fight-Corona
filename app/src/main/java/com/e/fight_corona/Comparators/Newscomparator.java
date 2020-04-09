package com.e.fight_corona.Comparators;

import com.e.fight_corona.models.News;

import java.sql.Timestamp;
import java.util.Comparator;

public class Newscomparator implements Comparator<News>
{
    public int compare(News q1,News q2)
    {
        long x=(long)q1.getTime();
        long y=(long)q2.getTime();
        Timestamp new1=new Timestamp(x);
        Timestamp new2=new Timestamp(y);
        return(new2.compareTo(new1));
    }
}
