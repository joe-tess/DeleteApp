package com.example.josephtessier.deleteapp

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.CardView
import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import android.util.Log
import android.util.TypedValue
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.example.josephtessier.deleteapp.R.id.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.list_row.*

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    override fun onNothingSelected(parent: AdapterView<*>?) {
        Toast.makeText(this, "Choisir une région", Toast.LENGTH_SHORT).show()
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//        Toast.makeText(this, , Toast.LENGTH_LONG).show()
    }

/*    private val mOnNavigationItemSelectedListener = OnNavigationItemSelectedListener { item ->

//        relativeLayoutID.cv2.visibility = View.INVISIBLE
//        relativeLayoutID.cv3.visibility = View.INVISIBLE

        when (item.itemId) {
            R.id.navigation_home -> {
//                message.setText(R.string.title_home)
                relativeLayoutID.cv1.visibility = View.VISIBLE
                relativeLayoutID.cv2.visibility = View.INVISIBLE
                cv3.visibility = View.INVISIBLE
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
//                message.setText(R.string.title_dashboard)
                relativeLayoutID.cv1.visibility = View.INVISIBLE
                relativeLayoutID.cv2.visibility = View.VISIBLE
                cv3.visibility = View.INVISIBLE
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
//                message.setText(R.string.title_notifications)
                relativeLayoutID.cv1.visibility = View.INVISIBLE
                relativeLayoutID.cv2.visibility = View.INVISIBLE
                cv3.visibility = View.VISIBLE
                return@OnNavigationItemSelectedListener true
            }
            R.id.nextBtn -> {
                if(cv1.visibility == View.VISIBLE) switchCV(cv1, cv2)
                if(cv2.visibility == View.VISIBLE) switchCV(cv2, cv3)
                return@OnNavigationItemSelectedListener true
            }
            R.id.previousBtn -> {
                if (cv2.visibility == View.VISIBLE) switchCV(cv2, cv1)
                if (cv3.visibility == View.VISIBLE) switchCV(cv3, cv2)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }*/


    // details sur l'utilisateur
    var userEmail: String? = null
    var userAge: Int? = null
    var userRegion: String? = null
    var userSexe: Char? = null
    var userThemes: ArrayList<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // création de la liste des thèmes et des checkboxes qui vont avec
        val listDeThemes: Array<String> = arrayOf("Loisirs", "Fêtes", "Auto-moto", "Shopping", "Multimédias", "Maison-travaux", "Services", "Beauté-soins", "Gastronomie", "Restaurant")
        /*val themeCBid = arrayOf(9990,9991,9992,9993,9994,9995,9996,9997,9998,9999)
        val layoutManager = LinearLayoutManager(this)
        val adapter = ThemeAdapter(listDeThemes, themeCBid, this)

        //setup list recyclerView
        recycler_view.layoutManager = layoutManager
        recycler_view.adapter = adapter
        adapter.notifyDataSetChanged()*/


        var themesAdapter: ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_list_item_1, listDeThemes)
        listview.adapter = themesAdapter

        var listTarget: MutableList<String> = mutableListOf()
        listview.setOnItemClickListener { parent, view, position, id ->                         // on click sur un row
            var themeName: String = listview.getItemAtPosition(position).toString()             // on récupère le nom du thème

            if(listTarget.contains(themeName)) {                                                // si la liste l'a, c'est qu'on veut le suppr
                view.setBackgroundColor(Color.TRANSPARENT)
                listTarget.remove(themeName)                                                    // on le vire de la list momentanée
                Log.d("LIST VIEW ****", listTarget.toString())
            } else {                                                                            // sinon on veut l'ajouter
                view.setBackgroundColor(Color.GREEN)                                            // balise visuelle
            Log.d("LIST VIEW BEFORE ****", listTarget.toString())
                listTarget.add(themeName)                                                       // on l'ajoute à la liste
                Log.d("LIST VIEW AFTER ****", listTarget.toString())
            }

            Toast.makeText(this, "theme = $themeName", Toast.LENGTH_SHORT).show()
        }


        //============================================================================================================
        /*var adapter = ThemeAdapter("auto-moto", 9990, this)
        //setup list recyclerView
        recycler_view.layoutManager = layoutManager
        recycler_view.adapter = adapter
//        adapter.notifyDataSetChanged()

        adapter = ThemeAdapter("Loisirs", 9991, this)
        //setup list recyclerView
        recycler_view.layoutManager = layoutManager
        recycler_view.adapter = adapter
//        adapter.notifyDataSetChanged()

        adapter = ThemeAdapter("shopping", 9992, this)
        //setup list recyclerView
        recycler_view.layoutManager = layoutManager
        recycler_view.adapter = adapter
        adapter.notifyDataSetChanged()*/
        //============================================================================================================

        // on cache les CardViews supplémentaires, on ne se concentre que sur le 1e
        cv2.visibility = View.INVISIBLE
        cv3.visibility = View.INVISIBLE
        cv4.visibility = View.INVISIBLE
        cv5.visibility = View.INVISIBLE
        sendAllDetailsButton.visibility = View.INVISIBLE
        previousBtn.visibility = View.INVISIBLE
        nextBtn.visibility = View.INVISIBLE

        previousBtn.setOnClickListener {
            nextBtn.visibility = View.VISIBLE
            when {
                cv2.visibility == View.VISIBLE -> {
                    switchCV(cv2, cv1)
                    previousBtn.visibility = View.INVISIBLE
                }
                cv3.visibility == View.VISIBLE -> switchCV(cv3, cv2)
                cv4.visibility == View.VISIBLE -> switchCV(cv4, cv3)
                cv5.visibility == View.VISIBLE -> switchCV(cv5, cv4)
            }
        }
        nextBtn.setOnClickListener {
            previousBtn.visibility = View.VISIBLE
            nextBtn.visibility = View.VISIBLE
            when {
                cv1.visibility == View.VISIBLE -> switchCV(cv1, cv2)
                cv2.visibility == View.VISIBLE -> switchCV(cv2, cv3)
                cv3.visibility == View.VISIBLE -> switchCV(cv3, cv4)
                cv4.visibility == View.VISIBLE -> {
                    switchCV(cv4, cv5)
                    nextBtn.visibility = View.INVISIBLE
                    sendAllDetailsButton.visibility = View.VISIBLE
                }
            }
        }

        val listDeRegions = arrayOf("— choix de région —".toUpperCase(), "region1", "region2", "region3", "region4", "region5", "region6")
        spinnerID.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,listDeRegions)
        spinnerID.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) { }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                userRegion = listDeRegions[position]
            }

        }

        // liste des actions_listeners pour enregistrer les données
        sendEmailBtn.setOnClickListener {
            // logiquement faudrait aussi vérifier que ce mail n'est pas déjà référencé dans notre BDD
            var email = loginEmail.text.toString().trim()
            if(!TextUtils.isEmpty(email)) {
                if (email.contains('@') && email.contains('.')) {
                    userEmail = email
                    Toast.makeText(this, userEmail, Toast.LENGTH_SHORT).show()
                    switchCV(cv1, cv2)
                    nextBtn.visibility = View.VISIBLE
                    previousBtn.visibility = View.VISIBLE
                } else Toast.makeText(this, "Email invalide", Toast.LENGTH_SHORT).show()
            } else Toast.makeText(this, "Renseigner un email", Toast.LENGTH_SHORT).show()
        }

        sexHommeBtn.setOnClickListener{
            userSexe = 'H'
            Toast.makeText(this, userSexe.toString(), Toast.LENGTH_SHORT).show()
            switchCV(cv2, cv3)
        }

        sexFemmeBtn.setOnClickListener {
            userSexe = 'F'
            Toast.makeText(this, userSexe.toString(), Toast.LENGTH_SHORT).show()
            switchCV(cv2, cv3)
        }

        sendAgeBtn.setOnClickListener { // marche mal
            var age = RGroup.checkedRadioButtonId
            when (age) {
                radioButton15_25 -> userAge = 15
                radioButton25_40 -> userAge = 25
                radioButton40_60 -> userAge = 40
                radioButton60_plus -> userAge = 60
            }
            if(userAge.toString() != "null") {
                Toast.makeText(this, userAge.toString(), Toast.LENGTH_SHORT).show()
                switchCV(cv3, cv4)
            } else Toast.makeText(this, "Précisez votre âge", Toast.LENGTH_SHORT).show()
        }

        btnSubmit.setOnClickListener {
            if (userRegion!!.contains("—")) {
                Toast.makeText(this, "Sélectionner une région", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, userRegion, Toast.LENGTH_SHORT).show()
                switchCV(cv4, cv5)
                nextBtn.visibility = View.INVISIBLE
            }
        }


            val listTest: MutableList<String> = mutableListOf()
        for(i in 9990..9999){
            var ckbx = findViewById<CheckBox>(i)
            if(ckbx != null){
            ckbx.setOnCheckedChangeListener{ view, isChecked ->
//                if(isChecked) Log.d("checkkkk" ,ckbx.text.toString())
                if(isChecked) listTest.add(ckbx.text.toString())
            }}
        }
