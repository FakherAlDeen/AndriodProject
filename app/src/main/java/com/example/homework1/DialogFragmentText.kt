package com.example.homework1

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.fragment.app.DialogFragment

class DialogFragmentText:DialogFragment (R.layout.text_fragment_dialog) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val submitbt : Button = view.findViewById(R.id.OkBtn);
        val radioGroup = view.findViewById<RadioGroup>(R.id.radioGroup);
        submitbt.setOnClickListener {
            val selectedOption: Int = radioGroup.checkedRadioButtonId
            val mainAct: MainActivity = getActivity() as MainActivity;
            when (selectedOption){
                R.id.Size16 -> mainAct.ChangeSizeOfText(16f);
                R.id.Size24 -> mainAct.ChangeSizeOfText(24f);
                R.id.Size32 -> mainAct.ChangeSizeOfText(32f);
            }
            dismiss()
        }
    }
}