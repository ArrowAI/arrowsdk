package com.btpltech.bankingbot.Activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.btpltech.bankingbot.Fragment.ConversationHistory;
import com.btpltech.bankingbot.Fragment.ListChat;

/**
 * Created by Suman kumar jha on 5/16/2016.
 */
public class Pager extends FragmentStatePagerAdapter
{
    int tabCount;

    public Pager(FragmentManager fm, int tabCount)
    {
        super(fm);
        this.tabCount = tabCount;
    }
    @Override
    public Fragment getItem(int position)
    {
        switch (position)
        {
            case 0:
                ListChat tab1 = new ListChat();
//                MainActivity tab1 = new MainActivity();
                return tab1;
            case 1:
                //ListChat tab2 = new ListChat();
                ConversationHistory tab2 = new ConversationHistory();
                return tab2;
            default:
                return null;
        }
    }

    //Overriden method getCount to get the number of tabs
    @Override
    public int getCount() {
        return tabCount;
    }
}
