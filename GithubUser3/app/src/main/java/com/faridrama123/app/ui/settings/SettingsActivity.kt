package com.faridrama123.app.ui.settings

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CompoundButton
import android.widget.Toast
import com.faridrama123.app.R
import com.faridrama123.app.data.repository.SettingsManager
import com.faridrama123.app.data.repository.SettingsRepo
import com.faridrama123.app.databinding.ActivitySettingsBinding
import com.faridrama123.app.service.AlarmReceiver
import com.faridrama123.app.utils.DatePickerFragment
import com.faridrama123.app.utils.TimePickerFragment
import java.text.SimpleDateFormat
import java.util.*

class SettingsActivity : AppCompatActivity(){

    private lateinit var settingsRepo: SettingsRepo
    private lateinit var binding : ActivitySettingsBinding
    private lateinit var alarmReceiver : AlarmReceiver
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initToolbar()
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val settings = SettingsManager(this)
        settingsRepo = SettingsRepo.getInstance(settings)


        alarmReceiver = AlarmReceiver()
        checkalarm()
        initalarm()


    }

    private fun checkalarm(){
        val isactive = settingsRepo.getFromPreference()
        if ( isactive) {
            binding.simpleSwitch.setChecked(true)
        }else{
            binding.simpleSwitch.setChecked(false)
        }

        val isAlarmSet = alarmReceiver.isAlarmSet(this, AlarmReceiver.TYPE_REPEATING)
        if (isAlarmSet){
            Toast.makeText(getApplicationContext(),"Ada Alarm Aktif",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getApplicationContext(),"Tidak ada Alarm Aktif",Toast.LENGTH_SHORT).show();
        }
    }


    private fun initalarm(){

        binding.simpleSwitch.setOnCheckedChangeListener(object: CompoundButton.OnCheckedChangeListener
        {
            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                if (isChecked){
                    settingsRepo.save()
                    val repeatTime = "09:00"
                    val repeatMessage = repeatTime+": dont forget check ur app"
                    alarmReceiver.setRepeatingAlarm(this@SettingsActivity, AlarmReceiver.TYPE_REPEATING, repeatTime, repeatMessage)
                }else{
                    settingsRepo.delete()
                    alarmReceiver.cancelAlarm(this@SettingsActivity, AlarmReceiver.TYPE_REPEATING)
                }
            }

        })



    }

    private fun initToolbar() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            elevation = 0f
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }



    companion object {

        private const val TIME_PICKER_REPEAT_TAG = "TimePickerRepeat"
    }

}