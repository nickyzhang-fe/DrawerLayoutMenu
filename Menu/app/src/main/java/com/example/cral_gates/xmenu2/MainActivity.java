package com.example.cral_gates.xmenu2;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;


import com.example.cral_gates.base.BaseActivity;
import com.example.cral_gates.base.Constants;
import com.example.cral_gates.fragment.ContentFragment;
import com.example.cral_gates.fragment.MenuFragment;
import com.example.cral_gates.widget.xmenu.XMenu;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseActivity implements MenuFragment.OnFragmentInteractionListener{
    private XMenu xMenu;
    private MenuFragment mMenuFragment;
    private ContentFragment mContentFragment;
    private String[] test = {"t1","t2","t3","t4","t5","t6","test7"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        xMenu = new XMenu(this);
        setContentView(xMenu);
        
        configContent();
        configMenu();
    }

    private void configMenu() {
        mMenuFragment = new MenuFragment(test);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.menu_frame, mMenuFragment).commit();
        xMenu.setMenu(R.layout.menu_container);
        int width = Math.min(Constants.sWidth, Constants.sHeight);
        xMenu.setMenuWidth(3 * width / 4);
    }

    /*
     * #0001 外部传入title
     * leobert
     * 2015-8-28 15:03:47
     * */
    private void configContent() {
    	List<View> mViewList = new ArrayList<View>();
    	for(int i=0;i<test.length;i++){
          mViewList.add(LayoutInflater.from(getApplicationContext()).inflate(R.layout.page_item, null));
      }
        mContentFragment = new ContentFragment(test,mViewList);//new ContentFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, mContentFragment).commit();
        xMenu.setContent(R.layout.activity_main);
    }
    @Override
	public void onFragmentInteraction(String id){
    	//at here we can get the list item id we touch 
    	Log.i("leobert:", "Interaction" + id);
    }
    public void toggle() {
        xMenu.toggle();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                toggle();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