//            if(checkboxTheme.isChecked) listTest.add(checkboxTheme.text.toString())





        // envoie de toutes les données
        sendAllDetailsButton.setOnClickListener {

            // récupération de tous les checkboxes qui sont checked (MARCHE PAS)
            /*for(theme in listDeThemes){
                Log.d("THEME == ", theme)
//                var targetID : Int = this.resources.getIdentifier(theme, toString(), this.packageName)
                var targetID = 9990
                Log.d("ID THEME == ", targetID.toString())
                var cb = findViewById<CheckBox>(targetID)
                if(cb.isChecked) {
                    Log.d("checked ###", cb.text.toString())
//                    listTest.add(checkboxTheme.text.toString())
                    listTest.add("*")
                } else Log.d("not checked ***", cb.text.toString())

            }if(findViewById<CheckBox>(9990).isChecked) listTest.add("auto-moto")
            if(findViewById<CheckBox>(9991).isChecked) listTest.add("loisirs")
            if(findViewById<CheckBox>(9992).isChecked) listTest.add("shopping")*/
            for (i in 9990..9999){
                var targetID = i
                var cb = findViewById<CheckBox>(targetID)
                if(cb != null && cb.isChecked) listTest.add(cb.text.toString())
                Log.d("======", targetID.toString() + cb?.isChecked.toString())
            }


            Log.d("arrayListThemes >>>> ", listTest.toString())

            // on envoie les infos si le mail est correct
            if (!TextUtils.isEmpty(loginEmail.text.toString().trim())){
                        Toast.makeText(this, "sending info", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Rentrer un email", Toast.LENGTH_SHORT).show()
                        switchCV(cv5, cv1)
                    }
        }

    }


    fun switchCV(depart: CardView, destination: CardView){
        depart.visibility = View.INVISIBLE
        destination.visibility = View.VISIBLE
    }

}

