package com.debutinfotech.viewpagerindicator

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_pager_item.*


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [PagerItem.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [PagerItem.newInstance] factory method to
 * create an instance of this fragment.
 */
class PagerItem : Fragment() {

    // TODO: Rename and change types of parameters
    private var mItem: Item? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (getArguments() != null) {
            mItem = getArguments().getParcelable(ARG_PARAM1)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                                     savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_pager_item, container, false)
    }


    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        text_index.setText(mItem?.index)
        text_index.setBackgroundColor(Color.parseColor(mItem?.resourceId))
    }





    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private val ARG_PARAM1 = "param1"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PagerItem.
         */
        fun newInstance(item:Item): PagerItem {
            val fragment = PagerItem()
            val args = Bundle()
            args.putParcelable(ARG_PARAM1, item)
            fragment.setArguments(args)
            return fragment
        }
    }
}// Required empty public constructor
