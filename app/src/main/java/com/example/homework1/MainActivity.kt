package com.example.homework1

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val spinnerVal : Spinner = findViewById(R.id.spinnerFaye3)
        val anaNagm = arrayOf(
            "العمر بيجري سابق السنين",
            "أنا شاب لكن من جوه عجوز",
            "عندي جناحات بس محبوس",
            "مجروح بنزف طموح",
            "بدمِّي بكتب كلامي وأبوح",
            "بسرح بتخيل بروح",
            "بغنِّي ترد فيا الروح",
            "يمكن ده مش مكاني",
            "أو الزمن ده مش زماني",
            "أو أنا؛ موهوم (موهوم)",
            "يمكن خيالي وداني",
            "لدنيا أو لعالم تاني",
            "أو أنا، مجنون",
            "أو يمكن",
            "أنا نجم بس ما فيش سما",
            "عندي جناحات بس ما فيش هوا",
            "أنا نجم بس ما فيش سما",
            "عندي جناحات بس ما فيش هوا (هوا)",
            "وبعدين سامع جوه مني صوتين",
            "صوت سابقني والتاني قديم",
            "صوت يدل، وصوت يضل",
            "صوت يحرر، وصوت يشل",
            "وبعدين، أرُوح لمين؟",
            "بحلم ببكرة أنا طول الليل",
            "يجي النهار وأصطبح",
            "كل اللي حلمته بيتمسح",
            "أوقات بحس إني سابق (إني سابق)",
            "إنتوا عايشين في الماضي وأنا معاه بتخانق (معاه بتخانق)",
            "مكان أنا شايفه مش هنا",
            "أحلامي أقوى مني ومكملة",
            "أنا نجم بس ما فيش سما",
            "عندي جناحات بس ما فيش هوا",
            "أنا نجم بس ما فيش سما",
            "عندي جناحات بس ما فيش هوا (هوا)",
            "أنا نجم بس ما فيش سما",
            "عندي جناحات بس ما فيش هوا",
            "شايف لبعيد بس ما فيش طريق",
            "عندي حكايات هحكيها لمين وأنا وحيد؟"
        )
        val menniLeek = arrayOf(
            "حبيبي حبيبي حبيبي حبيبي",
            "مني ليك نظرة لعينيك",
            "حتقول لك اني بداري غرامي عليك",
            "مني ليك نظرة لعينيك",
            "حتقول لك اني بحبك وميتة فيك",
            "مني ليك نظرة لعينيك",
            "حتقول لك اني بداري غرامي عليك",
            "مني ليك نظرة لعينيك",
            "حتوقل لك اني بحبك بحبك",
            "قلبي وعيني قالوا لي يا ريت",
            "لو قلت لي انك حبيت",
            "قلبي وعيني قالوا لي يا ريت",
            "حبيبي يلا تعالى لي قولها لي ريح بالي انا",
            "طول عمرك وانت في بالي ولا غالي غيرك يا انا",
            "حبيبي تعالى لي قولها لي ريح بالي انا",
            "وانت في بالي ولا غالي غيرك يا انا",
            "حبيبي يلا تعالى لي قولها لي ريح بالي انا",
            "طول عمرك وانت في بالي ولا غالي غيرك يا انا",
            "حبيبي تعالى لي قولها لي ريح بالي انا",
            "طول عمرك وانت في بالي ولا غالي غيرك يا انا",
            "حبيبي يلا تعالى لي قولها لي ريح بالي انا",
            "طول عمرك وانت في بالي ولا غالي غيرك يا انا"
        )
        val wegzBa5t = arrayOf(
            "عيني منها بشكل جدي (بشكل جدي)",
            "وتقيلة مش تقلانة، اتبخر سحري (عيني مليانة منها)",
            "ومش باجي على بالها ما فكرتش (ولا باجي على بالها)",
            "وبالي فيها بابا بالي مش ملكي (ولا باجي على بالها)",
            "بلعب البخت (وبعدين؟)",
            "سألتها ورفضتني",
            "استغربتها، أومال فين هيبتي؟",
            "حبيبي أنا بضحك على خيبتي",
            "ياخي كنت عالثبات وهزتني",
            "واللي فات ده كان سحري",
            "مش باجي على بالها إن جات سيرتي",
            "أنا بالي معاها مش ملكي",
            "قالت كلام قبل أما تشوفني عارفني صعب ومش مفهوم",
            "كلامي دبش ومش موزون أو بتحب حد والبال مشغول؟",
            "ما شوفتوهاش ولا حد يلومني ملاك بريء ولا أنا مخدوع؟",
            "هي توتر ولا أنا مهزوز أو آذاها حد والكل مرفوض؟",
            "ولا إيه القصة، حبيبي فيدني اللي آذوك ياما، حبيبي سيبني",
            "أخلي الجاي ورود وشموع يتغنالها وبصوت مسموع",
            "ولا إيه القصة، حبيبي فيدني اللي آذوك ياما، حبيبي سيبني",
            "أنسيك ماضي كله دموع ولا ماقدرش أسيبك موجوع",
            "سحرتني ولا سحرتلي؟ (سحرتني ولا سحرتلي؟)",
            "وعيون مش محتاجة تسحرلي (وعيون مش محتاج تسحرلي)",
            "مش عايزة تجيبني بس جابتني (مش عايزة تجيبني بس جابتني)",
            "ونسيتني وفي قصتي حبستني",
            "والله ووقعت، بفكر فيك، كتير بالي عليك مشغول",
            "وأنا بتصعب عليا حالي وأنا بكتبلك كلام معسول",
            "بعيد عن عيني ويا عيني مع غيري ومش مبسوط",
            "ده أنا أضيع عمري يا عمري وأحبك حب مش مشروط",
            "لا تلاقي عندي وعود كدابة وكبرت على ولا شيء مضمون",
            "كلامي على قد اللي معايا في الحب ماستناش مردود",
            "بالراحة على قلبي المكسور اللي مشكلته إنه فيك مسحور",
            "مين إختار اللي عينيك عملوه، ده مش ذنبي أنا صحيت محطوط",
            "ولا إيه القصة، حبيبي فيدني اللي آذوك ياما، حبيبي سيبني",
            "أخلي الجاي ورود وشموع يتغنالها وبصوت مسموع",
            "ولا إيه القصة، حبيبي فيدني اللي آذوك ياما، حبيبي سيبني",
            "أنسيك ماضي كله دموع ولا ماقدرش أسيبك موجوع",
            "ولا إيه القصة، حبيبي فيدني اللي آذوك ياما، حبيبي سيبني",
            "أخلّي الجاي ورود وشموع يتغنالها وبصوت مسموع",
            "ولا إيه القصة، حبيب"
        )
        var flag : String = ""
        val textViewsContainer: TextView = findViewById(R.id.Lyrics)
        val myButton: Button = findViewById(R.id.Faye3Button)
        val spoopyfyButton: Button = findViewById(R.id.spotifyFaye3Bgd)
        var url = ""
        var options = arrayOf("Faye3 + ana nagm","Faye3 + meennni leek", "Faye3 + wegz special")
        spinnerVal.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,options )

        spinnerVal.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                flag = options.get(p2)
             }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
        myButton.setOnClickListener{
            val stringBuilder = StringBuilder()
            if (flag == "Faye3 + ana nagm"){
                for (line in anaNagm) {
                    stringBuilder.append(line).append("\n")
                }
                textViewsContainer.text = stringBuilder.toString()
                url = "https://open.spotify.com/track/6CU68VwcV10VfulVQMfj3X?si=e8e0727dc9f84e3b"
                spoopyfyButton.text = "Ana Nagm On SPOTIFY faye3"
            }else if (flag == "Faye3 + meennni leek"){
                for (line in menniLeek) {
                    stringBuilder.append(line).append("\n")
                }
                textViewsContainer.text = stringBuilder.toString()
                url = "https://open.spotify.com/track/19ZrbiAIKOBKUYPXOHsLZu?si=41ae8ee18a1b43e7"
                spoopyfyButton.text = "Menny Leek On SPOTIFY faye3"
            }else if (flag == "Faye3 + wegz special"){
                for (line in wegzBa5t) {
                    stringBuilder.append(line).append("\n")
                }
                textViewsContainer.text = stringBuilder.toString()
                url = "https://open.spotify.com/track/5kdlxZ5skOYY3VK8RlfEIZ?si=7be6f16457954c6b"
                spoopyfyButton.text = "Wegz On SPOTIFY faye3"
            }else {
                textViewsContainer.text = "Choose something pwease"
                url = ""
            }
        }

        spoopyfyButton.setOnClickListener {
            if (url != ""){
                Log.d("Button Click", "Button clicked!")
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))

                if (intent.resolveActivity(packageManager) != null) {
                    startActivity(intent)
                }else {
                    Toast.makeText(this, "No web browser app found.", Toast.LENGTH_SHORT).show()
                }
            }else {
                Toast.makeText(this, "Choose one of the faye3 choices.", Toast.LENGTH_SHORT).show()
            }
        }
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
     val inflater = menuInflater
     inflater.inflate(R.menu.menu_dark, menu)
     return super.onCreateOptionsMenu(menu)
     }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.Dark -> {
                val rootView = findViewById<View>(android.R.id.content)
                rootView.setBackgroundColor(ContextCompat.getColor(this, R.color.app_background_color_new))
                return true
            }
            R.id.FSize ->{
                var dialogVar = DialogFragmentText()
                dialogVar.show(supportFragmentManager, "Size Dialog")
            }
            // Handle other menu items similarly
            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }

    public fun ChangeSizeOfText(size: Float){
        val TVLyrics = findViewById<TextView>(R.id.Lyrics);
        TVLyrics.setTextSize(TypedValue.COMPLEX_UNIT_SP,size);
    }

}