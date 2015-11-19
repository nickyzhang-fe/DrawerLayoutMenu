package com.example.cral_gates.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.cral_gates.base.BaseFragment;
import com.example.cral_gates.fragment.dummy.DummyContent;
import com.example.cral_gates.fragment.dummy.DummyContent.DummyItem;
import com.example.cral_gates.widget.xmenu.XMenu;
import com.example.cral_gates.xmenu2.R;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Large screen devices (such as tablets) are supported by replacing the
 * ListView with a GridView.
 * <p/>
 * Activities containing this fragment MUST implement the
 * {@link OnFragmentInteractionListener} interface.
 */
public class MenuFragment extends BaseFragment implements
		AbsListView.OnItemClickListener {

	// TODO: Rename parameter arguments, choose names that match
	// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";

	// TODO: Rename and change types of parameters
	private String mParam1;
	private String mParam2;

	private OnFragmentInteractionListener mListener;

	/**
	 * The fragment's ListView/GridView.
	 */
	private AbsListView mListView;

	/**
	 * The Adapter which will be used to populate the ListView/GridView with
	 * Views.
	 */
	private ListAdapter mAdapter;

	private List<DummyItem> mDummyItems = new ArrayList<DummyItem>();

	// TODO: Rename and change types of parameters
	public static MenuFragment newInstance(String param1, String param2) {
		MenuFragment fragment = new MenuFragment();
		Bundle args = new Bundle();
		args.putString(ARG_PARAM1, param1);
		args.putString(ARG_PARAM2, param2);
		fragment.setArguments(args);
		return fragment;
	}

	/**
	 * Mandatory empty constructor for the fragment manager to instantiate the
	 * fragment (e.g. upon screen orientation changes).
	 */
	public MenuFragment() {
	}

	/***
	 * i create this to give the titles in menu
	 * 
	 * @author leobert 2015-8-28 16:42:11
	 */
	public MenuFragment(String[] titles) {
		// change string to dummyitem:map and structure a dummyContent
		DummyContentStruture(titles);
	}

	private void DummyContentStruture(String[] titles) {
		for (int i = 0; i < titles.length; i++) {
			mDummyItems.add(new DummyItem(String.valueOf(i), titles[i]));
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (getArguments() != null) {
			mParam1 = getArguments().getString(ARG_PARAM1);
			mParam2 = getArguments().getString(ARG_PARAM2);
		}

		// TODO: Change Adapter to display your content
		// i suppose that a public method should be created to set the content
		// mDummyContent = new DummyContent();

		for (int i = 0; i < mDummyItems.size(); i++) {
			DummyContent.addItem(mDummyItems.get(i));
		}

		mAdapter = new ArrayAdapter<DummyContent.DummyItem>(getActivity(),
				android.R.layout.simple_list_item_1, android.R.id.text1,
				DummyContent.ITEMS);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_menu, container, false);

		// Set the adapter
		mListView = (AbsListView) view.findViewById(android.R.id.list);
		((AdapterView<ListAdapter>) mListView).setAdapter(mAdapter);

		// Set OnItemClickListener so we can be notified on item clicks
		mListView.setOnItemClickListener(this);

		return view;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mListener = (OnFragmentInteractionListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnFragmentInteractionListener");
		}
	}

	@Override
	public void onDetach() {
		super.onDetach();
		mListener = null;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		if (null != mListener) {
			// Notify the active callbacks interface (the activity, if the
			// fragment is attached to one) that an item has been selected.
			mListener
					.onFragmentInteraction(DummyContent.ITEMS.get(position).id);

			// 使用接口或者什么使得导航成功
			// 这里是通过静态方法实现的
			ContentFragment.setCurrentContentView(position);
			XMenu.toggle();
		}
	}

	/**
	 * The default content for this Fragment has a TextView that is shown when
	 * the list is empty. If you would like to change the text, call this method
	 * to supply the text it should use.
	 */
	public void setEmptyText(CharSequence emptyText) {
		View emptyView = mListView.getEmptyView();

		if (emptyView instanceof TextView) {
			((TextView) emptyView).setText(emptyText);
		}
	}

	/**
	 * This interface must be implemented by activities that contain this
	 * fragment to allow an interaction in this fragment to be communicated to
	 * the activity and potentially other fragments contained in that activity.
	 * <p/>
	 * See the Android Training lesson <a href=
	 * "http://developer.android.com/training/basics/fragments/communicating.html"
	 * >Communicating with Other Fragments</a> for more information.
	 */
	public interface OnFragmentInteractionListener {
		// TODO: Update argument type and name
		public void onFragmentInteraction(String id);
	}

}
