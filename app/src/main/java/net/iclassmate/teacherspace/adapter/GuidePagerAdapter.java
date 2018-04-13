package net.iclassmate.teacherspace.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
/**
 * Created by xyd on 2016/1/29.
 */
public class GuidePagerAdapter extends PagerAdapter {

	private Context context;
	private List<View> views;

	public GuidePagerAdapter(Context context, List<View> views) {
		this.context = context;
		this.views = views;

	}

	@Override
	public int getCount() {
		return views == null ? 0 : views.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		((ViewPager) container).removeView(views.get(position));
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		((ViewPager) container).addView(views.get(position), 0);
		return views.get(position);

	}

}
