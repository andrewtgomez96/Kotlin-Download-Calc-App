package com.example.angomez.mini_app1

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_container.view.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [ContainerFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [ContainerFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class ContainerFragment : Fragment() {
    //TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_container, container, false)

        //declarations of text view file size and network speed
        var tvNS = view.nSpeed!!
        var tvFS = view.fSize!!

        //The TextChangeListener for changes to the File Size TextView
        tvFS.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                //error checking for nullptr exception and empty case
                if (s != null && !s.toString().equals("", ignoreCase = true)) {
                    updateCalculation(tvNS, tvFS, view)
                }
            }
        })

        //The TextChangeListener for changes to the Network Speed TextView
        tvNS.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                //error checking for nullptr exception and empty case
                if (s != null && !s.toString().equals("", ignoreCase = true)) {
                    updateCalculation(tvNS, tvFS, view)
                }
            }
        })

        return view
    }

    fun updateCalculation(tvNS: EditText, tvFS: EditText, view: View) {

        val printTime = view.dlSeconds as TextView
        //makes sure length of both is valid to avoid crashing on null
        if (tvNS.text.length > 0 && tvFS.text.length > 0) {

            val networkSpeed = Integer.parseInt(tvNS.text.toString())
            val fileSize = Integer.parseInt(tvFS.text.toString())


            val perByteSpeed = networkSpeed * Math.pow(10.0, 6.0) / 8
            val sizeInBytes = fileSize * Math.pow(2.0, 20.0)

            var downloadTime = sizeInBytes / perByteSpeed
            downloadTime = Math.round(downloadTime * 10) / 10.0

            //call to extension function
            printTime.setTime(downloadTime, printTime)

        } else {
            printTime.setTime(0.0, printTime)
        }

    }

    //extension function for TextView to set text of the view given a Double
    fun TextView.setTime(downloadTime: Double, printTime: TextView){ printTime.text = java.lang.Double.toString(downloadTime) }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         */
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                ContainerFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}
