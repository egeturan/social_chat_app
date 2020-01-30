package com.example.hugo.danismanchat2;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabsAccessorAdapter extends FragmentPagerAdapter {

    public TabsAccessorAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position)
    {
        switch (position)
        {
            case 0:
                ChatsFragment chatsFragment = new ChatsFragment();
                return chatsFragment;
            case 1:
                GroupsFragment groupsFragment = new GroupsFragment();
                return groupsFragment;
            case 2:
                ContactsFragment contactsFragment = new ContactsFragment();
                return contactsFragment;
       /*     case 3:
                RequestFragment requestFragment = new RequestFragment();
                return requestFragment;*/

                default:
                  return null;
        }

    }

    @Override
    public int getCount() {
        return 3;
        //return 4;
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position)
    {
        switch (position)
        {
            case 0:
                return "Konuşma Geçmişi";
            case 1:
                return "Danışmanlarım";
            case 2:
                return "Danışman Bul";
      /*      case 3:
                return "Konuşma Talepleri"; */
            default:
                return null;
        }
    }
}
