package com.machichiotte.courloc

import android.app.AlertDialog
import android.app.Fragment
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.machichiotte.courloc.adapter.ListAdapter
import com.machichiotte.courloc.pojo.Task
import com.machichiotte.courloc.pojo.User
import kotlinx.android.synthetic.main.fragment_list.*

class FragmentList : Fragment() {

    var i = 0
    val list: ArrayList<String> = ArrayList()

    var mDatabase: DatabaseReference? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }


    private fun writeNewUser(userId: String, name: String, email: String) {
        val user = User(name, email)

        mDatabase?.child("users")?.child(userId)?.setValue(user)
    }

    private fun writeNewTask(userId: String, title: String) {
        val task = Task(title)

        mDatabase?.child("tasks")?.child(userId)?.setValue(task)
    }

    override fun onCreateView(inflater: LayoutInflater?,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        return inflater?.inflate(R.layout.fragment_list,
                container, false)
    }

    override fun onResume() {
        super.onResume()
        initialize()
    }

    private fun initialize() {
        btn_add_list.setOnClickListener { addItemToList() }

        // Creates a vertical Layout Manager
        //rv_list.layoutManager = LinearLayoutManager(activity)
        rv_list.layoutManager = GridLayoutManager(activity, 1, GridLayoutManager.VERTICAL, false)
        rv_list.adapter = ListAdapter(list, activity)
    }

    private fun addItemToList() {
        /*list.add("element::" + i)

        writeNewUser("" + i, "" + i, "" + i);
        i++
        rv_list.adapter.notifyDataSetChanged()*/

        addTaskDialog()
    }

    private fun addTaskDialog() {

        val alertDialog = AlertDialog.Builder(this@FragmentList.activity)
        alertDialog.setTitle("Nouvelle tâche")
        alertDialog.setMessage("Ajouter une nouvelle tâche")

        val input = EditText(this@FragmentList.activity)
        val lp = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT)
        input.setLayoutParams(lp)
        alertDialog.setView(input)
        alertDialog.setIcon(R.drawable.ic_news);

        alertDialog.setPositiveButton("YES") { dialog, which ->
            // Do something when user press the positive button
            writeNewTask("" + i, "" + input.text)
            rv_list.adapter.notifyDataSetChanged()
            /* password = input.getText().toString();
             if (password.compareTo("") == 0) {
                 if (pass.equals(password)) {
                     Toast.makeText(activity.getApplicationContext(),
                             "Password Matched", Toast.LENGTH_SHORT).show();
                     Intent myIntent1 = new Intent(view.getContext(),
                             BottomTabActivity.class);
                     startActivityForResult(myIntent1, 0);


                 } else {
                     Toast.makeText(activity.getApplicationContext(),
                             "Wrong Password!", Toast.LENGTH_SHORT).show();
                 } */
        }


        alertDialog.setNegativeButton("No") { dialog, which ->
            dialog.cancel()
        }

        alertDialog.show()
    }

/*
    val input = EditText(this@FragmentList.activity)
    val lp = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT)
    input.layoutParams = lp
    alertDialog.setView(input) */
}
