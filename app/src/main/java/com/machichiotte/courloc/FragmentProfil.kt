package com.machichiotte.courloc

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.machichiotte.courloc.R.id.time
import com.machichiotte.courloc.pojo.Me
import com.machichiotte.courloc.pojo.Message
import com.machichiotte.courloc.pojo.User
import kotlinx.android.synthetic.main.fragment_profil.*


class FragmentProfil : Fragment() {

    private var mFirebaseAnalytics: FirebaseAnalytics? = null

    private var mDatabase: DatabaseReference? = null
    private var mMessageReference: DatabaseReference? = null

    companion object {

        fun newInstance(): FragmentProfil {
            return FragmentProfil()
        }
    }

    override fun onCreateView(inflater: LayoutInflater?,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {



        // Inflate the layout for this fragment
        return inflater?.inflate(R.layout.fragment_profil,
                container, false)


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mDatabase = FirebaseDatabase.getInstance().reference
        mMessageReference = FirebaseDatabase.getInstance().getReference("message")

        //btn_test.text = "Profil test"

        //writeMessage()
        //writeData("username", "email@gmail.com")
        //loadDatabase(mDatabase!!)

        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(activity)


    }

    override fun onResume() {
        super.onResume()
        initialize()
    }

    private fun initialize() {
        btn_test!!.setOnClickListener { analyticsTest() }
    }

    private fun analyticsTest() {
        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "id")
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "name")
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image")
        mFirebaseAnalytics!!.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle)
    }

    private fun writeMessage() {
        // Write a message to the database
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("message")

        myRef.setValue("Hello, World!")
    }

    private fun writeData(username: String, email: String) {

        val userId = "012"
        val author = "author"
        val body = "body"

        mDatabase!!.child("title").setValue("JavaSampleApproach")

        val user = User(username, email)
        mDatabase!!.child("users").child(userId).setValue(user)

        val message = Message(author, body, time)
        mMessageReference!!.setValue(message)

    }


    fun loadDatabase(firebaseData: DatabaseReference) {
        val availableSalads: List<Me> = mutableListOf(
                Me("Gherkin", "Fresh and delicious"),
                Me("Lettuce", "Easy to prepare"),
                Me("Tomato", "Boring but healthy"),
                Me("Zucchini", "Healthy and gross")
        )
        availableSalads.forEach {
            val key = firebaseData.child("salads").push().key
            //it.uuid = key
            //firebaseData.child("salads").child(key).setValue(it)
        }
    }

/*
    private fun initMeMenu() {
        val menuListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                menu.clear()
                dataSnapshot.children.mapNotNullTo(menu) { it.getValue<Me>(Me::class.java) }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                println("loadPost:onCancelled ${databaseError.toException()}")
            }
        }
        firebaseData.child("salads").addListenerForSingleValueEvent(menuListener)
    }*/
}
