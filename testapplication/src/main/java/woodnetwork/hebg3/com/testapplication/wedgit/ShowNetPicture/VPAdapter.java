package woodnetwork.hebg3.com.testapplication.wedgit.ShowNetPicture;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class VPAdapter extends FragmentPagerAdapter {

	private List<Fragment> list = new ArrayList<Fragment>();

	public VPAdapter(FragmentManager fm) {
		super(fm);
	}

	public VPAdapter(FragmentManager fm, List<Fragment> list) {
		super(fm);
		this.list = list;
	}

	@Override
	public Fragment getItem(int arg0) {
		return list.get(arg0);
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public int getItemPosition(Object object) {
		return super.getItemPosition(object);
	}
}
